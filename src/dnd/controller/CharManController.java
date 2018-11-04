import dnd.model.CharDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import dnd.model.Character;

import java.sql.SQLException;

public class CharManController {
    @FXML
    private TableView<Character> characterTableView;
    @FXML
    private TableColumn<Character, String> nameColumn;
    @FXML
    private Label classLab;
    @FXML
    private Label backLab;
    @FXML
    private Label pnLab;
    @FXML
    private Label raceLab;
    @FXML
    private Label alignLab;
    @FXML
    private Label expLab;

    //проба пера с id
    @FXML
    private Label idLab;



    public void initialize() {


        //Инициализация таблицы с именами персонажей
        if (!nameColumn.getColumns().isEmpty()) {
            characterTableView.getItems().clear();
        }
            CharDAOImpl.getAll();
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            characterTableView.setItems(CharDAOImpl.getCharacterData());

            //Очистка дополнительной информации о персонаже
            showCharacterDetails(null);

            // Слушаем изменения выбора, и при изменении отображаем
            // дополнительную информацию о персонаже.
            characterTableView.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showCharacterDetails(newValue));


    }

    //Отображение в левой части приложения изменений в выборе персонажа
    private void showCharacterDetails(Character character) {
        if (character != null) {
            classLab.setText(character.getChClass());
            backLab.setText(character.getBackground());
            pnLab.setText(character.getPlayerName());
            raceLab.setText(character.getRace());
            alignLab.setText(character.getAlign());
            expLab.setText(Integer.toString(character.getExp()));
            idLab.setText(Integer.toString(character.getId()));
        } else {
            classLab.setText("");
            backLab.setText("");
            pnLab.setText("");
            raceLab.setText("");
            alignLab.setText("");
            expLab.setText("");
            idLab.setText("");
        }
    }

    @FXML
    void newChar(ActionEvent event) {
        Navigator.loadScreen(Navigator.NCH);
    }

    @FXML
    void handleDelete() {
        CharDAOImpl.delete(Integer.parseInt(idLab.getText()));
        int selectedIndex = characterTableView.getSelectionModel().getSelectedIndex();
        characterTableView.getItems().remove(selectedIndex);

    }


    @FXML
    void previousPane(ActionEvent event) {
        //Очистка дополнительной информации о персонаже     
        characterTableView.getItems().clear();
        Navigator.loadScreen(Navigator.MMENU);
    }

    @FXML
    void newCharPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.NCH);
    }

}