package com.maryiasidaruk.tictactoe.board;

import com.maryiasidaruk.tictactoe.exception.InvalidPositionException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * The board of tic-tac-toe.
 */
@Data
@Component
public class Board {

    private Cell[][] cells;

    @Autowired
    public Board(@Value("${boardSize}") int size) {
        this.cells = new Cell[size][size];
        initializeCells();
    }

    /**
     * Create board will all empty cells.
     *
     * @return empty cells
     */
    Cell[][] initializeCells() {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells.length; y++) {
                cells[x][y] = new Cell(' ');
            }
        }
        return cells;
    }

    public int getSize() {
        return cells.length;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    /**
     * Checks is there are no empty cells on the board.
     *
     * @return true if board has no empty cells
     */
    public boolean isFull() {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .noneMatch(Cell::isEmpty);
    }

    /**
     * Set given character to the cell with the position [x,y].
     *
     * @param x - x coordinate of the position to mark
     * @param y - y coordinate of the position to mark
     * @param c - char to set to the cell
     */
    public void markCell(int x, int y, char c) {
        if (isValidPosition(x) && isValidPosition(y) && cells[x][y].isEmpty()) {
            cells[x][y].setCharacter(c);
        } else {
            throw new InvalidPositionException("Invalid position was entered.\n");
        }
    }

    /**
     * Checks is position between 0 and size of the board.
     *
     * @param position - x or y coordinate
     * @return true if boardSize >=position >=0
     */
    boolean isValidPosition(int position) {
        return position >= 0 && position <= cells.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < cells.length; y++) {
            sb.append('|');
            for (int x = 0; x < cells.length; x++) {
                sb.append(cells[x][y].getCharacter()).append('|');
            }
            if (y < cells.length - 1) {
                sb.append("\n--+-+--\n");
            }
        }
        return sb.toString();
    }

}
