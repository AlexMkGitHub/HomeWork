package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    Socket socket;
    Server server;
    DataInputStream in;
    DataOutputStream out;
    WorkWithSql ws = new WorkWithSql();

    private boolean authenticated;
    private String nickname;
    private String login;


    public ClientHandler(Socket socket, Server server) {


        try {
            this.socket = socket;
            this.server = server;

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    //Цикл аутентификации
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth ")) {
                            String[] token = str.split("\\s+");
                            nickname = server.getAuthService()
                                    .getNicknameByLoginAndPassword(token[1], token[2]);
                            login = token[1];
                            if (nickname != null) {
                                if (!server.isLoginAuthenticated(login)) {
                                    sendMsg("/authok " + nickname);
                                    server.subscribe(this);
                                    authenticated = true;
                                    break;
                                } else {
                                    sendMsg("С этим логином уже вошли!");
                                }
                            } else {
                                sendMsg("Неверный логин или пароль!");
                            }
                        }

                        if (str.startsWith("/reg ")) {
                            String[] token = str.split("\\s+");
                            if (token.length < 4) {
                                continue;
                            }

                            boolean regOk = server.getAuthService().registration(token[1], token[2], token[3]);
                            if (regOk) {
                                sendMsg("/regok");
                            } else {
                                sendMsg("/regno");
                            }
                        }

                    }

                    //Цикл работы
                    while (authenticated) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            sendMsg("/end");
                            System.out.println("Client disconnected");
                            break;
                        }

                        if (str.startsWith("/chNick ") || str.startsWith("/w ")) {
                            if (str.startsWith("/chNick ")) {
                                String[] token = str.split("\\s+", 3);
                                if (token.length < 3) {
                                    continue;
                                }
                                boolean chOk = ws.updateNickname(token[1], token[2]);
                                if (chOk) {
                                    nickname = token[2];
                                    server.updateClient(this);
                                    server.broadcastSystemMsg(token[1] + " сменил ник на  " + token[2]);
                                    sendMsg("/chNickOk");
                                } else {
                                    sendMsg("/chNickFalse");
                                }
                            }

                            if (str.startsWith("/w ")) {
                                String[] token = str.split("\\s+", 3);
                                if (token.length < 3) {
                                    continue;
                                }
                                server.privatMsg(this, token[1], token[2]);
                                System.out.println(nickname + " отправил " + token[1] + ": " + token[2]);
                            }
                        } else {
                            server.broadcastMsg(this, str);
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    server.unsubscribe(this);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {
        return nickname;
    }

    public String getLogin() {
        return login;
    }
}
