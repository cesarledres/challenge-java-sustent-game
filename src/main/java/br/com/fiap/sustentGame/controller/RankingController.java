package br.com.fiap.sustentGame.controller;

import br.com.fiap.sustentGame.dto.RankingResponseDTO;
import br.com.fiap.sustentGame.service.RankingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
@Tag(
        name = "Ranking",
        description = "Classificação dos usuários por pontuação"
)
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping
    @Operation(summary = "Consultar os 5 melhores usuários")
    public List<RankingResponseDTO> listarRanking() {
        return rankingService.listarRanking();
    }
}