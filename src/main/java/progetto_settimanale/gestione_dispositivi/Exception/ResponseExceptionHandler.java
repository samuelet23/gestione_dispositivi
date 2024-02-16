package progetto_settimanale.gestione_dispositivi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(NotFoundElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorType notFoundExceptionHandler(NotFoundElementException e){
        return new ErrorType(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorType exceptionHandler(Exception e){
        return new ErrorType(e.getMessage());
    }

}
