package com.bidding.auction.bundle;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bidding.auction.events.Event;
import com.bidding.auction.events.EventRepository;
import com.bidding.auction.exception.FieldNotFoundException;
import com.bidding.auction.product.Product;
import com.bidding.auction.product.ProductRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
/**
 * Contoller responsible for performing CRUD operations on the Bundle table
 */
@RestController//specifies the class represents a restfull controller
@CrossOrigin//allows for cross origin request
public class BundleController {
    @Autowired//allows automatic dependency injection
    private BundleRepository bundleRepository;//connect to bundle table
    @Autowired
    private EventRepository eventRepository;//connect to event table
    @Autowired
    private ProductRepository productRepository;//connect to product table
    /**
     * method that returns all products in the product table as a java list
     * @return
     */
    @GetMapping(path ="/get-all-bundles")//specifies it is a get request
    public List<Bundle> getAllBundles(){
        return bundleRepository.findAll();//finds all bundles in the bundle table
    }
    /**
     * request method to find a specific bundle given a bundle id 
     * @param id //id of the bundle
     * @return
     */
    @GetMapping(path="/get-bundle-by-id/{id}")//specifies it is a ge request
    public Bundle getbundleById(@PathVariable Integer id){
        //checks if a bundle with @param id exists
        Optional<Bundle> oneBundle=bundleRepository.findById(id);
        if(!oneBundle.isPresent()){//if bundle does not exist throw new error of bundle not found
            throw new FieldNotFoundException("bundle not found");

        }
        //return bundle if bundle was found
        return oneBundle.get();
    }
    /***
     * post request that creates and save a new bundle in the database given the event where the bundle exists in
     * @param event_id //event the bunde exist in
     * @param bundle //body of the request received
     * @return 
     */
    @PostMapping(path="/create-bundle/{event_id}")//specifies it is a post request and the url
    /**
     * @Valid anotation check if the request body passes all the set constraints
     */
    public ResponseEntity<Object> createNewBundle(@Valid @PathVariable Integer event_id,@RequestBody Bundle bundle){
        List<Product> expiredProduct=new ArrayList<>();//list to hold all expired product to be added to the bundle
        Optional<Event> event=eventRepository.findById(event_id);//check if the event id presented exists in the database
        if(!event.isPresent()){//if event does not exist throw new error
            throw new FieldNotFoundException("event does not exist");
        }

        List<Product> productBelonging=productRepository.findAll();//get all products
        /**
         * check all product and add the expird producs to the expired product List
         */
       for (Product product : productBelonging) {
           if (product.getExpiry().after(new Date())) {
               product.setBundleProduct(bundle);
               System.out.println("product 1 added to list");
           }
       }
        
         bundle.setProducts(expiredProduct);//add products to the bundle
        bundle.setOneEvent(event.get());//set the which the bundle belongs to
        bundleRepository.save(bundle);//save the event to the database
        
       /**
        * return new location of the product
        */
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bundle.getBundleId()).toUri();//get location of the new created bundle
        return ResponseEntity.created(location).build();//return location of new bundle
    }
}
