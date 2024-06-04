package com.felipe.texoittest.domain.movie.entity;

import com.felipe.texoittest.infrastructure.movie.model.MovieModel;

public class MovieEntity {

    private final Integer year;

    private final Boolean winner;

    public MovieEntity(MovieModel movieModels) {
        this.year = movieModels.getYear();
        this.winner = movieModels.getWinner();
    }

    public Integer getYear() {
        return year;
    }

    public Boolean getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                ", year=" + year +
                ", winner=" + winner +
                '}';
    }
}
