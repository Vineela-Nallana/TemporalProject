package com.temproj.temporalProject.repository;



import com.temproj.temporalProject.model.SeatInventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatInventoryRepository extends MongoRepository<SeatInventory, String> {

    List<SeatInventory> findByMovieNameAndTheatreName(String movieName, String theatreName);

    List<SeatInventory> findByMovieNameAndTheatreNameAndSeatIdIn(String movieName, String theatreName, List<String> seatIds);
}

