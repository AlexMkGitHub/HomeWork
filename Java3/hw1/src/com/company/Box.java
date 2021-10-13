package com.company;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private T fruit;
    private float weightOfOne;
    private int fruitAmount;
    private ArrayList<T> fruitList = new ArrayList<T>();

    public Box(T fruit, float weightOfOne, int fruitAmount) {
        this.fruit = fruit;
        this.weightOfOne = weightOfOne;
        this.fruitAmount = fruitAmount;
        this.fruitList = fruitList;
    }

    public void addFruit(int amount) {
        for (int i = 0; i < amount; i++) {
            fruitList.add(fruit);
        }
    }

    public void getFruit(int amount) {
        for (int i = 0; i < amount; i++) {
            fruitList.remove(fruit);
        }
    }

    public int amountInBox() {
        return fruitList.size();
    }

    public float getBoxWeight() {
        return fruitList.size() * weightOfOne;
    }

    public boolean compareTo(Box<? extends Fruit> b) {
        float w = fruitList.size() * weightOfOne;
        return w == b.getBoxWeight();

    }

    public void pourIntoBox(Box<T> b) {
        int s = fruitList.size();
        for (int i = 0; i < s; i++) {
            b.fruitList.add(fruit);
        }
        fruitList.removeAll(fruitList);
    }
}




