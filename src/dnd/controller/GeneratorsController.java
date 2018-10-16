package dnd.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

public class GeneratorsController {

    @FXML
    private ChoiceBox Gens;
    @FXML
    private Pane GenPane1;
    @FXML
    private Pane GenPane2;
    @FXML
    private Pane GenPane3;
    @FXML
    private Pane GenPane4;
    @FXML
    private Pane GenPane5;


    ObservableList<String> GeneratorList = FXCollections
            .observableArrayList("GenPane1", "Generator 2", "Generator 3", "Generator 4", "Generator 5",
                    "Generator 6", "Generator 7");
    /*
    private void setVis()  {
        GenPane1.setVisible(false);
        GenPane2.setVisible(false);
        GenPane3.setVisible(false);

    }
    */
    @FXML
    private void initialize() {
        GenPane1.setVisible(false);
        Gens.setValue("Choose generator...");
        Gens.setItems(GeneratorList);
        Gens.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue.equals("Generator 1")) {
                    GenPane1.setVisible(true);
                }
                else {
                    GenPane1.setVisible(false);
                }
            }
        });

        //Gens.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
          //  @Override
            //public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
              //  System.out.println(Gens.getItems().get((Integer) number2));
                //GenPane1.setVisible(true);
         //   }
       // });
    }







    @FXML
    private void handleRoll(ActionEvent actionEvent) throws Exception {}

}
