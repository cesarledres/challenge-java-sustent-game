package br.com.suntentgame.model;

import java.util.ArrayList;
import java.util.List;

public class Plataforma {

	//atributos

	private List<Usuario> usuarios;
	private List<Video> videos;


	//construtor

	public Plataforma() {
		usuarios = new ArrayList<>();
		videos = new ArrayList<>();
	}

	//get

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Video> getVideos() {
		return videos;
	}

	//metodos

	public boolean cadastrarUsuario(Usuario usuario) {
		usuarios.add(usuario);
		System.out.println("Usuário cadastrado com sucesso! ID do usuário: " + usuario.getIdUsuario());
		return true;
	}

	public Usuario buscarUsuarioPorId(int idUsuario) {
		for (Usuario usuario : usuarios) {
			if (usuario.getIdUsuario() == idUsuario) {
				return usuario;
			}
		}
		return null;
	}

	public boolean cadastrarVideo(Video video) {
		videos.add(video);
		System.out.println("Vídeo cadastrado com sucesso! ID do vídeo: " + video.getIdVideo());
		return true;
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
