package com.bidding.auction.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
    
    public ExceptionResponse(Date timestamp,String message,String details){
        super();
        this.timestamp=timestamp;
        this.message=message;
        this.details=details;
    }
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
