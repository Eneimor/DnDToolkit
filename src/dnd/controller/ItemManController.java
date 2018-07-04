import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ItemManController {

    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.MMENU);
    }

}