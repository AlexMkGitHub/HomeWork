import test.AfterSuite;
import test.BeforeSuite;
import test.Test;

public class PrintMessage {

    @BeforeSuite
    public static void testBegin() {
    }

    @Test(priority = 7)
    public static void printMessage1() {
        System.out.println("Method №1");
    }

    @Test(priority = 2)
    public static void printMessage2() {
        System.out.println("Method №2");
    }

    @Test(priority = 3)
    public static void printMessage3() {
        System.out.println("Method №3");
    }

    @Test(priority = 4)
    public static void printMessage4() {
        System.out.println("Method №4");
    }

    @AfterSuite
    public static void testEnd() {
    }
}