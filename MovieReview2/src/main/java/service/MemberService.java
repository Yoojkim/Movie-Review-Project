package service;

import domain.MemberDTO;
import response.BaseResponse;

import java.io.IOException;
import java.util.Map;

public interface MemberService {
    Map<String, String> login(MemberDTO memberDTO) throws Exception; //token 반환
    BaseResponse signUp(MemberDTO memberDTO) throws Exception;
    //logOut, withdraw는 jwt token으로 로그아웃, 탈퇴 구현
    BaseResponse logOut() throws IOException;
    BaseResponse withdraw();
    Map<String, String> refresh() throws Exception; //token 반환(반환형 재확인)
}