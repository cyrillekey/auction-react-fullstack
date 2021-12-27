package com.bidding.auction.bundle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BundleRepository extends JpaRepository<Bundle,Integer> {
    
}
