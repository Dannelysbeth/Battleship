import java.io.IOException;
import java.util.Scanner;

import static java.lang.Math.*;

public class Main {
    enum Ship {
        AC("Aircraft Carrier", 5), B("Battleship", 4), S("Submarine", 3), C("Cruiser", 3), D("Destroyer", 2);
        private final String name;
        private final int length;

        Ship(String name, int length) {
            this.name = name;
            this.length = length;
        }
    }

    static final int BOARD_SIZE = 10;

    static final char EMPTY = '~';
    static final char MISS = 'M';
    static final char HIT = 'X';
    static final char SHIP = 'O';

    int[][] myBoard;
    int[][] componentsBoard;

    private static char[][] initBoard() {
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
        return board;
    }

    private static char[][] placeShipOnBoard(char[][] board, Ship ship, Scanner scanner) {
        boolean correct;
        boolean farEnough;
        while (true) {
            String startPoint = scanner.next();
            String endPoint = scanner.next();
            int[][] pointsCoordinates;
            pointsCoordinates = transformPoints(startPoint, endPoint);
            correct = checkLength(pointsCoordinates, ship.length);
            farEnough = checkIfShipsCross(pointsCoordinates, board);
            if (correct && farEnough) {
                board = setBoard(board, pointsCoordinates);
                break;
            } else if (!farEnough) {
                System.out.println("Error! You placed it too close to another one. Try again:");
            } else {
                System.out.println("\nError! Wrong length of the " + ship.name + "! Try again: ");
            }
        }
        return board;
    }

    private static char[][] setBoard(char[][] board, Scanner scanner) {
        printBoard(board);

        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells)");
        board = placeShipOnBoard(board, Ship.AC, scanner);
        printBoard(board);


        System.out.println("\nEnter the coordinates of the Battleship (4 cells)");
        board = placeShipOnBoard(board, Ship.B, scanner);
        printBoard(board);


        System.out.println("\nEnter the coordinates of the Submarine (3 cells)");
        board = placeShipOnBoard(board, Ship.S, scanner);

        printBoard(board);
        System.out.println("\nEnter the coordinates of the Cruiser (3 cells)");
        board = placeShipOnBoard(board, Ship.C, scanner);

        printBoard(board);
        System.out.println("\nEnter the coordinates of the Destroyer (2 cells)");
        board = placeShipOnBoard(board, Ship.D, scanner);
        printBoard(board);

