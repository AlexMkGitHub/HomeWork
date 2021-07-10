package hw7;

public class Main {

    public static void main(String[] args) {
        Plate plate = new Plate(50);
        Cat[] cat = {
                new Cat("Мурзик", -10),
                new Cat("Васька", 15),
                new Cat("Рыжий", 7),
                new Cat("Марс", 10),
                new Cat("Вредный", 12),
                new Cat("Бродяга", 17),
                new Cat("Котофей", 9)
        };

        for (Cat value : cat) {
            value.catCanEat(plate);
        }
        plate.addFood(20);
        cat[3].catCanEat(plate);
        cat[5].catCanEat(plate);
        plate.addFood(100);
    }
}