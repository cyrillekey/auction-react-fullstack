package com.bidding.auction.bundle;
/**
 * Imports used in the program
 */
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

import com.bidding.auction.events.Event;
import com.bidding.auction.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity//specifiesthe class represents a table
/**
 * class that holds the bundle which is a collection of products that have not been sold on epiry date
 */
public class Bundle {
    @Id//unique identifier for the table
    @GeneratedValue//value is auto generated 
    private Integer bundleId;//id for the bundle
    @Past//date bundle was created should be in the past
    private Date bundleCreation;//date bundle is created
    @Future//bundle expiry date
    private Date bundleExpiry;//date the bundle will expire
    private String image_url;//image of the bundle
    @OneToMany(mappedBy = "bundleProduct")//list of products belonging to the bundle
    private List<Product> products;//list of prudtcs
    private Float bundlePrice;//price of the bundle
    @JsonIgnore//specifies field should not be returned in the request
    @OneToOne(fetch = FetchType.LAZY)
    private Event oneEvent;//events the bid belongs to
    /**
     * default constructor used by springboot
     */
    protected Bundle(){

    }
    /**
     * initial contructor when Object is created
     * @param bundleId id of the bundle
     * @param bundleCreation date bundle is created
     * @param bundleExpiry bundle expiry date
     * @param image_url bundle image url
     * @param bundlePrice bundle price
     */
    public Bundle(Integer bundleId,Date bundleCreation,Date bundleExpiry,String image_url,Float bundlePrice){
        this.bundleId=bundleId;
        this.bundleCreation=bundleCreation;
        this.bundleExpiry=bundleExpiry;
        this.bundlePrice=bundlePrice;
        this.image_url=image_url;
    }
    /**
     * getters and setters for the bundle class
     */
    public void setBundleCreation(Date bundleCreation) {
        this.bundleCreation = bundleCreation;
    }
    public void setBundleExpiry(Date bundleExpiry) {
        this.bundleExpiry = bundleExpiry;
    }
    public void setBundleId(Integer bundleId) {
        this.bundleId = bundleId;
    }
    public void setBundlePrice(Float bundlePrice) {
        this.bundlePrice = bundlePrice;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public Date getBundleCreation() {
        return bundleCreation;
    }
    public Date getBundleExpiry() {
        return bundleExpiry;
    }
    public Integer getBundleId() {
        return bundleId;
    }
    public Float getBundlePrice() {
        return bundlePrice;
    }
    public String getImage_url() {
        return image_url;
    }
    public Event getOneEvent() {
        return oneEvent;
    }
    public void setOneEvent(Event oneEvent) {
        this.oneEvent = oneEvent;
    }
    public List<Product> getProducts() {
        return products;
    }
    
}
