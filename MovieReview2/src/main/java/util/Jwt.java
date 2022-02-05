package util;
//토큰 발행, 검증

import com.fasterxml.jackson.databind.ObjectMapper;
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

@Component
public class Jwt {

    @Autowired
    private MemberMapper memberMapper;

    //sub(token 목적)- access/refresh
    public String createToken(Long id, String sub){
        String salt=memberMapper.getSalt(id);

        //header
        Map<String, Object> headers=new HashMap<String, Object>();
        headers.put("typ","JWT");
        headers.put("alg","HS256");

        //payload
        Map<String, Object> payloads=new HashMap<String, Object>();
        payloads.put("id",id);
        payloads.put("sub",sub); //토큰 제목

        //token 만료 설정
        LocalDateTime time= LocalDateTime.now();

        if(sub.equals("access")){
            time.plusDays(1);
        }
        //sub가 "refresh"인 경우
        else{
            time.plusDays(14);
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
    //토큰 유효성 검증
    //+ Exception 걍 자동추가 한 건데 뭔지 모르겠음 ... ㅎㅎ
    public int isValid(String token, Integer flag) throws Exception {
        String authToken="";
        Map<String, Object> payloads=this.validateFormat(token,flag);
        String salt=memberMapper.getSalt(Long.valueOf(String.valueOf(payloads.get("id"))));
        String sub=String.valueOf(payloads.get("sub")); //object->String

        //token String 가공 (Http Request 헤더에서 토큰 추출)
        if(token.startsWith("Bearer ")){
            authToken=token.substring(7);
        }

        try {
            //jwt String에서 token 얻기
            Claims claims = Jwts.parser()
                    .setSigningKey(salt.getBytes())
                    .parseClaimsJws(authToken)
                    .getBody();

            //정상 반환
            if(sub.equals("access")) return 0;
            else return 1;
        }
        catch (ExpiredJwtException e1){
            //parsing 과정에서 만료된 토큰 Exception 처리됨
            //예외처리
            throw new Exception("parsing 과정 에러");
        }
    }

    //payload 반환
    public Map<String, Object> validateFormat(String token, Integer flag) throws IOException {
        //예외처리 일단 x, 검사도 일단 x
        String[] tokenSplit=token.split("\\."); //escape
        Base64.Decoder decoder=Base64.getDecoder();
        String payloadStr=new String(decoder.decode(tokenSplit[1]));

        Map<String, Object> payloads=null;
        payloads=new ObjectMapper().readValue(payloadStr,Map.class);

        return payloads;
    }

}
