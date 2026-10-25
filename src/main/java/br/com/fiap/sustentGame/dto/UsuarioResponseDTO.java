package br.com.fiap.sustentGame.dto;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        Integer pontos
) {
}
