package client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class History {
    private final Controller controller;
    private DataOutputStream outLocalFileChat;

    public History(Controller controller) {
        this.controller = controller;
    }

    public void lastChatHistory() {
        try {
            File file = new File("history_" + controller.getEnterLogin() + ".txt");
            if (file.exists()) {
                List list = Files.readAllLines(Paths.get("history_" + controller.getEnterLogin() + ".txt"));
                int n = list.size() - 100;
                if (n < 0) n = 0;
                for (int i = n; i < list.size(); i++) {
                    if (list.get(i).toString() == null) {
                        return;
                    } else {
                        controller.getTextArea().appendText(list.get(i).toString().substring(2));
                        controller.getTextArea().appendText("" + "\n");
                    }
                }
            }

            //Если надо, чтобы история чата начиналась с чистого листа, то убрать true
            outLocalFileChat = new DataOutputStream(new FileOutputStream("history_" + controller.getEnterLogin() + ".txt", true));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataOutputStream getOutLocalFileChat() {
        return outLocalFileChat;
    }
}