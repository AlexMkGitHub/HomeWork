package hw2;

public class Main {

    public static void main(String[] args) {

        Exceptions exep = new Exceptions();

        String[][] str = {
                {"45", "21", "98", "37"},
                {"157", "1", "27", "709"},
                {"44", "078", "2", "12"},
                {"32", "889", "321", "43"},
//              {"2", "ffff", "578"},
//              {"2", "21", "578"},
        };

        System.out.println("Сумма чисел в массиве равна " + exep.badArray(str));
    }
}
