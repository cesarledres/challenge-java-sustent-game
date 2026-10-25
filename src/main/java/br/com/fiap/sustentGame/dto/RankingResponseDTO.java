package br.com.fiap.sustentGame.dto;

public record RankingResponseDTO(
        Integer posicao,
        Long idUsuario,
        String nome,
        Integer pontos,
        Boolean acessoRecompensa
) {
}