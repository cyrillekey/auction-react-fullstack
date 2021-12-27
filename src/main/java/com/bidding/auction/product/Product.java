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


@Entity

public class Product{
    
    private String pname;
    @Id
    @GeneratedValue
    private Integer productid;  
    @Range(min=0,message="Price should be greater than zero")
    private Integer minimum_price;
    @Past(message = "Date should be in the past")
    private Date dateSaved;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User userProduct;
    private String imageUrl;
    @Future(message = "Expiry date should be in the future")
    private  Date expiry;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Bid> bids;
    @OneToOne(fetch = FetchType.LAZY)
    private Bundle bundleProduct;
    private Integer bidWinnerid;
    @OneToOne(fetch=FetchType.LAZY)
    private Shipping productShipping; 
    Product(){

    }
    public Product(String pname,Integer productid,Integer minimum_price,Date datesaved,User user,String imageUrl,Date expiry){
        this.pname=pname;
        this.productid=productid;
        this.dateSaved=datesaved;
        this.minimum_price=minimum_price;
        this.userProduct=user;
        this.imageUrl=imageUrl;
        this.expiry=expiry;
    }
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
    @Override
    public String toString() {
        return String.format("[productid:%s,productname:%s,product_url:%s,datesaved:%s,minimum_price:%s]", productid,pname,imageUrl,dateSaved,minimum_price);
        
    }
}