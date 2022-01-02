/**
 * class responsible for connecting with the event table and allows for operations to be carried on the table
 */
package com.bidding.auction.events;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository//specifies class is a repository and what entity it maps to
public interface EventRepository extends JpaRepository<Event,Integer> {
    List<Event> findByEventName(String event_name);
    
}
