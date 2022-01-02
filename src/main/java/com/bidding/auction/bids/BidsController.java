package com.bidding.auction.bids;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bidding.auction.exception.DateExpiredException;
import com.bidding.auction.exception.FieldNotFoundException;
import com.bidding.auction.product.Product;
import com.bidding.auction.product.ProductRepository;
import com.bidding.auction.user.User;
import com.bidding.auction.user.UserRepository;

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
/**
 * Class responsible for returning information about the bid
 */
public class BidsController {
    @Autowired
    protected BidsRepository bidsRepository;//connects to the bid database
    @Autowired
    protected UserRepository userRepository;//connects to the user database
    @Autowired
    protected ProductRepository productRepository;//connects to the product database
    //Returns all bids in bud table
    @GetMapping(path="/get-all-bids")//request that will be used to access the method
    public List<Bid> findAllBids(){
        return bidsRepository.findAll();//returns list of all bids in the table
    }
    //get specific bid
    @GetMapping(path="/find-bid-by-id/{id}")//request that will be used to find a specific bid given id
    /**
     * 
     * @param id is the id of the bid
     * @return returns the bid if found
     */
    public Bid getOneBid(@PathVariable Integer id){
        Optional<Bid> oneBid=bidsRepository.findById(id);//check if bid with the id exists in the database
         if(!oneBid.isPresent()){

             throw new FieldNotFoundException("not fount bid id="+id);//if bid is not present throw new error of bid not found
         }   
         return oneBid.get();//if bid is found return the bid
    }
    //Post request that creates a new bid
    @PostMapping(path="/add-new-bid/{user_id}/product/{product_id}")//request for creating a new bid
    /**
     * 
     * @param user_id id of the user placing the bid
     * @param product_id id of the product the user is biding on
     * @param newbid bid to be saves
     * @return
     */
    public ResponseEntity<Object> place_bid(@Valid @PathVariable Integer user_id,@PathVariable Integer product_id ,@RequestBody Bid newbid){
       
        Optional<Product> product=productRepository.findById(product_id);//check if product exists
        Optional<User> user=userRepository.findById(user_id);//check if user exists
        if(!product.isPresent()){
            throw new FieldNotFoundException("product not found id="+product_id);//if product does not exist throw new error
        }
        if(!user.isPresent()){
            throw new FieldNotFoundException("user not found id="+user_id);//throw error is user does not exist
        }
        if((new Date()).after(product.get().getExpiry())){
            throw new DateExpiredException("product expired");//check if date of bid is after today
        }
        newbid.setUser(user.get());//set the user for the bid being placed
        newbid.setProduct(product.get());//set the product the bid belongs to
        newbid.setBidstatus("bid pending");
        bidsRepository.save(newbid);//save the new bid
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newbid.getBid_id()).toUri();//get the url to the new bid created
        return ResponseEntity.created(location).build();//return the url to the new bid that has been created

    } 
    //request to find all bids placed on a single product
    @GetMapping(path="/bid-by-product/{id}")//
    /**
     * 
     * @param id id of the product
     * @return returns the list of all bids placed on a product
     */
    public List<Bid> bidsByProductId(@PathVariable Integer id){
        Optional<Product> product=productRepository.findById(id);//check if the product exists
        if(!product.isPresent()){
            throw new FieldNotFoundException("product not found id="+id);//if the product does not exist throw a new error
        }
        if(product.isEmpty()){
            throw new FieldNotFoundException("bid not found for product id="+id);//if empty then no bids have yet to be placed on the product then throw new error
        }
        return product.get().getBids();//returns the bids that have been placed on the produt
    }
    //find bids placed by a specific user
    @GetMapping(path="/bid-by-user/{id}")
    /**
     * 
     * @param id user id
     * @return returns all the bids by the user
     */
    public List<Bid> getBidsByUser(@PathVariable Integer id){//pathvariable is the id that will be passed on the url
        Optional<User> userBids=userRepository.findById(id);//check if user exist in the user database
        if(!userBids.isPresent()){
            throw new FieldNotFoundException("user not found id-"+id);//if user does not exist throw new error
        }
        return userBids.get().getBids();//return all bids placed by the user
    }
    //find bid by user and product
    @GetMapping(value="/bid-by-user/{id}/product/{product}")//get request that returns 
    /**
     * method to find all bids by user on a specific product
     * @param id id of the user
     * @param product id of the product that has been bid on
     * @return returns list of all bids
     */
    public List<Bid> bidByUserAndproduct(@PathVariable Integer id,@PathVariable Integer product){
        List<Bid> bids=new ArrayList<>();//array that holds list of bids
        Optional<User> user=userRepository.findById(id);//check if user exists
        if(!user.isPresent()){
            throw new FieldNotFoundException("not found id="+id);//throw error if product does not exist
        }
        Optional<Product> oneproduct=productRepository.findById(product);//check if the product exists
        if(!oneproduct.isPresent()){
            throw new FieldNotFoundException("product not found id-"+product);//if product does not exist throw product does not exist error
        }
        /**
         * loop through all the bids for a product and select bids belong to user with id @param id
         */
        List<Bid> bidsFromProduct=oneproduct.get().getBids();
            for(Bid onebid:bidsFromProduct){
                if(onebid.getProduct().getProductid()==product){
                bids.add(onebid);
                }
            }
            
        return bids;//return bids belonging to a user
    }
    /**
     * 
     * returns all the bids for a product in descending order
     * @param product_id id of the product
     * @return
     */
    @GetMapping(value="/get-winning-bid-by-product/{product_id}")
    public List<Bid> getMethodName(@PathVariable Integer product_id) {
        return bidsRepository.findWinningBid(product_id);
    }
    
}

