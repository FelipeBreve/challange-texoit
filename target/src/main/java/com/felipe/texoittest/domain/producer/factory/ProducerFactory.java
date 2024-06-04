package com.felipe.texoittest.domain.producer.factory;

import com.felipe.texoittest.domain.movie.entity.MovieEntity;
import com.felipe.texoittest.domain.movie.factory.MovieFactory;
import com.felipe.texoittest.domain.producer.entity.ProducerEntity;
import com.felipe.texoittest.infrastructure.producer.model.ProducerModel;

import java.util.List;

public class ProducerFactory {

    public static ProducerEntity createProducerWithMovies(ProducerModel model) {
        List<MovieEntity> movieEntities = model
                .getMovieModels()
                .stream()
                .map(MovieFactory::create)
                .toList();
        return new ProducerEntity(model, movieEntities);
    }

}
