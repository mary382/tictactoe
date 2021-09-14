package com.maryiasidaruk.tictactoe.board;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Square on the game board.
 */
@Data
@AllArgsConstructor
public class Cell {
    private static final Character WHITESPACE = ' ';

    private Character character;

    /**
     * Checks is cell is empty.
     *
     * @return true if cell not marked
     */
    public boolean isEmpty() {
        return character.compareTo(WHITESPACE) == 0;
    }

}
