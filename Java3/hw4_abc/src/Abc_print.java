public class Abc_print {
    private final  Object MON = new Object();
    private final  int NUM = 5;
    private volatile char letter = 'A';

        /*Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
         Используйте wait/notify/notifyAll.
         */
    public static void main(String[] args) throws InterruptedException {
        Abc_print abc = new Abc_print();
        Thread thread1 = new Thread(abc::printA);
        Thread thread2 = new Thread(abc::printB);
        Thread thread3 = new Thread(abc::printC);

        System.out.println("start");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println();
        System.out.println("end");
    }

    public void printA() {
        synchronized (MON) {
            try {
                for (int i = 0; i < NUM; i++) {
                    while (letter != 'A') {
                        MON.wait();
                    }
                    System.out.print("A");
                    letter = 'B';
                    MON.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (MON) {
            try {
                for (int i = 0; i < NUM; i++) {
                    while (letter != 'B') {
                        MON.wait();
                    }
                    System.out.print("B");
                    letter = 'C';
                    MON.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void printC() {
        synchronized (MON) {
            try {
                for (int i = 0; i < NUM; i++) {
                    while (letter != 'C') {
                        MON.wait();
                    }
                    System.out.print("C");
                    letter = 'A';
                    MON.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
