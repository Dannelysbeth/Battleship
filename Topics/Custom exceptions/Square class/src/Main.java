import java.util.Scanner;

class Square {
    int a;

    public Square(int a) throws SquareSizeException {
        if (a > 0) this.a = a;
        else ; //put you code here

    }
}

class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int a = scn.nextInt();
        //put your code here
        Square square = new Square(a);

    }
}

class SquareSizeException extends Exception {
    public SquareSizeException(String message) {
        super(message);
    }
}