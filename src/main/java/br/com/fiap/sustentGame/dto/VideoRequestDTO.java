package br.com.fiap.sustentGame.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VideoRequestDTO(
    @NotBlank(message = "A URL do vídeo é obrigatória")
    String urlVideo,

    @NotNull(message = "O ID do usuário é obrigatório")
    Long usuarioId
) {
}
