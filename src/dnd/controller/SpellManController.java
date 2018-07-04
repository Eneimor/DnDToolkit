import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SpellManController {

    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.MMENU);
    }

}