package com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OutputFindAllMoviesMinAndMaxInterval {

    @JsonProperty("min")
    private final List<AttributesMinAndMaxInterval> min;
    @JsonProperty("max")
    private final List<AttributesMinAndMaxInterval> max;

    public OutputFindAllMoviesMinAndMaxInterval(List<AttributesMinAndMaxInterval> min, List<AttributesMinAndMaxInterval> max) {
        this.min = min;
        this.max = max;
    }

    public List<AttributesMinAndMaxInterval> getMin() {
        return min;
    }

    public List<AttributesMinAndMaxInterval> getMax() {
        return max;
    }
}
