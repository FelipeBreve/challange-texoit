package com.felipe.texoittest.infrastructure.usecase.findallmovieswhowinnermoretwoawards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.FindAllMoviesWhoWinnerMoreTwoTimes;
import com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.dto.OutputFindAllMoviesMinAndMaxInterval;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class FindAllMoviesWhoWinnerMoreTwoTimesTest {

    @Autowired
    private FindAllMoviesWhoWinnerMoreTwoTimes findAllMoviesWhoWinnerMoreTwoTimes;

    @Test
    public void testFindAllMoviesWhoWinnerMoreTwoAwards() {
        OutputFindAllMoviesMinAndMaxInterval result = findAllMoviesWhoWinnerMoreTwoTimes.execute();

        assertEquals(2, result.getMin().size());
        assertEquals(1, result.getMax().size());

        assertEquals("Joel Silver", result.getMin().get(0).getProducer());
        assertEquals(1, result.getMin().get(0).getInterval());
        assertEquals(1990, result.getMin().get(0).getPreviousWin());
        assertEquals(1991, result.getMin().get(0).getFollowingWin());

        assertEquals("Matthew Vaughn", result.getMin().get(1).getProducer());
        assertEquals(1, result.getMin().get(1).getInterval());
        assertEquals(2002, result.getMin().get(1).getPreviousWin());
        assertEquals(2003, result.getMin().get(1).getFollowingWin());

        assertEquals("Matthew Vaughn", result.getMax().get(0).getProducer());
        assertEquals(22, result.getMax().get(0).getInterval());
        assertEquals(2015, result.getMax().get(0).getPreviousWin());
        assertEquals(2037, result.getMax().get(0).getFollowingWin());
    }

}
