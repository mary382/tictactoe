package com.maryiasidaruk.tictactoe.game;

import com.maryiasidaruk.tictactoe.board.Board;
import com.maryiasidaruk.tictactoe.player.Player;
import com.maryiasidaruk.tictactoe.player.PlayerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GameTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private List<Player> players = Collections.singletonList(new PlayerStub());

    @Mock
    private Player player;

    @Mock
    private PlayerFactory playerFactory;

    @Mock
    private Board board;

    @Mock
    private GameConditions gameConditions;

    @InjectMocks
    private Game game;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void shouldCreateBoard_whenCreateBoard() {
        when(playerFactory.createPlayers()).thenReturn(players);
        when(gameConditions.hasWinner(board)).thenReturn(false);
        when(board.isFull()).thenReturn(false);
        when(board.toString()).thenReturn("| | | |\n" +
                "--+-+--\n" +
                "| | | |\n" +
                "--+-+--\n" +
                "| | | |\n");

        game.createBoard();

        assertEquals("Game Board Creation...\n" +
                "| | | |\n" +
                "--+-+--\n" +
                "| | | |\n" +
                "--+-+--\n" +
                "| | | |\n" + "\n" +
                "Board created.", outputStreamCaptor.toString()
                .trim());
    }

    class PlayerStub extends Player {

        PlayerStub() {
            super("Stub", 'S');
        }

        @Override
        public int[] getNextPosition(Board board) {
            return new int[]{0, 0};
        }
    }

    @Test
    public void shouldReturnTrue_whenPlay_givenBoardGameConditionsAndPlayers() {
        when(playerFactory.createPlayers()).thenReturn(List.of(new Player(
                "X",
                'X'
        )));
        when(gameConditions.hasWinner(board)).thenReturn(false);
        when(board.isFull()).thenReturn(false);
        when(player.getNextPosition(any())).thenReturn(new int[1]);
        game.setPlayers(players);

        assertTrue(game.play());
    }

    @Test
    public void shouldReturnFalse_whenPlay_givenBoardGameFullConditionsAndPlayers() {
        when(playerFactory.createPlayers()).thenReturn(List.of(new Player(
                "X",
                'X'
        )));
        when(gameConditions.hasWinner(board)).thenReturn(false);
        when(board.isFull()).thenReturn(true);
        when(player.getNextPosition(any())).thenReturn(new int[1]);
        game.setPlayers(players);

        assertFalse(game.play());
    }

    @Test
    public void shouldReturnPlayer_whenGetCurrentPlayer_givenListOfPlayers() {
        game.setPlayers(players);
        Player expectedPlayer = new PlayerStub();

        Player actualPlayer = game.getCurrentPlayer();

        assertThat(actualPlayer).usingRecursiveComparison().isEqualTo(expectedPlayer);
    }

    @Test
    public void shouldReturnFalse_whenIsGameContinued_givenHasWinnerTrue() {
        when(gameConditions.hasWinner(any())).thenReturn(true);
        game.setPlayers(players);
        assertFalse(game.isGameContinued());
    }

    @Test
    public void shouldReturnFalse_whenIsGameContinued_givenIsBoardFullTrue() {
        when(board.isFull()).thenReturn(true);
        assertFalse(game.isGameContinued());
    }

    @Test
    public void shouldReturnTrue_whenIsGameContinued_givenIsBoardNotFullAndHasWinnerFalse() {
        when(board.isFull()).thenReturn(false);
        when(gameConditions.hasWinner(any())).thenReturn(false);
        assertTrue(game.isGameContinued());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}