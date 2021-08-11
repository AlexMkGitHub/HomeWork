package hw6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    static ServerSocket server;
    static Socket socket;
    static final String IP_ADRESS = "localhost";
    private static final int PORT = 8189;

    public static void main(String[] args) {

        try {
            socket = new Socket(IP_ADRESS, PORT);
            Scanner scClientOut = new Scanner(System.in);
            Scanner scClientIn = new Scanner(socket.getInputStream());
            PrintWriter outClient = new PrintWriter(socket.getOutputStream(), true);
            Thread t1 = new Thread(() -> {

                while (true) {
                    String strOut = scClientOut.nextLine();
                    outClient.println(strOut);
                    if (strOut.equals("/end")) {
                        break;
                    }
                }
                try {
                    socket.close();
                    server.close();
                } catch (NullPointerException e) {
                    System.out.println("Чат отключен.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t1.setDaemon(true);
            t1.start();

            new Thread(() -> {
                while (true) {
                    String strIn = scClientIn.nextLine();
                    System.out.println(strIn);
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

