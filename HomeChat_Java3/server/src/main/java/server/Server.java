package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private ServerSocket server;
    private Socket socket;
    private final int PORT = 8189;

    private List<ClientHandler> clients;
    private AuthService authService;


    public Server() {
        clients = new CopyOnWriteArrayList<>();
        authService = new SimpleAuthServiceSqlDb();
        try {
            server = new ServerSocket(PORT);
            System.out.println("Server started!");


            while (true) {
                socket = server.accept();
                System.out.println("Client connected");
                new ClientHandler(socket, this);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(ClientHandler sender, String msg) {
        String message = String.format("[%s]: %s ", sender.getNickname(), msg);
        for (ClientHandler c : clients) {
            c.sendMsg(message);
        }
    }

    public void broadcastSystemMsg(String msg) {
        String message = String.format("%s", msg);
        for (ClientHandler c : clients) {
            c.sendMsg(message);
        }
    }

    public void privatMsg(ClientHandler sender, String recipient, String msg) {
        for (ClientHandler c : clients) {
            if (c.getNickname().equals(recipient)) {
                c.sendMsg(String.format("[Личное сообщение] от [%s]: %s", sender.getNickname(), msg));
            }
            if (sender.getNickname().equals(recipient)) {
                return;
            }
            if (c.getNickname().equals(sender.getNickname())) {
                c.sendMsg(String.format("[Вы] -> [%s]: %s", recipient, msg));
            }
        }
    }


    public boolean isLoginAuthenticated(String login) {
        for (ClientHandler c : clients) {
            if (c.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastClientList() {
        StringBuilder sb = new StringBuilder("/clientlist");
        for (ClientHandler c : clients) {
            sb.append(" ").append(c.getNickname());
        }
        String message = sb.toString();
        for (ClientHandler c : clients) {
            c.sendMsg(message);
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        broadcastClientList();
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastClientList();
    }

    public AuthService getAuthService() {
        return authService;
    }

}
