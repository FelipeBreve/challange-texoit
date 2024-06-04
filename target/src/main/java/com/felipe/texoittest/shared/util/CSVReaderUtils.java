package com.felipe.texoittest.shared.util;

import com.felipe.texoittest.shared.exception.FilePathNotFoundException;
import com.felipe.texoittest.shared.exception.ReadFileException;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CSVReaderUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVReaderUtils.class);
    public static <T> List<T> readCsv(Class<T> type, String filePath) {
        LOGGER.info("Reading CSV file from path: {}", filePath);
        Resource resource = new ClassPathResource(filePath);

        if (!resource.exists()) {
            throw new FilePathNotFoundException("File not found at path: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            return new CsvToBeanBuilder<T>(reader)
                    .withType(type)
                    .withSeparator(';')
                    .build()
                    .parse();
        } catch (RuntimeException | IOException e) {
            throw new ReadFileException("Error processing csv file: " + filePath, e.getCause());
        }
    }

}
