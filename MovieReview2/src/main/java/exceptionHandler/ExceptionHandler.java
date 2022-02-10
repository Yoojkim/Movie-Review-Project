package exceptionHandler;

import exception.BaseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BaseException.class)
    public ResponseEntity<Map<String,String>> ExceptionHandler(BaseException e){
        Map<String,String> responseBody=new HashMap<>();

        responseBody.put("Code",Integer.toString(e.getCode()));
        responseBody.put("Error Message",e.getErrorMessage());
        responseBody.put("Http Status",e.getHttpStatus().toString());

        return new ResponseEntity<>(responseBody,e.getHttpStatus());

    }


}
