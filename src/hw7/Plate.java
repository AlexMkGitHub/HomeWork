package hw7;

public class Plate {
    private int plateSize;
    public int plateSizeOriginal;

    public Plate(int plateSize) {
        this.plateSize = plateSize;
        this.plateSizeOriginal = plateSize;

    }

    public int getPlateSize() {
        return plateSize;
    }

    public void setPlateSize(int plateSize) {
        this.plateSize = plateSize;
    }

    public int addFood(int addFood) {
        if (addFood < 0 || addFood == 0) {
            System.out.println("Жадина!!! Добавь побольше еды!!!");
            System.out.println("Сейчас в миске " + plateSize + " еды");
            System.out.println("Вы можете ещё добавить " + (plateSizeOriginal - plateSize) + " еды!\n");
            return plateSize;
        } else {
            if (plateSizeOriginal == plateSize) {
                System.out.println("Миска полная!\n");
                return plateSize;
            } else {
                if (addFood + plateSize > plateSizeOriginal) {
                    System.out.println("Вы можете добавить только " + (plateSizeOriginal - plateSize) + " еды!");
                    System.out.println("Сейчас в миске " + plateSize + " еды\n");
                    return plateSize;
                } else {
                    if (addFood + plateSize <= plateSizeOriginal) {
                        plateSize = plateSize + addFood;
                        System.out.println("Спасибо, что добавили еды!");
                        System.out.println("Сейчас в миске " + plateSize + " еды");
                        System.out.println("Вы можете ещё добавить " + (plateSizeOriginal - plateSize) + " еды!\n");
                    }
                    return plateSize;
                }
            }
        }
    }
}
