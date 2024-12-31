package com.temproj.temporalProject.workflow;



import com.temproj.temporalProject.model.TicketBookingRequest;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface BookingWorkflow {

    @WorkflowMethod
    String startBooking(TicketBookingRequest request);
}
