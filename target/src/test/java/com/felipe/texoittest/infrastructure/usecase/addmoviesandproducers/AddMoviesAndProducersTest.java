package com.felipe.texoittest.infrastructure.usecase.addmoviesandproducers;

import com.felipe.texoittest.domain.movie.repository.MovieRepository;
import com.felipe.texoittest.domain.producer.repository.ProducerRepository;
import com.felipe.texoittest.infrastructure.movie.model.MovieModel;
import com.felipe.texoittest.infrastructure.producer.model.ProducerModel;
import com.felipe.texoittest.shared.exception.ListEmptyToProcessException;
import com.felipe.texoittest.usecase.addmoviesandproducers.AddMoviesAndProducers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class AddMoviesAndProducersTest {

    @Autowired
    private AddMoviesAndProducers addMoviesAndProducers;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Test
    void testAddMovieAndProducerCsvWithSuccess() throws ListEmptyToProcessException {
        addMoviesAndProducers.execute();
        List<MovieModel> movieModels = movieRepository.findAll();
        List<ProducerModel> producerModels = producerRepository.findAll();

        // Pois é feito a insercao duas vezes, apenas para teste
        assertEquals(412, movieModels.size());
        assertEquals(360, producerModels.size());
    }

}
