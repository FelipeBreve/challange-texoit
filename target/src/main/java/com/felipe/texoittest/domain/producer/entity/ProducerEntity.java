package com.felipe.texoittest.domain.producer.entity;

import com.felipe.texoittest.domain.movie.entity.MovieEntity;
import com.felipe.texoittest.infrastructure.producer.model.ProducerModel;
import com.felipe.texoittest.shared.domain.AggregateRoot;

import java.util.Comparator;
import java.util.List;

public class ProducerEntity implements AggregateRoot {
    private final String name;
    private final List<MovieEntity> movieEntities;
    private int minInterval = Integer.MAX_VALUE;
    private int maxInterval = 0;
    private MovieEntity minPreviousWin;
    private MovieEntity minFollowingWin;
    private MovieEntity maxPreviousWin;
    private MovieEntity maxFollowingWin;

    public ProducerEntity(ProducerModel model, List<MovieEntity> movieEntities) {
        this.name = model.getName();
        this.movieEntities = filterAndSortMoviesByYear(movieEntities);
        calculateIntervalMoviesAndPreviousAndFollowingWins();
    }

    private List<MovieEntity> filterAndSortMoviesByYear(List<MovieEntity> movies) {
        return movies.stream()
                .filter(MovieEntity::getWinner)
                .sorted(Comparator.comparingInt(MovieEntity::getYear))
                .toList();
    }

    public void calculateIntervalMoviesAndPreviousAndFollowingWins() {
        for (int i = 1; i < movieEntities.size(); i++) {
            int intervalCurrent = movieEntities.get(i).getYear() - movieEntities.get(i - 1).getYear();
            if (intervalCurrent < minInterval) {
                minPreviousWin = movieEntities.get(i - 1);
                minFollowingWin = movieEntities.get(i);
                minInterval = intervalCurrent;
            } else {
                maxInterval = intervalCurrent;
                maxPreviousWin = movieEntities.get(i - 1);
                maxFollowingWin = movieEntities.get(i);
            }
        }
    }

    private boolean existsMaxInterval() {
        return maxInterval != 0;
    }

    public String getName() {
        return name;
    }

    public MovieEntity getMinPreviousWin() {
        return minPreviousWin;
    }

    public MovieEntity getMinFollowingWin() {
        return minFollowingWin;
    }

    public MovieEntity getMaxPreviousWin() {
        return existsMaxInterval() ? maxPreviousWin : minPreviousWin;
    }

    public MovieEntity getMaxFollowingWin() {
        return existsMaxInterval() ? maxFollowingWin : minFollowingWin;
    }

    public int getMinInterval() {
        return minInterval;
    }

    public int getMaxInterval() {
        return existsMaxInterval() ? maxInterval : minInterval;
    }
}
