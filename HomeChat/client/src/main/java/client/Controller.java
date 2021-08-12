package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private HBox authPanel;
    @FXML
    private HBox msgPanel;

    private Socket socket;
    private final int PORT = 8189;
    private final String IP_ADRESS = "localhost";
    private DataInputStream in;
    private DataOutputStream out;

    private boolean authencated;
    private String nickname;
    private Stage stage;

    public void setAuthencated(boolean authencated) {
        this.authencated = authencated;
        authPanel.setVisible(!authencated);
        authPanel.setManaged(!authencated);
        msgPanel.setVisible(authencated);
        msgPanel.setManaged(authencated);

        if (!authencated) {
            nickname = "";
        }
        setTitle(nickname);
        textArea.clear();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            stage = (Stage) textField.getScene().getWindow();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("Bye bye!!!");
                    if (socket != null && !socket.isClosed()) {
                        try {
                            out.writeUTF("/end");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        });
        setAuthencated(false);
    }

    private void connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    //Цикл аутентификации
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/")) {
                            if (str.equals("/end")) {
                                break;
                            }
                            if (str.startsWith("/authok")) {
                                nickname = str.split("\\s")[1];
                                setAuthencated(true);
                                break;

                            }
                        } else {
                            textArea.appendText(str + "\n");
                        }

                    }

                    //Цикл работы
                    while (authencated) {
                        String str = in.readUTF();

                        if (str.equals("/end")) {
                            break;
                        }
                        textArea.appendText(str + "\n");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Disconnected");
                    setAuthencated(false);
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

    @FXML
    public void sendMsg(ActionEvent actionEvent) {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void tryToAuth(ActionEvent actionEvent) {
        if (socket == null || socket.isClosed()) {
            connect();
        }

        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();
        String msg = String.format("/auth %s %s", login, password);

        try {
            out.writeUTF(msg);
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTitle(String nickname) {
        Platform.runLater(() -> {
            if (!nickname.equals("")) {
                stage.setTitle(String.format("HomeChat [%s] ", nickname));
            } else {
                stage.setTitle("HomeChat");
            }

        });

    }
}
