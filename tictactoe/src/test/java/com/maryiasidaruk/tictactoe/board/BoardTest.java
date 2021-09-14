package com.maryiasidaruk.tictactoe.board;

import com.maryiasidaruk.tictactoe.exception.InvalidPositionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {

    @Spy
    Cell[][] cell = {{new Cell(' '), new Cell('X'), new Cell(' ')}, {new Cell(' '), new Cell('X'), new Cell(' ')}, {new Cell(' '), new Cell('O'), new Cell(' ')}};
    Board board = new Board(3);

    @Test
    public void shouldReturnTrue_whenInitializeCells_givenCells3Size() {
        Cell emptyCell = new Cell(' ');
        Cell[] emptyRow = {emptyCell, emptyCell, emptyCell};
        Cell[][] expectedCell = new Cell[][]{emptyRow, emptyRow, emptyRow};

        Cell[][] actualCell = board.initializeCells();

        assertThat(actualCell).usingDefaultComparator().isEqualTo(expectedCell);
    }

    @Test
    void shouldReturnThree_WhenGetSize_givenBoardWithThree() {
        int actualSize = board.getSize();
        assertThat(actualSize).isEqualTo(3);
    }

    @Test
    void shouldReturnSpace_WhenGetCell_givenBoardWithEmptyCells() {
        Cell actualCell = board.getCell(0, 0);
        assertThat(actualCell).isEqualTo(new Cell(' '));
    }

    @Test
    void shouldReturnFalse_whenIsFull_givenCellsEmpty() {
        boolean actualResult = board.isFull();
        assertFalse(actualResult);
    }

    @Test
    void shouldReturnTrue_whenIsFull_givenCellsEmpty() {
        Board board = new Board(3);
        for (int x = 0; x < board.getCells().length; x++) {
            for (int y = 0; y < board.getCells().length; y++) {
                board.getCells()[x][y] = new Cell('X');
            }
        }
        boolean actualResult = board.isFull();
        assertTrue(actualResult);
    }

    @Test
    void shouldSetCellCharacter_whenMarkCell_givenPositionAndMarkX() {
        Board board = new Board(3);
        board.setCells(board.initializeCells());

        board.markCell(0, 0, 'X');

        assertThat(board.getCell(0, 0)).isEqualTo(new Cell('X'));
    }

    @Test
    public void shouldThrowException_whenMarkCell_givenNegativeXCoordinate() throws Exception {
        Board board = new Board(3);
        board.setCells(board.initializeCells());

        Assertions.assertThrows(InvalidPositionException.class, () -> {
            board.markCell(-1, 0, 'X');
        });
    }

    @Test
    public void shouldThrowException_whenMarkCell_givenNegativeYCoordinate() throws Exception {
        Board board = new Board(3);
        board.setCells(board.initializeCells());

        Assertions.assertThrows(InvalidPositionException.class, () -> {
            board.markCell(0, -1, 'X');
        });
    }

    @Test
    public void shouldThrowException_whenMarkCell_givenNotEmptyCell() throws Exception {
        Board board = new Board(3);
        for (int x = 0; x < board.getCells().length; x++) {
            for (int y = 0; y < board.getCells().length; y++) {
                board.getCells()[x][y] = new Cell('X');
            }
        }

        Assertions.assertThrows(InvalidPositionException.class, () -> {
            board.markCell(0, 0, 'X');
        });
    }

    @Test
    public void shouldReturnTrue_whenIsValidPosition_givenPosition2() {
        int givenPosition = 2;
        assertTrue(board.isValidPosition(givenPosition));
    }

    @Test
    public void shouldReturnFalse_whenIsValidPosition_givenPositionBiggerThanBoardSize() {
        int givenPosition = 4;
        assertFalse(board.isValidPosition(givenPosition));
    }

    @Test
    public void shouldReturnFalse_whenIsValidPosition_givenPositionSmallerThanZero() {
        int givenPosition = -1;
        assertFalse(board.isValidPosition(givenPosition));
    }

}