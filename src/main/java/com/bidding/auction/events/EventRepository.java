package com.bidding.auction.events;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
    List<Event> findByEventName(String event_name);
    
}
