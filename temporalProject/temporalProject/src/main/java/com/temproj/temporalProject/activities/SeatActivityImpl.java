package com.temproj.temporalProject.activities;



import com.temproj.temporalProject.model.TicketBookingRequest;
import com.temproj.temporalProject.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
@Component
public class SeatActivityImpl implements SeatActivity {

    @Autowired
    private SeatService seatService;

    @Override
    public void blockSeats(TicketBookingRequest request) {
        seatService.updateSeatStatus(request.getMovieName(), request.getTheatreName(), request.getSeatNumbers(), "B");
    }

    @Override
    public boolean processPayment(TicketBookingRequest request) {
        // Simulate payment success or failure
        return Math.random() > 0.5; // 50% success rate
    }

    @Override
    public void updateSeatStatus(TicketBookingRequest request, String status) {
        seatService.updateSeatStatus(request.getMovieName(), request.getTheatreName(), request.getSeatNumbers(), status);
    }
}
