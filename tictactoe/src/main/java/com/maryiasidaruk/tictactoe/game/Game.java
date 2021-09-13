package com.maryiasidaruk.tictactoe.game;

import com.maryiasidaruk.tictactoe.board.Board;
import com.maryiasidaruk.tictactoe.player.Player;
import com.maryiasidaruk.tictactoe.player.PlayerFactory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class Game {

    private static final String PLAYER_MARK_ON_MESSAGE = "%s:";
    private static final String INVALID_COORDINATE_MSG = "Please check and enter valid x and y coordinate.";
    private static final String GAME_ENDS_WITH_A_DRAW_MSG = "GAME ENDS WITH A DRAW";
    private static final String PLAYER_WON_MSG = "%s WON!";

    private Board board;
    private GameConditions gameConditions;

    private List<Player> players;
    private int step = 0;

    @Autowired
    Game(Board board, PlayerFactory playerFactory, GameConditions gameConditions) {
        this.board = board;
        this.gameConditions = gameConditions;
        this.players = playerFactory.createPlayers();
    }

    public void createBoard(){
        System.out.println("Game Board Creation...");
        printBoard();
        System.out.println("Board created.");
    }

    public boolean play() {
        System.out.println(String.format(PLAYER_MARK_ON_MESSAGE, this.getCurrentPlayer().getName()));
        try {
            makeTurn();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e.getMessage() + INVALID_COORDINATE_MSG);
            printBoard();
            return true;
        }

        printBoard();
        boolean isGameContinued = isGameContinued();
        step++;
        return isGameContinued;
    }

    private void printBoard() {
        System.out.println(board);
    }

    private void makeTurn() {
        int[] position = this.getCurrentPlayer().getNextPosition(board);
        board.markCell(
                position[0],
                position[1],
                this.getCurrentPlayer().getCharacter()
        );
    }

    Player getCurrentPlayer() {
        return players.get(step % players.size());
    }

    boolean isGameContinued() {
        if (hasWinner()) {
            System.out.println(String.format(PLAYER_WON_MSG, this.getCurrentPlayer().getName()));
            return false;
        } else if (isBoardFull()) {
            System.out.println(GAME_ENDS_WITH_A_DRAW_MSG);
            return false;
        }
        return true;
    }

    private boolean isBoardFull() {
        return board.isFull();
    }

    private boolean hasWinner() {
        return gameConditions.hasWinner(board);
    }

}
