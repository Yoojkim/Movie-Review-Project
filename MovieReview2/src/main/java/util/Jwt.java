package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import errormessage.ErrorMessage;
import exception.AccessTokenExpiredException;
import exception.BaseException;
import exception.RefreshTokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//JWT token 발행, 검증
@Component
public class Jwt {

    @Autowired
    private MemberMapper memberMapper;

    public String createToken(Long id, String sub){
        String salt=memberMapper.getSalt(id);

        //header
        Map<String, Object> headers=new HashMap<String, Object>();
        headers.put("typ","JWT");
        headers.put("alg","HS256");

        //payload
        Map<String, Object> payloads=new HashMap<String, Object>();
        payloads.put("id",id);
        payloads.put("sub",sub);

        //token 만료 설정
        LocalDateTime time= LocalDateTime.now();

        if(sub.equals("access")){
            time=time.plusDays(1);
        } else{
            time=time.plusDays(14);
        }

        Instant instant = time.atZone(ZoneId.systemDefault()).toInstant();
        Date exp=Date.from(instant);

        //token 발급
        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                .setExpiration(exp) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, salt.getBytes()) // HS256과 Key로 Sign
                .compact(); // 토큰 생성, 직렬화

        return jwt;
    }

    //0- access token, 1- refresh token
    //Http 헤더 AUTHORIZATION 에서 토큰 추출
    //토큰 유효성 검증
    public int isValid(String token, Integer flag) throws BaseException, IOException {
        String authToken="";
        Map<String, Object> payloads=this.validateFormat(token,flag);
        String salt=memberMapper.getSalt(Long.valueOf(String.valueOf(payloads.get("id"))));
        String sub=String.valueOf(payloads.get("sub"));

        if(token.startsWith("Bearer ")){
            authToken=token.substring(7);
        }

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(salt.getBytes())
                    .parseClaimsJws(authToken)
                    .getBody();

            //정상 반환
            if(sub.equals("access")) return 0; //acess token인 경우
            else return 1; //refresg token인 경우
        }
        catch (ExpiredJwtException e1){
            if(flag==0) throw new AccessTokenExpiredException(ErrorMessage.EXPIRED_ACCESSTOKEN);
            else throw new RefreshTokenExpiredException(ErrorMessage.EXPIRED_REFRESHTOKEN);
        }
    }

    //payload 반환
    public Map<String, Object> validateFormat(String token, Integer flag) throws BaseException, IOException {
        String[] tokenSplit=token.split("\\."); //escape
        Base64.Decoder decoder=Base64.getDecoder();
        String payloadStr=new String(decoder.decode(tokenSplit[1]));

        Map<String, Object> payloads=null;
        payloads=new ObjectMapper().readValue(payloadStr,Map.class);

        System.out.println(payloads);
        return payloads;
    }

}
