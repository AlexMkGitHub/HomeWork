public class ArrayMethods {

    /**
     * Задание №1
     **/
    public int[] takeFromArray(int... arr) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                index = i + 1;
            }
        }
        int[] arrResult;
        int newIndex = arr.length - index;
        if (index == 0) {
            throw new RuntimeException();
        } else {
            arrResult = new int[newIndex];
            for (int j = 0; j < arrResult.length; j++) {

                arrResult[j] = arr[index];
                index++;
            }
        }
        return arrResult;
    }

    /**
     * Задание №2
     **/
    public Boolean checkingNumbers(int... arr) {
        int one = 0;
        int four = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                one++;
                continue;
            }
            if (arr[i] == 4) {
                four++;
                continue;
            }
            if (arr[i] != 1 || arr[i] != 4) {
                return false;
            }
        }
        return one != 0 && four != 0;
    }

}
