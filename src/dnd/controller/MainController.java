package dnd.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * Главный класс-контроллер для переключения fxml
 */
public class MainController {

    /** Где будет размещаться переключаемые fxml */
    @FXML
    private StackPane Holder;

    /**
     * Заменяет отображаемый fxml.
     *
     *
     *
     * @param node узел нода для установки сцены.
     */
    public void setScreen(Node node) {
        Holder.getChildren().setAll(node);
    }

}