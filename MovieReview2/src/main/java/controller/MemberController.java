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

import java.io.IOException;

@RequestMapping("/member")
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    //로그인
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody MemberDTO memberDTO) throws Exception {
        return new ResponseEntity(memberService.login(memberDTO), HttpStatus.OK);
    }

    //회원가입
    @RequestMapping(value="/signup",method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody MemberDTO memberDTO) throws Exception {
        return new ResponseEntity(memberService.signUp(memberDTO),HttpStatus.OK);
    }

    /* //로그아웃
    @RequestMapping(value="/logout",method=RequestMethod.POST)
    public ResponseEntity logOut() throws IOException {
        return new ResponseEntity(memberService.logOut(),HttpStatus.OK);
    }*/

    //회원탈퇴
    @RequestMapping(value="/withdraw",method =RequestMethod.POST)
    public ResponseEntity withDraw() throws IOException {
        return new ResponseEntity(memberService.withdraw(), HttpStatus.OK);
    }

    //토큰갱신
    @RequestMapping(value="/refresh",method=RequestMethod.POST)
    public ResponseEntity refresh() throws Exception {
        return new ResponseEntity(memberService.refresh(),HttpStatus.OK);
    }
}
