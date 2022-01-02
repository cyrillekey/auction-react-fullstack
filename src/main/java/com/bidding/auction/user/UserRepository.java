package com.bidding.auction.user;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository//connects to the user table 
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);//method provided by springboot to find users given email address 
}
