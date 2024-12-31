package com.temproj.temporalProject.worker;



import com.temproj.temporalProject.activities.SeatActivityImpl;
import com.temproj.temporalProject.workflow.BookingWorkflowImpl;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class WorkerSetup {

    @Autowired
    private WorkerFactory workerFactory;

    @Autowired
    private SeatActivityImpl activities;

    @PostConstruct
    public void setupWorker() {
        Worker worker = workerFactory.newWorker("movieTicketTaskQueue");
        worker.registerWorkflowImplementationTypes(BookingWorkflowImpl.class);
        worker.registerActivitiesImplementations(activities);
    }
}
