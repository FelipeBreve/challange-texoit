package com.felipe.texoittest.infrastructure.config;

import com.felipe.texoittest.shared.exception.ListEmptyToProcessException;
import com.felipe.texoittest.usecase.addmoviesandproducers.AddMoviesAndProducers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitBeans implements ApplicationRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(InitBeans.class);

    @Autowired
    private AddMoviesAndProducers addMoviesAndProducers;

    @Override
    public void run(ApplicationArguments args) throws ListEmptyToProcessException {
        LOGGER.info("Init process create database");
        addMoviesAndProducers.execute();
        LOGGER.info("finish process create database");
    }
}

