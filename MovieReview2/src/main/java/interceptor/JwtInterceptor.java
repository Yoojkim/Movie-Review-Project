package interceptor;

import com.google.common.net.HttpHeaders;
import errormessage.ErrorMessage;
import exception.InterceptorNotToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.Jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                jwt.isValid(token,0);
                return true;
            }
            else{
                //토큰이 없는 경우
                throw new InterceptorNotToken(ErrorMessage.NULL_TOKEN);
            }
        }
    }
}
