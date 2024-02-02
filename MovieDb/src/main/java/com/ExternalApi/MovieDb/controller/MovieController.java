package com.ExternalApi.MovieDb.controller;

import com.ExternalApi.MovieDb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchAndStoreMovies() {
        // Assuming you have a service layer to handle business logic
        movieService.fetchAndStoreMovies();
        return ResponseEntity.ok("Movies fetched and stored successfully.");
    }


}
