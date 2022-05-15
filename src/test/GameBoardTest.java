package test;

import main.GameBoard;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Game of Life rules.
 */
class GameBoardTest {
    /**
     * Tests GameBoard's size
     */
    @Test
    void testBoardSizeRows() {
        GameBoard board = new GameBoard(10);
        assertEquals(10, board.getNumberOfRows());
    }

    /**
     * Tests nextGeneration if GameBoard only contains false elements
     */
    @Test
    void testNextGenerationFalseBoard() {
        GameBoard board = new GameBoard(3);
        GameBoard nextGeneration = board.nextGeneration();
        for (int i = 0; i < nextGeneration.getCellBoard().length; i++) {
            boolean[] booleans = nextGeneration.getCellBoard()[i];
            for (boolean cell : booleans) {
                assertFalse(cell);
            }
        }
    }

    /**
     * Tests nextGeneration if GameBoard only contains true elements
     */
    @Test
    void testNextGenerationTrueBoard() {
        boolean[][] initialBoard = new boolean[3][3];
        for (boolean[] booleans : initialBoard) {
            Arrays.fill(booleans, true);
        }
        GameBoard board = new GameBoard(initialBoard);
        GameBoard nextGeneration = board.nextGeneration();
        assertTrue(nextGeneration.getCellBoard()[0][0]);
        assertTrue(nextGeneration.getCellBoard()[2][0]);
        assertTrue(nextGeneration.getCellBoard()[0][2]);
        assertTrue(nextGeneration.getCellBoard()[2][2]);
    }

    /**
     * Tests nextGeneration for a GameBoard with size 1 x 1
     */
    @Test
    void testNextGenerationBoardSize1x1() {
        boolean[][] initialBoard = new boolean[1][1];
        initialBoard[0][0] = true;
        GameBoard board = new GameBoard(initialBoard);
        GameBoard nextGeneration = board.nextGeneration();
        assertFalse(nextGeneration.getCellBoard()[0][0]);
    }
}