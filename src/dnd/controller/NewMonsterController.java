import dnd.Utils.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewMonsterController {
    @FXML private TextField origName;
    @FXML private TextField locName;
    @FXML private ChoiceBox<String>size;
    @FXML private ChoiceBox<String>type;
    @FXML private ChoiceBox<String>align;
    @FXML private Spinner AC;
    @FXML private ChoiceBox<String> acType;
    @FXML private Spinner hp;
    @FXML private TextField speed;
    @FXML private Spinner str;
    @FXML private Spinner dex;
    @FXML private Spinner con;
    @FXML private Spinner intl;
    @FXML private Spinner wis;
    @FXML private Spinner cha;
    @FXML private TextField skills;
    @FXML private TextField damVul;
    @FXML private TextField damRes;
    @FXML private TextField damImm;
    @FXML private TextField condImm;
    @FXML private TextField senses;
    @FXML private Spinner CR;


    ObservableList<String> alignList = FXCollections.observableArrayList();
    ObservableList<String> sizeList = FXCollections.observableArrayList("Tiny","Small","Medium","Large","Huge","Gargantuan");
    ObservableList<String> typeList = FXCollections.observableArrayList();
    ObservableList<String> acList = FXCollections.observableArrayList();



    public void initialize(){
        SpinnerValueFactory<Integer> strValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 9);
        SpinnerValueFactory<Integer> dexValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 9);
        SpinnerValueFactory<Integer> conValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 9);
        SpinnerValueFactory<Integer> intlValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 9);
        SpinnerValueFactory<Integer> wisValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 9);
        SpinnerValueFactory<Integer> chaValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 9);
        SpinnerValueFactory<Integer> acValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 9);
        SpinnerValueFactory<Integer> hpValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 9);
        SpinnerValueFactory<Integer> crValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, 1);
        Connect c = new Connect();
        PreparedStatement ps1 = c.getPreparedStatement("SELECT DISTINCT name FROM cl_alignment");
        PreparedStatement ps2 = c.getPreparedStatement("SELECT DISTINCT name FROM cl_alignment");
        try {
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                alignList.add(rs1.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        align.setItems(alignList);
        size.setItems(sizeList);

    }



    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.BEASTMAN);
    }
}
