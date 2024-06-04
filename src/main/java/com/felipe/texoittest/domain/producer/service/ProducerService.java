package com.felipe.texoittest.domain.producer.service;

import com.felipe.texoittest.domain.producer.entity.ProducerEntity;

import java.util.Comparator;
import java.util.List;

public class ProducerService {

    public static List<ProducerEntity> getTopMinIntervalWinner(List<ProducerEntity> producer) {
        int MIN_WINNERS = 1;
        return producer
                .stream()
                .sorted(Comparator.comparing(ProducerEntity::getMinInterval))
                .limit(MIN_WINNERS)
                .toList();
    }

    public static List<ProducerEntity> getTopMaxIntervalWinner(List<ProducerEntity> producer, List<ProducerEntity> producerMin) {
        int MAX_WINNERS = 1;
        return producer
                .stream()
                .filter(p -> !producerMin.contains(p))
                .sorted(Comparator.comparing(ProducerEntity::getMaxInterval).reversed())
                .limit(MAX_WINNERS)
                .sorted(Comparator.comparing(ProducerEntity::getMaxInterval))
                .toList();
    }
}
