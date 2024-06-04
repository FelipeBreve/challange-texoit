package com.felipe.texoittest.domain.movie.factory;

import com.felipe.texoittest.domain.movie.entity.MovieEntity;
import com.felipe.texoittest.infrastructure.movie.model.MovieModel;

public class MovieFactory {
    public static MovieEntity create(MovieModel movieModels) {
        return new MovieEntity(movieModels);
    }
}
