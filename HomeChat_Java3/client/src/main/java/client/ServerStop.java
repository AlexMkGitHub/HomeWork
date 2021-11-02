package client;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.awt.*;

public class ServerStop {

    @FXML
    public TextArea textArea;
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
