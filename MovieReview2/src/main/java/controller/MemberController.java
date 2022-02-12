package controller;

import annotation.ValidationGroups;
import domain.MemberDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
    @ApiOperation(value="로그인",notes="MemberDTO 객체를 통해 로그인한다.")
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody @Validated(ValidationGroups.logIn.class) MemberDTO memberDTO) throws Exception {
        return new ResponseEntity(memberService.login(memberDTO), HttpStatus.OK);
    }

    //회원가입
    @ApiOperation(value="회원가입",notes="MemberDTO 객체를 받아 해당 정보로 회원가입한다.")
    @RequestMapping(value="/signup",method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody @Validated(ValidationGroups.signUp.class) MemberDTO memberDTO) throws Exception {
        return new ResponseEntity(memberService.signUp(memberDTO),HttpStatus.OK);
    }

    //회원탈퇴
    @ApiOperation(value="회원탈퇴",notes="현재 JWT token의 사용자를 탈퇴시킨다.")
    @RequestMapping(value="/withdraw",method =RequestMethod.POST)
    public ResponseEntity withDraw() throws IOException {
        return new ResponseEntity(memberService.withdraw(), HttpStatus.OK);
    }

    //토큰갱신
    @ApiOperation(value="access 토큰 갱신",notes="authorization 헤더의 refresh 토큰을 통해 access 토큰을 발급한다.")
    @RequestMapping(value="/refresh",method=RequestMethod.POST)
    public ResponseEntity refresh() throws Exception {
        return new ResponseEntity(memberService.refresh(),HttpStatus.OK);
    }
}
