package com.maryiasidaruk.tictactoe.game;

import com.maryiasidaruk.tictactoe.board.Board;
import com.maryiasidaruk.tictactoe.game.GameConditions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class GameConditionsTest {
    private static final char CHARACTER_X = 'X';
    private static final char CHARACTER_O = 'O';

    @Test
    public void shouldReturnFalse_whenHasWinner_givenBoardWithSize3AndOneCellIsNotEmpty() {
        Board board = new Board(3);
        board.markCell(0, 0, CHARACTER_X);
        GameConditions gameConditions = new GameConditions();

        assertFalse(gameConditions.hasWinner(board));
    }

    @Test
    public void findWinner_shouldReturnTrue_whenRowPositionsHaveSameChar() {
        Board board = new Board(3);
        board.markCell(0, 0, CHARACTER_X);
        board.markCell(0, 1, CHARACTER_O);
        for (int i = 0; i < 3; i++) {
            board.markCell(1, i, CHARACTER_X);
        }
        GameConditions gameConditions = new GameConditions();

        assertTrue(gameConditions.hasWinner(board));
    }

    @Test
    public void shouldReturnTrue_whenHasWinner_whenColumnPositionsHaveSameCharacter() {
        Board board = new Board(3);
        board.markCell(0, 0, CHARACTER_X);
        board.markCell(1, 0, CHARACTER_O);
        for (int i = 0; i < 3; i++) {
            board.markCell(i, 1, CHARACTER_X);
        }
        GameConditions gameConditions = new GameConditions();

        assertTrue(gameConditions.hasWinner(board));
    }

    @Test
    public void shouldReturnTrue_whenHasWinner_givenMainDiagonalPositionsHaveSameCharacter() {
        Board board = new Board(3);
        board.markCell(0, 0, CHARACTER_X);
        board.markCell(1, 1, CHARACTER_X);
        board.markCell(2, 2, CHARACTER_X);
        GameConditions gameConditions = new GameConditions();

        assertTrue(gameConditions.hasWinner(board));
    }

    @Test
    public void findWinner_shouldReturnTrue_whenDiagonal2PositionsHaveSameChar() {
        Board board = new Board(3);
        board.markCell(0, 2, CHARACTER_X);
        board.markCell(1, 1, CHARACTER_X);
        board.markCell(2, 0, CHARACTER_X);
        GameConditions gameConditions = new GameConditions();

        assertTrue(gameConditions.hasWinner(board));
    }

    @Test
    public void shouldReturnFalse_whenHasWinner_givenDiagonalDoNotHaveSameCharacter() {
        Board board = new Board(3);
        board.markCell(0, 0, CHARACTER_X);
        board.markCell(1, 1, CHARACTER_O);
        board.markCell(0, 2, CHARACTER_X);
        GameConditions gameConditions = new GameConditions();

        assertFalse(gameConditions.hasWinner(board));
    }
}