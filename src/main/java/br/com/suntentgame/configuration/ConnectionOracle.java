package br.com.suntentgame.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionOracle {
    private final Configuracao conf;

    public ConnectionOracle() {
        this.conf = new Configuracao();
    }


    public Connection getConnection() throws SQLException {
        String URL = conf.get("db.url");
        String USER = conf.get("db.user");
        String PASS = conf.get("db.password");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
