package br.com.suntentgame;

import br.com.suntentgame.DAO.UsuarioDAO;
import br.com.suntentgame.model.*;
import br.com.suntentgame.service.BotValidacao;
import br.com.suntentgame.service.Plataforma;
import br.com.suntentgame.service.Ranking;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Plataforma plataforma = new Plataforma();
        BotValidacao bot = new BotValidacao();
        Ranking ranking = new Ranking(plataforma.getUsuarios());

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        int resposta = 0;
        while (resposta != 7) {
            System.out.println("=== MENU ===");
            System.out.println("1) Cadastrar usuário");
            System.out.println("2) Cadastrar vídeo");
            System.out.println("3) Exibir detalhes do usuário");
            System.out.println("4) Exibir detalhes do vídeo");
            System.out.println("5) Validar vídeo");
            System.out.println("6) Gerar ranking");
            System.out.println("7) Sair");
            System.out.print("Escolha uma opção: ");
            resposta = scanner.nextInt();
        
            switch(resposta) {
                case 1:
                    System.out.println("---");
                    System.out.print("Digite o nome do usuário: ");
                    String nomeUsuario = scanner.next();
                    System.out.print("Digite o email do usuário: ");
                    String emailUsuario = scanner.next();
                    Usuario usuario = new Usuario(nomeUsuario, emailUsuario);
                    plataforma.cadastrarUsuario(usuario); // adicionar verificação para ver se ja tem um usuario com esse email.
                    usuarioDAO.cadastrar(usuario);
                    break;
                case 2:
                    System.out.println("---");
                    System.out.print("Digite o ID do usuário para vincular ao vídeo: ");
                    int idUsuarioVideo = scanner.nextInt();
                    scanner.nextLine();
                    Usuario usuarioVideo = plataforma.buscarUsuarioPorId(idUsuarioVideo);
                    if (usuarioVideo == null) {
                        System.out.println("Usuário não encontrado. Vídeo não cadastrado.");
                        break;
                    }
                    System.out.print("Digite o título do vídeo: ");
                    String tituloVideo = scanner.nextLine();
                    System.out.print("Digite a duração do vídeo em minutos: ");
                    int duracaoVideo = scanner.nextInt();
                    Video video = new Video(idUsuarioVideo, tituloVideo, duracaoVideo);
                    plataforma.cadastrarVideo(video);
                    break;
                case 3:
                    System.out.println("---");
                    System.out.print("Digite o ID do usuário para exibir detalhes: ");
                    int idUsuario = scanner.nextInt();
                    Optional<Usuario> resultado = usuarioDAO.consultarPoId(idUsuario);
                    if (resultado.isPresent()){
                        Usuario usuarioDetalhes = resultado.get();
                        usuarioDetalhes.exibirDetalhes();
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("---");
                    System.out.print("Digite o ID do vídeo para exibir detalhes: ");
                    int idVideo = scanner.nextInt();
                    Video videoDetalhes = plataforma.buscarVideoPorId(idVideo);
                    if (videoDetalhes != null) {
                        videoDetalhes.exibirDetalhes();
                    } else {
                        System.out.println("Vídeo não encontrado.");
                    }
                    break;
                case 5:
                    System.out.println("---");
                    System.out.print("Digite o ID do vídeo para validar: ");
                    int idVideoValidar = scanner.nextInt();

                    System.out.print("Digite o ID do usuário para validar o vídeo: ");
                    int idUsuarioValidar = scanner.nextInt();

                    Video videoValidar = plataforma.buscarVideoPorId(idVideoValidar);
                    if (videoValidar == null) {
                        System.out.println("Vídeo não encontrado.");
                        break;
                    }

                    Usuario usuarioValidar = plataforma.buscarUsuarioPorId(idUsuarioValidar);
                    if (usuarioValidar == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    if (videoValidar.getIdUsuario() == idUsuarioValidar) {
                        bot.validarVideo(videoValidar, usuarioValidar);
                        System.out.println("Vídeo validado com sucesso!");

                    } else {
                        System.out.println("Usuário não autorizado a validar este vídeo.");
                    }
                    break;
                case 6:
                    System.out.println("---");
                    plataforma.gerarRanking();
                    break;
                case 7:
                    System.out.println("---");
                    System.out.println("Saindo...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            ranking.atualizarRanking();
            System.out.println("---");
        }
    }
}