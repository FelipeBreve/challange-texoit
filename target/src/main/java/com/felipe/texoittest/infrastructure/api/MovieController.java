package com.felipe.texoittest.infrastructure.api;

import com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.FindAllMoviesWhoWinnerMoreTwoTimes;
import com.felipe.texoittest.usecase.findallmovieswhowinnermoretwoawards.dto.OutputFindAllMoviesMinAndMaxInterval;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies", description = "API para manipular filmes.")
public class MovieController {
    @Autowired
    private FindAllMoviesWhoWinnerMoreTwoTimes findAllMoviesWhoWinnerMoreTwoTimes;

    @GetMapping(value = "/ranking-worst-awards")
    @Operation(summary = "Busca todos os filmes que ganharam mais de dois prêmios com maior intervalo e menor intervalo.")
    public ResponseEntity<OutputFindAllMoviesMinAndMaxInterval> worseMoviesAwards() {
        OutputFindAllMoviesMinAndMaxInterval result = findAllMoviesWhoWinnerMoreTwoTimes.execute();
        return ResponseEntity.ok(result);
    }

}
