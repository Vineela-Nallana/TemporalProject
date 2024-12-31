package com.temproj.temporalProject.controller;

import com.temproj.temporalProject.util.SeatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatUtil seatUtil;

    @GetMapping("/{movieName}/{theatreName}")
    public String getSeatArrangement(@PathVariable String movieName, @PathVariable String theatreName) {
        return seatUtil.displaySeats(movieName, theatreName);
    }
}
