package com.bidding.auction.bids;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
/**
 * Connects with the database that holds the bids table
 */
public interface BidsRepository extends JpaRepository<Bid,Integer>{//class that connects the bids table
    /**
     * Query that selects bids from tha bids table in descending order
     * @param product_id id of the product
     * @return list of bids
     * 
     */
    @Query(value = "SELECT * from bid where product_productid=?1 ORDER BY bid_price DESC" ,nativeQuery = true)
    List<Bid> findWinningBid(Integer product_id);//method that selects bids from the bids table
    @Query(value = "SELECT * FROM BID where bid_id <> :bid and product_productid=:prod",nativeQuery = true)
    List<Bid> findLoosingBids(@Param("bid") Integer bidId,@Param("prod") Integer product);
}
