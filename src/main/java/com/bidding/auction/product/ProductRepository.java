package com.bidding.auction.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
    @Query(value = "SELECT * from product where pname LIKE %:pname% and bid_winnerid IS NULL" ,nativeQuery = true)
    List<Product> findByPnameContaining ( @Param("pname") String pname);
}
