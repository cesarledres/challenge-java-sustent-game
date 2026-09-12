package br.com.suntentgame.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    public boolean cadastrar(T entidade)   throws SQLException;
    public boolean atualizar(T entidade)  throws SQLException;
    public boolean remover(T entidade)  throws SQLException;
    public Optional<T> consultarPoId(int entidade)  throws SQLException;
    public List<T> listar() throws SQLException;
}
