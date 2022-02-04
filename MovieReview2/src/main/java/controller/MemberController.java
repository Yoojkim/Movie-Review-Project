package controller;

import domain.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.MemberService;

/*로그인, 회원가입, 회원 탈퇴, 로그아웃
*중복 확인이나 그런 건 서비스 단에서 하는 듯..
* -> 내 생각엔 여기서 mapping으로 나눠서 안 해도 될 듯
* validation은 아직 구현x ... 그런극악무도한아이들 ㅠㅠ
*
* 근데 생각해보니까 닉네임 중복체크나 이메일이나 controller에서 구현하는 게 맞을듯!!!
* 근데지금은못함.. 모르니깐...
*/

@RequestMapping("/member")
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    //로그인
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody MemberDTO memberDTO){
        return new ResponseEntity(memberService.login(memberDTO), HttpStatus.OK);
    }

    //회원가입
    @RequestMapping(value="/signup",method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody MemberDTO memberDTO){
        return new ResponseEntity(memberService.signUp(memberDTO),HttpStatus.OK);
    }

    //로그아웃
    @RequestMapping(value="/logout",method=RequestMethod.POST)
    public ResponseEntity logOut(){
        return new ResponseEntity(memberService.logOut(),HttpStatus.OK);
    }

    //회원탈퇴
    @RequestMapping(value="/withdraw",method =RequestMethod.POST)
    public ResponseEntity withDraw(){
        return new ResponseEntity(memberService.withdraw(), HttpStatus.OK)
    }

    //토큰갱신
    @RequestMapping(value="/refresh",method=RequestMethod.POST)
    public ResponseEntity refresh(){
        return new ResponseEntity(memberService.refresh(),HttpStatus.OK);
    }


}
