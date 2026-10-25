
package br.com.fiap.sustentGame.service;

import br.com.fiap.sustentGame.dto.RankingResponseDTO;
import br.com.fiap.sustentGame.model.Usuario;
import br.com.fiap.sustentGame.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankingService {
    private final UsuarioRepository usuarioRepository;

    public RankingService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<RankingResponseDTO> listarRanking() {
        List<Usuario> usuarios = usuarioRepository.findTop5ByOrderByPontosDescIdAsc();
        List<RankingResponseDTO> ranking = new ArrayList<>();

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);

            int posicao = i + 1;
            boolean acessoRecompensa = posicao <= 3;

            RankingResponseDTO resposta = new RankingResponseDTO(
                    posicao,
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getPontos(),
                    acessoRecompensa
            );
            ranking.add(resposta);
        }
        return ranking;
    }
}
