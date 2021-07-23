package hw2;

public class MyArrayDataException extends NumberFormatException {
    private final int number1;
    private final int number2;


    public MyArrayDataException(String s1, int number1, String s2, int number2) {
        super(s1 + number1 + s2 + number2);
        this.number1 = number1;
        this.number2 = number2;
    }

}


