package br.com.suntentgame.model;

public class Usuario {
	private static int contadorUsuarios = 1;
	private final int idUsuario;
	private String nome;
	private String email;
	private int pontos;
	private int ranking;
	private boolean acessoRecompensa;

	public Usuario(String nome, String email) {
		this.idUsuario = contadorUsuarios++;
		this.nome = nome;
		this.email = email;
		this.pontos = 0;
		this.ranking = 0;
		this.acessoRecompensa = false;
	}

	public int getIdUsuario() {
		return idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public int getPontos() {
		return pontos;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public void setAcessoRecompensa(boolean acessoRecompensa) {
		this.acessoRecompensa = acessoRecompensa;
	}

	public void adicionarPontos(int pontuacao) {
		this.pontos += pontuacao;
	}

	public void exibirDetalhes() {
		System.out.println("Id: " + idUsuario);
		System.out.println("Nome: " + nome);
		System.out.println("Email: " + email);
		System.out.println("Pontos: " + pontos);
		System.out.println("Ranking: " + (ranking > 0 ? ranking : "não definido"));
		System.out.println("Acesso à recompensa: " + (acessoRecompensa ? "sim" : "não"));
	}
}