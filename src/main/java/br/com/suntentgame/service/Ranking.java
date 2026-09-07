package br.com.suntentgame.service;

import br.com.suntentgame.model.Usuario;

import java.util.Comparator;
import java.util.List;

public class Ranking {
	private List<Usuario> usuarios;

	public Ranking(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void atualizarRanking() {
		usuarios.sort(Comparator.comparingInt(
                Usuario::getPontos
        ).reversed());

		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuario = usuarios.get(i);
			usuario.setRanking(i + 1);
			usuario.setAcessoRecompensa(i < 5);
		}
	}

	public void ExibirTop5() {
		atualizarRanking();

		int limite = Math.min(5, usuarios.size());
		System.out.println("Top " + limite + " do Ranking:");
		for (int i = 0; i < limite; i++) {
			Usuario u = usuarios.get(i);
			System.out.println((i + 1) + " - " + u.getNome() + " - " + u.getPontos() + " pontos");
		}
	}
}
