package br.com.fiap.sustentGame.service;

import br.com.fiap.sustentGame.dto.VideoRequestDTO;
import br.com.fiap.sustentGame.dto.VideoResponseDTO;
import br.com.fiap.sustentGame.model.Usuario;
import br.com.fiap.sustentGame.model.Video;
import br.com.fiap.sustentGame.repository.UsuarioRepository;
import br.com.fiap.sustentGame.repository.VideoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final UsuarioRepository usuarioRepository;

    public VideoService(VideoRepository videoRepository,
                        UsuarioRepository usuarioRepository) {
        this.videoRepository = videoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public VideoResponseDTO criar(VideoRequestDTO dados) {
        Usuario usuario = buscarUsuarioPorId(dados.usuarioId());

        Video video = new Video();

        video.setUrlVideo(dados.urlVideo());
        video.setUsuario(usuario);

        Video salvo = videoRepository.save(video);

        return paraResponseDTO(salvo);
    }

    public VideoResponseDTO buscarPorId(Long id) {
        return paraResponseDTO(buscarEntidadePorId(id));
    }

    public void excluir(Long id) {
        videoRepository.delete(buscarEntidadePorId(id));
    }

    public List<VideoResponseDTO> listar() {
        return videoRepository.findAll()
                .stream()
                .map(this::paraResponseDTO)
                .toList();
    }

    public VideoResponseDTO atualizar(Long id, VideoRequestDTO dados) {
        Video video = buscarEntidadePorId(id);
        Usuario usuario = buscarUsuarioPorId(dados.usuarioId());

        video.setUrlVideo(dados.urlVideo());
        video.setUsuario(usuario);

        return paraResponseDTO(videoRepository.save(video));
    }

    private Video buscarEntidadePorId(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vídeo não encontrado com o ID " + id));
    }

    private Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado com o ID " + id));
    }

    private VideoResponseDTO paraResponseDTO(Video video) {
        return new VideoResponseDTO(
                video.getId(),
                video.getUrlVideo(),
                video.getStatus(),
                video.getDataEnvio(),
                video.getPontosRecebidos(),
                video.getUsuario().getId()
        );
    }
}
