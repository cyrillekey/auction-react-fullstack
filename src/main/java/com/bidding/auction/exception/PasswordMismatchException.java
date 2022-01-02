/**
 * Exception that is thrown when passwords do not match
 */
package com.bidding.auction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)//set the status of the response to bad request
public class PasswordMismatchException extends RuntimeException {
    //default constructor
    public PasswordMismatchException(String message){
        
        super(message);
    }
}
