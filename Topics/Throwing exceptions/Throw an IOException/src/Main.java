import java.io.IOException;

public class Main {

    static final int BOARD_SIZE = 10;

    // change this method
    public static void method() {
        char[][] board = new char[BOARD_SIZE ][BOARD_SIZE];
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
        for (int i = 0; i < BOARD_SIZE; i++) {

            char character = (char) ('A' + i);
            System.out.print(character + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char [] updateBoard(String firstCoordinate, int range, boolean horizontal) {
        if (coordinateNear >)
    }

    boolean checkIfCorrectPoints(String firstCoordinate, String secondCoordinate, int range, boolean horizontal) throws IOException {

       if (firstCoordinate.compareTo(secondCoordinate) > 0)  {
           String temp = firstCoordinate;
           firstCoordinate = secondCoordinate;
           secondCoordinate = temp;
       }
        if (firstCoordinate.charAt(0) == secondCoordinate.charAt(0)) {
            try {
                int firstNumber = ((int) firstCoordinate.charAt(1));
                int secondNumber = ((int) secondCoordinate.charAt(1));
            }
            if (firstCoordinate.charAt(1) - secondCoordinate.charAt(1) != range) {
                throw new IOException();
            }
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

    private static int transformLettersToNumbers(char letterCoordinate)
}
