package hw8;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private BattleMap battleMap;
    private Setting setting;


    public GameWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(600, 250, 600, 450);
        setTitle("TicTacToe");


        JPanel panel = new JPanel(new GridLayout(1, 2));
        JButton button = new JButton("Start new game");
        panel.add(button);

        JButton buttonExit = new JButton("Exit");
        panel.add(buttonExit);
        add(panel, BorderLayout.SOUTH);

        battleMap = new BattleMap(this);
        add(battleMap, BorderLayout.CENTER);
        setting = new Setting(this);

        button.addActionListener(e -> setting.setVisible(true));
        buttonExit.addActionListener(e -> System.exit(0));



        setVisible(true);
    }

    void startNewGame (int fieldSize, int winLenght) {
        battleMap.startNewGame (fieldSize, winLenght);

    }
}
