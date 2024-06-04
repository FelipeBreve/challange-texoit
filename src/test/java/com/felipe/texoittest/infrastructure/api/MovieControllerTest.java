package com.felipe.texoittest.infrastructure.api;

import com.felipe.texoittest.domain.movie.repository.MovieRepository;
import com.felipe.texoittest.domain.producer.repository.ProducerRepository;
import com.felipe.texoittest.infrastructure.movie.model.MovieModel;
import com.felipe.texoittest.infrastructure.producer.model.ProducerModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Transactional
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Test
    public void testFindAllMovies() throws Exception {
        mockMvc.perform(get("/movies/ranking-worst-awards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.min[0].producer").value("Joel Silver"))
                .andExpect(jsonPath("$.min[0].interval").value(1))
                .andExpect(jsonPath("$.min[0].previousWin").value(1990))
                .andExpect(jsonPath("$.min[0].followingWin").value(1991))
                .andExpect(jsonPath("$.max[0].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.max[0].interval").value(13))
                .andExpect(jsonPath("$.max[0].previousWin").value(2002))
                .andExpect(jsonPath("$.max[0].followingWin").value(2015));
    }

    @Test
    public void testFindAllMoviesWhoWinnerMoreThreeTimes() throws Exception {
        mockMvc.perform(get("/movies/ranking-worst-awards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.min[0].producer").value("Joel Silver"))
                .andExpect(jsonPath("$.min[0].interval").value(1))
                .andExpect(jsonPath("$.min[0].previousWin").value(1990))
                .andExpect(jsonPath("$.min[0].followingWin").value(1991))
                .andExpect(jsonPath("$.max[0].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.max[0].interval").value(13))
                .andExpect(jsonPath("$.max[0].previousWin").value(2002))
                .andExpect(jsonPath("$.max[0].followingWin").value(2015));

        MovieModel movieModel = movieRepository.save(new MovieModel("Filme 1", 2030, "Studio de teste", true));
        ProducerModel producerModel = producerRepository.findById(152).get();
        movieModel.getProducerModels().add(producerModel);
        producerModel.getMovieModels().add(movieModel);
        movieRepository.save(movieModel);


        mockMvc.perform(get("/movies/ranking-worst-awards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.min[0].producer").value("Joel Silver"))
                .andExpect(jsonPath("$.min[0].interval").value(1))
                .andExpect(jsonPath("$.min[0].previousWin").value(1990))
                .andExpect(jsonPath("$.min[0].followingWin").value(1991))
                .andExpect(jsonPath("$.max[0].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.max[0].interval").value(15))
                .andExpect(jsonPath("$.max[0].previousWin").value(2015))
                .andExpect(jsonPath("$.max[0].followingWin").value(2030));
    }

}