        return board;
    }

    private static char[][] maskBoard(char[][] boardToMask) {
        char[][] maskedBoard = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (boardToMask[i][j] == SHIP) {
                    maskedBoard[i][j] = EMPTY;
                } else {
                    maskedBoard[i][j] = boardToMask[i][j];
                }
            }
        }
        return maskedBoard;
    }

    private static void printPlayersView(char[][] hittingBoard, char[][] myBoard) {
        System.out.println("\n");
        printBoard(maskBoard(hittingBoard));
        System.out.println("---------------------");
        printBoard(myBoard);
    }

    private static boolean checkIfSankAShip(char [] [] board, int fCoordinate, int sCoordinate) {
        if (fCoordinate < BOARD_SIZE - 1 && board[fCoordinate + 1] [sCoordinate] == SHIP){
            return false;
        } else if (fCoordinate > 1 && board[fCoordinate - 1] [sCoordinate] == SHIP) {
            return false;
        } else if (sCoordinate < BOARD_SIZE - 1 && board[fCoordinate] [sCoordinate + 1] == SHIP) {
            return false;
        } else if (sCoordinate > 1 && board[fCoordinate] [sCoordinate - 1] == SHIP) {
            return false;
        }
        return true;
    }

    private static char[][] shoot(char[][] hittingBoard, Scanner scanner) {
        String shot = scanner.next();
        int[][] pointCoordinate;

        while (true) {
            pointCoordinate = transformPoint(shot);
            if (checkCoordinates(pointCoordinate)) {
                break;
            }
            System.out.println("Error! You entered the wrong coordinates! Try again:\n");
            shot = scanner.next();
        }

        int fCoordinate = pointCoordinate[0][0];
        int sCoordinate = pointCoordinate[0][1];
        if (hittingBoard[fCoordinate][sCoordinate] == SHIP || hittingBoard[fCoordinate][sCoordinate] == HIT) {
            hittingBoard[fCoordinate][sCoordinate] = HIT;
            System.out.print("You hit a ship! ");
            if(checkIfSankAShip(hittingBoard, fCoordinate, sCoordinate)){
                System.out.println("You sank a ship!");
            }
        } else {
            hittingBoard[fCoordinate][sCoordinate] = MISS;
            System.out.print("You missed! ");
        }

        return hittingBoard;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    // change this method
    public static void method() throws IOException {
        Scanner scanner = new Scanner(System.in);
        char[][] player1Board = initBoard();
        char[][] player2Board = initBoard();
        System.out.println("Player 1, place your ships on the game field");
        setBoard(player1Board, scanner);

        System.out.println("\nPress Enter and pass the move to another player\n");
//        scanner.nextLine(); // Wait for the Enter key press.
        System.in.read();
        clearScreen();

        System.out.println("Player 2, place your ships on the game field");
        setBoard(player2Board, scanner);



        boolean gameOn = true;
        boolean player1 = true;
        System.out.println("Press Enter and pass the move to another player");
        System.in.read();
        clearScreen();
        while (gameOn) {
            if (player1) {
                printPlayersView(player2Board, player1Board);
                System.out.println("\nPlayer 1, it's your turn: \n");
                player2Board = shoot(player2Board, scanner);
                if (checkIfWon(player2Board)) {
                    System.out.println("\nYou sank the last ship. You won. Congratulations!");
                    gameOn = false;
                }
                player1 = false;
            } else {
                printPlayersView(player1Board, player2Board);
                System.out.println("\nPlayer 2, it's your turn: \n");
                player1Board = shoot(player1Board, scanner);
                if (checkIfWon(player1Board)) {
                    System.out.println("\nYou sank the last ship. You won. Congratulations!");
                    gameOn = false;
                }
                player1 = true;
            }
            System.out.println("\nPress Enter and pass the move to another player\n");
            System.in.read();
            clearScreen();
        }


    }

    private static boolean checkIfWon(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] transformPoints(String firstCoordinate, String secondCoordinate) {
        int[][] coordinatesTable = new int[2][2];
        int firstNumber = Integer.parseInt(firstCoordinate.substring(1)) - 1;
        int secondNumber = Integer.parseInt(secondCoordinate.substring(1)) - 1;
        coordinatesTable[0][0] = min(transformLettersToNumbers(firstCoordinate.charAt(0)), transformLettersToNumbers(secondCoordinate.charAt(0)));
        coordinatesTable[0][1] = min(firstNumber, secondNumber);
        coordinatesTable[1][0] = max(transformLettersToNumbers(firstCoordinate.charAt(0)), transformLettersToNumbers(secondCoordinate.charAt(0)));
        coordinatesTable[1][1] = max(firstNumber, secondNumber);
        return coordinatesTable;
    }

    public static int[][] transformPoint(String coordinate) {
        int[][] coordinatesTable = new int[1][2];
        int number = Integer.parseInt(coordinate.substring(1)) - 1;

        coordinatesTable[0][0] = transformLettersToNumbers(coordinate.charAt(0));
        coordinatesTable[0][1] = number;

        return coordinatesTable;

    }

    static boolean checkCoordinates(int[][] coordinates) {
        return coordinates[0][0] < 10 && coordinates[0][1] < 10;
    }


    public static char[][] setBoard(char[][] board, int[][] intTable) {
        if (intTable[0][0] == intTable[1][0]) {
            for (int i = intTable[0][1]; i <= intTable[1][1]; i++) {
                board[intTable[0][0]][i] = 'O';
            }
        } else if (intTable[0][1] == intTable[1][1]) {
            for (int i = intTable[0][0]; i <= intTable[1][0]; i++) {
                board[i][intTable[0][1]] = 'O';
            }
        } else {
            //throw new IOException("Smth wrong");
        }
        return board;
    }

    public static char[][] updateBoard(char[][] board, int[][] coordinate) {
        int firstCoordinate = coordinate[0][0];
        int secondCoordinate = coordinate[0][1];
        if (board[firstCoordinate][secondCoordinate] == 'O' || board[firstCoordinate][secondCoordinate] == 'X') {
            board[firstCoordinate][secondCoordinate] = 'X';
        } else {
            board[firstCoordinate][secondCoordinate] = 'M';
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
        } else return condition2 || condition4;
    }


    private static boolean checkIfShipsCross(int[][] coordinatesChart, char[][] board) {
        if (coordinatesChart[0][1] != coordinatesChart[1][1]) {
            for (int i = coordinatesChart[0][1]; i <= coordinatesChart[1][1]; i++) {
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
            for (int i = coordinatesChart[0][0]; i <= coordinatesChart[1][0]; i++) {
                if (i != 0 && board[i - 1][coordinatesChart[0][1]] == 'O') {
                    return false;

                }
                if (i != 9 && board[i + 1][coordinatesChart[0][1]] == 'O') {
                    return false;
                }
                if (coordinatesChart[0][1] != 0 && board[i][coordinatesChart[0][1] - 1] == 'O') {
                    return false;
                }
                if (coordinatesChart[0][1] != 9 && board[i][coordinatesChart[0][1] + 1] == 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    private static int transformLettersToNumbers(char letterCoordinate) {
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
                return 10;
            }
        }
    }
}
