package main;

import java.io.FileNotFoundException;

/**
 * Runs Conway's Game of Life in console. Data is collected from a given file (selected in source code).
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ConsoleGameBoardDisplay consoleDisplay = new ConsoleGameBoardDisplay();
        Game game = new Game(consoleDisplay);
        game.simulation("Board Data/blinker");
    }
}