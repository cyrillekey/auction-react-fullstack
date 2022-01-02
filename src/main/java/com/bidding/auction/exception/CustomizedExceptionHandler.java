/**
 * class responsible for handling all custom exception declared and sending response in structured method
 */

package com.bidding.auction.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController//specifies is a rest controller
/**
 * to be able to handle write custome exception response we extends the responseentityexceptionhandler
 */
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    /**
     * method to handle method not valid exception
     */
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers,HttpStatus status,WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), "Validation Failed", ex.getLocalizedMessage());//create new response
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);//return new exception response to the request and the status of the response as a bad request

    }

    @ExceptionHandler(Exception.class)//specifies which exception the method handle
    /**
     * method handles all exceptions that may occur
     * @param ex
     * @param request
     * @return
     */
    public final ResponseEntity<Object> handleAllException(Exception ex,WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));//create new exception reponse
        return new ResponseEntity<>(exceptionResponse,HttpStatus.METHOD_NOT_ALLOWED);//return new response message with status as method not allowed
    }
    @ExceptionHandler(FieldNotFoundException.class)//method to handle exception incase record is not found
    /**
     * method that handles exception incase a record is not found
     * @param ex
     * @param request
     * @return
     */
    public final ResponseEntity<Object> handleFieldNotFound(Exception ex,WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));//create new respone message
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);//return new response with status as not found
    }
    /**
     * handles exception incase date has expired
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(DateExpiredException.class)
    public final ResponseEntity<Object> handleExpiry(Exception ex,WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
    /**
     * handle exception incase passwords do not match
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(PasswordMismatchException.class)
    public final ResponseEntity<Object> handlePasswordMismatch(Exception ex,WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
