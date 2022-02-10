package exception;

import errormessage.ErrorMessage;

public class TokenMatchingException extends BaseException{
    public TokenMatchingException(ErrorMessage errorMessage){
        super(errorMessage);
    }
}
