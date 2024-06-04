package com.felipe.texoittest.usecase.addmoviesandproducers;

import com.felipe.texoittest.infrastructure.movie.model.MovieModel;
import com.felipe.texoittest.domain.movie.repository.MovieRepository;
import com.felipe.texoittest.infrastructure.producer.model.ProducerModel;
import com.felipe.texoittest.domain.producer.repository.ProducerRepository;
import com.felipe.texoittest.shared.exception.ListEmptyToProcessException;
import com.felipe.texoittest.shared.util.CSVReaderUtils;
import com.felipe.texoittest.usecase.addmoviesandproducers.dto.MoviesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AddMoviesAndProducers {

    public static final Logger LOGGER = LoggerFactory.getLogger(AddMoviesAndProducers.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Value("${csv.file.path}")
    private String CSV_NAME_PATH;

    @Transactional
    public void execute() throws ListEmptyToProcessException {
        LOGGER.info("Start use-case AddMoviesAndProducers");
        List<MoviesDTO> list = CSVReaderUtils.readCsv(MoviesDTO.class, CSV_NAME_PATH);

        if (list.isEmpty()) {
            LOGGER.error("Empty list of movies");
            throw new ListEmptyToProcessException("Empty list of movies");
        }

        for (MoviesDTO moviesDTO : list) {
            MovieModel movieModel = createMovieModel(moviesDTO);

            Set<ProducerModel> producerModels = attachOrCreateProducers(separatingProducer(moviesDTO.getProducers()), movieModel);
            movieModel.setProducerModels(producerModels);

            movieRepository.save(movieModel);
        }
        LOGGER.info("Finish use-case AddMoviesAndProducers");
    }

    private Set<String> separatingProducer(String producers) {
        String REGEX = ", | and ";
        return new HashSet<>(List.of(producers.split(REGEX)));
    }

    private Set<ProducerModel> attachOrCreateProducers(Set<String> producers, MovieModel movieModel) {
        Set<ProducerModel> producerModels = new HashSet<>();

        for (String producerName : producers) {
            ProducerModel producerModel = producerRepository.findByName(producerName);
            if (producerModel == null) {
                producerModel = new ProducerModel(producerName);
                producerRepository.save(producerModel);
            }
            producerModel.getMovieModels().add(movieModel);
            producerModels.add(producerModel);
        }

        return producerModels;
    }

    private MovieModel createMovieModel(MoviesDTO moviesDTO) {
        String WINNER = "yes";
        return new MovieModel(
                moviesDTO.getTitle(),
                Integer.parseInt(moviesDTO.getYear()),
                moviesDTO.getStudios(),
                WINNER.equals(moviesDTO.getWinner())
        );
    }
}
