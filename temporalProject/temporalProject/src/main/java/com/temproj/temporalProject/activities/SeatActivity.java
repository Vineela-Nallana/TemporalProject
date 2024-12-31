package com.temproj.temporalProject.activities;



import com.temproj.temporalProject.model.TicketBookingRequest;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import org.springframework.stereotype.Component;

@Component
@ActivityInterface
public interface SeatActivity {
@ActivityMethod
    void blockSeats(TicketBookingRequest request);
    @ActivityMethod
    boolean processPayment(TicketBookingRequest request);
    @ActivityMethod
    void updateSeatStatus(TicketBookingRequest request, String status);
}

