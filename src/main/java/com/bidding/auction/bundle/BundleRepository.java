package com.bidding.auction.bundle;
/**
 * Connects to the bundle table to perform crud operations
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository//specifies the class connects to a database
/**
 * to use Jpa to connect to a database we use an interface that extends the JpaRepository class
 * The <Bundle Integer> specifies that this repository connect to the bundle class and the primary key is an integer
 */
public interface BundleRepository extends JpaRepository<Bundle,Integer> {
    
}
