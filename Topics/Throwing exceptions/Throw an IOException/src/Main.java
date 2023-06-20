import java.io.IOException;

public class Main {

    static final int BOARD_SIZE = 10;

    // change this method
    public static void method() {
        char[][] board = new char[BOARD_SIZE ][BOARD_SIZE];
//        board[0][0] = ' ';
////        for (int i = 1; i <= BOARD_SIZE; i++) {
////            i > 9 ? board[0][i] = (char) ('0' + i) : board[0][i] = ;
////            board[i][0] = (char) ('A' + i - 1);
////        }
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
}
