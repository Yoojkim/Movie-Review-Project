package exception;

import errormessage.ErrorMessage;

public class RequestInputException extends BaseException{
    public RequestInputException(ErrorMessage errorMessage){
        super(errorMessage);
    }
}
