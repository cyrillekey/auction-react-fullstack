package com.bidding.auction.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository//specifies it is a repository to connect to product table
public interface ProductRepository extends JpaRepository<Product,Integer>{
    @Query(value = "SELECT * from product where bid_winnerid is NULL" ,nativeQuery = true)
    List<Product> findAll();
    @Query(value = "SELECT * from product where LOWER(pname) LIKE %:pname% and bid_winnerid IS NULL" ,nativeQuery = true)//custom query used to search for products
    List<Product> findByPnameContaining ( @Param("pname") String pname);//method to get products with pname similar to @param('pname')
}
