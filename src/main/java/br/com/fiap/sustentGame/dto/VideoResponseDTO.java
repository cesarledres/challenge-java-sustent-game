package br.com.fiap.sustentGame.dto;

import java.time.LocalDateTime;

public record VideoResponseDTO(
    Long id,
    String urlVideo,
    String status,
    LocalDateTime dataEnvio,
    Integer pontosRecebidos,
    Long usuarioId
) {
}
