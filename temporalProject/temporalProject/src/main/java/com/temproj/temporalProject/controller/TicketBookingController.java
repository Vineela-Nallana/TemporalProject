package com.temproj.temporalProject.controller;



import com.temproj.temporalProject.model.BookingStatus;
import com.temproj.temporalProject.model.TicketBookingRequest;
import com.temproj.temporalProject.service.SeatService;
import com.temproj.temporalProject.service.TicketBookingService;
import com.temproj.temporalProject.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class TicketBookingController {

    @Autowired
    private TicketBookingService ticketBookingService;

    @Autowired
    private WorkflowService workflowService;

    //adding
    @Autowired
    private SeatService seatService;

    @PostMapping
    public BookingStatus bookTickets(@RequestBody TicketBookingRequest request) {
        seatService.updateSeatStatus(request.getMovieName(), request.getTheatreName(), request.getSeatNumbers(), "B");
        String workflowId = workflowService.startBookingWorkflow(request);
        ticketBookingService.saveBooking(request);
        return new BookingStatus("Workflow started. Workflow ID: " + workflowId, true);
    }
}

