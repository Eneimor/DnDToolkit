import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class BeastManController {

    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.MMENU);
    }

}