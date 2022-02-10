package exceptionHandler;

import errormessage.ErrorMessage;
import exception.BaseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Throwable.class)
    public ResponseEntity<Map<String,String>> ExceptionHandler(Throwable e) {

        if (e instanceof BaseException) {
            BaseException be = (BaseException) e;
            Map<String, String> responseBody = new HashMap<>();

            responseBody.put("Code", Integer.toString(be.getCode()));
            responseBody.put("Error Message", be.getErrorMessage());
            responseBody.put("Http Status", be.getHttpStatus().toString());

            return new ResponseEntity<>(responseBody, be.getHttpStatus());
        } else {
            BaseException be = new BaseException(e.getClass().getSimpleName(), ErrorMessage.VALIDATION_FAIL_EXCEPTION);
            List<ObjectError> messageList = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            String message = "";
            for(int i=0; i<messageList.size(); i++){
                String validationMessage =  messageList.get(i).getDefaultMessage();
                message += "[" + validationMessage + "]";
            }
            be.setErrorMessage(message);

            Map<String, String> responseBody = new HashMap<>();

            responseBody.put("Code", Integer.toString(be.getCode()));
            responseBody.put("Error Message", be.getErrorMessage());
            responseBody.put("Http Status", be.getHttpStatus().toString());

            return new ResponseEntity<>(responseBody, be.getHttpStatus());
        }
    }
}
