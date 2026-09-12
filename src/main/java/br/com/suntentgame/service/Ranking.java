package br.com.suntentgame.service;

import br.com.suntentgame.model.Usuario;

import java.util.Comparator;
import java.util.List;

public class Ranking {
	private final List<Usuario> usuarios;

	public Ranking(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void calcularRanking() {
		usuarios.sort(
				Comparator.comparingInt(Usuario::getPontos).reversed()
		);

		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuario = usuarios.get(i);
			usuario.setRanking(i + 1);
			usuario.setAcessoRecompensa(i < 5);
		}
	}

	public void exibirTop5() {
		int limite = Math.min(5, usuarios.size());

		for (int i = 0; i < limite; i++) {
			Usuario usuario = usuarios.get(i);
			System.out.println(usuario.getRanking() + "º - " + usuario.getNome() + " - " + usuario.getPontos() + " pontos");
		}
	}
}