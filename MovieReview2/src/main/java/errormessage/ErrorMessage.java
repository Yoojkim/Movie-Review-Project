package errormessage;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMessage {
    //열거형 선언
    INVALID_USER_EXCEPTION(1,"회원 정보가 존재하지 않습니다.",HttpStatus.BAD_REQUEST),
    INVALID_PW_EXCEPTION(2,"비밀번호가 일치하지 않습니다.",HttpStatus.BAD_REQUEST),
    ALREADY_AUTHORIZED_EMAIL(3,"이미 등록된 이메일입니다.",HttpStatus.BAD_REQUEST),
    ALREADY_USED_NICKNAME(4,"이미 사용 중인 닉네임입니다.",HttpStatus.BAD_REQUEST),
    NOT_REFRESHTOKEN(5,"Refresh token이 아닌 Access token이 들어왔습니다.",HttpStatus.UNAUTHORIZED),
    EXPIRED_REFRESHTOKEN(6,"Refresh token이 만료되었습니다.",HttpStatus.UNAUTHORIZED),
    EXPIRED_ACCESSTOKEN(7,"Access token이 만료되었습니다.",HttpStatus.UNAUTHORIZED),
    NULL_TOKEN(8,"Token이 전달되지 않았습니다.",HttpStatus.UNAUTHORIZED);

    int code;
    String errorMessage;
    HttpStatus httpStatus;

    ErrorMessage(int code, String errorMessage, HttpStatus httpStatus){
        this.code=code;
        this.errorMessage=errorMessage;
        this.httpStatus=httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
