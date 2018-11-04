import dnd.Utils.Connect;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneratorsController {
    @FXML
    private Label txtlabel;

    @FXML
    private ChoiceBox chb;

    @FXML
    private ChoiceBox shChb;

    @FXML
    private ChoiceBox placeid;

    @FXML
    private Pane generatorPane1;

    ObservableList<String> rumors = FXCollections.observableArrayList();
    ObservableList<String> genChb2 = FXCollections.observableArrayList();


    public static final String sql1 = "SELECT rumor FROM gens WHERE ";
    public static final String sqlEnd = "ORDER BY RANDOM() LIMIT 1";
    public static final String sql2 = "SELECT DISTINCT genname FROM gens";
    public static final String sql3 = "SELECT DISTINCT place FROM gens WHERE genname = ";
    public static final String sql8 = "SELECT DISTINCT dopcol FROM gens WHERE genname = ";
    public static final String sql = "SELECT rumor FROM gens ORDER BY RANDOM() LIMIT 1"; //общий запрос

    ObservableList<String> genChb = FXCollections.observableArrayList();

    public void initialize() {
        Connect connect = new Connect();
        PreparedStatement ps1 = connect.getPreparedStatement(sql2);
        try {
            ResultSet rs2 = ps1.executeQuery();
            while (rs2.next()) {
                genChb.add(rs2.getString("genname"));
            }
            chb.setItems(genChb);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        generatorPane1.setVisible(false);
        chb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String nVal = (String) chb.getValue();
                //Это рабочий вариант. Сохрани
                if (newValue.equals(nVal)) {
                    PreparedStatement ps3 = connect.getPreparedStatement(sql3 + "\"" + nVal + "\"");
                    PreparedStatement ps99 = connect.getPreparedStatement(sql8 + "\"" + nVal + "\" AND dopcol IS NOT NULL");
                    if (placeid.getItems().isEmpty()) {
                        try {
                            ResultSet rs3 = ps3.executeQuery();
                            ResultSet rs99 = ps99.executeQuery();
                            while (rs3.next()) {
                                rumors.add(rs3.getString("place"));
                            }
                            placeid.setItems(rumors);
                            if (!rs99.isClosed()) {
                                if (shChb.getItems().isEmpty()) {
                                    shChb.setDisable(false);
                                    shChb.setVisible(true);
                                    while (rs99.next()) {
                                        genChb2.add(rs99.getString("dopcol"));
                                    }
                                } else {
                                    shChb.setDisable(false);
                                    shChb.setVisible(true);
                                    genChb2.clear();
                                    while (rs99.next()) {
                                        genChb2.add(rs99.getString("dopcol"));
                                    }
                                }
                            } else {
                                shChb.setDisable(true);
                                shChb.setVisible(false);
                            }
                            shChb.setItems(genChb2);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        rumors.clear();
                        try {
                            ResultSet rs3 = ps3.executeQuery();
                            ResultSet rs99 = ps99.executeQuery();
                            while (rs3.next()) {
                                rumors.add(rs3.getString("place"));
                            }
                            placeid.setItems(rumors);
                            if (!rs99.isClosed()) {
                                if (shChb.getItems().isEmpty()) {
                                    shChb.setDisable(false);
                                    shChb.setVisible(true);
                                    while (rs99.next()) {
                                        genChb2.add(rs99.getString("dopcol"));
                                    }
                                } else {
                                    genChb2.clear();
                                    shChb.setDisable(false);
                                    shChb.setVisible(true);
                                    while (rs99.next()) {
                                        genChb2.add(rs99.getString("dopcol"));
                                    }
                                }
                            } else {
                                shChb.setDisable(true);
                                shChb.setVisible(false);
                            }
                            shChb.setItems(genChb2);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    generatorPane1.setVisible(true);
                }
            }
        });
    }

    //Кнопка дя генерации
    //TODO: Сделать условия для кнопки генерации
    @FXML
    private void handleGenerate(ActionEvent actionEvent) throws Exception {
        try {
            String gen = (String) chb.getValue();
            String place = (String) placeid.getValue();
            String dop = (String) shChb.getValue();

            Connect connect = new Connect();
            //Если второй чб активен
            if (gen.equals(gen) && !shChb.isDisable()) {
                //если чб1 и чб2 выбраны
                if (place.equals(place) && dop.equals(dop)) {
                    PreparedStatement ps4 = connect.getPreparedStatement(sql1 + "genname = \"" + gen + "\" AND place = \"" + place + "\" AND dopcol = \"" + dop + "\"" + sqlEnd);
                    try {
                        ResultSet rs = ps4.executeQuery();
                        txtlabel.setText(rs.getString("rumor"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            //Если второй чб не активен
            if (gen.equals(gen) && shChb.isDisable()) {
                //если чб 1 выбран
                if (place.equals(place)) {
                    PreparedStatement ps4 = connect.getPreparedStatement(sql1 + "genname = \"" + gen + "\" AND place = \"" + place + "\"" + sqlEnd);
                    try {
                        ResultSet rs = ps4.executeQuery();
                        txtlabel.setText(rs.getString("rumor"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (NullPointerException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Внимание");
            a.setHeaderText("Ошибка!");
            a.setContentText("Выберите все условия");
            a.showAndWait();
        }
    }

    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.DMT);
    }
}
