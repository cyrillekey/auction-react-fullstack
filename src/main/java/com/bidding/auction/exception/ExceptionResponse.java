/**
 * how response will be structured incase of an occurence of an exception in the requests
 */
package com.bidding.auction.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;//time the exception occurs
    private String message;//message of the exception
    private String details;//details of the exception
    
    /**
     * default constructor called when object is created
     * @param timestamp
     * @param message
     * @param details
     */
    public ExceptionResponse(Date timestamp,String message,String details){
        super();
        this.timestamp=timestamp;//assign timestamp to reponse
        this.message=message;//assign message
        this.details=details;//assign details
    }
    /**
     * getters and setters for the ExceptionReponse class
     */

    public String getDetails() {
        return details;
    }
    public String getMessage() {
        return message;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    
}
