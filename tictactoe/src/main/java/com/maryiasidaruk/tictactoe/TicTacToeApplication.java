package com.maryiasidaruk.tictactoe;

import com.maryiasidaruk.tictactoe.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicTacToeApplication implements CommandLineRunner {

    @Autowired
    private Game game;

    public static void main(String[] args) {
        SpringApplication.run(TicTacToeApplication.class);
    }

    @Override
    public void run(String... args) {
        game.createBoard();
        while (game.play()) ;
    }

}
