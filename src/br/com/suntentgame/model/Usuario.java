package br.com.suntentgame.model;

public class Usuario {

	//atributos

	private static int contadorUsuarios = 1;
	private int idUsuario;
	private String nome;
	private String email;
	private int pontos;
	private int ranking;
	private boolean acessoRecompensa;

	//construtor

	

	public Usuario(String nome, String email) {
		this.idUsuario = contadorUsuarios++;
		this.nome = nome;
		this.email = email;
		this.pontos = 0;
		this.ranking = 0;
		this.acessoRecompensa = false;
	}

	//get

	public int getIdUsuario() {
		return idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public int getPontos() {
		return pontos;
	}

	public int getRanking() {
		return ranking;
	}

	public boolean hasAcessoRecompensa() {
		return acessoRecompensa;
	}

	//set

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public void setAcessoRecompensa(boolean acessoRecompensa) {
		this.acessoRecompensa = acessoRecompensa;
	}

	//metodos

	public void adicionarPontos(int pontuacao) {
		this.pontos += pontuacao;
	}

	public void exibirDetalhes() {
		System.out.println("Id: " + idUsuario);
		System.out.println("Nome: " + nome);
		System.out.println("Email: " + email);
		System.out.println("Pontos: " + pontos);
		System.out.println("br.com.suntentgame.model.Ranking: " + (ranking > 0 ? ranking : "não definido"));
		System.out.println("Acesso à recompensa: " + (acessoRecompensa ? "sim" : "não"));

	}
}
