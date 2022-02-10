package exception;

import errormessage.ErrorMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class BaseException extends RuntimeException{
    private String className;
    private String errorMessage;
    private int code;
    private HttpStatus httpStatus;

    public BaseException(ErrorMessage errorMessage){
        this.className=this.getClass().getSimpleName();
        this.errorMessage=errorMessage.getErrorMessage();
        this.code=errorMessage.getCode();
        this.httpStatus=errorMessage.getHttpStatus();
    }

    public BaseException(String className,ErrorMessage errorMessage){
        this.className=className;
        this.errorMessage=errorMessage.getErrorMessage();
        this.code=errorMessage.getCode();
        this.httpStatus=errorMessage.getHttpStatus();
    }

}
