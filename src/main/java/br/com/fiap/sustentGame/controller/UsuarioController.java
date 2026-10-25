package br.com.fiap.sustentGame.controller;

import br.com.fiap.sustentGame.dto.UsuarioRequestDTO;
import br.com.fiap.sustentGame.dto.UsuarioResponseDTO;
import br.com.fiap.sustentGame.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@Tag(
        name = "Usuários",
        description = "Operações de gerenciamento de usuarios"
)
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar usuario")
    public ResponseEntity<UsuarioResponseDTO> criar(@Valid @RequestBody UsuarioRequestDTO usuario){
        UsuarioResponseDTO resposta = usuarioService.criar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    @Operation(
            summary = "Listar usuarios",
            description = "Retorna todos os usuarios cadastrados."
    )
    public List<UsuarioResponseDTO> listar(){
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    @Operation (summary = "Buscar usuario por ID")
    public UsuarioResponseDTO buscarPorId(
            @Parameter(description="Identificador do Usuario",
                    example= "1", required =true) @PathVariable Long id){
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuario")
    public UsuarioResponseDTO atualizar(@PathVariable Long id,
                                      @Valid @RequestBody UsuarioRequestDTO usuario){
        return usuarioService.atualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir usuario")
    public void excluir (@PathVariable Long id){
        usuarioService.excluir(id);
    }

    @GetMapping("/teste")
    public String teste(){
        return "Server On!";
    }
}
