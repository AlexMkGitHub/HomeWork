package hw3;

import java.util.Arrays;
import java.util.Random;

public class Main_hw3 {

    public static void main(String[] args) {

        /*
          1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
          С помощью цикла и условия заменить 0 на 1, 1 на 0;
        */
        System.out.println("\nЗадание 1");
        int[] arry1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println(Arrays.toString(arry1));
        for (int i = 0; i < arry1.length; i++) {
            if (arry1[i] == 0) {
                arry1[i] += 1;
            } else {
                arry1[i] -= 1;
            }
        }
        System.out.println(Arrays.toString(arry1));

        /*
          2. Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его
          значениями 1 2 3 4 5 6 7 8 … 100;
         */
        System.out.println("\nЗадание 2");
        int[] arry2 = new int[100];
        arry2[0] = 1;
        for (int i = 1; i < arry2.length; i++) {
            arry2[i] = arry2[i - 1] + 1;
        }
        System.out.println(Arrays.toString(arry2));

        /*
          3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом,
          и числа меньшие 6 умножить на 2;
         */
        System.out.println("\n Задание 3");
        int[] arry3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int x : arry3) {
            System.out.printf("%-3d", x);
        }
        for (int i = 0; i < arry3.length; i++) {
            if (arry3[i] < 6) {
                arry3[i] *= 2;
            }
        }
        System.out.println();
        for (int x : arry3) {
            System.out.printf("%-3d", x);
        }

        /*
          4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
          и с помощью цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из диагоналей,
          если обе сложно). Определить элементы одной из диагоналей можно по следующему принципу: индексы таких
          элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n];
        */
        System.out.println();
        System.out.println("\nЗадание 4");
        int[][] arry4 = new int[5][5];
        for (int i = 0; i < arry4.length; i++) {
            for (int j = 0; j < arry4[i].length; j++) {
                if (i == j) {
                    arry4[i][j] = 1;
                }
                if (i + j == arry4.length - 1) {
                    arry4[i][j] = 1;
                }
                System.out.printf("%-3d", arry4[i][j]);
            }
            System.out.println();
        }

        // Задание 5
        System.out.println("\nЗадание 5");
        int[] arryPrint = arryOut(8, 79);
        System.out.println(Arrays.toString(arryPrint));

        /*
         * 6. * Задать одномерный массив и найти в нем минимальный и максимальный элементы ;
         */
        System.out.println("\nЗадание 6");
        Random random = new Random();
        int[] arry7 = new int[20];
        int min = 2147483647;
        int max = -2147483648;
        for (int i = 0; i < arry7.length; i++) {
            arry7[i] = random.nextInt(101);
            if (min > arry7[i]) min = arry7[i];
            if (max < arry7[i]) max = arry7[i];
        }
        System.out.println(Arrays.toString(arry7));
        System.out.println("Минимальное значение равно " + min);
        System.out.println("Максимальное значение равно " + max);
    }

    /**
     * 5. Написать метод, принимающий на вход два аргумента: len и initialValue, и возвращающий одномерный
     * массив типа int длиной len, каждая ячейка которого равна initialValue;
     **/
    public static int[] arryOut(int len, int initialValue) {
        int[] arry5 = new int[len];
        for (int i = 0; i < arry5.length; i++) {
            arry5[i] = initialValue;
        }
        return arry5;
    }
}

