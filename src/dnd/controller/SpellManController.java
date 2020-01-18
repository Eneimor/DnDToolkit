import dnd.Utils.Connect;
import dnd.model.Spellbook;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpellManController {
    @FXML private Label nameLabel;
    @FXML private Label castLabel;
    @FXML private Label distanceLabel;
    @FXML private Label compLabel;
    @FXML private Label durLabel;
    @FXML private ListView lstView;
    @FXML private TextArea descText;
    @FXML private RadioButton onelvlBtn;
    @FXML private RadioButton twolvlBtn;
    @FXML private RadioButton threelvlBtn;
    @FXML private RadioButton fourlvlBtn;
    @FXML private RadioButton fivelvlBtn;
    @FXML private RadioButton sixlvlBtn;
    @FXML private RadioButton svnlvlBtn;
    @FXML private RadioButton eightlvlBtn;
    @FXML private RadioButton ninelvlBtn;
    @FXML private RadioButton barbarButton;
    @FXML private RadioButton bardButton;
    @FXML private RadioButton priestButton;
    @FXML private RadioButton druidButton;
    @FXML private RadioButton warriorButton;
    @FXML private RadioButton monkButton;
    @FXML private RadioButton palButton;
    @FXML private RadioButton rangButton;
    @FXML private RadioButton rougeButton;
    @FXML private RadioButton mageButton;
    @FXML private RadioButton wizardButton;
    @FXML private RadioButton sorcButton;
    @FXML private Button backBtn;
    @FXML private Button newSplBtn;
    @FXML private TextField txtfld;
    @FXML private ChoiceBox classChoice;
    @FXML private ChoiceBox levelChoice;


    private final String sql = "SELECT a.id as id, spellname, c.casttype as casttype, d.distance as distance, \n" +
            "b.components as components, e.name as duration, description, spelllvl \n" +
            "FROM cl_spells a \n" +
            "LEFT JOIN sp_components b ON a.id = b.spellid\n" +
            "LEFT JOIN sp_casttimetype c ON a.spellcasttimetype = c.id\n" +
            "LEFT JOIN sp_distance d ON a.spelldistance = d.id\n" +
            "LEFT JOIN sp_durationtype e ON a.spelldurationtype = e.id\n" +
            "ORDER BY spellname";

    private static ObservableList<Spellbook> spellbookData = FXCollections.observableArrayList();

    ObservableList<String> classes = FXCollections.observableArrayList();
    ObservableList<Integer> level = FXCollections.observableArrayList();

    public final String sqlClasses = "SELECT name FROM cl_class";



    public void initialize() {
        Connect connect = new Connect();



        PreparedStatement ps = connect.getPreparedStatement(sql);
        PreparedStatement ps1 = connect.getPreparedStatement(sqlClasses);
        try {
            ResultSet rs = ps.executeQuery();

            ResultSet rs1 = ps1.executeQuery();
            while (rs.next()){
                Spellbook sb = new Spellbook();
                sb.setId(rs.getInt("id"));
                sb.setName(rs.getString("spellname"));
                sb.setCasttime(rs.getString("casttype"));
                sb.setDistance(rs.getString("distance"));
                sb.setComponents(rs.getString("components"));
                sb.setDuration(rs.getString("duration"));
                sb.setDescription(rs.getString("description"));
                sb.setLvl(rs.getInt("spelllvl"));
                spellbookData.add(sb);
            }
            while (rs1.next()) {
                classes.add(rs1.getString("name"));
            }
            classes.add("All");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i = 1; i < 21; i++) {
            level.add(i);
        }

        levelChoice.setItems(level);

        classChoice.setItems(classes);

        // 1. Обворачиваем ObservableList в FilteredList (изначально отображается вся инфа).
        FilteredList<Spellbook> flList = new FilteredList<>(spellbookData, p -> true);

        // 2. Устанавливаем слушателя для поля поиска
        txtfld.textProperty().addListener((observable, oldValue, newValue) -> {
            flList.setPredicate(spell -> {
                // Если текст в фильтре пустой, то отображаем весь список
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Приводим вводимое слово к нижнему регистру к нижнему регистру

                String lowerCaseFilter = newValue.toLowerCase();

                if (spell.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Совпало по имени.
                }

                return false; // Не совпало.
            });
        });
        // 3. Обворачиваем FilteredList в SortedList.
        SortedList<Spellbook> sortedData = new SortedList<>(flList);

        //наполняем ListView
        lstView.setItems(sortedData);


        //Отображаем данные в ListView
        lstView.setCellFactory(param -> new ListCell<Spellbook>() {
            @Override
            protected void updateItem(Spellbook item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        //Не показываем ничего пока не выбрано ни одно заклинание
        showSpellData(null);

        //Вешаем слушателя на ListView для отображения детальной информации
        lstView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Spellbook>() {
            public void changed(ObservableValue<? extends Spellbook> observable, Spellbook oldValue, Spellbook newValue) {
                showSpellData(newValue);
            }
        });




    }




    private void showSpellData(Spellbook spellbook) {
        if (spellbook != null) {
            nameLabel.setText(spellbook.getName());
            castLabel.setText(spellbook.getCasttime());
            distanceLabel.setText(spellbook.getDistance());
            compLabel.setText(spellbook.getComponents());
            durLabel.setText(spellbook.getDuration());
            descText.setText(spellbook.getDescription());
        } else {
            nameLabel.setText("");
            castLabel.setText("");
            distanceLabel.setText("");
            compLabel.setText("");
            durLabel.setText("");
            descText.setText("");
        }
    }

    

    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.MMENU);
    }
    @FXML
    void newSpell (ActionEvent event) {
        Navigator.loadScreen(Navigator.NWSPL);
    }

}