package hw5;

import java.util.Arrays;

public class SecondMethod {

    private static final int SIZE = 10000000;
    private static final int HALF = SIZE / 2;
    private static float[] arr = new float[SIZE];

    // Создаем два массива для левой и правой части исходного
    private static final float[] leftHalf = new float[HALF];
    private static final float[] rightHalf = new float[HALF];

    public static void secondMethod() throws InterruptedException {

        Arrays.fill(arr, 1.0f);
        long startTime = System.currentTimeMillis();

        // Копируем в них значения из большого массива
        System.arraycopy(arr, 0, leftHalf, 0, 5000000);
        System.arraycopy(arr, 5000000, rightHalf, 0, 5000000);
        System.out.println("Array split time: " + (System.currentTimeMillis() - startTime) + " ms.");

        // Запускает два потока и параллельно просчитываем каждый малый массив
        Thread t1 = new Thread(() -> {
            long startTime2 = System.currentTimeMillis();
            mathMethod(leftHalf);
            System.out.println("t1 thread: " + (System.currentTimeMillis() - startTime2) + " ms.");
        });
        Thread t2 = new Thread(() -> {
            long startTime3 = System.currentTimeMillis();
            mathMethod(rightHalf);
            System.out.println("t2 thread: " + (System.currentTimeMillis() - startTime3) + " ms.");
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Склеиваем малые массивы обратно в один большой
        long startTime4 = System.currentTimeMillis();
        float[] mergedArray = new float[SIZE];
        System.arraycopy(leftHalf, 0, mergedArray, 0, 5000000);
        System.arraycopy(rightHalf, 0, mergedArray, 5000000, 5000000);
        System.out.println("Arrays geting after: " + (System.currentTimeMillis() - startTime4) + " ms.");

        System.out.println("Total running time: " + (System.currentTimeMillis() - startTime) + " ms.");

    }

    static void mathMethod(float[] arry) {
        for (int i = 0; i < arry.length; i++) {
            arry[i] = (float) (arry[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

}
