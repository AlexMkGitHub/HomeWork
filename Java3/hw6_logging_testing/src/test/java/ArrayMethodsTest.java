import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayMethodsTest {
    ArrayMethods arrayMethods;

    @BeforeEach
    void beforeStart() {
        arrayMethods = new ArrayMethods();
    }

    @Test
    void takeFromArray() {
        int[] in = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] out = {1, 7};
        Assertions.assertArrayEquals(out, arrayMethods.takeFromArray(in));
    }

    @Test
    void takeFromArrayLastFour() {
        int[] in = {8, 9, 4, 0, 3, 7, 4};
        int[] out = {};
        Assertions.assertArrayEquals(out, arrayMethods.takeFromArray(in));
    }

    @Test
    void takeFromArrayWithThrows() {
        int[] in = {8, 9, 0, 3, 7};
        Assertions.assertThrows(RuntimeException.class,
                () -> arrayMethods.takeFromArray(in));
    }

    @Test
    void checkingNumbers() {
        Boolean in = arrayMethods.checkingNumbers(1, 1, 1, 1, 4, 1, 1, 1);
        Assertions.assertTrue(in);
    }

    @Test
    void checkingNumbersWithOutFour() {
        Boolean in = arrayMethods.checkingNumbers(1, 1, 1, 1, 1, 1, 1);
        Assertions.assertFalse(in);
    }

    @Test
    void checkingNumbersWithOtherDigit() {
        Boolean in = arrayMethods.checkingNumbers(1, 1, 1, 1, 4, 7, 1, 1, 1);
        Assertions.assertFalse(in);
    }

}