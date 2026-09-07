package br.com.suntentgame.model;

public class Video {
	private static int contadorVideos = 1;
	private final int idVideo;
	private final int idUsuario;
	private String titulo;
	private int duracao;
	private String status;
	private int pontuacao;

	public Video(int idUsuario, String titulo, int duracao) {
		this.idVideo = contadorVideos++;
		this.idUsuario = idUsuario;
		this.titulo = titulo;
		this.duracao = duracao;
		this.status = "PENDENTE";
		this.pontuacao = 0;
	}

	public int getIdVideo() {
		return idVideo;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getStatus() {
		return status;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public void exibirDetalhes() {
		System.out.println("Id: " + idVideo);
		System.out.println("Título: " + titulo);
		System.out.println("Duração: " + duracao);
		System.out.println("Status: " + status);
		System.out.println("Pontuação do vídeo: " + pontuacao);
		System.out.println("ID do usuário que cadastrou: " + idUsuario);
	}
}
