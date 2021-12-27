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


@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Integer transactionId;
    private String referenceCode;
    private Float amount;
    private String responseCode;
    private Date datePaid;
    @OneToOne(mappedBy = "bidTrans")    
    private Bid bid;
    @ManyToOne(fetch = FetchType.LAZY)
    private User userTrans;

    protected Transaction(){

    }
    public Transaction(Integer transactionId,String referenceCode,Float amount,String responseCode,Date datePaid,User userTrans,Bid bid){
        this.transactionId=transactionId;
        this.referenceCode=referenceCode;
        this.amount=amount;
        this.referenceCode=responseCode;
        this.datePaid=datePaid;
        this.bid=bid;
        this.userTrans=userTrans;
    }
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
