package com.maryiasidaruk.tictactoe.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CellTest {

    @Test
    void shouldReturnTrue_whenIsEmpty_givenEmptyCharacter() {
        Cell givenEmptyCell = new Cell(' ');
        assertTrue(givenEmptyCell.isEmpty());
    }

    @Test
    void shouldReturnFalse_whenIsEmpty_givenEmptyCharacter() {
        Cell givenEmptyCell = new Cell('X');
        assertFalse(givenEmptyCell.isEmpty());
    }
}