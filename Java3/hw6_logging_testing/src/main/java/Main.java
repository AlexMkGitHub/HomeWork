import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        ArrayMethods arrayMethods = new ArrayMethods();

        //Задание №1
        System.out.println(Arrays.toString(arrayMethods.takeFromArray(8, 9, 4, 0, 3, 7)));
        System.out.println(Arrays.toString(arrayMethods.takeFromArray(8, 9, 0, 3, 7, 4)));

        System.out.println();

        //Задание №2
        System.out.println(arrayMethods.checkingNumbers(1, 1, 1, 4, 1, 1, 1, 1, 1));

    }

}
