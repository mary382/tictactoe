package com.maryiasidaruk.tictactoe.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class Cell {
    private static final Character WHITESPACE = ' ';

    private Character character;

    public boolean isEmpty() {
        return character.compareTo(WHITESPACE) == 0;
    }

}
