package com.felipe.texoittest.infrastructure.producer.model;

import com.felipe.texoittest.infrastructure.movie.model.MovieModel;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "producer")
public class ProducerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "producerModels")
    private Set<MovieModel> movieModels;

    public ProducerModel() {
    }

    public ProducerModel(String name) {
        this.name = name;
        this.movieModels = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MovieModel> getMovieModels() {
        return movieModels;
    }

    public void setMovieModels(Set<MovieModel> movieModels) {
        this.movieModels = movieModels;
    }
}