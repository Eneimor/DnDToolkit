import dnd.Utils.lvlcntr;
import javafx.animation.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import dnd.Utils.SpriteAnimation;
import dnd.Utils.DR;

import java.util.ArrayList;


public class DiceRollerController {
    DR a = new DR();

    @FXML
    private Label resultLabel;
    @FXML
    private Spinner numOfDices;
    @FXML
    private Spinner mods;
    //Наполнение ChoiceBox
    @FXML
    private ChoiceBox TypeOfDices;

    @FXML
    private void initialize() {
        TypeOfDices.setValue("D4");
        TypeOfDices.setItems(TypeOfDicesList);
        SpinnerValueFactory<Integer> diceValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1);
        SpinnerValueFactory<Integer> modsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,1);
        this.numOfDices.setValueFactory(diceValueFactory);
        this.mods.setValueFactory(modsValueFactory);
    }

    ObservableList<String> TypeOfDicesList = FXCollections
            .observableArrayList("D4", "D6", "D8", "D10", "D12", "D20", "D100");

    //Добавляем чекбоксы
    @FXML
    private CheckBox ch1;
    @FXML
    private CheckBox ch2;

    //Создание Imageview и спрайтовой анимации
    @FXML
    private ImageView imgvw;

    private static final int COLUMNS = 3;
    private static final int COUNT = 3;
    private static final int OFFSET_X = 0;
    private static final int OFFSET_Y = 32;
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;


    //Метод, совершающий ролл
    @FXML
    private void handleRoll(ActionEvent actionEvent) throws Exception {

        int d6 = a.rnd.nextInt(6) + 1;
        int d8 = a.rnd.nextInt(8) + 1;
        int d10 = a.rnd.nextInt(10) + 1;
        int d12 = a.rnd.nextInt(12) + 1;
        int d20 = a.rnd.nextInt(20) + 1;
        int d100 = a.rnd.nextInt(100) + 1;


        String dice = (String) TypeOfDices.getValue();
        ArrayList<Integer> diceResults = new ArrayList();
        int n = (int) numOfDices.getValue();
        String v = "";
        int sum = 0;

        //Переменные для модификаторов
        int mod = (int) mods.getValue();
        int temp;
        int sum1;
        String c;

        if (ch1.isSelected()) {
            if (ch2.isSelected()) {
                if (dice.equals("D4")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(4) + 1);
                        c = diceResults.get(i) + " + " + mod;
                        v += c + " + ";
                        temp = diceResults.get(i) + mod;
                        sum += temp;
                    }
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum);
                }
                if (dice.equals("D6")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(6) + 1);
                        c = diceResults.get(i) + " + " + mod;
                        v += c + " + ";
                        temp = diceResults.get(i) + mod;
                        sum += temp;
                    }
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum);
                }
                if (dice.equals("D8")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(8) + 1);
                        c = diceResults.get(i) + " + " + mod;
                        v += c + " + ";
                        temp = diceResults.get(i) + mod;
                        sum += temp;
                    }
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum);
                }
                if (dice.equals("D10")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(10) + 1);
                        c = diceResults.get(i) + " + " + mod;
                        v += c + " + ";
                        temp = diceResults.get(i) + mod;
                        sum += temp;
                    }
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum);
                }
                if (dice.equals("D12")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(12) + 1);
                        c = diceResults.get(i) + " + " + mod;
                        v += c + " + ";
                        temp = diceResults.get(i) + mod;
                        sum += temp;
                    }
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum);
                }
                if (dice.equals("D20")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(20) + 1);
                        c = diceResults.get(i) + " + " + mod;
                        v += c + " + ";
                        temp = diceResults.get(i) + mod;
                        sum += temp;
                    }
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum);
                }
                if (dice.equals("D100")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(100) + 1);
                        c = diceResults.get(i) + " + " + mod;
                        v += c + " + ";
                        temp = diceResults.get(i) + mod;
                        sum += temp;
                    }
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum);
                }
            } else {
                if (dice.equals("D4")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(4) + 1);
                        v += diceResults.get(i) + " + ";
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum + " + " + mod + " = " + sum1);
                }
                if (dice.equals("D6")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(6) + 1);
                        v += diceResults.get(i) + " + ";
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum + " + " + mod + " = " + sum1);
                }
                if (dice.equals("D8")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(8) + 1);
                        v += diceResults.get(i) + " + ";
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum + " + " + mod + " = " + sum1);
                }
                if (dice.equals("D10")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(10) + 1);
                        v += diceResults.get(i) + " + ";
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum + " + " + mod + " = " + sum1);
                }
                if (dice.equals("D12")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(12) + 1);
                        v += diceResults.get(i) + " + ";
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum + " + " + mod + " = " + sum1);
                }
                if (dice.equals("D20")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(20) + 1);
                        v += diceResults.get(i) + " + ";
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum + " + " + mod + " = " + sum1);
                }
                if (dice.equals("D100")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(100) + 1);
                        v += diceResults.get(i) + " + ";
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    String b = v.substring(0, v.length() - 3);
                    resultLabel.setText(b + " = " + sum + " + " + mod + " = " + sum1);
                }

            }
        } else {
            if (ch2.isSelected()) {
                if (dice.equals("D4")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(4) + 1);
                        sum += diceResults.get(i) + mod;
                    }
                    resultLabel.setText(String.valueOf(sum));
                }
                if (dice.equals("D6")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(6) + 1);
                        sum += diceResults.get(i)  + mod;
                    }
                    resultLabel.setText(String.valueOf(sum));
                }
                if (dice.equals("D8")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(8) + 1);
                        sum += diceResults.get(i)  + mod;
                    }
                    resultLabel.setText(String.valueOf(sum));
                }
                if (dice.equals("D10")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(10) + 1);
                        sum += diceResults.get(i)  + mod;
                    }
                    resultLabel.setText(String.valueOf(sum));
                }
                if (dice.equals("D12")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(12) + 1);
                        sum += diceResults.get(i)  + mod;
                    }
                    resultLabel.setText(String.valueOf(sum));
                }
                if (dice.equals("D20")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(20) + 1);
                        sum += diceResults.get(i)  + mod;
                    }
                    resultLabel.setText(String.valueOf(sum));
                }
                if (dice.equals("D100")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(100) + 1);
                        sum += diceResults.get(i)  + mod;
                    }
                    resultLabel.setText(String.valueOf(sum));
                }
            } else {
                if (dice.equals("D4")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(4) + 1);
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    resultLabel.setText(String.valueOf(sum1));
                }
                if (dice.equals("D6")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(6) + 1);
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    resultLabel.setText(String.valueOf(sum1));
                }
                if (dice.equals("D8")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(8) + 1);
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    resultLabel.setText(String.valueOf(sum1));
                }
                if (dice.equals("D10")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(10) + 1);
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    resultLabel.setText(String.valueOf(sum1));
                }
                if (dice.equals("D12")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(12) + 1);
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    resultLabel.setText(String.valueOf(sum1));
                }
                if (dice.equals("D20")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(20) + 1);
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    resultLabel.setText(String.valueOf(sum1));
                }
                if (dice.equals("D100")) {
                    for (int i = 0; i < n; i++) {
                        diceResults.add(a.rnd.nextInt(100) + 1);
                        sum += diceResults.get(i);
                    }
                    sum1 = sum + mod;
                    resultLabel.setText(String.valueOf(sum1));
                }
            }
        }
    }



    /*private void handleRoll(ActionEvent actionEvent) throws Exception {
        Image image = new Image("/dnd/resource/img/sprite.png");
        imgvw.setImage(image);
        imgvw.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
        final Animation animation = new SpriteAnimation(
                imgvw,
                Duration.millis(3000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT);

        animation.setCycleCount(animation.INDEFINITE);
        animation.play();
    }
    */


        @FXML
        void previousPane (ActionEvent event){
            Navigator.loadScreen(Navigator.DMT);
        }
    }

/**  СЛУШАТЕЛЬ ДЛЯ TextField - вдруг пригодится
 *             numOfDices.textProperty().addListener((obs, oldText, newText) -> {
 *             System.out.println("Text changed from "+oldText+" to "+newText);});
 */