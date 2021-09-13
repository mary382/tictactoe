package com.maryiasidaruk.tictactoe.player;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerFactory {

    private static final String PLAYER_X = "Player X";
    private static final String PLAYER_O = "Player O";

    private static final Character CHARACTER_X = 'X';
    private static final Character CHARACTER_0 = 'O';

    public List<Player> createPlayers() {
        return List.of(
                new Player(
                        PLAYER_X,
                        CHARACTER_X
                ),
                new Player(
                        PLAYER_O,
                        CHARACTER_0
                )
        );
    }
}
