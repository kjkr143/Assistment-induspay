package com.ExternalApi.MovieDb.repository;

import com.ExternalApi.MovieDb.model.Movie;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
