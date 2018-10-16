import dnd.Utils.DR;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import dnd.model.Character;
import dnd.controller.*;

public class NewCharController {
    DR dr = new DR();

    @FXML
    private Label strLabel;
    @FXML
    private Label dexLabel;
    @FXML
    private Label conLabel;
    @FXML
    private Label intLabel;
    @FXML
    private Label wisLabel;
    @FXML
    private Label chaLabel;
    @FXML
    private Label nameLabel;

    private Stage dialogStage;
    private Character character;
    private boolean okClicked = false;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {}

    @FXML
    void Gen(ActionEvent event) throws Exception {
        dr.DR();
        strLabel.setText(String.valueOf(dr.getChars(0)));
        dexLabel.setText(String.valueOf(dr.getChars(1)));
        conLabel.setText(String.valueOf(dr.getChars(2)));
        intLabel.setText(String.valueOf(dr.getChars(3)));
        wisLabel.setText(String.valueOf(dr.getChars(4)));
        chaLabel.setText(String.valueOf(dr.getChars(5)));
    }

    //Возвращаем true если юзер кликнул на ОК, иначе false
    public boolean isOkClicked() {
        return okClicked;
    }

    //TODO: Вызывается, если юзер кликнул ОК
    @FXML
    private void handleOK() {
        if (isInputValid()) {
            character.setName(nameLabel.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    //TODO: Проверяет пользовательский ввод
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameLabel.getText()==null || nameLabel.getText().length() == 0) {
            errorMessage += "Введите имя";
        }
        if (errorMessage.length()==0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fileds!");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            
            return false;
        }
    }

    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.CHARMAN);
    }
}
