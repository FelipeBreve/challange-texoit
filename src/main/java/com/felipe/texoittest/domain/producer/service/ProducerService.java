package com.felipe.texoittest.domain.producer.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.felipe.texoittest.domain.producer.entity.ProducerEntity;

public class ProducerService {

    public static List<ProducerEntity> getAllTopMinIntervalWinner(List<ProducerEntity> producer) {
        Map<Integer, List<ProducerEntity>> producerEntityGroupedMin = producer
                .stream()
                .collect(Collectors.groupingBy(ProducerEntity::getMinInterval, TreeMap::new, Collectors.toList()));

        Integer minInterval = producerEntityGroupedMin.keySet().stream().findFirst().orElse(0);

        return producerEntityGroupedMin.get(minInterval)
                .stream()
                .toList();
    }

    public static List<ProducerEntity> getAllTopMaxIntervalWinner(List<ProducerEntity> producer, List<ProducerEntity> producerMin) {
        Map<Integer, List<ProducerEntity>> producerEntityGroupedMax = producer
                .stream()
                .collect(Collectors.groupingBy(ProducerEntity::getMaxInterval, TreeMap::new, Collectors.toList()));

        Integer maxInterval = producerEntityGroupedMax.keySet().stream().max(Comparator.naturalOrder()).orElse(0);

        return producerEntityGroupedMax.get(maxInterval)
                .stream()
                .toList();
    }
}
