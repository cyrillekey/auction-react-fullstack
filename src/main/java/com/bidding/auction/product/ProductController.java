package com.bidding.auction.product;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bidding.auction.exception.FieldNotFoundException;
import com.bidding.auction.user.User;
import com.bidding.auction.user.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties.Throttler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired 
    private UserRepository userRepository;
    @GetMapping(path="/all-products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @GetMapping(path="/find-one-product/{id}")
    public Product findOneProduct(@PathVariable Integer id){
        Optional<Product> findProduct=productRepository.findById(id);
        if(!findProduct.isPresent()){
            throw new FieldNotFoundException("product not found id-"+id);
        }
        return findProduct.get();
    }
    //get products posted by a specific user
    @GetMapping(path="/product-by-user/{id}")
    public List<Product> getProductsByUser(@PathVariable Integer id){
        Optional<User> user=userRepository.findById(id);
        if(!user.isPresent()){
            throw new FieldNotFoundException("user not found id-"+id);

        }
        return user.get().getProductsList();
    }
    //function to save a new product
    @PostMapping(path="/add-new-product/{id}")
    public ResponseEntity<Object> saveNewProduct(@Valid @RequestBody Product newProduct,@PathVariable Integer id){
        Optional<User> newuser=userRepository.findById(id);        
        if(!newuser.isPresent()){
            throw new FieldNotFoundException("not found id-");
        }
        User newUser=newuser.get();
        newProduct.setUserProduct(newUser);
        productRepository.save(newProduct);
        System.out.println(newUser.getUsername());
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newProduct.getProductid()).toUri();
        return ResponseEntity.created(location).build();

        
    }
    //delete a product
    @DeleteMapping(path="/delete-product-by-id/{id}")
    public void deleteUser(@PathVariable Integer id){
        productRepository.deleteById(id);
    }
    @GetMapping(path="/find-product/{name}")
    public List<Product> findProduct(@PathVariable String name){
        List <Product> productResonse=productRepository.findByPnameContaining(name);
        
        return productResonse;
    }
    @GetMapping(path="/set-winning-bid/product/{id}/bid/{bid}")
    public void setWinning(@PathVariable Integer id,@PathVariable Integer bid){
        Optional<Product> prod=productRepository.findById(id);
        if(!prod.isPresent()){
            throw new FieldNotFoundException("product not found");
        }
        prod.get().setBidWinner(bid);
        productRepository.save(prod.get());

    }

}
