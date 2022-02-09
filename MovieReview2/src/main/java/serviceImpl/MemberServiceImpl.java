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

//withdraw 구현
//예외처리 -> 메시지로 보내도록 구현
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private Jwt jwt;

    @Override
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

        return token;
    }

    @Override
    //email, nickName, password
    public BaseResponse signUp(MemberDTO memberDTO) throws Exception{
        //email 중복
        if(memberMapper.emailExist(memberDTO.getEmail())){
            throw new Exception("존재하는 이메일입니다.");
        }

        //닉네임 중복
        if(memberMapper.nickNameExist(memberDTO.getNickName())){
            throw new Exception("존재하는 닉네임입니다.");
        }

        //pw 암호화
        memberDTO.setPassword(BCrypt.hashpw(memberDTO.getPassword(), BCrypt.gensalt()));

        //회원가입
        memberMapper.signUp(memberDTO);//email, password, nickname

        //memberId
        Long memberId=memberMapper.getIdToEmail(memberDTO.getEmail());
        memberDTO.setId(memberId);

        //jwt 발행에 필요한 salt
        String salt=BCrypt.gensalt();
        memberMapper.setSalt(salt,memberDTO.getId());

        return new BaseResponse("회원가입 성공", HttpStatus.OK);
    }

    //현재 로그인한 id 얻기
    public Long getLoginId() throws IOException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token=req.getHeader(HttpHeaders.AUTHORIZATION);
        Map<String, Object> payloads=jwt.validateFormat(token,0); //accessToken

        //id 추출
        Long id=Long.valueOf(String.valueOf(payloads.get("id")));

        return id;
    }

    //회원탈퇴
    @Override
    public BaseResponse withdraw() throws IOException {
        //현재 로그인한 아이디를 jwt 기반으로 확인한 후, 해당 회원을 탈퇴
        Long id=this.getLoginId();
        memberMapper.deleteMember(id);

        return new BaseResponse("회원 탈퇴 완료",HttpStatus.OK);
    }

    //authorization header-> refresh token
    @Override
    public Map<String, String> refresh() throws Exception {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String refreshToken=req.getHeader(HttpHeaders.AUTHORIZATION);
        int state=jwt.isValid(refreshToken,1); //refresh token
        if (state==1){
            Map<String, String> token=new HashMap<>();
            token.put("access",jwt.createToken(this.getLoginId(),"access"));
            token.put("refresh",refreshToken); //기존의 refresh Token
            return token;
        }
        else{
            //access token이 들어온 경우
            throw new Exception("토큰 잘못 들어옴");
        }
    }
}
