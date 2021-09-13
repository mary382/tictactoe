package com.maryiasidaruk.tictactoe.player;

import com.maryiasidaruk.tictactoe.board.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
@AllArgsConstructor
public class Player {

    private String name;
    private Character character;

    public int[] getNextPosition(final Board board) {
        return new int[]{
                ThreadLocalRandom.current().nextInt(0, board.getSize()),
                ThreadLocalRandom.current().nextInt(0, board.getSize())
        };
    }

}
