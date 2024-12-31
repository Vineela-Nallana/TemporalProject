package com.temproj.temporalProject.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ticket_inventory")
public class BookingStatus {

    private String message;
    private boolean success;

    // Constructors
    public BookingStatus() {
    }

    public BookingStatus(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "BookingStatus{" +
                "message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
