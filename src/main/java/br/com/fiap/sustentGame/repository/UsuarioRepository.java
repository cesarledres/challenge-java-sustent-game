package br.com.fiap.sustentGame.repository;

import br.com.fiap.sustentGame.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findTop5ByOrderByPontosDescIdAsc();
}
