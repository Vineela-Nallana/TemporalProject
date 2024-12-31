package com.temproj.temporalProject.service;


import com.temproj.temporalProject.model.SeatInventory;
import com.temproj.temporalProject.repository.SeatInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatInventoryRepository seatInventoryRepository;

    public List<SeatInventory> getSeatInventory(String movieName, String theatreName) {
        return seatInventoryRepository.findByMovieNameAndTheatreName(movieName, theatreName);
    }

    public void updateSeatStatus(String movieName, String theatreName, List<String> seatIds, String status) {
        List<SeatInventory> seats = seatInventoryRepository.findByMovieNameAndTheatreNameAndSeatIdIn(movieName, theatreName, seatIds);
        seats.forEach(seat -> seat.setStatus(status));
        seatInventoryRepository.saveAll(seats);
    }
}
