package Java1.hw8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleMap extends JPanel {
    private GameWindow gameWindow;
    private int fieldSize;
    private int winLength;
    private int cellX;
    private int cellY;

    private boolean isInit;

    private static int cellWidth;
    private static int cellHeight;

    public BattleMap(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBackground(Color.ORANGE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                cellX = e.getX() / cellWidth;
                cellY = e.getY() / cellHeight;

                if (isInit && !Logic.isGameFinished) {
                    Logic.humanTurn(cellY, cellX);
                }
                repaint();
            }
        });
    }

    void startNewGame(int fieldSize, int winLenght) {
        this.fieldSize = fieldSize;
        this.winLength = winLenght;

        isInit = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isInit) {
            return;
        }

        int panelWight = getWidth();
        int panelHeight = getHeight();

        cellWidth = panelWight / fieldSize;
        cellHeight = panelHeight / fieldSize;

        g.setColor(Color.GRAY);
        ((Graphics2D) g).setStroke(new BasicStroke(2f));
        for (int i = 1; i < fieldSize; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWight, y);
        }

        for (int i = 1; i < fieldSize; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);

        }
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (Logic.map[i][j] == Logic.DOT_X) {
                    drawX(g, i, j);
                } else {
                    if (Logic.map[i][j] == Logic.DOT_O) {
                        circle(g, i, j);
                    }
                    repaint();
                }
            }
        }

        if (Logic.checkWinLines(Logic.DOT_X, Logic.DOTS_TO_WIN)) {
            g.setColor(Color.GREEN);
            ((Graphics2D) g).setStroke(new BasicStroke(10f));
            g.drawLine((Logic.x1 * cellWidth) + (cellWidth / 2), (Logic.y1 * cellHeight) + (cellHeight / 2),
                    (Logic.x2 * cellWidth) + (cellWidth / 2), (Logic.y2 * cellHeight) + (cellHeight / 2));
        }
        if (Logic.checkWinLines(Logic.DOT_O, Logic.DOTS_TO_WIN)) {
            g.setColor(Color.MAGENTA);
            ((Graphics2D) g).setStroke(new BasicStroke(10f));
            g.drawLine((Logic.x1 * cellWidth) + (cellWidth / 2), (Logic.y1 * cellHeight) + (cellHeight / 2),
                    (Logic.x2 * cellWidth) + (cellWidth / 2), (Logic.y2 * cellHeight) + (cellHeight / 2));

        }
    }

    public void drawX(Graphics g, int y, int x) {
        g.setColor(Color.BLUE);
        ((Graphics2D) g).setStroke(new BasicStroke(4f));
        g.drawLine(x * cellWidth + 15, y * cellHeight + 15, (x + 1) * cellWidth - 15, (y + 1) * cellHeight - 15);
        g.drawLine((x + 1) * cellWidth - 15, y * cellHeight + 15, x * cellWidth + 15, (y + 1) * cellHeight - 15);

    }

    static void circle(Graphics g, int y, int x) {
        g.setColor(Color.RED);
        ((Graphics2D) g).setStroke(new BasicStroke(4f));
        g.drawOval(x * cellWidth + 7, y * cellHeight + 7, cellWidth - 15, cellHeight - 15);
    }

}
