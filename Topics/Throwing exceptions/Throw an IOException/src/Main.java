import java.io.IOException;
import java.util.Scanner;

import static java.lang.Math.*;

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
        String shipName = "";
        boolean correct = false;
        boolean farEnough = false;
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells)");
        while (true) {
            startPoint = scanner.next();
            endPoint = scanner.next();
            int[][] pointsCoordinates;
            pointsCoordinates = transformPoint(startPoint, endPoint);
            correct = checkLength(pointsCoordinates, 5);
            farEnough = checkIfShipsCross(pointsCoordinates, board);
            if (correct && farEnough) {
                board = updateBoard(board, pointsCoordinates, 5);
                break;
            } else if (!farEnough) {
                System.out.println("Error! You placed it too close to another one. Try again:");
            } else {
                System.out.println("\nError! Wrong length of the Aircraft Carrier! Try again: ");
            }

        }
        printBoard(board);
        System.out.println("\nEnter the coordinates of the Battleship (4 cells)");
        while (true) {
            startPoint = scanner.next();
            endPoint = scanner.next();
            int[][] pointsCoordinates;
            pointsCoordinates = transformPoint(startPoint, endPoint);
            correct = checkLength(pointsCoordinates, 4);
            farEnough = checkIfShipsCross(pointsCoordinates, board);
            if (correct && farEnough) {
                board = updateBoard(board, pointsCoordinates, 4);
                break;
            } else if (!farEnough) {
                System.out.println("Error! You placed it too close to another one. Try again:");
            } else {
                System.out.println("\nError! Wrong length of the Battleship! Try again: ");
            }
        }

        //TODO
        printBoard(board);
        System.out.println("\nEnter the coordinates of the Submarine (3 cells)");
        while (true) {
            startPoint = scanner.next();
            endPoint = scanner.next();
            int[][] pointsCoordinates;
            pointsCoordinates = transformPoint(startPoint, endPoint);
            correct = checkLength(pointsCoordinates, 3);
            farEnough = checkIfShipsCross(pointsCoordinates, board);
            if (correct && farEnough) {
                board = updateBoard(board, pointsCoordinates, 3);
                break;
            } else if (!farEnough) {
                System.out.println("Error! You placed it too close to another one. Try again:");
            } else {
                System.out.println("\nError! Wrong length of the Submarine! Try again: ");
            }
        }

        printBoard(board);
        System.out.println("\nEnter the coordinates of the Cruiser (3 cells)");
        while (true) {
            startPoint = scanner.next();
            endPoint = scanner.next();
            int[][] pointsCoordinates;
            pointsCoordinates = transformPoint(startPoint, endPoint);
            correct = checkLength(pointsCoordinates, 3);
            farEnough = checkIfShipsCross(pointsCoordinates, board);
            if (correct && farEnough) {
                board = updateBoard(board, pointsCoordinates, 3);
                break;
            } else if (!farEnough) {
                System.out.println("Error! You placed it too close to another one. Try again:");
            } else {
                System.out.println("\nError! Wrong length of the Cruiser! Try again: ");
            }
        }

        printBoard(board);
        System.out.println("\nEnter the coordinates of the Destroyer (2 cells)");
        while (true) {
            startPoint = scanner.next();
            endPoint = scanner.next();
            int[][] pointsCoordinates;
            pointsCoordinates = transformPoint(startPoint, endPoint);
            correct = checkLength(pointsCoordinates, 2);
            farEnough = checkIfShipsCross(pointsCoordinates, board);
            if (correct && farEnough) {
                board = updateBoard(board, pointsCoordinates, 2);
                break;
            } else if (!farEnough) {
                System.out.println("Error! You placed it too close to another one. Try again:");
            } else {
                System.out.println("\nError! Wrong length of the Destroyer! Try again: ");
            }
        }
        printBoard(board);

    }

    public static int[][] transformPoint(String firstCoordinate, String secondCoordinate) throws IOException {
        int[][] coordinatesTable = new int[2][2];
        int firstNumber = Integer.parseInt(firstCoordinate.substring(1)) - 1;
        int secondNumber = Integer.parseInt(secondCoordinate.substring(1)) - 1;
        coordinatesTable[0][0] = min(transformLettersToNumbers(firstCoordinate.charAt(0)), transformLettersToNumbers(secondCoordinate.charAt(0)));
        coordinatesTable[0][1] = min(firstNumber, secondNumber);
        coordinatesTable[1][0] = max(transformLettersToNumbers(firstCoordinate.charAt(0)), transformLettersToNumbers(secondCoordinate.charAt(0)));
        coordinatesTable[1][1] = max(firstNumber, secondNumber);
        return coordinatesTable;
    }

    public static char[][] updateBoard(char[][] board, int[][] intTable, int range) throws IOException {
        if (intTable[0][0] == intTable[1][0]) {
            for (int i = intTable[0][1]; i <= intTable[1][1]; i++) {
                board[intTable[0][0]][i] = 'O';
            }
        } else if (intTable[0][1] == intTable[1][1]) {
            for (int i = intTable[0][0]; i <= intTable[1][0]; i++) {
                board[i][intTable[0][1]] = 'O';
            }
        } else {
            throw new IOException("Smth wrong");
        }
        return board;
    }

    public static void printBoard(char[][] board) {
        System.out.print(" ");
        for (int j = 1; j <= BOARD_SIZE; j++) {
            System.out.print(" " + j);
        }
        System.out.println();
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

    private static boolean checkLength(int[][] coordinatesChart, int range) {
        range--;
        boolean condition1 = abs(coordinatesChart[0][0] - coordinatesChart[1][0]) == range;
        boolean condition2 = coordinatesChart[0][0] - coordinatesChart[1][0] == 0;
        boolean condition3 = abs(coordinatesChart[0][1] - coordinatesChart[1][1]) == range;
        boolean condition4 = coordinatesChart[0][1] - coordinatesChart[1][1] == 0;

        if (!(condition1 || condition2)) {
            return false;
        } else if (!(condition3 || condition4)) {
            return false;
        } else if (condition2 && condition4) {
            return false;
        } else if (!(condition2 || condition4)) {
            return false;
        }
        return true;
    }

    private static boolean checkIfShipsCross(int[][] coordinatesChart, char[][] board) {
        if (coordinatesChart[0][0] == coordinatesChart[1][0]) {
            for (int i = coordinatesChart[0][1]; i < coordinatesChart[1][1]; i++) {
                if (i != 0 && board[coordinatesChart[0][0]][i - 1] == 'O') {
                    return false;

                }
                if (i != 9 && board[coordinatesChart[0][0]][i + 1] == 'O') {
                    return false;
                }
                if (coordinatesChart[0][0] != 0 && board[coordinatesChart[0][0] - 1][i] == 'O') {
                    return false;
                }
                if (coordinatesChart[0][0] != 9 && board[coordinatesChart[0][0] + 1][i] == 'O') {
                    return false;
                }
            }
        } else {
            for (int i = coordinatesChart[0][0]; i < coordinatesChart[1][0]; i++) {
                if (i != 0 && board[i - 1][coordinatesChart[0][0]] == 'O') {
                    return false;

                }
                if (i != 9 && board[i + 1][coordinatesChart[0][0]] == 'O') {
                    return false;
                }
                if (coordinatesChart[0][0] != 0 && board[i][coordinatesChart[0][0] - 1] == 'O') {
                    return false;
                }
                if (coordinatesChart[0][0] != 9 && board[i][coordinatesChart[0][0] + 1] == 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    private static int transformLettersToNumbers(char letterCoordinate) throws IOException {
        switch (letterCoordinate) {
            case 'A': {
                return 0;
            }
            case 'B': {
                return 1;
            }
            case 'C': {
                return 2;
            }
            case 'D': {
                return 3;
            }
            case 'E': {
                return 4;
            }
            case 'F': {
                return 5;
            }
            case 'G': {
                return 6;
            }
            case 'H': {
                return 7;
            }
            case 'I': {
                return 8;
            }
            case 'J': {
                return 9;
            }
            default: {
                throw new IOException("Wrong letter value!");
            }
        }
    }
}
