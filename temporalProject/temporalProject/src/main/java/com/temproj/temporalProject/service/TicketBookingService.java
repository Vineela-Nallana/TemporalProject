package com.temproj.temporalProject.service;



import com.temproj.temporalProject.model.BookingStatus;
import com.temproj.temporalProject.model.TicketBookingRequest;
import com.temproj.temporalProject.repository.TicketBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketBookingService {

    @Autowired
    private TicketBookingRepository ticketBookingRepository;

    public BookingStatus saveBooking(TicketBookingRequest request) {
        ticketBookingRepository.save(request);
        return new BookingStatus("Booking saved successfully!", true);
    }
}

