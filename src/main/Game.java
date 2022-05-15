package main;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Generates a Game of Life using any given GameBoardDisplay. Controls game from System.in (there is a lot of room for
 * improvement here).
 */
public class Game {
    private GameBoardDisplay boardDisplay;

    /**
     * Constructor for Game.
     * @param boardDisplay GameBoardDisplay to visualize game
     */
    public Game(GameBoardDisplay boardDisplay) {
        this.boardDisplay = boardDisplay;
    }

    /**
     * Loads file given a filename and generates a GameBoard. Game is then displayed using boardDisplay.
     * Controls Game by input from System.in. If input is "" (by the user pressing enter), displays next generation of
     * cells by creating a new GameBoard using nextGeneration. If input is "e", the program exits. If any other input,
     * nothing happens.
     * @param filename name of file location
     * @throws FileNotFoundException if file does not exist
     */
    public void simulation(String filename) throws FileNotFoundException {
        GameBoardLoader loader = new GameBoardLoader();
        GameBoard gameBoard = new GameBoard(loader.dataCollection(filename));
        boardDisplay.printBoard(gameBoard);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            switch (scanner.nextLine()) {
                case "":
                    gameBoard = gameBoard.nextGeneration();
                    boardDisplay.printBoard(gameBoard);
                    break;
                case "e":
                    return;
                default:
                    break;
            }
        }
    }
}
