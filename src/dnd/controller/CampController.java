import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CampController {

    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.MMENU);
    }

}