package com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.dto.factory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.felipe.texoittest.domain.producer.entity.ProducerEntity;
import com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.dto.AttributesMinAndMaxInterval;
import com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.dto.OutputFindAllMoviesMinAndMaxInterval;

public class OutputFindAllMoviesMinAndMaxIntervalFactory {

    public static OutputFindAllMoviesMinAndMaxInterval create(List<ProducerEntity> producerEntitiesMin, List<ProducerEntity> producerEntitiesMax) {
        List<AttributesMinAndMaxInterval> max = producerEntitiesMax.stream()
                .flatMap(producerEntity -> IntStream.range(0, producerEntity.getMaxPreviousWin().size())
                        .mapToObj(i -> new AttributesMinAndMaxInterval(
                                producerEntity.getName(),
                                producerEntity.getMaxInterval(),
                                producerEntity.getMaxPreviousWin().get(i).getYear(),
                                producerEntity.getMaxFollowingWin().get(i).getYear()
                        ))
                )
                .collect(Collectors.toList());

        List<AttributesMinAndMaxInterval> min = producerEntitiesMin.stream()
                .flatMap(producerEntity -> IntStream.range(0, producerEntity.getMinPreviousWin().size())
                        .mapToObj(i -> new AttributesMinAndMaxInterval(
                                producerEntity.getName(),
                                producerEntity.getMinInterval(),
                                producerEntity.getMinPreviousWin().get(i).getYear(),
                                producerEntity.getMinFollowingWin().get(i).getYear()
                        ))
                )
                .collect(Collectors.toList());

        return new OutputFindAllMoviesMinAndMaxInterval(min, max);
    }
}
