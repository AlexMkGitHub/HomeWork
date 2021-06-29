package hw4;

import java.util.Random;
import java.util.Scanner;

public class Main_hw4 {

    static final Scanner sc = new Scanner(System.in);
    static final Random random = new Random();
    static final int SIZE = sizeGame();
    static final int DOTS_TO_WIN = numberForWin();

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;


    public static void main(String[] args) {
        System.out.println();
        initMap();
        printMap();

        while (true) {
            System.out.println();
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Вы победили!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
            System.out.println();
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Компьютер победил. Сейчас их даже в шахматы не выиграть...");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }


    public static void initMap() {
        map = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%c ", map[i][j]);
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x;
        int y;

        do {
            System.out.println("input Y X");
            y = sc.nextInt() - 1;
            x = sc.nextInt() - 1;
        } while (!isCellValid(y, x));

        map[y][x] = DOT_X;
    }

    public static void aiTurn() {
        int x;
        int y;

        do {
            y = random.nextInt(SIZE);
            x = random.nextInt(SIZE);
        } while (!isCellValid(y, x));

        map[y][x] = DOT_O;
    }

    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }

        return map[y][x] == DOT_EMPTY;
    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


    public static int sizeGame() {
        int numberN;
        do {
            System.out.println("Введите число N от 2 и более, где N*N размер игрового поля:");
            numberN = sc.nextInt();
        } while (numberN < 2);
        return numberN;
    }

    public static int numberForWin() {
        int forWin;
        do {
            System.out.println("Введите количество занятых полей для победы, начиная с 2:");
            forWin = sc.nextInt();
        } while (forWin < 2);
        return forWin;
    }

    public static boolean checkWin(char c) {
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            int sumCharX = 0;
            int sumCharY = 0;
            int sumCharD1 = 0;
            int sumCharD2 = 0;
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    if ((k == 0 && map[j][k] == c && map[j][k + 1] == c) || (k > 0 && map[j][k] == c
                            && map[j][k - 1] == c) || (k > 0 && k < SIZE - 1 && map[j][k] == c && map[j][k + 1] == c)) {
                        sumCharX++;
                    }
                    if ((j == 0 && map[j][k] == c && map[j + 1][k] == c) || (j > 0 && map[j][k] == c
                            && map[j - 1][k] == c) || (j > 0 && j < SIZE - 1 && map[j][k] == c && map[j + 1][k] == c)) {
                        sumCharY++;
                    }
                    if ((k == 0 && j == 0 && map[j][k] == c && map[j + 1][k + 1] == c) || (k > 0 && j > 0 && map[j][k] == c
                            && map[j - 1][k - 1] == c) || (k > 0 && k < SIZE - 1 && j > 0 && j < SIZE - 1 && map[j][k] == c &&
                            map[j + 1][k + 1] == c)) {
                        sumCharD1++;
                    }



                    if (sumCharX == DOTS_TO_WIN) {
                        return true;
                    }
                    if (sumCharY == DOTS_TO_WIN) {
                        return true;
                    }
                    if (sumCharD1 == DOTS_TO_WIN) {
                        return true;
                    }

                }
            }
        }
        return false;
    }
}





