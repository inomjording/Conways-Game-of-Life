package main;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * Class loads GameBoard from file. Each row is separated with \n and each element is separated with a blank space.
 * Elements converts as following:
 * "1" => true
 * "0" => false
 * else: throws IllegalArgumentException
 *
 * Possible to make interface for different loading methods.
 */
public class GameBoardLoader {
    /**
     * Opens file filename and collects data into a 2D-array cellBoard = boolean[][].
     * @param filename file location name
     * @return 2D-array boolean[][]
     * @throws FileNotFoundException if no file with filename
     * @throws IllegalArgumentException if file's format is wrong
     */
    public boolean[][] dataCollection(String filename) throws FileNotFoundException {
        File file = new File(filename);
        int[] boardDimensions = getBoardDimFromFile(file);
        boolean[][] cellBoard = new boolean[boardDimensions[1]][boardDimensions[0]];
        int lineCount = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            for (int i = 0; i < boardDimensions[0]; i++) {
                if (line[i].equals("1") || line[i].equals("0")) {
                    cellBoard[lineCount][i] = cellConverter(line[i]);
                }
                else {
                    throw new IllegalArgumentException("Invalid character " + line[i] + " at line " + lineCount + " position " + i);
                }
            }
            lineCount++;
        }
        return cellBoard;
    }

    /**
     * Based on open file, counts lines and number of elements on each line.
     * @param file opened file
     * @return dimensions = [numberOfColumns, numberOfRows]
     * @throws FileNotFoundException if file does not exist
     */
    private int[] getBoardDimFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int[] dimensions = new int[2];
        dimensions[0] = scanner.nextLine().split(" ").length;
        dimensions[1] = 1;
        while (scanner.hasNextLine()) {
            dimensions[1]++;
            scanner.nextLine();
        }
        return dimensions;
    }

    /**
     * Determines if given cell argument corresponds to an alive value (true) or dead (false). Since cell is either
     * "1" or "0", it only checks if cell == "1".
     * @param cell either given as "1" or "0" from dataCollection
     * @return either true or false
     */
    private boolean cellConverter(String cell) {
        return cell.equals("1");
    }
}
