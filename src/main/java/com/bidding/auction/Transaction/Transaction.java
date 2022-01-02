/**
 * class responsible for transaction bean and table
 */
package com.bidding.auction.Transaction;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.bidding.auction.bids.Bid;
import com.bidding.auction.user.User;


@Entity//creates a transaction table
public class Transaction {
    @Id//specifies transactionid is the primary key
    @GeneratedValue//value is auto generated
    private Integer transactionId;//id of the transaction
    private String referenceCode;//reference code of the transaction
    private Float amount;//amount paid
    private String responseCode;//for status of the transaction if complete or failed
    private Date datePaid;//date the transaction was made
    @OneToOne(mappedBy = "bidTrans")    //creates a one to one relationship with bid the transcation belongs to
    private Bid bid;//bid the transaction belongs to
    @ManyToOne(fetch = FetchType.LAZY)//creates a many to one relationship with the user paying for the transaction
    private User userTrans;//user responsible for the transaction

    //default contructor used springboot
    protected Transaction(){

    }
    /**
     * contructor that initializes the object when object is created
     */
    public Transaction(Integer transactionId,String referenceCode,Float amount,String responseCode,Date datePaid,User userTrans,Bid bid){
        this.transactionId=transactionId;
        this.referenceCode=referenceCode;
        this.amount=amount;
        this.referenceCode=responseCode;
        this.datePaid=datePaid;
        this.bid=bid;
        this.userTrans=userTrans;
    }
    /**
     * 
     * setters and getters for the Transaction class
     */

    public void setBid(Bid bid) {
        this.bid = bid;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }
    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
    public void setUserTrans(User userTrans) {
        this.userTrans = userTrans;
    }
    public Float getAmount() {
        return amount;
    }
    public Bid getBid() {
        return bid;
    }
    public Date getDatePaid() {
        return datePaid;
    }
    public String getReferenceCode() {
        return referenceCode;
    }
    public String getResponseCode() {
        return responseCode;
    }
    public Integer getTransactionId() {
        return transactionId;
    }
    public User getUserTrans() {
        return userTrans;
    }
    @Override
    public String toString() {
        return String.format("[reference:%s,amount:%s,date:%s,responseCode:%s]",transactionId,amount,datePaid,responseCode);
    }
}
