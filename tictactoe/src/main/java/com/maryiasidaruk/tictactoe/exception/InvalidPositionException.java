package com.maryiasidaruk.tictactoe.exception;

/**
 * Exception if position for mark cell is invalid.
 */
public class InvalidPositionException extends RuntimeException {

    public InvalidPositionException(String message) {
        super(message);
    }

}
