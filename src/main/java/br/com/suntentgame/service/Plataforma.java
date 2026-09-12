package br.com.suntentgame.service;

import br.com.suntentgame.model.Usuario;
import br.com.suntentgame.model.Video;

import java.util.ArrayList;
import java.util.List;

public class Plataforma {
	private List<Video> videos;

	public Plataforma() {
		videos = new ArrayList<>();
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
}
