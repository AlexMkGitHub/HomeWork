package hw6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server {
    static ServerSocket server;
    static Socket socket;
    private static final int PORT = 8189;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен.");

            socket = server.accept();
            System.out.println("Клиент подключился.");

            Scanner scServerIn = new Scanner(socket.getInputStream());
            Scanner scServerOut = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            new Thread(() -> {
                try {
                    while (true) {
                        String str = scServerIn.nextLine();
                        if (str.equals("/end")) {
                            System.out.println("Клиент отключился!");
                            break;
                        }
                        out.println("Клиент: " + str);
                        System.out.println("Клиент: " + str);
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Непредвиденное отключение клиента!!!");
                } finally {
                    try {
                        socket.close();
                        System.out.println("Сокет закрыт.");
                        server.close();
                        System.out.println("Сервер закрыт.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            Thread t1 = new Thread(() -> {
                while (true) {
                    String strOut = scServerOut.nextLine();
                    out.println("Сервер: " + strOut);
                    System.out.println("Сервер: " + strOut);
                }
            });
            t1.setDaemon(true);
            t1.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
