//package com.temproj.temporalProject.workflow;
//
//
//
//import com.temproj.temporalProject.activities.SeatActivity;
//import com.temproj.temporalProject.model.TicketBookingRequest;
//import io.temporal.activity.ActivityOptions;
//import io.temporal.workflow.Workflow;
//
//import java.time.Duration;
//
//public class BookingWorkflowImpl implements BookingWorkflow {
//
//    private final SeatActivity seatActivity;
//
//    public BookingWorkflowImpl() {
//        ActivityOptions activityOptions = ActivityOptions.newBuilder()
//                .setStartToCloseTimeout(Duration.ofSeconds(30))
//                .build();
//
//        this.seatActivity = Workflow.newActivityStub(SeatActivity.class, activityOptions);
//    }
////
//    @Override
//    public void startBooking(TicketBookingRequest request) {
//        try {
//            seatActivity.blockSeats(request);
//            boolean paymentSuccess = seatActivity.processPayment(request);
//
//            if (paymentSuccess) {
//                seatActivity.updateSeatStatus(request, "R"); // Reserved
//            } else {
//                seatActivity.updateSeatStatus(request, "A"); // Available (compensation)
//            }
//        } catch (Exception e) {
//            seatActivity.updateSeatStatus(request, "A"); // Fallback
//            throw Workflow.wrap(e);
//        }
//    }
//}
package com.temproj.temporalProject.workflow;

import com.temproj.temporalProject.activities.SeatActivity;
import com.temproj.temporalProject.model.TicketBookingRequest;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class BookingWorkflowImpl implements BookingWorkflow {

    private final SeatActivity seatActivity;

    public BookingWorkflowImpl() {
        RetryOptions retryOptions = RetryOptions.newBuilder()
                .setMaximumAttempts(3) // Retry up to 3 times
                .setInitialInterval(Duration.ofSeconds(5))
                .setBackoffCoefficient(2.0)
                .setMaximumInterval(Duration.ofSeconds(20))
                .build();

        ActivityOptions activityOptions = ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofMinutes(1)) // Activity timeout
                .setRetryOptions(retryOptions) // Retry on failure
                .build();

        this.seatActivity = Workflow.newActivityStub(SeatActivity.class, activityOptions);
    }

    @Override
    public String startBooking(TicketBookingRequest request) {
        try {
            // Step 1: Block seats (set status to "B")
            seatActivity.blockSeats(request);

            // Step 2: Process payment
            boolean paymentSuccess = seatActivity.processPayment(request);

            // Step 3: Update seat status based on payment outcome
            if (paymentSuccess) {
                seatActivity.updateSeatStatus(request, "R");
                return "Booking Confirmed";// Reserved
            } else {
                seatActivity.updateSeatStatus(request, "A");
                return "Booking Failed Due to Payment Failure";// Available (compensation)
            }
        } catch (Exception e) {
            // In case of any unexpected errors, ensure seats are reverted
            seatActivity.updateSeatStatus(request, "A"); // Fallback to "Available"
//            throw Workflow.wrap(e); // Rethrow the exception for proper logging/tracking
            return "Booking Failed Due to System Error";
        }
    }
}
