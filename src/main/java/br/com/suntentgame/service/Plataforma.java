package br.com.suntentgame.service;

import br.com.suntentgame.model.Usuario;
import br.com.suntentgame.model.Video;

import java.util.ArrayList;
import java.util.List;

public class Plataforma {
	private List<Usuario> usuarios;
	private List<Video> videos;

	public Plataforma() {
		usuarios = new ArrayList<>();
		videos = new ArrayList<>();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void cadastrarUsuario(Usuario usuario) {
		usuarios.add(usuario);
		System.out.println("Usuário cadastrado com sucesso! ID do usuário: " + usuario.getIdUsuario());
	}

	public Usuario buscarUsuarioPorId(int idUsuario) {
		for (Usuario usuario : usuarios) {
			if (usuario.getIdUsuario() == idUsuario) {
				return usuario;
			}
		}
		return null;
	}

	public void cadastrarVideo(Video video) {
		videos.add(video);
		System.out.println("Vídeo cadastrado com sucesso! ID do vídeo: " + video.getIdVideo());
	}

	public Video buscarVideoPorId(int idVideo) {
		for (Video video : videos) {
			if (video.getIdVideo() == idVideo) {
				return video;
			}
		}
		return null;
	}

	public void gerarRanking() {
		Ranking ranking = new Ranking(usuarios);
		ranking.ExibirTop5();
	}
}
