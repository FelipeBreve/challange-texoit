package com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.dto.factory;

import com.felipe.texoittest.domain.producer.entity.ProducerEntity;
import com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.dto.AttributesMinAndMaxInterval;
import com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.dto.OutputFindAllMoviesMinAndMaxInterval;

import java.util.List;

public class OutputFindAllMoviesMinAndMaxIntervalFactory {

    public static OutputFindAllMoviesMinAndMaxInterval create(List<ProducerEntity> producerEntitiesMin, List<ProducerEntity> producerEntitiesMax) {
        List<AttributesMinAndMaxInterval> min = producerEntitiesMin
                .stream()
                .map(producerEntity -> new AttributesMinAndMaxInterval(producerEntity.getName(), producerEntity.getMinInterval(), producerEntity.getMinPreviousWin().getYear(), producerEntity.getMinFollowingWin().getYear()))
                .toList();

        List<AttributesMinAndMaxInterval> max = producerEntitiesMax
                .stream()
                .map(producerEntity -> new AttributesMinAndMaxInterval(producerEntity.getName(), producerEntity.getMaxInterval(), producerEntity.getMaxPreviousWin().getYear(), producerEntity.getMaxFollowingWin().getYear()))
                .toList();

        return new OutputFindAllMoviesMinAndMaxInterval(min, max);
    }
}
