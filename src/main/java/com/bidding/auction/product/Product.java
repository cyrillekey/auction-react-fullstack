package com.bidding.auction.product;
import com.bidding.auction.shipping.Shipping;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

import com.bidding.auction.bids.Bid;
import com.bidding.auction.bundle.Bundle;
import com.bidding.auction.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.Range;


@Entity//specifies it represents a table

public class Product{
    
    private String pname;
    @Id//specifies product id is the primary key
    @GeneratedValue//specifies variable product id value is auto generated
    private Integer productid;  //id of the product
    @Range(min=0,message="Price should be greater than zero")
    private Integer minimum_price;//minimum price for the product
    @Past(message = "Date should be in the past")
    private Date dateSaved;//date the product was added
    @JsonIgnore//specifies user should not be returned in the response
    @ManyToOne(fetch = FetchType.LAZY)
    private User userProduct;//user who uploads the product
    private String imageUrl;////url of the image of the product
    @Future(message = "Expiry date should be in the future")
    private  Date expiry;//date the product will expire 
    @JsonIgnore
    @OneToMany(mappedBy = "product")//relationship that maps bid and product in a one to many relationship ie one product many bids one bid one product 
    private List<Bid> bids;//list of all bids on the product
    @OneToOne(fetch = FetchType.LAZY)//one to one relationship mapping with bundles
    private Bundle bundleProduct;//bundle the product belongs to
    private Integer bidWinnerid;//id of the user who has won the bid
    @OneToOne(fetch=FetchType.LAZY)
    private Shipping productShipping; //shipping id for the product once won
    private String productDesc;//description of the product
    /**
     * default constructor used by springboot
     */
    Product(){

    }
    /**
     * contructor called when method is created
     * @param pname
     * @param productid
     * @param minimum_price
     * @param datesaved
     * @param user
     * @param imageUrl
     * @param expiry
     * @param productDesc
     */
    public Product(String pname,Integer productid,Integer minimum_price,Date datesaved,User user,String imageUrl,Date expiry,String productDesc){
        this.pname=pname;
        this.productid=productid;
        this.dateSaved=datesaved;
        this.minimum_price=minimum_price;
        this.userProduct=user;
        this.imageUrl=imageUrl;
        this.expiry=expiry;
        this.productDesc=productDesc;
    }
    /**
     * setters and getters used for the class Product
     */

    public void setDateSaved(Date dateSaved) {
        this.dateSaved = dateSaved;
    }
    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setMinimum_price(Integer minimum_price) {
        this.minimum_price = minimum_price;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public void setProductid(Integer productid) {
        this.productid = productid;
    }
  
    public Date getDateSaved() {
        return dateSaved;
    }
    public Date getExpiry() {
        return expiry;
    }
    public String getPname() {
        return pname;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public Integer getMinimum_price() {
        return minimum_price;
    }
    public Integer getProductid() {
        return productid;
    }
    public void setUserProduct(User userProduct) {
        this.userProduct = userProduct;
    }
    public List<Bid> getBids() {
        return bids;
    }
    public void setBundleProduct(Bundle bundleProduct) {
        this.bundleProduct = bundleProduct;
    }
    public void setBidWinner(Integer bidWinnerId) {
        this.bidWinnerid = bidWinnerId;
    }
    public Integer getBidWinner() {
        return bidWinnerid;
    }
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
    public String getProductDesc() {
        return productDesc;
    }   
    @Override
    public String toString() {
        return String.format("[productid:%s,productname:%s,product_url:%s,datesaved:%s,minimum_price:%s]", productid,pname,imageUrl,dateSaved,minimum_price);
        
    }
}