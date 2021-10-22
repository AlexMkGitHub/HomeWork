package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


            server.getService().execute(() -> {
                try {
                    //Цикл аутентификации
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth ")) {
                            String[] token = str.split("\\s+");
                            if (token[1].equals("") || token[2].equals("")) {
                                ClientHandler.this.sendMsg("Неверный логин или пароль!");
                                break;
                            }
                            nickname = server.getAuthService()
                                    .getNicknameByLoginAndPassword(token[1], token[2]);
                            login = token[1];
                            if (login == null) {
                                ClientHandler.this.sendMsg("Неверный логин или пароль!");
                                break;
                            }
                            if (nickname != null) {
                                if (!server.isLoginAuthenticated(login)) {
                                    ClientHandler.this.sendMsg("/authok " + nickname);
                                    server.subscribe(ClientHandler.this);
                                    authenticated = true;
                                    break;
                                } else {
                                    ClientHandler.this.sendMsg("С этим логином уже вошли!");
                                }
                            } else {
                                ClientHandler.this.sendMsg("Неверный логин или пароль!");
                            }
                        }

                        if (str.startsWith("/reg ")) {
                            String[] token = str.split("\\s+");
                            if (token.length < 4) {
                                continue;
                            }

                            boolean regOk = server.getAuthService().registration(token[1], token[2], token[3]);
                            if (regOk) {
                                ClientHandler.this.sendMsg("/regok");
                            } else {
                                ClientHandler.this.sendMsg("/regno");
                            }
                        }

                    }

                    //Цикл работы
                    while (authenticated) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            ClientHandler.this.sendMsg("/end");
                            System.out.println("Client disconnected");
                            break;
                        }
                        if (str.equals("/serverstop")) {
                            //ClientHandler.this.sendMsg("/end");
                            server.broadcastSystemMsg("/stop");
                            //server.serverStop();
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
                                    server.broadcastClientList();
                                    server.broadcastSystemMsg("[" + token[1] + "]" + " сменил ник на  " + "[" + token[2] + "]");
                                    ClientHandler.this.sendMsg("/chNickOk");
                                } else {
                                    ClientHandler.this.sendMsg("/chNickFalse");
                                }
                            }

                            if (str.startsWith("/w ")) {
                                String[] token = str.split("\\s+", 3);
                                if (token.length < 3) {
                                    continue;
                                }
                                server.privatMsg(ClientHandler.this, token[1], token[2]);
                                System.out.println(nickname + " отправил " + token[1] + ": " + token[2]);
                            }
                        } else {
                            server.broadcastMsg(ClientHandler.this, str);
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    server.unsubscribe(ClientHandler.this);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
        } catch (IOException e) {


            e.printStackTrace();
        }
        //server.getService().shutdown();
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
