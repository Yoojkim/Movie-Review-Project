package interceptor;

import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.Jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* interceptor에서 client의 요청에서 token 확인
* + interceptor 처리할 path 따로 어디에 써주ㅓ야되는데 어디더라 기억이안난당..
* */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private Jwt jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if(request.getMethod().equals("OPTIONS")) {
            return true;
        }
        else{
            String token=request.getHeader(HttpHeaders.AUTHORIZATION);
            if(token !=null){
                //토큰 유효성 검증 -> 토큰 유효성 검증 실패시, jwt에서 처리
                jwt.isValid(token,0);
                return true;
            }
            else{
                //토큰이 없는 경우
                throw new Exception("인증(access) 토큰이 존재하지 않습니다."); //다른 처리 고려!
            }
        }
    }
}
