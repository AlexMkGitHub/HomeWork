package client;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class History {
    private final Controller controller;
    private PrintWriter outLocalFileChat;
    File file;

    public History(Controller controller) {
        this.controller = controller;
    }

    public String lastChatHistory(String login) {
        StringBuilder sb = new StringBuilder();
        file = new File("history_" + login + ".txt");
        if (file.exists()) {
            List list = null;
            try {
                list = Files.readAllLines(Paths.get("history_" + login + ".txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            int n = list.size() - 100;
            if (n < 0) n = 0;
            for (int i = n; i < list.size(); i++) {
                if (list.get(i).toString().equals("")) {
                    continue;
                }
                sb.append(list.get(i).toString()).append("\n");
            }
        }

        //Если надо, чтобы история чата начиналась с чистого листа, то убрать true
        try {
            outLocalFileChat = new PrintWriter(new FileOutputStream("history_" + login + ".txt", true), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void checkHistoryLength(String login) {
        System.out.println(file.getName());
        System.out.println(file.length());
        StringBuilder sb = new StringBuilder();
        try {
            if (file.length() > 10000) {
                List list = Files.readAllLines(Paths.get("history_" + login + ".txt"));
                outLocalFileChat = new PrintWriter(new FileOutputStream("history_" + login + ".txt"), true);
                int n = list.size() - 100;
                if (n < 0) n = 0;
                for (int i = n; i < list.size(); i++) {
                    if (list.get(i).toString().equals("")) {
                        continue;
                    }
                    sb.append(list.get(i).toString()).append("\n");
                    //outLocalFileChat.write(list.get(i).toString() + "\n");
                }
                outLocalFileChat.write(sb.toString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public PrintWriter getOutLocalFileChat() {
        return outLocalFileChat;
    }
}