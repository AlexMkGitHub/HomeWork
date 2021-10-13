package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ArrayMethods<T> {
    private T[] arr;

    public ArrayMethods(T... arr) {
        this.arr = arr;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    //1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
    public void elemArrayChange(int index1, int index2) {

        if (index1 > arr.length - 1 || index2 > arr.length - 1) {
            System.out.println("Значения выходят за пределы массива! Преобразование невозможно!");
        } else {
            T interimElement = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = interimElement;
        }
    }

    //2. Написать метод, который преобразует массив в ArrayList;
    public ArrayList<T> arrayToList() {
        return  new ArrayList<T>(Arrays.asList(arr));
    }

}
