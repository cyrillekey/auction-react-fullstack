package com.bidding.auction.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.bidding.auction.Transaction.Transaction;
import com.bidding.auction.bids.Bid;
import com.bidding.auction.events.Event;
import com.bidding.auction.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer userId;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    @Column(unique = true)
    private String email;
    @Size(min = 6)
    private String password;
    @Past
    private Date datejoined;
    @JsonIgnore
    @OneToMany(mappedBy = "userProduct")
    private List<Product>products;
    @JsonIgnore
    @OneToMany(mappedBy="user")
    private List<Bid> bids;
    @OneToMany(mappedBy="userTrans")
    private List<Transaction> transactions;
    @JsonIgnore
    @ManyToMany
    private List<Event> events;
    User(){
        
    }
    public User(Integer userId,String username,String email,String passoword,Date datejoined){
        this.userId=userId;
        this.username=username;
        this.email=email;
        this.password=passoword;
        this.datejoined=datejoined;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDatejoined(Date datejoined) {
        this.datejoined = datejoined;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getDatejoined() {
        return datejoined;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Integer getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    public void setProductsList(List<Product> productsList) {
        this.products = productsList;
    }
    public List<Product> getProductsList() {
        return products;
    }
    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
    public List<Bid> getBids() {
        return bids;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    public List<Event> getEvents() {
        return events;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }
    @Override
    public String toString() {
        return String.format("[id:%s,email:%s,username:%s,date-joined:%s]",userId,email,username,datejoined);
    }
}
