/**
 * class that maps to a user and represents a user bean and table
 */
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



@Entity//specifies it is a table
public class User {
    @Id//specifies it is the primary key for the table
    @GeneratedValue//specifies value is auto generated
    private Integer userId;//id of the user
    @NotNull//specifies value should not be null
    @Column(unique = true)//specifies each value should be unique
    private String username;//username of the user
    @NotNull//specify email should not ne null
    @Column(unique = true)//email should ne unique
    private String email;//email of the user
    @Size(min = 6)//specifies minimum size of the password
    private String password;//password of the user
    @Past//constraint date joined is in the past
    private Date datejoined;//date the user joined
    @JsonIgnore//field will be neglected in response
    @OneToMany(mappedBy = "userProduct")//one to many relationship mapping with the products
    private List<Product>products;//list of products the user has uploades
    @JsonIgnore//ignore field in response
    @OneToMany(mappedBy="user")//maps a one to many realtionship with bids
    private List<Bid> bids;//list of bids placed by the user
    @OneToMany(mappedBy="userTrans")//maps to transactions by the user
    private List<Transaction> transactions;//list of transactions by the user
    @JsonIgnore//ignore field in response
    @ManyToMany//maps a many to many relationship to events
    private List<Event> events;//list of events the user belongs to
    User(){
        
    }
    /**
     * default contructor used when the object is created
     * @param userId
     * @param username
     * @param email
     * @param passoword
     * @param datejoined
     */
    public User(Integer userId,String username,String email,String passoword,Date datejoined){
        this.userId=userId;
        this.username=username;
        this.email=email;
        this.password=passoword;
        this.datejoined=datejoined;
    }
    /**
     * 
     * getters and setters for the user class
     */
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
