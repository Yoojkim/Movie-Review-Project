package exception;

import errormessage.ErrorMessage;

public class AccessTokenExpiredException extends BaseException{
    public AccessTokenExpiredException(ErrorMessage errorMessage){
        super(errorMessage);
    }
}
