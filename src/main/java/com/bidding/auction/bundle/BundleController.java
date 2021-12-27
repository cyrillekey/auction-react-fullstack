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

@RestController
@CrossOrigin
public class BundleController {
    @Autowired
    private BundleRepository bundleRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ProductRepository productRepository;
    @GetMapping(path ="/get-all-bundles")
    public List<Bundle> getAllBundles(){
        return bundleRepository.findAll();
    }
    @GetMapping(path="/get-bundle-by-id/{id}")
    public Bundle getbundleById(@PathVariable Integer id){
        Optional<Bundle> oneBundle=bundleRepository.findById(id);
        if(!oneBundle.isPresent()){
            throw new FieldNotFoundException("bundle not found");

        }
        return oneBundle.get();
    }
    //TODO fix adding a new bundle
    @PostMapping(path="/create-bundle/{event_id}")
    public ResponseEntity<Object> createNewBundle(@Valid @PathVariable Integer event_id,@RequestBody Bundle bundle){
        List<Product> expiredProduct=new ArrayList<>();
        Optional<Event> event=eventRepository.findById(event_id);
        if(!event.isPresent()){
            throw new FieldNotFoundException("event does not exist");
        }
        List<Product> productBelonging=productRepository.findAll();
        
       for (Product product : productBelonging) {
           if (product.getExpiry().after(new Date())) {
               product.setBundleProduct(bundle);
               System.out.println("product 1 added to list");
           }
       }

         bundle.setProducts(expiredProduct);
        bundle.setOneEvent(event.get());
        bundleRepository.save(bundle);
        

        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bundle.getBundleId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
