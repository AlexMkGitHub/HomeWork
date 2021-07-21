package Java1.hw7;

public class Cat {
    public String name;
    private int needFood = 10;
    public boolean catEat = false;

    public Cat(String name, int needFood) {
        this.name = name;
        this.needFood = needFood;
    }

    public String getName() {
        return name;
    }

    public int getNeedFood() {
        return needFood;
    }

    public boolean catCanEat(Plate plate) {
        if (getNeedFood() <= 0) {
            System.out.println("Кот " + getName() + " есть не хочит! Его уровень голода равен " + getNeedFood());
            System.out.println();
            return catEat = false;
        }
        if (getNeedFood() <= plate.getPlateSize()) {
            catEat = true;
            plate.setPlateSize(plate.getPlateSize() - getNeedFood());
            System.out.println("Спасибо! Кот " + getName() + " съел " + getNeedFood());
            System.out.println();
            return true;
        } else {
            System.out.println("Коту " + getName() + " не хватает еды! Ему надо " + getNeedFood() +
                    " еды! В сейчас в миске " + plate.getPlateSize() + " еды!");
            System.out.println();
            return catEat = false;

        }

    }
}

