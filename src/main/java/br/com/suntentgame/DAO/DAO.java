package br.com.suntentgame.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    boolean cadastrar(T entidade)   throws SQLException;
    void atualizar(T entidade)  throws SQLException;
    void remover(T entidade)  throws SQLException;
    Optional<T> consultarPoId(int entidade)  throws SQLException;
    List<T> listar() throws SQLException;
}