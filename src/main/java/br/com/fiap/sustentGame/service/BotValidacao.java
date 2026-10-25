package br.com.fiap.sustentGame.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BotValidacao {
    public boolean validarVideo() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}