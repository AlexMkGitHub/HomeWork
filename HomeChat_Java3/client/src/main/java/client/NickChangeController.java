package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NickChangeController {

    @FXML
    public TextField newNick;
    @FXML
    private TextArea textArea;

    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @FXML
    public void tryToChange(ActionEvent actionEvent) {
        String newNickname = newNick.getText().trim();

        if (newNickname.equals("")) {
            changeResult("Поля должны быть не пустые!\n");
            return;
        }
        if (newNickname.contains(" ")) {
            changeResult("Никнейм не должны содержать пробелы!\n");
            return;
        }

        controller.changeNickName(newNickname);

    }

    public void changeResult(String msg) {
        textArea.clear();
        textArea.appendText(msg + "\n");
    }
}
