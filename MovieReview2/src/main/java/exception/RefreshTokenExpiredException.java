package exception;

import errormessage.ErrorMessage;

public class RefreshTokenExpiredException extends BaseException{
    public RefreshTokenExpiredException(ErrorMessage errorMessage){
        super(errorMessage);
    }
}
