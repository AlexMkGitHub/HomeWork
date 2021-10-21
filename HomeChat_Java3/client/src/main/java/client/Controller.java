package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final History history = new History();
    @FXML
    private ListView<String> clientList;
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
    private String enterLogin = "";
    private Stage stage;
    private Stage regStage;
    private RegController regController;
    private NickChangeController chNickController;
    private String newNickName;

    public void setAuthencated(boolean authencated) {
        this.authencated = authencated;
        authPanel.setVisible(!authencated);
        authPanel.setManaged(!authencated);
        msgPanel.setVisible(authencated);
        msgPanel.setManaged(authencated);
        clientList.setVisible(authencated);
        clientList.setManaged(authencated);

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

                            if (str.startsWith("/authok")) {
                                nickname = str.split("\\s")[1];
                                setAuthencated(true);
                                textArea.appendText(history.lastChatHistory(enterLogin));

                                break;
                            }
                            if (str.equals("/regok")) {
                                regController.regResult("Регистрация прошла успешно.");
                            }
                            if (str.equals("/regno")) {
                                regController.regResult("Логин или никнейм уже заняты!");
                            }

                        } else {
                            textArea.appendText(str + "\n");
                        }

                    }
                    //Цикл работы
                    while (authencated) {
                        String str = in.readUTF();
                        if (str.startsWith("/")) {
                            if (str.equals("/end")) {
                                break;
                            }
                            if (str.startsWith("/clientlist ")) {
                                String[] token = str.split("\\s+");
                                Platform.runLater(() -> {
                                    clientList.getItems().clear();
                                    for (int i = 1; i < token.length; i++) {
                                        clientList.getItems().add(token[i]);
                                    }
                                });
                            }

                            if (str.startsWith("/chNickOk")) {
                                chNickController.changeResult("Ник успешно изменён.");
                                setTitle(newNickName);
                            }

                            if (str.startsWith("/chNickFalse")) {
                                chNickController.changeResult("Данный ник занят!");
                            }
                        } else {
                            textArea.appendText(str + "\n");
                            history.getOutLocalFileChat().write(str + "\n");
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        System.out.println("Disconnected");
                        setAuthencated(false);
                        textArea.clear();
                        history.checkHistoryLength(enterLogin);
                        history.getOutLocalFileChat().close();
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
        enterLogin = login;
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

    public void clientListClick(MouseEvent mouseEvent) {
        String recever = clientList.getSelectionModel().getSelectedItem();
        textField.setText(String.format("/w %s ", recever));
    }

    private void createRegWindow() {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/reg.fxml"));
            Parent root = fxmlloader.load();
            regStage = new Stage();
            regStage.setTitle("Registration");
            regStage.setScene(new Scene(root, 600, 400));
            regController = fxmlloader.getController();
            regController.setController(this);

            regStage.initStyle(StageStyle.UTILITY);
            regStage.initModality(Modality.APPLICATION_MODAL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRegWindow(ActionEvent actionEvent) {
        if (regStage == null) {
            createRegWindow();
        }
        regStage.show();
    }

    public void registration(String login, String password, String nickname) {
        String msg = String.format("/reg %s %s %s", login, password, nickname);

        if (socket == null || socket.isClosed()) {
            connect();
        }

        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeNickName(String newNick) {
        newNickName = newNick;
        String msg = String.format("/chNick %s %s", nickname, newNick);

        if (socket == null || socket.isClosed()) {
            connect();

        }

        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chNick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/changeNickname.fxml"));
            Parent root = fxmlloader.load();
            regStage = new Stage();
            regStage.setTitle("Change nickname");
            regStage.setScene(new Scene(root, 600, 400));
            chNickController = fxmlloader.getController();
            chNickController.setController(this);

            regStage.initStyle(StageStyle.UTILITY);
            regStage.initModality(Modality.APPLICATION_MODAL);
            regStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}