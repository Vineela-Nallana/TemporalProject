package com.temproj.temporalProject.service;



import com.temproj.temporalProject.model.TicketBookingRequest;
import com.temproj.temporalProject.workflow.BookingWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WorkflowService {

    @Autowired
    private final  WorkflowClient workflowClient;


    public WorkflowService(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }
    public String startBookingWorkflow(TicketBookingRequest request) {
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue("movieTicketTaskQueue")
                .setWorkflowId(UUID.randomUUID().toString())
                .build();

        BookingWorkflow workflow = workflowClient.newWorkflowStub(BookingWorkflow.class, options);
         return workflow.startBooking(request);

//        return "Workflow started with ID: " + options.getWorkflowId();
    }
}
