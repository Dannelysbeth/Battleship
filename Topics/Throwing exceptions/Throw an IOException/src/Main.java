import java.io.IOException;
import java.util.Scanner;

public class Main {

    static final int BOARD_SIZE = 10;

    // change this method
    public static void method() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String startPoint, endPoint;
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = '~';
            }
        }
        System.out.print(" ");
        for (int j = 1; j <= BOARD_SIZE; j++) {
            System.out.print(" " + j);
        }
        System.out.println();
        printBoard(board);
//        for (int i = 0; i < BOARD_SIZE; i++) {
//
//            char character = (char) ('A' + i);
//            System.out.print(character + " ");
//            for (int j = 0; j < BOARD_SIZE; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        startPoint = scanner.next();
        endPoint = scanner.next();
        int[][] pointsCoordinates;
        pointsCoordinates = transformPoint(startPoint, endPoint);
        board = updateBoard(board, pointsCoordinates, 5);
        printBoard(board);

    }

    public static int[][] transformPoint(String firstCoordinate, String secondCoordinate) throws IOException {
        int[][] coordinatesTable = new int[2][2];
        coordinatesTable[0][0] = transformLettersToNumbers(firstCoordinate.charAt(0));
        coordinatesTable[0][1] = Character.valueOf(firstCoordinate.charAt(1));
        coordinatesTable[1][0] = transformLettersToNumbers(secondCoordinate.charAt(0));
        coordinatesTable[1][1] = Character.valueOf(secondCoordinate.charAt(1));
        return coordinatesTable;
    }

    public static char[][] updateBoard(char[][] board, int[][] intTable, int range) throws IOException {
        if (intTable[0][0] == intTable[1][0]) {
            for (int i = intTable[0][1]; i < intTable[1][1]; i++) {
                board[intTable[0][0] - 1][i - 1] = 'O';
            }
        } else if (intTable[0][1] == intTable[1][1]) {
            for (int i = intTable[0][0]; i < intTable[1][0]; i++) {
                board[i - 1][intTable[0][1] - 1] = 'O';
            }
        } else {
            throw new IOException("Smth wrong");
        }
        return board;
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            char character = (char) ('A' + i);
            System.out.print(character + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    /* Do not change code below */
    public static void main(String[] args) throws IOException {

        try {
            method();
        } catch (Exception e) {
            throw new IOException();
        }
    }

    public void setBoardGame() {

    }

    private static int transformLettersToNumbers(char letterCoordinate) throws IOException {
        int value = 0;
        switch (letterCoordinate) {
            case 'A': {
                return 1;
            }
            case 'B': {
                return 2;
            }
            case 'C': {
                return 3;
            }
            case 'D': {
                return 4;
            }
            case 'E': {
                return 5;
            }
            case 'F': {
                return 6;
            }
            case 'G': {
                return 7;
            }
            case 'H': {
                return 8;
            }
            case 'I': {
                return 9;
            }
            case 'J': {
                return 10;
            }
            default: {
                throw new IOException("Wrong letter value!");
            }
        }
    }
}
