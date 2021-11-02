package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.*;

public class Server {
    private ServerSocket server;
    private Socket socket;
    private final int PORT = 8189;

    private List<ClientHandler> clients;
    private AuthService authService;
    private ExecutorService service;
    private Logger logger;

    public Server() {
        clients = new CopyOnWriteArrayList<>();
        authService = new SimpleAuthServiceSqlDb();
        service = Executors.newCachedThreadPool();
        logger = Logger.getLogger(Server.class.getName());
        try {
            server = new ServerSocket(PORT);
            logger.log(Level.INFO, "Server started!");


            while (true) {
                socket = server.accept();
                new ClientHandler(socket, this);
                logger.log(Level.WARNING, "Client with IP " + server + " trying to connect!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
                socket.close();
                service.shutdown();
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

    public void serverStop() {
        try {
            server.close();
            socket.close();
            service.shutdown();
            logger.log(Level.INFO, "Server STOP!!!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public AuthService getAuthService() {
        return authService;
    }

    public ExecutorService getService() {
        return service;
    }

}
