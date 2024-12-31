package com.temproj.temporalProject.repository;



import com.temproj.temporalProject.model.TicketBookingRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketBookingRepository extends MongoRepository<TicketBookingRequest, String> {
}

