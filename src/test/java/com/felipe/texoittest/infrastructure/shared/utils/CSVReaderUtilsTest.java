package com.felipe.texoittest.infrastructure.shared.utils;

import com.felipe.texoittest.shared.exception.FilePathNotFoundException;
import com.felipe.texoittest.shared.exception.ReadFileException;
import com.felipe.texoittest.shared.util.CSVReaderUtils;
import com.felipe.texoittest.usecase.addmoviesandproducers.dto.MoviesDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CSVReaderUtilsTest {

    @Test
    public void testFileNotFound() {
        String filePath = "path/not/found.csv";

        FilePathNotFoundException exception = assertThrows(FilePathNotFoundException.class, () -> CSVReaderUtils.readCsv(MoviesDTO.class, filePath));

        assertTrue(exception.getMessage().contains("File not found at path: " + filePath));
    }

    @Test
    public void testFileWithProblems() {
        String filePath = "movielist-with-problem.csv";
        ReadFileException exception = assertThrows(ReadFileException.class, () -> CSVReaderUtils.readCsv(MoviesDTO.class, filePath));
        assertTrue(exception.getMessage().contains("Error processing csv file: " + filePath));
    }

    @Test
    public void testFileWithSuccess() {
        String filePath = "movielist-less.csv";

        List<MoviesDTO> list = CSVReaderUtils.readCsv(MoviesDTO.class, filePath);

        assertTrue(list.size() > 0);
    }

}
