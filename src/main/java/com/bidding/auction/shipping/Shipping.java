/**
 * class responsible for shipping table and  bean
 */
package com.bidding.auction.shipping;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.bidding.auction.product.Product;

@Entity//creates the shipping table
public class Shipping {
    @Id//specifies shipping id is the primary key
    @GeneratedValue//shipping id is automatically generated
    private Integer shippingId;//shipping id
    private String shippingLocation;//location shipment is headed to
    @OneToOne(fetch=FetchType.LAZY,mappedBy="productShipping")//one to one relationship with product being shipped
    private Product shippedProduct;//product being shipped
    private Date shippingDate;//date shipment was done
    private Date estimatedArrivalDate;//estimated arrival date for shipment
    private String pickupLocation;//location for shipping pickup
    Shipping(){

    }
    /**
     * constructor used when object is first
     * @param shippingId
     * @param shippingLocation
     * @param shippedProduct
     * @param shippingDate
     * @param estimatedArrivalDate
     * @param pickupLocation
     */
    /** */
    public Shipping(Integer shippingId,String shippingLocation,Product shippedProduct,Date shippingDate,Date estimatedArrivalDate,String pickupLocation)
    {
        this.shippingId=shippingId;
        this.shippingLocation=shippingLocation;
        this.shippedProduct=shippedProduct;
        this.shippingDate=shippingDate;
        this.estimatedArrivalDate=estimatedArrivalDate;
        this.pickupLocation=pickupLocation;
    }

/**
 * 
 * Setters and getters used by the shipping class
 */

    public Date getEstimatedArrivalDate() {
        return estimatedArrivalDate;
    }
    public String getPickupLocation() {
        return pickupLocation;
    }
    public Product getShippedProduct() {
        return shippedProduct;
    }
    public Date getShippingDate() {
        return shippingDate;
    }
    public Integer getShippingId() {
        return shippingId;
    }
    public String getShippingLocation() {
        return shippingLocation;
    }
    public void setEstimatedArrivalDate(Date estimatedArrivalDate) {
        this.estimatedArrivalDate = estimatedArrivalDate;
    }
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
    public void setShippedProduct(Product shippedProduct) {
        this.shippedProduct = shippedProduct;
    }
    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }
    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }
    public void setShippingLocation(String shippingLocation) {
        this.shippingLocation = shippingLocation;
    }
    @Override
    public String toString() {
        return String.format("[shippingId:%s,shippingDate:%s,shippingLocation:%s,pickupLocation:%s,arrivalDate:%s]", shippingId,shippingDate,shippingLocation,pickupLocation,estimatedArrivalDate);
    }
}
