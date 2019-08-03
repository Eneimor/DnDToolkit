import dnd.Utils.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class newSpell {

    @FXML
    private Button backBtn;
    @FXML
    private Button okBtn;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField lvlTxt;
    @FXML
    private TextField castTxt;
    @FXML
    private TextField distTxt;
    @FXML
    private TextField compTxt;
    @FXML
    private TextField durationTxt;
    @FXML
    private TextArea descTxt;

    private String sql = "INSERT INTO spellbook (name, casttime, distance, components, duration, description, lvl) VALUES"
            + "(?,?,?,?,?,?,?)";

    public void initialize() {


    }

    @FXML
    private void insSpell(ActionEvent event) {
        Connect connect = new Connect();
        PreparedStatement ps = connect.getPreparedStatement(sql);
        try {
            ps.setString(1, nameTxt.getText());
            ps.setString(2, castTxt.getText());
            ps.setString(3, distTxt.getText());
            ps.setString(4, compTxt.getText());
            ps.setString(5, durationTxt.getText());
            ps.setString(6, descTxt.getText());
            ps.setInt(7, Integer.parseInt(lvlTxt.getText()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) okBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.SPELLS);
    }

}