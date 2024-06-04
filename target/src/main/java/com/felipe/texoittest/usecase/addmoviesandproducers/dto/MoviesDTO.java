package com.felipe.texoittest.usecase.addmoviesandproducers.dto;

import com.opencsv.bean.CsvBindByName;

public class MoviesDTO {

    @CsvBindByName(column = "year")
    private String year;
    @CsvBindByName(column = "title")
    private String title;
    @CsvBindByName(column = "studios")
    private String studios;
    @CsvBindByName(column = "producers")
    private String producers;
    @CsvBindByName(column = "winner")
    private String winner;
    public MoviesDTO() {
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudios() {
        return studios;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}