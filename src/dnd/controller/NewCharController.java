import dnd.Utils.DR;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

    private Stage newCharStage;
    private Character character;
    private boolean okClicked = false;


    @FXML
    private void initialize() {}

    /*
    //Устанавливаем сцену для этого окна
    public Stage setNewCharStage(Stage newCharStage) {
        this.newCharStage() = newCharStage;
        return newCharStage;
    }
    */

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

    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.CHARMAN);
    }
}
