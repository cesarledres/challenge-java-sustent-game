package br.com.suntentgame.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracao {
    private final Properties propriedades = new Properties();

    public Configuracao(){
        carregar();
    }

    private void carregar(){
        try(InputStream arquivo = getClass()
                .getClassLoader()
                .getResourceAsStream("application.properties")
        ){
            if (arquivo == null){
                throw new IllegalStateException("O arquivo application não foi encontrado");
            }
            propriedades.load(arquivo);
        }catch (IOException e){
            throw new IllegalStateException("Erro de carregar o arquivo");
        }
    }

    public String get(String chave){
        return propriedades.getProperty(chave);
    }
}