package service;

import domain.MemberDTO;
import response.BaseResponse;

import java.util.Map;

public interface MemberService {
    Map<String, String> login(MemberDTO memberDTO); //token 반환
    BaseResponse signUp(MemberDTO memberDTO);
    //logOut, withdraw는 jwt token으로 로그아웃, 탈퇴 구현
    BaseResponse logOut();
    BaseResponse withdraw();
    Map<String, String> refresh(); //token 반환(반환형 재확인)
}
