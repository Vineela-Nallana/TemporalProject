//package com.temproj.temporalProject.util;
//
//
//
//import com.temproj.temporalProject.model.SeatInventory;
//import com.temproj.temporalProject.repository.SeatInventoryRepository;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class SeatUtil {
//
//    private final SeatInventoryRepository repository;
//
//    public SeatUtil(SeatInventoryRepository repository) {
//        this.repository = repository;
//    }
//
//    public String displaySeats(String movieName, String theatreName) {
//        List<SeatInventory> seats = repository.findByMovieNameAndTheatreName(movieName, theatreName);
//
//        if (seats.isEmpty()) {
//            return "No seat inventory available for the specified movie and theatre.";
//        }
//
//        StringBuilder display = new StringBuilder("Seat Arrangement:\n");
//        char currentRow = 'A';
//        for (SeatInventory seat : seats) {
//            char row = seat.getSeatId().charAt(0);
//            if (row != currentRow) {
//                display.append("\n"); // Start a new row
//                currentRow = row;
//            }
//            display.append(seat.getStatus()).append(" "); // Append seat status (A/R)
//        }
//        return display.toString();
//    }
//}
package com.temproj.temporalProject.util;

import com.temproj.temporalProject.model.SeatInventory;
import com.temproj.temporalProject.repository.SeatInventoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeatUtil {

    private final SeatInventoryRepository repository;

    public SeatUtil(SeatInventoryRepository repository) {
        this.repository = repository;
    }

    public String displaySeats(String movieName, String theatreName) {
        List<SeatInventory> seats = repository.findByMovieNameAndTheatreName(movieName, theatreName);

        if (seats.isEmpty()) {
            return "No seat inventory available for the specified movie and theatre.";
        }

        // Define the seat arrangement in a grid (e.g., A1 to L10)
        int rows = 12; // A to L
        int cols = 10; // 1 to 10
        char startRow = 'A';

        // Initialize a 2D array for seat arrangement
        String[][] seatGrid = new String[rows][cols];

        // Fill the 2D array with seat status from inventory
        for (SeatInventory seat : seats) {
            String seatId = seat.getSeatId();
            char row = seatId.charAt(0); // Extract the row character (e.g., 'A')
            int col = Integer.parseInt(seatId.substring(1)) - 1; // Extract column index (e.g., 1 -> 0)
            int rowIndex = row - startRow; // Convert row character to index (A -> 0, B -> 1, ...)

            if (rowIndex >= 0 && rowIndex < rows && col >= 0 && col < cols) {
                seatGrid[rowIndex][col] = seat.getStatus(); // Set the seat status (A/R/B)
            }
        }

        // Build a 2D display string
        StringBuilder display = new StringBuilder("Seat Arrangement:\n");
        for (int i = 0; i < rows; i++) {
            display.append((char) (startRow + i)).append(": "); // Add row label (A, B, ...)
            for (int j = 0; j < cols; j++) {
                display.append(seatGrid[i][j] != null ? seatGrid[i][j] : "A").append(" "); // Default to "A" if null
            }
            display.append("\n");
        }

        return display.toString();
    }
}
