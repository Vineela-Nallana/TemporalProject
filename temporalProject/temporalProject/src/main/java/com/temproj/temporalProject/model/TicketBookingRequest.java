package com.temproj.temporalProject.model;



import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "ticket_inventory")
public class TicketBookingRequest {

    private String movieName;
    private String theatreName;
    private List<String> seatNumbers;
    private String userEmail;

    // Constructors
    public TicketBookingRequest() {
    }

    public TicketBookingRequest(String movieName, String theatreName, List<String> seatNumbers, String userEmail) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumbers = seatNumbers;
        this.userEmail = userEmail;
    }

    // Getters and Setters
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public List<String> getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(List<String> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "TicketBookingRequest{" +
                "movieName='" + movieName + '\'' +
                ", theatreName='" + theatreName + '\'' +
                ", seatNumbers=" + seatNumbers +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}

