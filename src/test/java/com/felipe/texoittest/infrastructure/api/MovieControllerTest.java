package com.felipe.texoittest.infrastructure.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.texoittest.domain.movie.repository.MovieRepository;
import com.felipe.texoittest.domain.producer.repository.ProducerRepository;
import com.felipe.texoittest.infrastructure.movie.model.MovieModel;
import com.felipe.texoittest.infrastructure.producer.model.ProducerModel;

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
                .andExpect(jsonPath("$.min[1].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.min[1].interval").value(1))
                .andExpect(jsonPath("$.min[1].previousWin").value(2002))
                .andExpect(jsonPath("$.min[1].followingWin").value(2003))
                .andExpect(jsonPath("$.max[0].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.max[0].interval").value(22))
                .andExpect(jsonPath("$.max[0].previousWin").value(1980))
                .andExpect(jsonPath("$.max[0].followingWin").value(2002))
                .andExpect(jsonPath("$.max[1].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.max[1].interval").value(22))
                .andExpect(jsonPath("$.max[1].previousWin").value(2015))
                .andExpect(jsonPath("$.max[1].followingWin").value(2037));
    }

    @Test
    public void testFindAllMoviesCreatingMoreProducerWithAwardMinInterval() throws Exception {
        MovieModel movieModel = movieRepository.save(new MovieModel("Filme 1", 1985, "Studio de teste", true));
        ProducerModel producerModel = producerRepository.findById(28).orElse(null);
        movieModel.getProducerModels().add(producerModel);
        assert producerModel != null;
        producerModel.getMovieModels().add(movieModel);
        movieRepository.save(movieModel);

        mockMvc.perform(get("/movies/ranking-worst-awards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.min[0].producer").value("Bo Derek"))
                .andExpect(jsonPath("$.min[0].interval").value(1))
                .andExpect(jsonPath("$.min[0].previousWin").value(1984))
                .andExpect(jsonPath("$.min[0].followingWin").value(1985))
                .andExpect(jsonPath("$.min[1].producer").value("Joel Silver"))
                .andExpect(jsonPath("$.min[1].interval").value(1))
                .andExpect(jsonPath("$.min[1].previousWin").value(1990))
                .andExpect(jsonPath("$.min[1].followingWin").value(1991))
                .andExpect(jsonPath("$.min[2].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.min[2].interval").value(1))
                .andExpect(jsonPath("$.min[2].previousWin").value(2002))
                .andExpect(jsonPath("$.min[2].followingWin").value(2003));

    }

    @Test
    public void testFindAllMoviesCreatingMoreProducerWithAwardMaxInterval() throws Exception {
        MovieModel movieModel = movieRepository.save(new MovieModel("Filme 1", 2012, "Studio de teste", true));
        ProducerModel producerModel = producerRepository.findById(28).orElse(null);
        movieModel.getProducerModels().add(producerModel);
        assert producerModel != null;
        producerModel.getMovieModels().add(movieModel);
        movieRepository.save(movieModel);

        mockMvc.perform(get("/movies/ranking-worst-awards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.max[0].producer").value("Bo Derek"))
                .andExpect(jsonPath("$.max[0].interval").value(22))
                .andExpect(jsonPath("$.max[0].previousWin").value(1990))
                .andExpect(jsonPath("$.max[0].followingWin").value(2012))
                .andExpect(jsonPath("$.max[1].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.max[1].interval").value(22))
                .andExpect(jsonPath("$.max[1].previousWin").value(1980))
                .andExpect(jsonPath("$.max[1].followingWin").value(2002))
                .andExpect(jsonPath("$.max[2].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.max[2].interval").value(22))
                .andExpect(jsonPath("$.max[2].previousWin").value(2015))
                .andExpect(jsonPath("$.max[2].followingWin").value(2037));

    }

}
