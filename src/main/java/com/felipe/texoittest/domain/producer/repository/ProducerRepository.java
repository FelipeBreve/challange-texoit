package com.felipe.texoittest.domain.producer.repository;

import com.felipe.texoittest.infrastructure.producer.model.ProducerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProducerRepository extends JpaRepository<ProducerModel, Integer> {
    ProducerModel findByName(String name);

    @Query(value = """
            select DISTINCT producer.* from producer
              where producer.id in (
                SELECT movie_producer.producer_id FROM MOVIE_PRODUCER
                JOIN movie on movie.id = movie_producer.movie_id
                and movie.winner = true
                GROUP BY MOVIE_PRODUCER.producer_id
                HAVING count(MOVIE_PRODUCER.producer_id) > 1
              )
            """, nativeQuery = true)
    List<ProducerModel> findAllProducerWhoWinnerMoreTwoMovies();
}
