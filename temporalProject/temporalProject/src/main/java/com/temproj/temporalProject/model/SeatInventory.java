package com.temproj.temporalProject.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "seat_inventory")
public class SeatInventory {

    @Id
    private String seatId;
    private String status; // "A" for Available, "B" for Blocked, "R" for Reserved
    private String movieName;
    private String theatreName;

    // Constructors
    public SeatInventory() {
    }

    public SeatInventory(String seatId, String status, String movieName, String theatreName) {
        this.seatId = seatId;
        this.status = status;
        this.movieName = movieName;
        this.theatreName = theatreName;
    }

    // Getters and Setters
    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    @Override
    public String toString() {
        return "SeatInventory{" +
                "seatId='" + seatId + '\'' +
                ", status='" + status + '\'' +
                ", movieName='" + movieName + '\'' +
                ", theatreName='" + theatreName + '\'' +
                '}';
    }
}
