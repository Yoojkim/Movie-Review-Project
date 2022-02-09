package serviceImpl;

import com.google.common.net.HttpHeaders;
import domain.MemberDTO;
import mapper.MemberMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import response.BaseResponse;
import service.MemberService;
import util.Jwt;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//withdraw 구현 안 함
//예외처리 다시 해주기.. 일단 throw new exception으로
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private Jwt jwt;

    @Override
    //id, password, salt 필요
    public Map<String, String> login(MemberDTO memberDTO) throws Exception {
        MemberDTO dbMember=memberMapper.getMemberToEmail(memberDTO.getEmail());

        //해당 회원이 없는 경우(Email 기반)
        if(dbMember==null){
            throw new Exception("해당 회원이 존재하지 않습니다.");
        }

        //password 다른 경우
        if(!BCrypt.checkpw(memberDTO.getPassword(),dbMember.getPassword())){
            throw new Exception("비밀번호가 다릅니다.");
        }

        //refresh, access token 발행
        Map<String, String> token=new HashMap<>();
        token.put("access",jwt.createToken(dbMember.getId(),"access"));
        token.put("refresh",jwt.createToken(dbMember.getId(), "refresh"));

        return token; //token 반환(http body로)
    }

    @Override
    //MemberDTO-> 이메일, 이름, 비밀번호
    public BaseResponse signUp(MemberDTO memberDTO) throws Exception{
        //email 중복
        if(memberMapper.emailExist(memberDTO.getEmail())){
            throw new Exception("존재하는 이메일입니다.");
        }

        //닉네임 중복
        if(memberMapper.nickNameExist(memberDTO.getNickName())){
            throw new Exception("존재하는 닉네임입니다.");
        }

        //암호화(memberDTO에 있는 pw 암호화됨)
        memberDTO.setPassword(BCrypt.hashpw(memberDTO.getPassword(), BCrypt.gensalt()));

        //회원가입
        memberMapper.signUp(memberDTO);//email, password, nickname

        //memberId
        Long memberId=memberMapper.getIdToEmail(memberDTO.getEmail());
        memberDTO.setId(memberId);

        //salt 삽입(token 발행시, 사용하기 위함) -> 보충 .. 걍근데하면안되나;
        String salt=BCrypt.gensalt();

        memberMapper.setSalt(salt,memberDTO.getId());

        return new BaseResponse("회원가입 성공", HttpStatus.OK);
    }

    //현재 login한 id 얻기
    //현재 jwt token(HttpserveltRequest에 있음)에서 id를 추출하고 싶음
    private Long getLoginId() throws IOException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token=req.getHeader(HttpHeaders.AUTHORIZATION);
        Map<String, Object> payloads=jwt.validateFormat(token,0); //accessToken

        //id 추출
        Long id=Long.valueOf(String.valueOf(payloads.get("id")));

        return id;
    }

/*    @Override
    public BaseResponse logOut() throws IOException {
        //jwt token의 salt와 db Member의 salt를 다르게 설정
        String salt=BCrypt.gensalt();
        memberMapper.setSalt(salt,this.getLoginId()); //id의 salt를 다르게 설정
        
        return new BaseResponse("로그아웃 완료",HttpStatus.OK);
    }*/

    //탈퇴
    @Override
    public BaseResponse withdraw() throws IOException {
        //현재 로그인한 아이디를 jwt 기반으로 확인한 후, 해당 회원을 탈퇴
        Long id=this.getLoginId();
        memberMapper.deleteMember(id);

        return new BaseResponse("회원 탈퇴 완료",HttpStatus.OK);
    }

    //header -> access, refresh
    //db에 refresh token 저장해야하는지
    @Override
    public Map<String, String> refresh() throws Exception {
        //현재 jwt token
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String refreshToken=req.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("refresh: "+refreshToken);
        int state=jwt.isValid(refreshToken,1);
        if (state==1){
            Map<String, String> token=new HashMap<>();
            token.put("access",jwt.createToken(this.getLoginId(),"access"));
            token.put("refresh",refreshToken); //기존의 refresh Token
            return token;
        }
        else{
            //예외처리 후에
            throw new Exception("토큰 잘못 들어옴");
        }
    }
}
