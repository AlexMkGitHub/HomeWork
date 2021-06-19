package hw2;

//Домашняя работа 2
public class Main_hw2 {
    public static void main(String[] args) {
        System.out.println("Задание 1:");
        checkSum(25, -7);
        System.out.println();

        System.out.println("Задание 2:");
        comNumb(-5);
        System.out.println();

        System.out.println("Задание 3:");
        comNumbBool(-7);
        System.out.println();

        System.out.println("Задание 4:");
        printString("Hello World!", 5);
        System.out.println();

        System.out.println("Задание 5:");
        System.out.println(leapYear(1700));

    }

    /**
     * 1. Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах от
     * 10 до 20 (включительно), если да – вернуть true, в противном случае – false.
     **/
    public static void checkSum(int a, int b) {
        boolean sum = (a + b) >= 10 && (a + b) <= 20;
        System.out.println(sum);
    }

    /**
     * 2. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
     * положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
     **/
    public static void comNumb(int c) {
        if (c < 0) {
            System.out.println("Данное число отрицательное!");
        } else {
            System.out.println("Данное число положительное!");
        }
    }

    /**
     * 3. Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true,
     * если число отрицательное, и вернуть false если положительное
     **/
    public static void comNumbBool(int c) {
        boolean com = c < 0;
        System.out.println(com);
    }

    /**
     * 4. Написать метод, которому в качестве аргументов передается строка и число, метод должен отпечатать в консоль
     * указанную строку, указанное количество раз;
     **/
    public static void printString(String word, int d) {
        while (d > 0) {
            System.out.println(word);
            d--;
        }
    }

    /**
     * 5. * Написать метод, который определяет, является ли год високосным, и возвращает boolean (високосный - true,
     * не високосный - false). Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
     **/

    public static boolean leapYear(int year) {
        int remainder1 = year % 4;
        int remainder2 = year % 100;
        int remainder3 = year % 400;
        return (remainder1 == 0 && remainder2 != 0) || (remainder2 == 0 && remainder3 == 0);
    }
}





