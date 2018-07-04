import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DMTController {

    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.MMENU);
    }

    @FXML
    void DRPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.DR);
    }

}