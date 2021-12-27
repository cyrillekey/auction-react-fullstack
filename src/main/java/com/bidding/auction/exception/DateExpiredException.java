package com.bidding.auction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DateExpiredException extends RuntimeException {
    public DateExpiredException(String message){
        super(message);
    }  
}
