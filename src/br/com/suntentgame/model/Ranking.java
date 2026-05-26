package br.com.suntentgame.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ranking {

	//atributos

	private List<Usuario> usuarios;

	//construtor

	public Ranking(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	//metodos

	public void atualizarRanking() {
		Collections.sort(
				usuarios,
				Comparator.comparingInt(
						Usuario::getPontos
				).reversed()
		);

		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuario = usuarios.get(i);
			usuario.setRanking(i + 1);
			usuario.setAcessoRecompensa(i < 5);
		}
	}

	public void ExibirTop5() {
		atualizarRanking();

		int limite = Math.min(5, usuarios.size());
		System.out.println("Top " + limite + " do br.com.suntentgame.model.Ranking:");
		for (int i = 0; i < limite; i++) {
			Usuario u = usuarios.get(i);
			System.out.println((i + 1) + " - " + u.getNome() + " - " + u.getPontos() + " pontos");
		}
	}
}
