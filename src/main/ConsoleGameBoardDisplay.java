package main;

/**
 * Displays GameBoard in console.
 */
public class ConsoleGameBoardDisplay implements GameBoardDisplay {
    /**
     * Given GameBoard, displays this using console. Alive cell is given by "#". Dead cell is given by "."
     * @param board GameBoard to be displayed
     */
    @Override
    public void printBoard(GameBoard board) {
        boolean[][] cellBoard = board.getCellBoard(); // To avoid running getCellBoard in iteration
        for (int i = 0; i < board.getNumberOfRows(); i++) {
            for (int j = 0; j < board.getNumberOfColumns(); j++) {
                if (cellBoard[i][j]) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
