package hw2;


public class Exceptions {

    public int badArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;

        if (array.length != 4) {
            throw new MyArraySizeException("Неверный размер массива. Количество строк должно быть равно 4, а не ",
                    array.length);
        }

        for (int i = 0; i < array.length; i++) {

            int x = 0;
            if (array[i].length != 4) {
                throw new MyArraySizeException("Неверный размер массива. Количество столбцов должно быть равно 4, " +
                        "а не ", array[i].length);
            }

            for (int j = 0; j < array.length; j++) {
                try {
                    x = Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Недопустимое значение в массиве ", i, " ", j);
                }
                sum = sum + x;
            }
        }
        return sum;
    }
}



