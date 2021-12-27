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

@RestController
@CrossOrigin
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/get-all-events")
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
    @GetMapping("/get-event-by-id/{event_id}")
    public Event getOneEvent(@PathVariable Integer event_id){
        Optional<Event> findEvent=eventRepository.findById(event_id);
        if(!findEvent.isPresent()){
            throw new FieldNotFoundException("event not found id-"+event_id);
        }
        return findEvent.get();
    }
    @PutMapping("/delete-event-by-id/{event_id}")
    public void deleteEvent(@PathVariable Integer event_id){
        eventRepository.deleteById(event_id);
    }
    @PostMapping("/create-new-event")
    public ResponseEntity<Object> createNewEvent(@Valid @RequestBody Event newEvent){
        eventRepository.save(newEvent);
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEvent.getEventId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
