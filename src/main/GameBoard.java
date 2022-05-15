package main;

/** Creates a 2D-Array cellBoard = Boolean[numberOfRows][numberOfColumns]. Each element represents a cell.
 * A cell cellBoard[i][j] is "alive" if value == true, and "dead" if == false.
 */
public class GameBoard {
    private int numberOfRows;
    private int numberOfColumns;
    private boolean[][] cellBoard;

    /**
     * Takes int boardSize and creates a 2D-array cellBoard = boolean[boardSize][boardSize]. Each element represents a
     * dead cell with value false.
     * @param boardSize int for numberOfRows and numberOfColumns
     * @throws NegativeArraySizeException if boardSize < 0
     */
    public GameBoard(int boardSize) {
        if (boardSize < 0) {
            throw new NegativeArraySizeException("boardSize cannot be negative.");
        } else {
            numberOfRows = boardSize;
            numberOfColumns = boardSize;
            cellBoard = new boolean[numberOfRows][numberOfColumns];
        }
    }

    /**
     * Takes int numberOfRows and numberOfColumns and creates a 2D-array cellBoard = boolean[numberOfRows][numberOfColumns].
     * Each element represents a dead cell with value false.
     * @param numberOfRows int for cellBoard length
     * @param numberOfColumns int for each cellBoard[i] length
     * @throws NegativeArraySizeException if any parameter is < 0
     */
    public GameBoard(int numberOfRows, int numberOfColumns) {
        if (numberOfRows < 0 || numberOfColumns < 0) {
            throw new NegativeArraySizeException("numberOfRows and numberOfColumns cannot be negative.");
        } else {
            this.numberOfRows = numberOfRows;
            this.numberOfColumns = numberOfColumns;
            cellBoard = new boolean[numberOfRows][numberOfColumns];
        }
    }

    /**
     * Takes 2D-array initialLayout and makes a GameBoard object
     * @param initialLayout boolean[][] directly set as cellBoard
     * @throws IllegalArgumentException if initialLayout is empty
     */
    public GameBoard(boolean[][] initialLayout) {
        if (initialLayout.length == 0 || initialLayout[0].length == 0) {
            throw new IllegalArgumentException("Board must be at least size 1 x 1");
        } else {
            numberOfRows = initialLayout.length;
            numberOfColumns = initialLayout[0].length;
            cellBoard = initialLayout;
        }
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public boolean[][] getCellBoard() {
        return cellBoard;
    }

    /**
     * Determines if a cell at cellBoard[row][column] is alive next generation. Cell's behavior is dependent on whether
     * it is dead or alive at current generation.
     * If cellBoard[row][column] == true: returns true if there are 2-3 adjacent alive cells. Else returns false.
     * aliveNeighbours will count one additional "neighbour" if cellBoard[row][column] == true.
     * If cellBoard[row][column] == false: returns true if there are 3 adjacent alive cells. Else returns false.
     * @param row int for element's row
     * @param column int for element's column
     * @return true if cell at cellBoard[row][column] in next generation is alive, else false
     */
    private boolean getNextCellState(int row, int column) {
        int aliveNeighbours = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++)
            {
                if (i < 0 || j < 0 || i >= numberOfRows || j >= numberOfColumns) {
                    continue;
                }
                if (cellBoard[i][j]) {
                    aliveNeighbours += 1;
                }
            }
        }
        if (cellBoard[row][column] && (aliveNeighbours == 3 || aliveNeighbours == 4)) { // if cell at is alive, aliveNeighbours += 1
            return true;
        }
        else return !cellBoard[row][column] && aliveNeighbours == 3; // if dead cell has 3 alive neighbours returns true, else false
    }

    /**
     * Creates a new GameBoard object based on the next generation of cells. Determines each cell value with getNextCellState.
     * @return a new board with same dimensions as cellBoard
     */
    public GameBoard nextGeneration() {
        boolean[][] nextGeneration = new boolean[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                nextGeneration[i][j] = getNextCellState(i, j);
            }
        }
        return new GameBoard(nextGeneration);
    }
}
