package com.bidding.auction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(String message){
        super(message);
    }
}
