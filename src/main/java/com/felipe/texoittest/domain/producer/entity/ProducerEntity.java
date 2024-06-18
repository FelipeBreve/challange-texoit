package com.felipe.texoittest.domain.producer.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import com.felipe.texoittest.domain.movie.entity.MovieEntity;
import com.felipe.texoittest.infrastructure.producer.model.ProducerModel;
import com.felipe.texoittest.shared.domain.AggregateRoot;

public class ProducerEntity implements AggregateRoot {
    private final String name;
    private final List<MovieEntity> movieEntities;
    private final int minInterval;
    private final int maxInterval;
    private final List<MovieEntity> minPreviousWin;
    private final List<MovieEntity> minFollowingWin;
    private final List<MovieEntity> maxPreviousWin;
    private final List<MovieEntity> maxFollowingWin;

    public ProducerEntity(ProducerModel model, List<MovieEntity> movieEntities) {
        this.name = model.getName();
        this.movieEntities = filterAndSortMoviesByYear(movieEntities);
        this.minPreviousWin = new ArrayList<>();
        this.minFollowingWin = new ArrayList<>();
        this.maxPreviousWin = new ArrayList<>();
        this.maxFollowingWin = new ArrayList<>();
        this.minInterval = calculateMinInterval();
        this.maxInterval = calculateMaxInterval();
        calculateIntervalMoviesAndPreviousAndFollowingWins();
    }

    public String getName() {
        return name;
    }

    public int getMinInterval() {
        return minInterval;
    }

    public int getMaxInterval() {
        return existsMaxInterval() ? maxInterval : minInterval;
    }

    public List<MovieEntity> getMinPreviousWin() {
        return minPreviousWin;
    }

    public List<MovieEntity> getMinFollowingWin() {
        return minFollowingWin;
    }

    public List<MovieEntity> getMaxPreviousWin() {
        return maxPreviousWin;
    }

    public List<MovieEntity> getMaxFollowingWin() {
        return maxFollowingWin;
    }

    private List<MovieEntity> filterAndSortMoviesByYear(List<MovieEntity> movies) {
        return movies.stream()
                .filter(MovieEntity::getWinner)
                .sorted(Comparator.comparingInt(MovieEntity::getYear))
                .toList();
    }

    private int calculateMinInterval() {
        return IntStream.range(1, movieEntities.size())
                .map(i -> movieEntities.get(i).getYear() - movieEntities.get(i - 1).getYear())
                .min()
                .orElse(Integer.MAX_VALUE);
    }

    private int calculateMaxInterval() {
        return IntStream.range(1, movieEntities.size())
                .map(i -> movieEntities.get(i).getYear() - movieEntities.get(i - 1).getYear())
                .max()
                .orElse(0);
    }

    private void calculateIntervalMoviesAndPreviousAndFollowingWins() {
        IntStream.range(1, movieEntities.size()).forEach(i -> {
            int interval = movieEntities.get(i).getYear() - movieEntities.get(i - 1).getYear();
            if (interval == minInterval) {
                minPreviousWin.add(movieEntities.get(i - 1));
                minFollowingWin.add(movieEntities.get(i));
            }
            if (interval == maxInterval) {
                maxPreviousWin.add(movieEntities.get(i - 1));
                maxFollowingWin.add(movieEntities.get(i));
            }
        });
    }

    private boolean existsMaxInterval() {
        return maxInterval != 0;
    }
}
