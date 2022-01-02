/**
 * class used for the shippping table and shipping bean
 */
package com.bidding.auction.events;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

import com.bidding.auction.bundle.Bundle;
import com.bidding.auction.user.User;


@Entity//creates a shipping table
public class Event {
    @Id//specifies it is the primary key
    @GeneratedValue//value is automatically generated
    private Integer eventId;//id of the event
    private String eventName;//event name
    @Past
    private Date eventStartingTime;//time event starts
    @Future//constraint to ensure end date is in the future
    private Date eventEndDate;//time event ends
    @ManyToMany(mappedBy = "events")
    private List<User> usersEvents;//list of users belonging to the event
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "oneEvent",cascade = CascadeType.REMOVE)//one to one relationship with the bundle belonging to the event
    private Bundle eventBundle;
    /**
     * default constructor used by springboot
     */
    protected Event(){

    }
    //constructor that initializes the object when created
    public Event(Integer eventId,String eventName,Date eventStartingDate,Date eventEndDate){
        this.eventId=eventId;
        this.eventName=eventName;
        this.eventStartingTime=eventStartingDate;
        this.eventEndDate=eventEndDate;
    }
    /**
     * 
     * getters and setters for the Event class
     */

    public Bundle getEventBundle() {
        return eventBundle;
    }
    public Date getEventEndDate() {
        return eventEndDate;
    }
    public String getEventName() {
        return eventName;
    }
    public Integer getEventId() {
        return eventId;
    }
    public Date getEventStartingTime() {
        return eventStartingTime;
    }
    public List<User> getUsersEvents() {
        return usersEvents;
    }
    public void setEventBundle(Bundle eventBundle) {
        this.eventBundle = eventBundle;
    }
    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }public void setEventName(String eventName) {
        this.eventName = eventName;
    }public void setEventStartingTime(Date eventStartingTime) {
        this.eventStartingTime = eventStartingTime;
    }
    public void setUsersEvents(List<User> usersEvents) {
        this.usersEvents = usersEvents;
    }
    @Override
    public String toString() {
        return String.format("[event_id:%s,eventName:%s,eventStartingDate:%s,eventEndDate:%s]", eventId,eventName,eventStartingTime,eventEndDate);
    }

}
