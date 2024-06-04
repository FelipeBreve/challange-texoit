package com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards;

import com.felipe.texoittest.domain.producer.entity.ProducerEntity;
import com.felipe.texoittest.domain.producer.factory.ProducerFactory;
import com.felipe.texoittest.domain.producer.repository.ProducerRepository;
import com.felipe.texoittest.domain.producer.service.ProducerService;
import com.felipe.texoittest.infrastructure.producer.model.ProducerModel;
import com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.dto.OutputFindAllMoviesMinAndMaxInterval;
import com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.dto.factory.OutputFindAllMoviesMinAndMaxIntervalFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindAllMoviesWhoWinnerMoreTwoTimes {
    @Autowired
    private ProducerRepository producerRepository;

    public OutputFindAllMoviesMinAndMaxInterval execute() {
        List<ProducerModel> producerModel = producerRepository.findAllProducerWhoWinnerMoreTwoMovies();
        List<ProducerEntity> producerEntities = new ArrayList<>();
        producerModel.forEach(producer -> {
            ProducerEntity producerEntity = ProducerFactory.createProducerWithMovies(producer);
            producerEntities.add(producerEntity);
        });

        List<ProducerEntity> producerEntitiesMin = ProducerService.getTopMinIntervalWinner(producerEntities);
        List<ProducerEntity> producerEntitiesMax = ProducerService.getTopMaxIntervalWinner(producerEntities, producerEntitiesMin);

        return OutputFindAllMoviesMinAndMaxIntervalFactory.create(producerEntitiesMin, producerEntitiesMax);
    }

}
