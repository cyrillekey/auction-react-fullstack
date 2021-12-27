package com.bidding.auction.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bidding.auction.events.Event;
import com.bidding.auction.events.EventRepository;
import com.bidding.auction.exception.FieldNotFoundException;
import com.bidding.auction.exception.PasswordMismatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    @GetMapping(path="/get-all-users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping(path="/find-by-id/{id}")
    public User findUserById(@PathVariable Integer id){
        Optional<User> userFound=userRepository.findById(id);
        if(!userFound.isPresent()){
            throw new FieldNotFoundException("user not found id="+id);
        }
        return userFound.get();
    }
    @PostMapping("/register-new")
    public ResponseEntity<Object> saveNewUser(@Valid @RequestBody User newUser){
        newUser.setDatejoined(new Date());
        User saveduser=userRepository.save(newUser);
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getUserId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PostMapping("/login-user")
    public User logInUser(@Valid @RequestBody LoginDetails login){
        Optional<User> user=userRepository.findByEmail(login.getEmail());
        if(!user.isPresent()){
            throw new FieldNotFoundException("user does not exist");
        }
        if(!(user.get().getPassword()).equals(login.getPassword())){
            throw new PasswordMismatchException("passwords do not match");
        }
        
        return user.get();
    }
    @PostMapping("/register-user/{user_id}/to-event/{event_id}")
    public ResponseEntity<Object> registerUserForEvent(@PathVariable Integer event_id,@PathVariable Integer user_id){
        List<User> usersList=new ArrayList<>();
        List<Event> eventList=new ArrayList<>();
        Optional<User> userExists=userRepository.findById(user_id);
        Optional<Event> eventPresent=eventRepository.findById(event_id);
        if(!userExists.isPresent()){
            throw new FieldNotFoundException("user not found id-"+user_id);
        }
        if(!eventPresent.isPresent()){
            throw new FieldNotFoundException("event does not exist id-"+event_id);
        }

        usersList.add(userExists.get());
        eventList.add(eventPresent.get());
        eventPresent.get().setUsersEvents(usersList);
        userExists.get().setEvents(eventList);
        eventRepository.save(eventPresent.get());
        userRepository.save(userExists.get());
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eventPresent.get().getEventId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
