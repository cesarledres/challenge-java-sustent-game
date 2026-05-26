package br.com.suntentgame.model;

public class BotValidacao {

	public void validarVideo(Video video, Usuario usuario) {
		String titulo = video.getTitulo().toLowerCase();

		String[] palavrasEnergiaSolar = {"energia", "solar", "painel", "renovavel"};
		for (String palavra : palavrasEnergiaSolar) {
			if (titulo.contains(palavra)) {
				usuario.adicionarPontos(85);
				video.setPontuacao(85);
				video.setStatus("APROVADO");
				System.out.println("Vídeo aprovado!");
				System.out.println(usuario.getNome() + " ganhou 85 pontos!");
				break;
			}
		}

		String[] palavrasPlantarArvore = {"arvore", "plantar", "reflorestamento", "plantando"};
		for (String palavra : palavrasPlantarArvore) {
			if (titulo.contains(palavra)) {
				usuario.adicionarPontos(80);
				video.setPontuacao(80);
				video.setStatus("APROVADO");
				System.out.println("Vídeo aprovado!");
				System.out.println(usuario.getNome() + " ganhou 80 pontos!");
				break;
			}
		}

		String[] palavrasTransporteSustentavel = {"bicicleta", "bike", "carona", "onibus"};
		for (String palavra : palavrasTransporteSustentavel) {
			if (titulo.contains(palavra)) {
				usuario.adicionarPontos(70);
				video.setPontuacao(70);
				video.setStatus("APROVADO");
				System.out.println("Vídeo aprovado!");
				System.out.println(usuario.getNome() + " ganhou 70 pontos!");
				break;
			}
		}

		String[] palavrasCompostagem = {"compostagem", "adubo", "organico"};
		for (String palavra : palavrasCompostagem) {
			if (titulo.contains(palavra)) {
				usuario.adicionarPontos(75);
				video.setPontuacao(75);
				video.setStatus("APROVADO");
				System.out.println("Vídeo aprovado!");
				System.out.println(usuario.getNome() + " ganhou 75 pontos!");
				break;
			}
		}

		String[] palavrasReciclagem = {"reciclagem", "reciclar", "papel", "vidro", "metal"};
		for (String palavra : palavrasReciclagem) {
			if (titulo.contains(palavra)) {
				usuario.adicionarPontos(95);
				video.setPontuacao(95);
				video.setStatus("APROVADO");
				System.out.println("Vídeo aprovado!");
				System.out.println(usuario.getNome() + " ganhou 95 pontos!");
				break;
			}
		}

		String[] palavrasLimpezaAmbiental = {"limpeza", "limpar", "lixo", "coleta"};
		for (String palavra : palavrasLimpezaAmbiental) {
			if (titulo.contains(palavra)) {
				usuario.adicionarPontos(90);
				video.setPontuacao(90);
				video.setStatus("APROVADO");
				System.out.println("Vídeo aprovado!");
				System.out.println(usuario.getNome() + " ganhou 90 pontos!");
				break;
			}
		}

		if (video.getStatus().equals("PENDENTE")) {
    		video.setStatus("REPROVADO");
			System.out.println("Vídeo reprovado.");
		}
	}
}
