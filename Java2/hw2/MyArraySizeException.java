package hw2;

public class MyArraySizeException extends ArrayIndexOutOfBoundsException {
    private final int number;

    public MyArraySizeException(String s, int number) {
        super(s + number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
