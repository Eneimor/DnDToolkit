
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Контроллер для главного меню.
 */
public class MainMenuController {

    /**
     * Объявляю кнопку для того чтобы навесить на нее потом нужный метод
     */
    @FXML
    private javafx.scene.control.Button ext;


    /**
     * Основной вид перехода между экранами меню
     * Переход к мэнержеру персонажей
     * @param event
     */
    @FXML
    void nextCharManPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.CHARMAN);
    }

    //Метод перехода к Spellbook
    @FXML
    void nextSpellbookPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.SPELLS);
    }

    //Метод перехода к Bestiarium
    @FXML
    void nextBeastPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.BEASTMAN);
    }

    //Метод перехода к Items
    @FXML
    void nextItemManPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.ITEMS);
    }

    //Метод перехода к DM Tools
    @FXML
    void nextDMTPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.DMT);
    }

    //Метод перехода к Campaigns
    @FXML
    void nextCampPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.CAMPAIGNS);
    }


    /**
     *  Отработка события закрытия приложения
     */

    //Метод закрывающий приложение
    @FXML
    private void handleExit(){
        // get a handle to the stage
        Stage stage = (Stage) ext.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}