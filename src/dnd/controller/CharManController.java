import dnd.Utils.Connect;
import dnd.model.CharDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import dnd.model.Character;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    //Обсервабл лист для отображения в TableView списка персонажей
    private static ObservableList<Character> characterData = FXCollections.observableArrayList();
    public static final String SELECT_ALL_CHARACTERS = "SELECT a.id as id, a.name as name, b.name as class FROM m_character as a " +
            "left join cl_class as b ON a.[class] = b.id";
    public static final String sql;

    static {
        sql = "DELETE FROM m_character WHERE id =";
    }


    public void initialize() {
        //Инициализация таблицы с именами персонажей
        Connect connect = new Connect();
        PreparedStatement ps = connect.getPreparedStatement(SELECT_ALL_CHARACTERS);
        if (nameColumn.getColumns().isEmpty()) {
            characterData.clear();
        }
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Character ch = new Character();
                    ch.setId(rs.getInt("id"));
                    ch.setName(rs.getString("name"));
                    ch.setChClass(rs.getString("class"));
                    characterData.add(ch);


                }
                nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
                characterTableView.setItems(characterData);
                showCharacterDetails(null);
                characterTableView.getSelectionModel().selectedItemProperty().addListener(
                        (observable, oldValue, newValue) -> showCharacterDetails(newValue));
            } catch (SQLException e) {
                e.printStackTrace();
            }
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