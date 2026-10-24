package br.com.fiap.sustentGame.service;

import br.com.fiap.sustentGame.dto.UsuarioRequestDTO;
import br.com.fiap.sustentGame.dto.UsuarioResponseDTO;
import br.com.fiap.sustentGame.model.Usuario;
import br.com.fiap.sustentGame.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO criar(UsuarioRequestDTO dados) {
        Usuario usuario = new Usuario();

        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setPontos(dados.pontos());


        Usuario salvo = usuarioRepository.save(usuario);

        return paraResponseDTO(salvo);
    }

    public UsuarioResponseDTO buscarPorId(Long id){
        return paraResponseDTO(buscarEntidadePorId(id));
    }

    public void excluir(Long id){
        usuarioRepository.delete(buscarEntidadePorId(id));
    }

    public List<UsuarioResponseDTO> listar(){
        return usuarioRepository.findAll()
                .stream()
                .map(this::paraResponseDTO)
                .toList();
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dados){
        Usuario usuario = buscarEntidadePorId(id);

        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setPontos(dados.pontos());

        return paraResponseDTO(usuarioRepository.save(usuario));
    }

    private Usuario buscarEntidadePorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aluno não encontrado com o ID " + id));
    }

    private UsuarioResponseDTO paraResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPontos()
        );
    }
}

