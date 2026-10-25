package br.com.fiap.sustentGame.controller;

import br.com.fiap.sustentGame.dto.VideoRequestDTO;
import br.com.fiap.sustentGame.dto.VideoResponseDTO;
import br.com.fiap.sustentGame.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video")
@Tag(
        name = "Vídeos",
        description = "Operações de gerenciamento de vídeos"
)
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar vídeo")
    public ResponseEntity<VideoResponseDTO> criar(
            @Valid @RequestBody VideoRequestDTO video) {

        VideoResponseDTO resposta = videoService.criar(video);

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    @Operation(
            summary = "Listar vídeos",
            description = "Retorna todos os vídeos cadastrados."
    )
    public List<VideoResponseDTO> listar() {
        return videoService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar vídeo por ID")
    public VideoResponseDTO buscarPorId(
            @Parameter(
                    description = "Identificador do vídeo",
                    example = "1",
                    required = true
            )
            @PathVariable Long id) {

        return videoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar vídeo")
    public VideoResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody VideoRequestDTO video) {

        return videoService.atualizar(id, video);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir vídeo")
    public void excluir(@PathVariable Long id) {
        videoService.excluir(id);
    }

    @PatchMapping("/{id}/validar")
    @Operation(summary = "Validar vídeo e atualizar pontuação")
    public VideoResponseDTO validar(@PathVariable Long id) {
        return videoService.validar(id);
    }

    @GetMapping("/teste")
    @Operation(summary = "Verificar funcionamento do servidor")
    public String teste() {
        return "Server On!";
    }
}
