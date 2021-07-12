package hw8;

import javax.swing.*;
import java.util.Random;

public class Logic extends BattleMap {
    static int SIZE = 3;
    static int DOTS_TO_WIN = 3;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    static final Random random = new Random();
    static boolean isGameFinished;

    public Logic(GameWindow gameWindow) {
        super(gameWindow);
    }

    public static void go() {
        isGameFinished = true;
        printMap();
        if (checkWinLines(DOT_X, DOTS_TO_WIN)) {
            System.out.println("Вы победили!");
            System.out.println("Координаты:" + Logic.x1 + " " + Logic.y1 + "  " + Logic.x2 + " " + Logic.y2);
            JOptionPane.showMessageDialog(null, "ПОЗДРАВЛЯЮ! Вы ВЫИГРАЛИ!!!",
                    "ПОБЕДА!!!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (isFull()) {
            System.out.println("Ничья");
            System.out.println("Координаты:" + Logic.x1 + " " + Logic.y1 + "  " + Logic.x2 + " " + Logic.y2);
            JOptionPane.showMessageDialog(null, "Эх, просто ничья.", "Ничья!",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        aiTurn();
        printMap();
        if (checkWinLines(DOT_O, DOTS_TO_WIN)) {
            System.out.println("Компьютер победил. Сейчас их даже в шахматы не выиграть...");
            System.out.println("Координаты:" + Logic.x1 + " " + Logic.y1 + "  " + Logic.x2 + " " + Logic.y2);

            JOptionPane.showMessageDialog(null, "Компьютер победил. Сейчас их даже в шахматы " +
                    "не выиграть...", "Вы проиграли!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (isFull()) {
            System.out.println("Ничья");
            System.out.println("Координаты:" + Logic.x1 + " " + Logic.y1 + "  " + Logic.x2 + " " + Logic.y2);
            JOptionPane.showMessageDialog(null, "Эх, просто ничья.", "Ничья!",
                    JOptionPane.INFORMATION_MESSAGE);

            return;
        }

        isGameFinished = false;
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

    public static void humanTurn(int y, int x) {
        if (isCellValid(y, x)) {
            map[y][x] = DOT_X;
            go();
        }
    }

    public static void aiTurn() {
        int x;
        int y;
// Попытка победить самому
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_O;
                    if (checkWinLines(DOT_O, DOTS_TO_WIN)) {
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }

// Сбить победную линии противника, если осталось 1 ход для победы
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_X;
                    if (checkWinLines(DOT_X, DOTS_TO_WIN)) {
                        map[i][j] = DOT_O;
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }

// Попытка победить самому, если осталось 2 хода для победы
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_O;

                    if (checkWinLines(DOT_O, DOTS_TO_WIN - 1) &&
                            Math.random() < 0.5) { //  фактор случайности, чтобы сбивал не все время первый попавшийся путь.
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }

// Сбить победную линии противника, если осталось 2 хода для победы
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_X;
                    if (checkWinLines(DOT_X, DOTS_TO_WIN - 1) &&
                            Math.random() < 0.5) { //  фактор случайности, чтобы сбивал не все время первый попавшийся путь.
                        map[i][j] = DOT_O;
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }


// Сходить в произвольную не занятую ячейку
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

    public static int x1;
    public static int x2;
    public static int y1;
    public static int y2;

    static boolean checkLine(int cy, int cx, int vy, int vx, char dot, int dotsToWin) {
        if (cx + vx * (dotsToWin - 1) > SIZE - 1 || cy + vy * (dotsToWin - 1) > SIZE - 1 ||
                cy + vy * (dotsToWin - 1) < 0) {
            return false;
        }

        for (int i = 0; i < dotsToWin; i++) {
            if (map[cy + i * vy][cx + i * vx] != dot) {
                return false;
            } else {
                y2 = cy + i * vy;
                x2 = cx + i * vx;
            }
        }
        return true;
    }

    static boolean checkWinLines(char dot, int dotsToWin) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkLine(i, j, 0, 1, dot, dotsToWin) ||
                        checkLine(i, j, 1, 0, dot, dotsToWin) ||
                        checkLine(i, j, 1, 1, dot, dotsToWin) ||
                        checkLine(i, j, -1, 1, dot, dotsToWin)) {
                    y1 = i;
                    x1 = j;
                    return true;

                }
            }
        }
        return false;
    }
}