package br.com.suntentgame;

import br.com.suntentgame.DAO.UsuarioDAO;
import br.com.suntentgame.model.*;
import br.com.suntentgame.service.BotValidacao;
import br.com.suntentgame.service.Plataforma;
import br.com.suntentgame.service.Ranking;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Plataforma plataforma = new Plataforma();
        BotValidacao bot = new BotValidacao();

        int resposta = 0;
        while (resposta != 9) {
            System.out.println("=== MENU ===");
            System.out.println("1) Cadastrar usuário");
            System.out.println("2) Editar usuário");
            System.out.println("3) Excluir usuário");
            System.out.println("4) Cadastrar vídeo");
            System.out.println("5) Exibir detalhes do usuário");
            System.out.println("6) Exibir detalhes do vídeo");
            System.out.println("7) Validar vídeo");
            System.out.println("8) Gerar ranking");
            System.out.println("9) Sair");
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
                    if (usuarioDAO.cadastrar(usuario)){
                        System.out.println("Usuario cadastrado com sucesso! Id do usuario: " + usuario.getIdUsuario());
                    }
                    break;
                case 2:
                    System.out.println("---");
                    System.out.println("Digite o id do usuario que queira editar:");
                    int idEditar = scanner.nextInt();
                    Optional<Usuario> resultadoEditar = usuarioDAO.consultarPoId(idEditar);
                    if (resultadoEditar.isPresent()) {
                        Usuario usuarioEditar = resultadoEditar.get();

                        System.out.println("Nome do usuario: " + usuarioEditar.getNome());
                        System.out.println("Digite o novo nome do usuário (0 para pular): ");
                        String novoNome = scanner.next();
                        if (!novoNome.equals("0")){
                            usuarioEditar.setNome(novoNome);
                        }

                        System.out.println("Email do usuário: " + usuarioEditar.getEmail());
                        System.out.println("Digite o novo email do usuário (0 para pular): ");
                        String novoEmail = scanner.next();
                        if (!novoEmail.equals("0")) {
                            usuarioEditar.setEmail(novoEmail);
                        }

                        usuarioDAO.atualizar(usuarioEditar);
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("---");
                    System.out.println("Digite o id do usuário que deseja exclur: "); //adicionar senha para exclusao
                    int idExcluir = scanner.nextInt();
                    Optional<Usuario> resultadoExcluir = usuarioDAO.consultarPoId(idExcluir);
                    if (resultadoExcluir.isPresent()){
                        Usuario usuarioExcluir = resultadoExcluir.get();
                        usuarioDAO.remover(usuarioExcluir);
                        System.out.println("Usuário excluido com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("---");
                    System.out.print("Digite o ID do usuário para vincular ao vídeo: ");
                    int idUsuarioVideo = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Usuario> resultadoCadastroVideo = usuarioDAO.consultarPoId(idUsuarioVideo);
                    if (resultadoCadastroVideo.isPresent()){
                        System.out.print("Digite o título do vídeo: ");
                        String tituloVideo = scanner.nextLine();
                        System.out.print("Digite a duração do vídeo em minutos: ");
                        int duracaoVideo = scanner.nextInt();
                        Video video = new Video(idUsuarioVideo, tituloVideo, duracaoVideo);
                        plataforma.cadastrarVideo(video);
                    } else {
                        System.out.println("Usuário não encontrado. Vídeo não cadastrado.");
                    }
                    break;
                case 5:
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
                case 6:
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
                case 7:
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

                    Optional<Usuario> resultadoValidacaoVideo = usuarioDAO.consultarPoId(idUsuarioValidar);
                    if (resultadoValidacaoVideo.isPresent()){
                        Usuario usuarioValidar = resultadoValidacaoVideo.get();
                        if (videoValidar.getIdUsuario() == idUsuarioValidar) {
                            bot.validarVideo(videoValidar, usuarioValidar);
                            System.out.println("Vídeo validado com sucesso!");

                        } else {
                            System.out.println("Usuário não autorizado a validar este vídeo.");
                        }
                        break;
                    } else {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }
                case 8:
                    System.out.println("---");
                    List<Usuario> usuarios = usuarioDAO.listar();
                    Ranking ranking = new Ranking(usuarios);
                    plataforma.gerarRanking(ranking);
                    break;
                case 9:
                    System.out.println("---");
                    System.out.println("Saindo...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println("---");
        }
    }
}