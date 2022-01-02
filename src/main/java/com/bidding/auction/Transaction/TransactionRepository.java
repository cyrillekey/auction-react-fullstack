package com.bidding.auction.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository//connects to transaction table 
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    
}
