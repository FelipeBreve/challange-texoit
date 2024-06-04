package com.felipe.texoittest.domain.movie.repository;

import com.felipe.texoittest.infrastructure.movie.model.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieModel, Integer> {
}
