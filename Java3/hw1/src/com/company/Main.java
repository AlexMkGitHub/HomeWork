package com.company;

public class Main {

    public static void main(String[] args) {

        ArrayMethods<Integer> numbers = new ArrayMethods<Integer>(1, 7, 4, 5, 11, 90, 5, 123, 9, 345, 8, 9, 0, 3, 79, 170, 670);
        numbers.elemArrayChange(1, 12);
        System.out.println(numbers);
        System.out.println();
        ArrayMethods<String> strings = new ArrayMethods<String>("Васька", "Мурзик", "Барсик", "Маркиз", "Задира");
        strings.elemArrayChange(2, 3);
        System.out.println();
        System.out.println(strings);
        System.out.println();
        strings.arrayToList();
        System.out.println(strings);
        System.out.println();

        Apple apple = new Apple();
        Orange orange = new Orange();
        Box<Apple> appleBox = new Box<Apple>(apple, 1f, 30);
        Box<Orange> orangeBox = new Box<Orange>(orange, 1.5f, 24);
        Box<Orange> orangeBox2 = new Box<Orange>(orange, 1.5f, 20);


        appleBox.addFruit(10);
        orangeBox.addFruit(5);

        appleBox.addFruit(12);
        orangeBox.addFruit(57);
        System.out.println(appleBox.amountInBox());
        System.out.println(orangeBox.amountInBox());
        appleBox.getFruit(4);
        orangeBox.getFruit(8);
        System.out.println(appleBox.amountInBox());
        System.out.println(orangeBox.amountInBox());

        System.out.println(appleBox.getBoxWeight());
        System.out.println(orangeBox.getBoxWeight());

        System.out.println(appleBox.compareTo(orangeBox));
        System.out.println();

        System.out.println(orangeBox.amountInBox());
        System.out.println(orangeBox2.amountInBox());
        System.out.println(orangeBox.getBoxWeight());
        System.out.println(orangeBox2.getBoxWeight());

        orangeBox.pourIntoBox(orangeBox2);
        System.out.println();
        System.out.println(orangeBox.amountInBox());
        System.out.println(orangeBox2.amountInBox());
        System.out.println(orangeBox.getBoxWeight());
        System.out.println(orangeBox2.getBoxWeight());

        orangeBox2.pourIntoBox(orangeBox);
        System.out.println(orangeBox.getBoxWeight());


    }
}
