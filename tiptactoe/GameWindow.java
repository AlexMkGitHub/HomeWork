package tiptactoe;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private BattleMap battleMap;
    private Setting setting;


    public GameWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(600, 250, 600, 600);
        setTitle("TicTacToe");
        centreWindow(this);
        setLayout(new BorderLayout());

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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(600, 250, 600, 600);
        setTitle("TicTacToe");
        centreWindow(this);
        setLayout(new BorderLayout());



        panel.add(buttonExit);
        add(panel, BorderLayout.SOUTH);

        battleMap = new BattleMap(this);
        add(battleMap, BorderLayout.CENTER);
        setting = new Setting(this);

        setVisible(true);
    }

    void startNewGame(int fieldSize, int winLenght) {
        battleMap.startNewGame(fieldSize, winLenght);

    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}
