import dnd.controller.MainController;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Класс для управления сценами
 *
 * Все методы статичны для простого доступа из дюбого места программы
 */
public class Navigator {

    /**
     * Различные переключаемые окна интерфейса
     */
    public static final String MAIN    = "main.fxml";
    public static final String MMENU = "dnd/view/MainMenu.fxml";
    public static final String CHARMAN = "dnd/view/CharMan.fxml";
    public static final String BEASTMAN = "dnd/view/Bestiary.fxml";
    public static final String CAMPAIGNS = "dnd/view/Campaigns.fxml";
    public static final String DMT = "dnd/view/DMTools.fxml";
    public static final String ITEMS = "dnd/view/Items.fxml";
    public static final String SPELLS = "dnd/view/Spellbook.fxml";
    public static final String DR = "dnd/view/DiceRoller.fxml";
    public static final String NCH = "dnd/view/NewChar.fxml";
    public static final String GENERATORS = "dnd/view/Generators.fxml";
    public static final String ENCOUNTERS = "dnd/view/Generators.fxml";
    public static final String ENVIRONMENT = "dnd/view/Generators.fxml";
    public static final String GENERS = "dnd/view/Generators.fxml";
    public static final String SOUNDS = "dnd/view/Generators.fxml";
    public static final String RULES = "dnd/view/Generators.fxml";
    public static final String MISC = "dnd/view/Generators.fxml";
    public static final String NWSPL = "dnd/view/newSpell.fxml";
    public static final String NWBST = "dnd/view/newBeast.fxml";
    public static final String SHWCHARACTER = "dnd/view/showCharacter.fxml";

    /** Главный контроллер слоев */
    private static MainController mainController;

    /**
     * Сохранение главного контроллера для дальнешего использования в навигации
     */
    public static void setMainController(MainController mainController) {
        Navigator.mainController = mainController;
    }

    /**
     * Загрузка fxml-файлов в основную fxml-панель
     *
     * До этого загруженные fxml файлы не кэшируются
     * fxml загружается заново и иерархия нодов генерируется
     * каждый раз когда метод вызывается
     *
     * Более сложная функция загрузки потенциально может добавить некоторые
     * улучшения в оптимизации, например:
     * TODO: кеш FXMLLoaders
     * - кэшированные узлы fxml, чтобы их можно было вызвать или повторно использовать
     * TODO: позволяет пользователю указывать повторное использование узла vista или новое создание
     * - возвращать и пересылать историю, как браузер
     *
     */
    public static void loadScreen(String fxml) {
        try {
            mainController.setScreen(
                    FXMLLoader.load(
                            Navigator.class.getResource(
                                    fxml
                            )
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
