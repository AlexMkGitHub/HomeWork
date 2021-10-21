package tiptactoe;

import javax.swing.*;
import java.awt.*;

public class Setting extends JFrame {
    private final int MIN_FIELD_SIZE = 3;
    private final int MAX_FIELD_SIZE = 10;

    private GameWindow gameWindow;

    private JSlider slFieldSize;
    private JSlider slDotsToWin;

    public Setting(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBounds(600, 250, 600, 450);
        setTitle("TicTacToe settings");

        slFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slFieldSize.setMajorTickSpacing(1);
        slFieldSize.setPaintTicks(true);
        slFieldSize.setPaintLabels(true);


        slDotsToWin = new JSlider(MIN_FIELD_SIZE, MIN_FIELD_SIZE, MIN_FIELD_SIZE);
        slDotsToWin.setMajorTickSpacing(1);
        slDotsToWin.setPaintTicks(true);
        slDotsToWin.setPaintLabels(true);

        slFieldSize.addChangeListener(e -> slDotsToWin.setMaximum(slFieldSize.getValue()));

        setLayout(new GridLayout(5, 1));
        add(new Label("Choose field size:"));
        add(slFieldSize);
        add(new Label("Choose wining line:"));
        add(slDotsToWin);

        JButton button = new JButton("Start a game:");
        add(button);
        button.addActionListener(e -> {
            int fieldSize = slFieldSize.getValue();
            int winLenght = slDotsToWin.getValue();

            Logic.SIZE = fieldSize;
            Logic.DOTS_TO_WIN = winLenght;
            Logic.initMap();
            Logic.printMap();
            Logic.isGameFinished = false;

            gameWindow.startNewGame(fieldSize, winLenght);

            setVisible(false);
        });

        setVisible(false);

    }
}
