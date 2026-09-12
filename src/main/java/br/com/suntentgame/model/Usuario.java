package br.com.suntentgame.model;

public class Usuario {
	private static int contadorUsuarios = 1;
	private int idUsuario;
	private String nome;
	private String email;
	private int pontos;
	private int ranking;
	private boolean acessoRecompensa;

	public Usuario() {
	}

	public Usuario(String nome, String email) {
		this.idUsuario = contadorUsuarios++;
		this.nome = nome;
		this.email = email;
		this.pontos = 0;
		this.ranking = 0;
		this.acessoRecompensa = false;
	}

	public Usuario(int idUsuario, String nome, String email, int pontos) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.pontos = pontos;
	}

	public int getIdUsuario() {
		return idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public boolean isAcessoRecompensa() {
		return acessoRecompensa;
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