import dnd.Utils.Buttons;
import dnd.Utils.Connect;
import dnd.Utils.DR;
import dnd.Utils.Generate;
import dnd.model.Character;
import dnd.model.Race;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class NewCharController {
    DR dr = new DR();
    Race race = new Race();

    @FXML private Label strLabel;
    @FXML private Label dexLabel;
    @FXML private Label conLabel;
    @FXML private Label intLabel;
    @FXML private Label wisLabel;
    @FXML private Label chaLabel;
    @FXML private Label strModLabel;
    @FXML private Label dexModLabel;
    @FXML private Label conModLabel;
    @FXML private Label intModLabel;
    @FXML private Label wisModLabel;
    @FXML private Label chaModLabel;

    @FXML private Label strLabel1;
    @FXML private Label dexLabel1;
    @FXML private Label conLabel1;
    @FXML private Label intLabel1;
    @FXML private Label wisLabel1;
    @FXML private Label chaLabel1;

    @FXML private RadioButton acrRb;
    @FXML private RadioButton anRb;
    @FXML private RadioButton arRb;
    @FXML private RadioButton atRb;
    @FXML private RadioButton decRb;
    @FXML private RadioButton hisRb;
    @FXML private RadioButton insRb;
    @FXML private RadioButton intRb;
    @FXML private RadioButton invRb;
    @FXML private RadioButton medRb;
    @FXML private RadioButton natRb;
    @FXML private RadioButton percRb;
    @FXML private RadioButton perfRb;
    @FXML private RadioButton persRb;
    @FXML private RadioButton relRb;
    @FXML private RadioButton sohRb;
    @FXML private RadioButton sthRb;
    @FXML private RadioButton surRb;
    @FXML private Label ststLb;

    @FXML private Label cntr;
    @FXML private Label nameLabel;
    @FXML private TextArea txtFld;
    @FXML private TextArea clTxt;
    @FXML private TextArea backTextArea;
    @FXML private ChoiceBox raceChoice;
    @FXML private ChoiceBox classChoice;
    @FXML private ChoiceBox abilityChoice;

    @FXML private ChoiceBox subraceChoice;
    @FXML private ChoiceBox lvlChoice;
    @FXML private ChoiceBox alignChoice;
    @FXML private ChoiceBox backChoice;

    @FXML private ChoiceBox perstrtChoice;
    @FXML private ChoiceBox idealChoice;
    @FXML private ChoiceBox bondChoice;
    @FXML private ChoiceBox flawChoice;

    @FXML private Pane countPane;
    @FXML private Pane stPane;

    @FXML private TextField strTF;
    @FXML private TextField dexTF;
    @FXML private TextField conTF;
    @FXML private TextField intTF;
    @FXML private TextField wisTF;
    @FXML private TextField chaTF;

    @FXML private Label profBonusLabel;
    @FXML private Label speedLabel;
    @FXML private Label maxhpLabel;
    @FXML private Label STLabel;
    @FXML private Label hitDiceLabel;

    @FXML private Label armorLabel;
    @FXML private Label weaponLabel;
    @FXML private Label InstrumentsLabel;

    @FXML private ImageView imgVw1;
    @FXML private ImageView imgVw2;


    private Stage dialogStage;
    private Character character;

    private boolean okClicked = false;



    ObservableList<String> races = FXCollections.observableArrayList();
    ObservableList<String> subraces = FXCollections.observableArrayList();
    ObservableList<String> classes = FXCollections.observableArrayList();
    ObservableList<Integer> level = FXCollections.observableArrayList();
    ObservableList<String> align = FXCollections.observableArrayList();
    ObservableList<String> backgrounds = FXCollections.observableArrayList();
    ObservableList<String> abilCh = FXCollections.observableArrayList();
    public final String sqlPersTraits = "SELECT description FROM cl_perstraits WHERE backid= ";
    public final String sqlIdeals = "SELECT description FROM cl_ideals WHERE backid = ";
    public final String sqlBonds = "SELECT description FROM cl_bonds WHERE backid = ";
    public final String sqlFlaws = "SELECT description FROM cl_flaws WHERE backid = ";

    public final String sqlRaces = "SELECT racename FROM cl_race";
    public final String sqlSubraces = "SELECT subracename FROM cl_subrace WHERE raceid = (SELECT id FROM cl_race WHERE racename = ";
    public final String sqlClasses = "SELECT name FROM cl_class";
    public final String sqlAlign = "SELECT alignName FROM cl_alignment WHERE id < 10";
    public final String sqlBack = "SELECT name FROM cl_background";

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {

        countPane.setVisible(false);
        stPane.setVisible(false);



        Image image = new Image("/dnd/resource/img/avatars/ef01.jpg");
        imgVw1.setImage(image);
        imgVw2.setImage(image);

        //Выставляем начальные значения
        strLabel.setText("8");
        dexLabel.setText("8");
        conLabel.setText("8");
        intLabel.setText("8");
        wisLabel.setText("8");
        chaLabel.setText("8");
        strLabel1.setText("8");
        dexLabel1.setText("8");
        conLabel1.setText("8");
        intLabel1.setText("8");
        wisLabel1.setText("8");
        chaLabel1.setText("8");
        strModLabel.setText("-1");
        dexModLabel.setText("-1");
        conModLabel.setText("-1");
        intModLabel.setText("-1");
        wisModLabel.setText("-1");
        chaModLabel.setText("-1");

        Connect connect = new Connect();
        PreparedStatement ps = connect.getPreparedStatement(sqlRaces);
        PreparedStatement ps1 = connect.getPreparedStatement(sqlClasses);
        PreparedStatement ps2 = connect.getPreparedStatement(sqlAlign);
        PreparedStatement ps3 = connect.getPreparedStatement(sqlBack);
        try {
            ResultSet rs1 = ps.executeQuery();
            ResultSet rs2 = ps1.executeQuery();
            ResultSet rs3 = ps2.executeQuery();
            ResultSet rs4 = ps3.executeQuery();

            while (rs1.next()) {
                races.add(rs1.getString("racename"));
            }
            while (rs2.next()) {
                classes.add(rs2.getString("name"));
            }
            while (rs3.next()) {
                align.add(rs3.getString("alignName"));
            }
            while (rs4.next()) {
                backgrounds.add(rs4.getString("name"));
            }
            raceChoice.setItems(races);
            classChoice.setItems(classes);
            alignChoice.setItems(align);
            backChoice.setItems(backgrounds);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        abilCh.add("Manual Choice");
        abilCh.add("Point Buy");
        abilityChoice.setItems(abilCh);

        for (int i = 1; i <= 20; i++) {
            level.add(i);
        }
        lvlChoice.setItems(level);

        //Слушатель для чойсбокса с абилками
        abilityChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String getAbilVal = (String) abilityChoice.getValue();

            switch (getAbilVal) {
                case "Manual Choice":
                    countPane.setVisible(false);
                    countPane.setDisable(true);
                    stPane.setDisable(false);
                    stPane.setVisible(true);
                    break;
                case "Point Buy":
                    countPane.setVisible(true);
                    countPane.setDisable(false);
                    stPane.setDisable(true);
                    stPane.setVisible(false);
                    break;
            }
        });



        //Слушатель для рас
        raceChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String getRaceVal = (String) raceChoice.getValue();

                //Получение списка подрас
                if (newValue.equals(getRaceVal)) {

                    //TODO: разобраться с NullPointerException при загрузке выпадающего списка подрас
                    PreparedStatement ps = connect.getPreparedStatement(sqlSubraces + "\"" + getRaceVal + "\")");

                        if (subraceChoice.getItems().isEmpty()) {
                            getSubraces(ps);
                            subraceChoice.setItems(subraces);
                        } else {
                            subraces.clear();
                            getSubraces(ps);
                            subraceChoice.setItems(subraces);
                        }

                    PreparedStatement ps2 = connect.getPreparedStatement("SELECT description FROM cl_race WHERE racename ="
                            + "\"" + getRaceVal + "\"");
                    try {
                        ResultSet rs2 = ps2.executeQuery();
                        txtFld.setText(rs2.getString("description"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        connect.closePrepareStatement(ps2);
                    }


                }
            }
        });

        //Слушатель для подрас
        subraceChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String getSubRaceVal = (String) subraceChoice.getValue();
                /*
                if (newValue.equals(getSubRaceVal)) {
                    PreparedStatement ps3 = connect.getPreparedStatement("SELECT description FROM cl_subrace WHERE subracename ="
                            + "\"" + getSubRaceVal + "\"");

                    try {
                        ResultSet rs3 = ps3.executeQuery();
                        if (rs3.isBeforeFirst()) {
                            txtFld.setText(rs3.getString("description"));
                        } else {
                            txtFld.setText("");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
*/
            }
        });

        //Слушатель для классов
        classChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String getClassVal = (String) classChoice.getValue();
                PreparedStatement ps3 = connect.getPreparedStatement("SELECT description FROM cl_class WHERE name = "
                        + "\"" + getClassVal + "\"");
                /*
                try {
                    ResultSet rs3 = ps3.executeQuery();
                    clTxt.setText(rs3.getString("description"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int counter;
                switch (getClassVal) {
                    case "Бард":
                        setDisable();
                        acrRb.setDisable(false);
                        anRb.setDisable(false);
                        arRb.setDisable(false);
                        atRb.setDisable(false);
                        decRb.setDisable(false);
                        hisRb.setDisable(false);
                        insRb.setDisable(false);
                        intRb.setDisable(false);
                        invRb.setDisable(false);
                        medRb.setDisable(false);
                        natRb.setDisable(false);
                        percRb.setDisable(false);
                        perfRb.setDisable(false);
                        persRb.setDisable(false);
                        relRb.setDisable(false);
                        sohRb.setDisable(false);
                        sthRb.setDisable(false);
                        surRb.setDisable(false);
                        acrRb.setVisible(true);
                        anRb.setVisible(true);
                        arRb.setVisible(true);
                        atRb.setVisible(true);
                        decRb.setVisible(true);
                        hisRb.setVisible(true);
                        insRb.setVisible(true);
                        intRb.setVisible(true);
                        invRb.setVisible(true);
                        medRb.setVisible(true);
                        natRb.setVisible(true);
                        percRb.setVisible(true);
                        perfRb.setVisible(true);
                        persRb.setVisible(true);
                        relRb.setVisible(true);
                        sohRb.setVisible(true);
                        sthRb.setVisible(true);
                        surRb.setVisible(true);
                        setCntr(3);
                        break;
                    case "Варвар":
                        setDisable();
                        atRb.setDisable(false);
                        percRb.setDisable(false);
                        surRb.setDisable(false);
                        intRb.setDisable(false);
                        natRb.setDisable(false);
                        anRb.setDisable(false);
                        atRb.setVisible(true);
                        percRb.setVisible(true);
                        surRb.setVisible(true);
                        intRb.setVisible(true);
                        natRb.setVisible(true);
                        anRb.setVisible(true);
                        setCntr(2);
                        break;
                    case "Воин":
                        setDisable();
                        acrRb.setDisable(false);
                        atRb.setDisable(false);
                        percRb.setDisable(false);
                        surRb.setDisable(false);
                        intRb.setDisable(false);
                        hisRb.setDisable(false);
                        insRb.setDisable(false);
                        anRb.setDisable(false);
                        acrRb.setVisible(true);
                        atRb.setVisible(true);
                        percRb.setVisible(true);
                        surRb.setVisible(true);
                        intRb.setVisible(true);
                        hisRb.setVisible(true);
                        insRb.setVisible(true);
                        anRb.setVisible(true);
                        setCntr(2);
                        break;
                    case "Волшебник":
                        setDisable();
                        invRb.setDisable(false);
                        hisRb.setDisable(false);
                        arRb.setDisable(false);
                        medRb.setDisable(false);
                        insRb.setDisable(false);
                        relRb.setDisable(false);
                        invRb.setVisible(true);
                        hisRb.setVisible(true);
                        arRb.setVisible(true);
                        medRb.setVisible(true);
                        insRb.setVisible(true);
                        relRb.setVisible(true);
                        setCntr(2);
                        break;
                    case "Друид":
                        setDisable();
                        percRb.setDisable(false);
                        surRb.setDisable(false);
                        arRb.setDisable(false);
                        medRb.setDisable(false);
                        anRb.setDisable(false);
                        natRb.setDisable(false);
                        insRb.setDisable(false);
                        relRb.setDisable(false);
                        percRb.setVisible(true);
                        surRb.setVisible(true);
                        arRb.setVisible(true);
                        medRb.setVisible(true);
                        anRb.setVisible(true);
                        natRb.setVisible(true);
                        insRb.setVisible(true);
                        relRb.setVisible(true);
                        setCntr(2);
                        break;
                    case "Жрец":
                        setDisable();
                        hisRb.setDisable(false);
                        medRb.setDisable(false);
                        insRb.setDisable(false);
                        relRb.setDisable(false);
                        persRb.setDisable(false);
                        hisRb.setVisible(true);
                        medRb.setVisible(true);
                        insRb.setVisible(true);
                        relRb.setVisible(true);
                        persRb.setVisible(true);
                        setCntr(2);
                        break;
                    case "Колдун":
                        setDisable();
                        invRb.setDisable(false);
                        intRb.setDisable(false);
                        hisRb.setDisable(false);
                        arRb.setDisable(false);
                        decRb.setDisable(false);
                        natRb.setDisable(false);
                        relRb.setDisable(false);
                        invRb.setVisible(true);
                        intRb.setVisible(true);
                        hisRb.setVisible(true);
                        arRb.setVisible(true);
                        decRb.setVisible(true);
                        natRb.setVisible(true);
                        relRb.setVisible(true);
                        setCntr(2);
                        break;
                    case "Монах":
                        setDisable();
                        acrRb.setDisable(false);
                        atRb.setDisable(false);
                        hisRb.setDisable(false);
                        insRb.setDisable(false);
                        relRb.setDisable(false);
                        sthRb.setDisable(false);
                        acrRb.setVisible(true);
                        atRb.setVisible(true);
                        hisRb.setVisible(true);
                        insRb.setVisible(true);
                        relRb.setVisible(true);
                        sthRb.setVisible(true);
                        setCntr(2);
                        break;
                    case "Паладин":
                        setDisable();
                        atRb.setDisable(false);
                        intRb.setDisable(false);
                        medRb.setDisable(false);
                        insRb.setDisable(false);
                        relRb.setDisable(false);
                        persRb.setDisable(false);
                        atRb.setVisible(true);
                        intRb.setVisible(true);
                        medRb.setVisible(true);
                        insRb.setVisible(true);
                        relRb.setVisible(true);
                        persRb.setVisible(true);
                        setCntr(2);
                        break;
                    case "Плут":
                        setDisable();
                        acrRb.setDisable(false);
                        invRb.setDisable(false);
                        atRb.setDisable(false);
                        percRb.setDisable(false);
                        perfRb.setDisable(false);
                        intRb.setDisable(false);
                        sohRb.setDisable(false);
                        decRb.setDisable(false);
                        insRb.setDisable(false);
                        sthRb.setDisable(false);
                        persRb.setDisable(false);
                        acrRb.setVisible(true);
                        invRb.setVisible(true);
                        atRb.setVisible(true);
                        percRb.setVisible(true);
                        perfRb.setVisible(true);
                        intRb.setVisible(true);
                        sohRb.setVisible(true);
                        decRb.setVisible(true);
                        insRb.setVisible(true);
                        sthRb.setVisible(true);
                        persRb.setVisible(true);
                        setCntr(4);
                        break;
                    case "Следопыт":
                        setDisable();
                        invRb.setDisable(false);
                        atRb.setDisable(false);
                        percRb.setDisable(false);
                        surRb.setDisable(false);
                        natRb.setDisable(false);
                        insRb.setDisable(false);
                        sthRb.setDisable(false);
                        anRb.setDisable(false);
                        invRb.setVisible(true);
                        atRb.setVisible(true);
                        percRb.setVisible(true);
                        surRb.setVisible(true);
                        natRb.setVisible(true);
                        insRb.setVisible(true);
                        sthRb.setVisible(true);
                        anRb.setVisible(true);
                        setCntr(3);
                        break;
                    case "Чародей":
                        setDisable();
                        intRb.setDisable(false);
                        arRb.setDisable(false);
                        decRb.setDisable(false);
                        insRb.setDisable(false);
                        relRb.setDisable(false);
                        persRb.setDisable(false);
                        intRb.setVisible(true);
                        arRb.setVisible(true);
                        decRb.setVisible(true);
                        insRb.setVisible(true);
                        relRb.setVisible(true);
                        persRb.setVisible(true);
                        setCntr(2);
                        break;
                }
                */
            }


        });



        //Слушатель для бэкграундов
        backChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String getBackVal = (String) backChoice.getValue();
                PreparedStatement ps2 = connect.getPreparedStatement("SELECT id FROM cl_background WHERE name = "
                        + "\"" + getBackVal + "\"");
                int getBackId = 0;
                try {
                    ResultSet rs2 = ps2.executeQuery();
                    getBackId = rs2.getInt("id");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connect.closePrepareStatement(ps2);
                }

                PreparedStatement ps4 = connect.getPreparedStatement("SELECT description FROM cl_background WHERE id ="
                        + getBackId);
                try {
                    ResultSet rs4 = ps4.executeQuery();
                    backTextArea.setText(rs4.getString("description"));
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connect.closePrepareStatement(ps4);
                }

                PreparedStatement psTrt = connect.getPreparedStatement(sqlPersTraits + getBackId);
                PreparedStatement psIdeal = connect.getPreparedStatement(sqlIdeals + getBackId);
                PreparedStatement psBonds = connect.getPreparedStatement(sqlBonds + getBackId);
                PreparedStatement psFlaws = connect.getPreparedStatement(sqlFlaws + getBackId);
                perstrtChoice.setItems(list(psTrt));
                idealChoice.setItems(list(psIdeal));
                bondChoice.setItems(list(psBonds));
                flawChoice.setItems(list(psFlaws));



            }
        });

        int stVal = 27;
        ststLb.setText(String.valueOf(stVal));





    }
    int cost;
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    private void setCntr(int counter) {
        cntr.setText(Integer.toString(counter));
        cntr.setVisible(true);
    }



    //TODO: методы для определения уровня и бонуса мастерства
    //TODO: начисление хп



    //метод выключающий все радиобаттоны
    private void setDisable() {
        acrRb.setVisible(false);
        anRb.setVisible(false);
        arRb.setVisible(false);
        atRb.setVisible(false);
        decRb.setVisible(false);
        hisRb.setVisible(false);
        insRb.setVisible(false);
        intRb.setVisible(false);
        invRb.setVisible(false);
        medRb.setVisible(false);
        natRb.setVisible(false);
        percRb.setVisible(false);
        perfRb.setVisible(false);
        persRb.setVisible(false);
        relRb.setVisible(false);
        sohRb.setVisible(false);
        sthRb.setVisible(false);
        surRb.setVisible(false);
        acrRb.setDisable(true);
        anRb.setDisable(true);
        arRb.setDisable(true);
        atRb.setDisable(true);
        decRb.setDisable(true);
        hisRb.setDisable(true);
        insRb.setDisable(true);
        intRb.setDisable(true);
        invRb.setDisable(true);
        medRb.setDisable(true);
        natRb.setDisable(true);
        percRb.setDisable(true);
        perfRb.setDisable(true);
        persRb.setDisable(true);
        relRb.setDisable(true);
        sohRb.setDisable(true);
        sthRb.setDisable(true);
        surRb.setDisable(true);
    }



    private void getSubraces(PreparedStatement ps) {
        Connect c = new Connect();
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subraces.add(rs.getString("subracename"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c.closePrepareStatement(ps);
        }
    }

    //метод для заполнения персональных черт персонажа
    private ObservableList<String> list(PreparedStatement ps) {
        ObservableList<String> list = FXCollections.observableArrayList();
        Connect connect = new Connect();
        try {
            ResultSet trtRs = ps.executeQuery();
            while (trtRs.next()) {
                list.add(trtRs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.closePrepareStatement(ps);
        }
        return list;
    }

    //Возвращаем true если юзер кликнул на ОК, иначе false
    public boolean isOkClicked() {
        return okClicked;
    }

    //TODO: Вызывается, если юзер кликнул ОК
    @FXML
    private void handleOK() {
        if (isInputValid()) {
            character.setName(nameLabel.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    //TODO: Проверяет пользовательский ввод
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameLabel.getText()==null || nameLabel.getText().length() == 0) {
            errorMessage += "Введите имя";
        }
        if (errorMessage.length()==0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fileds!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    void Gen() {
        Generate g = new Generate();

        strLabel.setText(String.valueOf(g.getStr()));
        dexLabel.setText(String.valueOf(g.getDex()));
        conLabel.setText(String.valueOf(g.getCon()));
        intLabel.setText(String.valueOf(g.getIntl()));
        wisLabel.setText(String.valueOf(g.getWis()));
        chaLabel.setText(String.valueOf(g.getCha()));

        strModLabel.setText(String.valueOf(g.getStrMod()));
        dexModLabel.setText(String.valueOf(g.getDexMod()));
        conModLabel.setText(String.valueOf(g.getConMod()));
        intModLabel.setText(String.valueOf(g.getIntlMod()));
        wisModLabel.setText(String.valueOf(g.getWisMod()));
        chaModLabel.setText(String.valueOf(g.getChaMod()));

        raceChoice.setValue(g.getRaceName());
        subraceChoice.setValue(g.getSubraceName());
        classChoice.setValue(g.getClassname());
        lvlChoice.setValue(g.getLevel());
        alignChoice.setValue(g.getAlignment());
        profBonusLabel.setText(String.valueOf(g.getProficiencyBonus()));
        speedLabel.setText(String.valueOf(g.getSpeed()));
        maxhpLabel.setText(String.valueOf(g.getMaxhp()));
        STLabel.setText(g.getSavingThrows());

        hitDiceLabel.setText(g.getHitDiceName());

        armorLabel.setText(g.getArmorType());
        weaponLabel.setText(g.getWeaponType());



        System.out.println(g.getRaceName());
        System.out.println(g.getSubraceName());
        System.out.println(g.getClassname());
        System.out.println(g.getHitDiceName());
        System.out.println(g.getArmorType());
    }

    @FXML
    void setStrButPlus(ActionEvent event) {
        Buttons b = new Buttons();
        b.abilityPlus(strLabel1,ststLb);
    }

    @FXML
    void setDexButPlus(ActionEvent event) {
        Buttons b = new Buttons();
        b.abilityPlus(dexLabel1,ststLb);
    }

    @FXML
    void setConButPlus(ActionEvent event) {
        Buttons b = new Buttons();
        b.abilityPlus(conLabel1,ststLb);
    }

    @FXML
    void setIntButPlus(ActionEvent event) {
        Buttons b = new Buttons();
        b.abilityPlus(intLabel1,ststLb);
    }

    @FXML
    void setWisButPlus(ActionEvent event) {
        Buttons b = new Buttons();
        b.abilityPlus(wisLabel1,ststLb);
    }

    @FXML
    void setChaButPlus(ActionEvent event) {
        Buttons b = new Buttons();
        b.abilityPlus(chaLabel1,ststLb);
    }

    @FXML
    void setStrButMinus(ActionEvent event) {
        Buttons b = new Buttons();
        b.abilityMinus(strLabel1,ststLb);
    }

    @FXML
    void setDexButMinus(ActionEvent event) {
        Buttons b = new Buttons();
        b.abilityMinus(dexLabel1,ststLb);
    }

    @FXML
    void setConButMinus(ActionEvent event) {
        Buttons b = new Buttons();
        b.abilityMinus(conLabel1,ststLb);
    }

    @FXML
    void setIntButMinus(ActionEvent event) {
        Buttons b = new Buttons();
        b.abilityMinus(intLabel1,ststLb);
    }

    @FXML
    void setWisButMinus(ActionEvent event) {
        Buttons b = new Buttons();
        b.abilityMinus(wisLabel1,ststLb);
    }

    @FXML
    void setChaButMinus(ActionEvent event) {
        Buttons b = new Buttons();
        b.abilityMinus(chaLabel1,ststLb);
    }

    //TODO: сделать переключение картинок
    @FXML
    void nextPicture(ActionEvent event) {
    }

    @FXML
    void previousPicture(ActionEvent event) {

    }



    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.CHARMAN);
    }
}