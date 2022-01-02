/**
 * response thrown when date is in the past instead of being in the future
 */

package com.bidding.auction.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)//send status of response to bad request
public class DateExpiredException extends RuntimeException {
    //default constuctor
    public DateExpiredException(String message){
        super(message);
    }  
}
