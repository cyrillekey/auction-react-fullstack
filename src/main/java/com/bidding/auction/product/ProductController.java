package com.bidding.auction.product;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bidding.auction.exception.FieldNotFoundException;
import com.bidding.auction.user.User;
import com.bidding.auction.user.UserRepository;
import com.bidding.auction.bids.Bid;
import com.bidding.auction.bids.BidsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController//specifies is a rest controller
@CrossOrigin//allows for cross origin requests
public class ProductController {
    @Autowired
    private ProductRepository productRepository;//connect to product table
    @Autowired 
    private UserRepository userRepository;//connect to user table
    @Autowired
    private BidsRepository bidRepository;

    @GetMapping(path="/all-products")//get method to obtain all products in the product table as a list
    public List<Product> getAllProducts(){
        return productRepository.findAll();//finds all products in the product table
    }
    @GetMapping(path="/find-one-product/{id}")//get method to find product given id of the product
    public Product findOneProduct(@PathVariable Integer id){
        Optional<Product> findProduct=productRepository.findById(id);//check if product with @param id exists
        if(!findProduct.isPresent()){
            //if product does not exist throw new error of product not found
            throw new FieldNotFoundException("product not found id-"+id);
        }
        return findProduct.get();//return product if found
    }
    //get products posted by a specific user given user id
    @GetMapping(path="/product-by-user/{id}")
    public List<Product> getProductsByUser(@PathVariable Integer id){
        Optional<User> user=userRepository.findById(id);//check if user exist first
        if(!user.isPresent()){//if user does not exist throw new error
            throw new FieldNotFoundException("user not found id-"+id);

        }
        //if user exist find all products uploaded by one user
        return user.get().getProductsList();
    }
    //function to save a new product
    @PostMapping(path="/add-new-product/{id}")//specifies it is a post request
    public ResponseEntity<Object> saveNewProduct(@Valid @RequestBody Product newProduct,@PathVariable Integer id){
        Optional<User> newuser=userRepository.findById(id);        //check if user exists
        if(!newuser.isPresent()){//throw error if user is not found
            throw new FieldNotFoundException("not found id-");
        }
        //if user is found get the user
        User newUser=newuser.get();
        newProduct.setUserProduct(newUser);//set the user of the product as the obtained user
        productRepository.save(newProduct);//save the new user
        /**
         * get and return the new location of the new created product
         */
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newProduct.getProductid()).toUri();
        return ResponseEntity.created(location).build();

        
    }
    //delete a product given an id
    @DeleteMapping(path="/delete-product-by-id/{id}")
    public void deleteUser(@PathVariable Integer id){
        productRepository.deleteById(id);//delete product from the database with id @param id
    }
    //find product by name
    @GetMapping(path="/find-product/{name}")
    public List<Product> findProduct(@PathVariable String name){
        List <Product> productResonse=productRepository.findByPnameContaining(name);//returns list of products with names similar to @param name used by the search funtion 
        
        return productResonse;
    }
    //method to set the winner of the product 
    @GetMapping(path="/set-winning-bid/product/{id}/bid/{bid}")//get request with product id and bid id
    public void setWinning(@PathVariable Integer id,@PathVariable Integer bid){
        Optional<Product> prod=productRepository.findById(id);//check is the product exists
        Optional<Bid> b=bidRepository.findById(bid);
        if(!prod.isPresent()){//if product does not exist throw new error
            throw new FieldNotFoundException("product not found");
        }
        if(!b.isPresent()){
            throw new FieldNotFoundException("bid not found");
        }
        b.get().setBidstatus("Bid won");
        List<Bid> bids=bidRepository.findLoosingBids(bid, id);
        for (Bid bid2 : bids) {
            bid2.setBidstatus("Bid lost");
            bidRepository.save(bid2);
        }
        prod.get().setBidWinner(bid);//set the bid that won
        productRepository.save(prod.get());//update product to contain winning bid
    

    }
    

}
