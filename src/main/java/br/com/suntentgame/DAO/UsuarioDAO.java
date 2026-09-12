package br.com.suntentgame.DAO;

import br.com.suntentgame.configuration.ConnectionOracle;
import br.com.suntentgame.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAO implements DAO<Usuario> {
    private final ConnectionOracle conexao;
    private int rows;

    public UsuarioDAO(){
        this.conexao = new ConnectionOracle();
    }

    @Override
    public boolean cadastrar(Usuario usuario) throws SQLException {
        rows = 0;
        String sql = "INSERT INTO T_USUARIO (nm_usuario, email, pontos) VALUES(?,?,?)";
        try{
            Connection conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getPontos());
            rows = stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows == 1;
    }

    @Override
    public boolean atualizar(Usuario usuario) throws SQLException {
        rows = 0;
        String sql = "UPDATE T_USUARIO set nm_usuario=?, email=?, pontos=? WHERE id_usuario=?";
        try{
            Connection conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getPontos());
            stmt.setInt(4,usuario.getIdUsuario());
            rows = stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rows == 1;
    }

    @Override
    public boolean remover(Usuario usuario) throws SQLException {
        rows = 0;
        String sql = "DELETE FROM T_USUARIO WHERE id_usuario=?";
        try{
            Connection conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuario.getIdUsuario());
            rows = stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  rows == 1;
    }

    @Override
    public Optional<Usuario> consultarPoId(int idUsuario) throws SQLException {
        String sql = "SELECT ID_USUARIO, NM_USUARIO, EMAIL, PONTOS FROM T_USUARIO WHERE ID_USUARIO=?";
        try{
            Connection conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                Usuario usuario1 = new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("NM_USUARIO"),
                        rs.getString("EMAIL"),
                        rs.getInt("PONTOS")
                );
                return Optional.of(usuario1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT ID_USUARIO, NM_USUARIO, EMAIL FROM T_USUARIO";
        try(Connection conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
                    usuario.setNome(rs.getString("NM_USUARIO"));
                    usuario.setEmail(rs.getString("EMAIL"));
                    usuario.setPontos(rs.getInt("PONTOS"));

                    usuarios.add(usuario);
                }
                return usuarios;
            }
        }
    }
}
