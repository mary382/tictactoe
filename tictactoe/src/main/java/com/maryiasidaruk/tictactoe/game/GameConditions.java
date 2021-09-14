package com.maryiasidaruk.tictactoe.game;

import com.maryiasidaruk.tictactoe.board.Board;
import com.maryiasidaruk.tictactoe.board.Cell;
import org.springframework.stereotype.Component;

@Component
public class GameConditions {

    /**
     * Checks if there is a win on the board.<br>
     * Rules to win:
     * <ul>
     *     <li>All elements in a row are one type chars</li>
     *     <li>All elements in a column are one type chars</li>
     *     <li>All elements in a diagonal are one type chars</li>
     * </ul>
     *
     * @param board
     * @return
     */
    public boolean hasWinner(Board board) {
        return isColumnFull(board)
                || this.isRowFull(board)
                || this.isMainDiagonalFull(board)
                || this.isSecondaryDiagonalFull(board);
    }

    private boolean isRowFull(Board board) {
        int boardIndexLimit = board.getSize() - 1;
        for (int i = 0; i <= boardIndexLimit; i++) {
            Cell firstCell = board.getCell(i, 0);
            for (int j = 0; j <= boardIndexLimit; j++) {
                Cell currentCell = board.getCell(i, j);
                if (currentCell.isEmpty() || !currentCell.equals(firstCell)) {
                    break;
                }
                if (j == boardIndexLimit) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isColumnFull(Board board) {
        int boardIndexLimit = board.getSize() - 1;
        for (int i = 0; i <= boardIndexLimit; i++) {
            Cell firstCell = board.getCell(0, i);
            for (int j = 0; j <= boardIndexLimit; j++) {
                Cell currentCell = board.getCell(j, i);
                if (currentCell.isEmpty() || !currentCell.equals(firstCell)) {
                    break;
                }
                if (j == boardIndexLimit) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isMainDiagonalFull(Board board) {
        int boardIndexLimit = board.getSize() - 1;
        Cell firstCell = board.getCell(0, 0);
        for (int i = 0; i <= boardIndexLimit; i++) {
            Cell currentValue = board.getCell(i, i);
            if (currentValue.isEmpty() || !currentValue.equals(firstCell)) {
                break;
            }
            if (i == boardIndexLimit) {
                return true;
            }
        }
        return false;
    }

    private boolean isSecondaryDiagonalFull(Board board) {
        int boardIndexLimit = board.getSize() - 1;
        Cell firstCell = board.getCell(0, boardIndexLimit);
        for (int i = 0; i <= boardIndexLimit; i++) {
            Cell checkedValue = board.getCell(i, boardIndexLimit - i);
            if (checkedValue.isEmpty() || !checkedValue.equals(firstCell)) {
                break;
            }
            if (i == boardIndexLimit) {
                return true;
            }
        }
        return false;
    }
}
