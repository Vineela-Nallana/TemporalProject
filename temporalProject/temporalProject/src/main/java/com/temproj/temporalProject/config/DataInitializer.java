package com.temproj.temporalProject.config;



import com.temproj.temporalProject.model.SeatInventory;
import com.temproj.temporalProject.repository.SeatInventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * DataInitializer is responsible for populating the SeatInventory collection
 * with initial data for a specific movie and theatre if the collection is empty.
 */
@Component
public class DataInitializer {

    @Bean
    public CommandLineRunner initSeats(SeatInventoryRepository repository) {
        return args -> {
            // Only initialize seats if the repository is empty
            if (repository.count() == 0) {
                String movieName = "Inception"; // Specify the movie
                String theatreName = "PVR Koramangala"; // Specify the theatre

                List<SeatInventory> seats = new ArrayList<>();
                for (char row = 'A'; row <= 'L'; row++) {
                    for (int col = 1; col <= 10; col++) {
                        seats.add(new SeatInventory(
                                row + String.valueOf(col), // Seat ID, e.g., A1, B2
                                "A",                      // Status: Available
                                movieName,                // Movie Name
                                theatreName               // Theatre Name
                        ));
                    }
                }

                repository.saveAll(seats); // Save to MongoDB
                System.out.println("Initialized seat inventory for movie: " + movieName + ", theatre: " + theatreName);
            }
        };
    }
}

