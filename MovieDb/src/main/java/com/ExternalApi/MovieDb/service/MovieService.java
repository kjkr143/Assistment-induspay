package com.ExternalApi.MovieDb.service;

import com.ExternalApi.MovieDb.model.Movie;
import com.ExternalApi.MovieDb.repository.MovieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class MovieService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MovieRepository movieRepository;

    public void fetchAndStoreMovies() {
        String apiKey = "f5b75b7ba769e99e610b321acf704633";
        String apiUrl = "https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey;

        // Make an HTTP GET request to the external API
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        // Check if the response is successful (status code 200)
        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();

            // Parse the JSON response and extract movie details
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode root = objectMapper.readTree(responseBody);
                JsonNode resultsNode = root.path("results");

                // Iterate through the results array
                for (JsonNode movieNode : resultsNode) {
                    String title = movieNode.path("title").asText();
                    String overview = movieNode.path("overview").asText();
                    LocalDate releaseDate = LocalDate.parse(movieNode.path("release_date").asText());

                    // Create a new Movie object and save it to the database
                    Movie movie = new Movie();
                    movie.setTitle(title);
                    movie.setOverview(overview);
                    movie.setReleaseDate(releaseDate);

                    movieRepository.save(movie);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}