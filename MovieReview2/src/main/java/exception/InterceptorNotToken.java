package exception;

import errormessage.ErrorMessage;

public class InterceptorNotToken extends BaseException{
    public InterceptorNotToken(ErrorMessage errorMessage){
        super(errorMessage);
    }
}
