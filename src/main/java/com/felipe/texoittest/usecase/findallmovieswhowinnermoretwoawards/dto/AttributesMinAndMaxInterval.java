package com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributesMinAndMaxInterval {
    @JsonProperty("producer")
    private String producer;
    @JsonProperty("interval")
    private Integer interval;
    @JsonProperty("previousWin")
    private Integer previousWin;
    @JsonProperty("followingWin")
    private Integer followingWin;

    public AttributesMinAndMaxInterval(String producer, Integer interval, Integer previousWin, Integer followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(Integer previousWin) {
        this.previousWin = previousWin;
    }

    public Integer getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Integer followingWin) {
        this.followingWin = followingWin;
    }
}
