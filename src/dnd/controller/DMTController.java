import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DMTController {
    
    @FXML
    void generatorsPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.GENERATORS);
    }

    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.MMENU);
    }

    @FXML
    void DRPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.DR);
    }

    @FXML
    void ENPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.ENCOUNTERS);
    }

    @FXML
    void ENVPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.ENVIRONMENT);
    }

    @FXML
    void DungeonPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.GENERS);
    }

    @FXML
    void SoundsPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.SOUNDS);
    }

    @FXML
    void RulesPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.RULES);
    }

    @FXML
    void MiscPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.MISC);
    }

}