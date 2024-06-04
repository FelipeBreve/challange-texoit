package com.felipe.texoittest.infrastructure.movie.model;

import com.felipe.texoittest.infrastructure.producer.model.ProducerModel;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movie")
public class MovieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    //Devido ao problema com o nome
    @Column(name = "\"YEAR\"")
    private Integer year;

    private String studios;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "movie_producer",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id")
    )
    private Set<ProducerModel> producerModels;

    private Boolean winner;

    public MovieModel() {
    }

    public MovieModel(String title, Integer year, String studios, Boolean winner) {
        this.title = title;
        this.year = year;
        this.studios = studios;
        this.winner = winner;
        this.producerModels = new HashSet<>();
    }

    public List<MovieModel> getMovies() {
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getStudios() {
        return studios;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    public Set<ProducerModel> getProducerModels() {
        return producerModels;
    }

    public void setProducerModels(Set<ProducerModel> producerModels) {
        this.producerModels = producerModels;
    }

    public void addProducer(ProducerModel producerModel) {
        this.producerModels.add(producerModel);
    }
}