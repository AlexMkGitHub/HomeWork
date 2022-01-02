package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TesterClass {

    public static void start(Class testClass) {

        Method[] tcMethods = testClass.getDeclaredMethods();
        int beforeSuite = 0;
        int afterSuite = 0;

// Проверка на наличие только одной аннотации @BeforeSuite и @AfterSuite.
        for (Method pm : tcMethods) {
            if (pm.isAnnotationPresent(BeforeSuite.class)) {
                beforeSuite++;
                if (beforeSuite > 1) {
                    throw new RuntimeException("@test.BeforeSuite запущено более одного раза!");
                }
            }
            if (pm.isAnnotationPresent(AfterSuite.class)) {
                afterSuite++;
                if (afterSuite > 1) {
                    throw new RuntimeException("@test.AfterSuite запущено более одного раза!");
                }
            }
        }

// Вывод тестов согласно приоритета в @Test
        for (int i = 0; i < 10; i++) {

            for (Method m : tcMethods) {
                if (m.isAnnotationPresent(BeforeSuite.class)) {
                    if (m.getAnnotation(BeforeSuite.class).priority() == i) {
                        System.out.println(m.getAnnotation(BeforeSuite.class).message());
                    }
                }

                if (m.isAnnotationPresent(AfterSuite.class)) {
                    if (m.getAnnotation(AfterSuite.class).priority() == i) {
                        System.out.println(m.getAnnotation(AfterSuite.class).message());
                    }
                }

                if (m.isAnnotationPresent(Test.class)) {
                    if (m.getAnnotation(Test.class).priority() == i) {
                        try {
                            m.invoke(null);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
    }
}
