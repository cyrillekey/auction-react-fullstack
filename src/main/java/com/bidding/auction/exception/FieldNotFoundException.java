/**
 * Class that handles response incase a record in not found in the database
 */
package com.bidding.auction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)//set status of response to not found
public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(String message){
        super(message);
    }
}
