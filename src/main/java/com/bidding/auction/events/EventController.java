/**
 * class that handles all event related requests
 */
package com.bidding.auction.events;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bidding.auction.exception.FieldNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController//specifies its a restfull controller
@CrossOrigin//alllows for cross origin requests
public class EventController {
    @Autowired
    private EventRepository eventRepository;//connects to events table

    @GetMapping("/get-all-events")//get request that fetches all events in the table
    public List<Event> getAllEvents(){
        return eventRepository.findAll();//finds and returns all events in the table
    }
    @GetMapping("/get-event-by-id/{event_id}")//get one specific event provided the event whose id matched the provided id
    public Event getOneEvent(@PathVariable Integer event_id){
        Optional<Event> findEvent=eventRepository.findById(event_id);//checks if event with the id exists
        if(!findEvent.isPresent()){
            throw new FieldNotFoundException("event not found id-"+event_id);//throw error if event with the id does not exist
        }
        return findEvent.get();//returns event if found
    }
    @PutMapping("/delete-event-by-id/{event_id}")//deletes an event from th event table
    public void deleteEvent(@PathVariable Integer event_id){
        eventRepository.deleteById(event_id);
      }
    @PostMapping("/create-new-event")//post request to create a new event
    public ResponseEntity<Object> createNewEvent(@Valid @RequestBody Event newEvent){
        //request body for this method is of eventy type
        eventRepository.save(newEvent);//save the new event
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEvent.getEventId()).toUri();
        /**
         * gets and returns the locations url of the newly created event
         */
        return ResponseEntity.created(location).build();
    }
}
