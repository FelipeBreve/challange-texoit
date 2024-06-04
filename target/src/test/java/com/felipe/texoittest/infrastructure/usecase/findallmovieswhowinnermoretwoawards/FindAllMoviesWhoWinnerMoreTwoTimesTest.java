package com.felipe.texoittest.infrastructure.usecase.findallmovieswhowinnermoretwoawards;

import com.felipe.texoittest.shared.exception.ListEmptyToProcessException;
import com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.FindAllMoviesWhoWinnerMoreTwoTimes;
import com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.dto.OutputFindAllMoviesMinAndMaxInterval;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class FindAllMoviesWhoWinnerMoreTwoTimesTest {

    @Autowired
    private FindAllMoviesWhoWinnerMoreTwoTimes findAllMoviesWhoWinnerMoreTwoTimes;

    @Test
    public void testFindAllMoviesWhoWinnerMoreTwoTimes() {
        OutputFindAllMoviesMinAndMaxInterval result = findAllMoviesWhoWinnerMoreTwoTimes.execute();

        assertEquals(1, result.getMin().size());
        assertEquals(1, result.getMax().size());

        assertEquals("Joel Silver", result.getMin().get(0).getProducer());
        assertEquals(1, result.getMin().get(0).getInterval());
        assertEquals(1990, result.getMin().get(0).getPreviousWin());
        assertEquals(1991, result.getMin().get(0).getFollowingWin());

        assertEquals("Matthew Vaughn", result.getMax().get(0).getProducer());
        assertEquals(13, result.getMax().get(0).getInterval());
        assertEquals(2002, result.getMax().get(0).getPreviousWin());
        assertEquals(2015, result.getMax().get(0).getFollowingWin());
    }

}
