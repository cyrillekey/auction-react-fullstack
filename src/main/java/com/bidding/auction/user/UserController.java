/**
 * class responsible for handling all user class related requests
 */
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@RestController//specifies it is a restfull controller
@CrossOrigin //allows for cross-origin-requests
public class UserController {
    @Autowired
    private UserRepository userRepository;//connect to user table through user repository
    @Autowired
    private EventRepository eventRepository;//connect to event table through ebent repository

    @GetMapping(path="/get-all-users")//get request to find all users registered
    public List<User> getAllUsers(){
        return userRepository.findAll();//select and return all users in the table
    }
    /**
     *
     * Finds a specific user given the user id in the request parameter
     *  
     * */
    @GetMapping(path="/find-by-id/{id}")//specifies it is a get request with id as one of the parameters
    public User findUserById(@PathVariable Integer id){
        Optional<User> userFound=userRepository.findById(id);//check if user exists in database with id @Param
        if(!userFound.isPresent()){
            //throw error if user does not exist
            throw new FieldNotFoundException("user not found id="+id);
        }
        return userFound.get();//return user if found
    }
    @PostMapping("/register-new")//post method to create a new user when registering user
    public ResponseEntity<Object> saveNewUser(@Valid @RequestBody User newUser){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();//class to hash password for storing in database 
        String password=bCryptPasswordEncoder.encode(newUser.getPassword());//hash user passwordd
        newUser.setPassword(password);//set hash value as new password
        newUser.setDatejoined(new Date());//set date joined to now
        User saveduser=userRepository.save(newUser);//save new user
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getUserId()).toUri();//get location of saved user
        return ResponseEntity.created(location).build();//return location of saved user as reponse
    }
    @PostMapping("/login-user")//post method when useer logs in
    public User logInUser(@Valid @RequestBody LoginDetails login){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

        Optional<User> user=userRepository.findByEmail(login.getEmail());//check if user exists with email provided in login request body
        if(!user.isPresent()){
            throw new FieldNotFoundException("user does not exist");
        }
        if(!bCryptPasswordEncoder.matches(login.getPassword(), user.get().getPassword())){//check if password matches the saved password
            throw new PasswordMismatchException("passwords do not match");//throw error if password do not match
        }
        
        return user.get();//return user is user exists
    }//post method to register user for an event
    @PostMapping("/register-user/{user_id}/to-event/{event_id}")
    public ResponseEntity<Object> registerUserForEvent(@PathVariable Integer event_id,@PathVariable Integer user_id){
        List<User> usersList=new ArrayList<>();//list to hold user
        List<Event> eventList=new ArrayList<>();//list to hold event
        Optional<User> userExists=userRepository.findById(user_id);//get user from database by supplied user id
        Optional<Event> eventPresent=eventRepository.findById(event_id);//get event from database using supplied event id
        if(!userExists.isPresent()){
            //throw error if user does not exist
            throw new FieldNotFoundException("user not found id-"+user_id);
        }
        if(!eventPresent.isPresent()){
            //throw error if event does not exist
            throw new FieldNotFoundException("event does not exist id-"+event_id);
        }

        usersList.add(userExists.get());//add user to list of users
        eventList.add(eventPresent.get());//add event to list of events
        eventPresent.get().setUsersEvents(usersList);//set the list of event user joins
        userExists.get().setEvents(eventList);//set events user joins
        eventRepository.save(eventPresent.get());//update event
        userRepository.save(userExists.get());//update user
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eventPresent.get().getEventId()).toUri();//get id of created event
        return ResponseEntity.created(location).build();//return location of new event
    }
}
