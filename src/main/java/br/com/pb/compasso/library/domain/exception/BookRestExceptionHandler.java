package br.com.pb.compasso.library.domain.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class BookRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRestExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseBody> handleTypeMismatchException(HttpServletRequest request, TypeMismatchException exc){

        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setTimeStamp(new Date());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.addError(exc.getMessage());
        error.setPath(request.getServletPath());
        LOGGER.error(exc.getMessage(), exc);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseBody> handlePageNotFoundException(HttpServletRequest request, PageNotFoundException exc){

        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setTimeStamp(new Date());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.addError(exc.getMessage());
        error.setPath(request.getServletPath());
        LOGGER.error(exc.getMessage(), exc);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseBody> handleInternalServerException(HttpServletRequest request, InternalServerException exc){

        ExceptionResponseBody error = new ExceptionResponseBody();




        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponseBody handleConstraintViolationException(HttpServletRequest request, Exception ex){

        ExceptionResponseBody error = new ExceptionResponseBody();

        error.setTimeStamp(new Date());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.addError(ex.getMessage());
        error.setPath(request.getServletPath());
        LOGGER.error(ex.getMessage(), ex);

        return error;

    }

}
