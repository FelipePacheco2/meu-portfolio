package br.com.felipe.Treinamento.exceptionReponse.hadler;

import br.com.felipe.Treinamento.exceptionReponse.ExceptionReponse;
import br.com.felipe.Treinamento.exceptionReponse.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomerEntityReponsehandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionReponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionReponse response = new ExceptionReponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionReponse> handleNotFoundExceptions(Exception ex, WebRequest request) {
        ExceptionReponse response = new ExceptionReponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
