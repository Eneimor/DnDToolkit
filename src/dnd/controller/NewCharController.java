import dnd.Utils.Buttons;
import dnd.Utils.Connect;
import dnd.Utils.Generate;
import dnd.model.Character;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;


public class NewCharController {
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
    @FXML private ListView profList1;
    @FXML private ListView profList2;
    @FXML private Label profCounter;
    @FXML private Label skillLabel;
    @FXML private VBox vbxOuter;
    @FXML private GridPane combatGridPane;

    private Stage dialogStage;
    private Character character;

    private boolean okClicked = false;
    private int skillcnt;
    private int numOfVars;      //колчество вариантов в одной инвентарной группе
    private int countOfPans;    //количество групп инвентаря
    private String[][] equipmentVariants; //массив в который по хорошему надо класть айдишники
    private ComboBox<String>[] equipBox;
    private ObservableList equipList[];

    private int combatSkillCount;
    private Pane[] combatPane;
    int countofCombatPanes;


    ObservableList<String> races = FXCollections.observableArrayList();
    ObservableList<String> subraces = FXCollections.observableArrayList();
    ObservableList<String> classes = FXCollections.observableArrayList();
    ObservableList<Integer> level = FXCollections.observableArrayList();
    ObservableList<String> align = FXCollections.observableArrayList();
    ObservableList<String> backgrounds = FXCollections.observableArrayList();
    ObservableList<String> abilCh = FXCollections.observableArrayList();
    ObservableList<String> proficiencyList1 = FXCollections.observableArrayList();
    ObservableList<String> proficiencyList2 = FXCollections.observableArrayList();
    ObservableList<Integer> combatList = FXCollections.observableArrayList();



    public final String sqlPersTraits = "SELECT description FROM cl_perstraits WHERE backid= ";
    public final String sqlIdeals = "SELECT description FROM cl_ideals WHERE backid = ";
    public final String sqlBonds = "SELECT description FROM cl_bonds WHERE backid = ";
    public final String sqlFlaws = "SELECT description FROM cl_flaws WHERE backid = ";
    public final String sqlRaces = "SELECT racename FROM cl_race";
    public final String sqlSubraces = "SELECT subracename FROM cl_subrace WHERE raceid = (SELECT id FROM cl_race WHERE racename = ";
    public final String sqlClasses = "SELECT name FROM cl_class";
    public final String sqlAlign = "SELECT alignName FROM cl_alignment WHERE id < 10";
    public final String sqlBack = "SELECT name FROM cl_background";
    public final String sqlSkills = "SELECT b.locname FROM class_skills a INNER JOIN cl_skills b ON a.skillid = b.id WHERE classid = ";
    public final String sqlSkillcounter = "SELECT skillcount FROM class_skillscount WHERE classid = ";
    public final String sqlPaneCount = "SELECT count(distinct pancounter) AS countOfPans,max(equipvar) AS maxCountofVars FROM class_equip WHERE classid = ?";
    public final String getSqlGetEquipGroup = "SELECT description from class_equip WHERE classid = ? AND pancounter = ?";

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        Generate g = new Generate();

        countPane.setVisible(false);
        stPane.setVisible(false);

        Image image = new Image("/dnd/resource/img/avatars/ef01.jpg");

        imgVw1.setImage(image);
        imgVw2.setImage(image);

        //Наполняем с писки
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
        } finally {
            connect.closePrepareStatement(ps);
            connect.closePrepareStatement(ps1);
            connect.closePrepareStatement(ps2);
            connect.closePrepareStatement(ps3);
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
                //TODO: посмотреть что тут за ошибка выпадает
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
                    } finally {
                        connect.closePrepareStatement(ps3);
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
                PreparedStatement ps3 = connect.getPreparedStatement("SELECT id, description FROM cl_class WHERE name = "
                        + "\"" + getClassVal + "\"");
                try {
                    ResultSet rs3 = ps3.executeQuery();
                    g.setClassid(rs3.getInt("id"));
                    clTxt.setText(rs3.getString("description"));
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connect.closePrepareStatement(ps3);
                }

                PreparedStatement psSkills = connect.getPreparedStatement(sqlSkills + g.getClassid());
                PreparedStatement psClassCount = connect.getPreparedStatement(sqlSkillcounter + g.getClassid() + ";");

                try {
                    ResultSet rsSkills = psSkills.executeQuery();
                    ResultSet rsSkillCount = psClassCount.executeQuery();

                    if (!proficiencyList1.isEmpty()) proficiencyList1.clear();
                    if (!proficiencyList2.isEmpty()) proficiencyList2.clear();
                    while (rsSkills.next()) {
                        proficiencyList1.add(rsSkills.getString("locname"));
                    }
                    profList1.setItems(proficiencyList1);
                    skillcnt = rsSkillCount.getInt("skillcount");
                    profCounter.setText(String.valueOf(skillcnt));
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connect.closePrepareStatement(psSkills);
                    connect.closePrepareStatement(psClassCount);
                }

                //Эквипмент
                PreparedStatement psGetNumbersForEquip = connect.getPreparedStatement(sqlPaneCount);
                try {
                    psGetNumbersForEquip.setInt(1, g.getClassid());
                    ResultSet rsEquipNumbers = psGetNumbersForEquip.executeQuery();
                    countOfPans = rsEquipNumbers.getInt("countOfPans");
                    numOfVars = rsEquipNumbers.getInt("maxCountofVars");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connect.closePrepareStatement(psGetNumbersForEquip);
                }

                if (!vbxOuter.getChildren().isEmpty()) vbxOuter.getChildren().clear();

                equipmentVariants = new String[countOfPans][numOfVars];
                equipBox = new ComboBox[countOfPans];
                equipList = new ObservableList[countOfPans];
                for (int i = 0; i < countOfPans; i++) {
                    PreparedStatement psGetEquipGroup = connect.getPreparedStatement(getSqlGetEquipGroup);
                    try {
                        psGetEquipGroup.setInt(1, g.getClassid());
                        psGetEquipGroup.setInt(2, (i + 1));
                        ResultSet rsGetEquipGroup = psGetEquipGroup.executeQuery();
                        equipBox[i] = new ComboBox<>();
                        equipList[i] = FXCollections.observableArrayList();
                        while (rsGetEquipGroup.next()) {
                            equipList[i].add(rsGetEquipGroup.getString("description"));
                        }
                        equipBox[i].setItems(equipList[i]);
                        equipBox[i].setPrefHeight(30);
                        equipBox[i].setPrefWidth(vbxOuter.getPrefWidth() - 40);
                        equipBox[i].setLayoutX(vbxOuter.getLayoutX() + 25);
                        equipBox[i].setLayoutY(i * 30);
                        vbxOuter.setSpacing(20);
                        vbxOuter.getChildren().add(equipBox[i]);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        connect.closePrepareStatement(psGetEquipGroup);
                    }
                }

                //Боевые скиллы


                /*
                 * инициализируем гридпейн
                 * инииализируем массив с надписями количество строк * количество колонок - 1
                 * в цикле добавлем строку в гридпейн и раскладываем по ним надписи
                 * делаем гридпейн видимым
                 * */


                Label[][] combatLabel;
                RowConstraints[] combatRow;


                int counter;
                PreparedStatement psCountOfCombatRows = connect.getPreparedStatement("SELECT COUNT(*) as counter FROM class_features " +
                        "WHERE classid = " + g.getClassid() + " AND lvl <= " + lvlChoice.getValue());
                PreparedStatement psCombatSkills = connect.getPreparedStatement("SELECT id, featname, lvl, description FROM class_features " +
                        "WHERE classid = " + g.getClassid() + " AND lvl <= " + lvlChoice.getValue() + " ORDER BY lvl");
                try {
                    ResultSet rsCombatCount = psCountOfCombatRows.executeQuery();
                    ResultSet rsCombatSkills = psCombatSkills.executeQuery();
                    counter = rsCombatCount.getInt("counter");
                    combatLabel = new Label[counter][3];
                    combatRow = new RowConstraints[counter + 1];
                    combatGridPane.setGridLinesVisible(true);

                    if (!combatList.isEmpty()) combatList.clear();
                    if (combatGridPane.getRowCount() == 0||combatGridPane.getRowCount() > 1)
                        {
                            combatGridPane.getChildren().clear();
                            combatGridPane.getRowConstraints().add(new RowConstraints(55));
                            combatGridPane.getColumnConstraints().add(new ColumnConstraints(110));
                            combatGridPane.getColumnConstraints().add(new ColumnConstraints(110));
                            combatGridPane.getColumnConstraints().add(new ColumnConstraints(155));
                            combatGridPane.getColumnConstraints().add(new ColumnConstraints(185));
                            combatGridPane.setGridLinesVisible(true);
                            combatGridPane.setColumnIndex(new Label("Name"), 1);
                            combatGridPane.setColumnIndex(new Label("Level"), 2);
                            combatGridPane.setColumnIndex(new Label("Description"), 3);


                        }
                    while (rsCombatSkills.next()) {
                        combatList.add(rsCombatSkills.getInt("id"));
                        combatRow[rsCombatSkills.getRow() - 1] = new RowConstraints();
                        combatRow[rsCombatSkills.getRow() - 1].setPrefHeight(55);
                        combatGridPane.getRowConstraints().add(combatRow[rsCombatSkills.getRow() - 1]);

                        combatLabel[rsCombatSkills.getRow() - 1][0] = new Label(rsCombatSkills.getString("featname"));
                        combatLabel[rsCombatSkills.getRow() - 1][1] = new Label(rsCombatSkills.getString("lvl"));
                        combatLabel[rsCombatSkills.getRow() - 1][2] = new Label(rsCombatSkills.getString("description"));

                        combatGridPane.add(combatLabel[rsCombatSkills.getRow() - 1][0], 1, rsCombatSkills.getRow());
                        combatGridPane.add(combatLabel[rsCombatSkills.getRow() - 1][1], 2, rsCombatSkills.getRow());
                        combatGridPane.add(combatLabel[rsCombatSkills.getRow() - 1][2], 3, rsCombatSkills.getRow());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connect.closePrepareStatement(psCombatSkills);
                }


                combatGridPane.setVisible(true);
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
        Random rnd = new Random();
        Connect connect = new Connect();

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

        for (int i = 0; i < equipBox.length; i++) {
            equipBox[i].setValue((String) equipList[i].get(rnd.nextInt(equipList[i].size())));
        }


        if (!proficiencyList2.isEmpty()) proficiencyList2.clear();
        for (int i = 0; i < skillcnt; i++) {
            int o = rnd.nextInt(skillcnt);
            String sk = proficiencyList1.get(o);
            proficiencyList2.add(sk);
            proficiencyList1.remove(o);
            profList1.setItems(proficiencyList1);
            profList2.setItems(proficiencyList2);
            profCounter.setText(String.valueOf(0));
        }

        skillLabel.setText("");
        for (int i = 0; i < proficiencyList2.size(); i++) {
            if (skillLabel.getText().isBlank()) {
                skillLabel.setText(proficiencyList2.get(i));
                skillLabel.setText(skillLabel.getText() + ", ");
            } else if (i == proficiencyList2.size()-1) {
                skillLabel.setText(skillLabel.getText() + proficiencyList2.get(i));
            } else {
                skillLabel.setText(skillLabel.getText() + proficiencyList2.get(i) + ", ");
            }
        }
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



    //TODO: доработать эти два метода. В целом работают, но снова проблема с пустыми значениями в Label
    @FXML
    void addSkill() {
        int a = Integer.parseInt(profCounter.getText());
        if (a > 0) {
            String s = (String) profList1.getSelectionModel().getSelectedItem();
            if (!s.isBlank()) {
                proficiencyList2.add(s);
                proficiencyList1.remove(s);
                profList1.setItems(proficiencyList1);
                profList2.setItems(proficiencyList2);
                a--;
                profCounter.setText(String.valueOf(a));
                if (skillLabel.getText().isBlank()) {
                    skillLabel.setText(s);
                } else {
                    skillLabel.setText(skillLabel.getText() + ", " + s);
                }
            }
        }
    }

    /**
     * ЭКВИП:
     * ЗОЛОТО ИЛИ ЭКВИПМЕНТ
     * ЕСЛИ ЗОЛОТО, ТО
     * */

    @FXML
    void removeSkill() {
        int a = Integer.parseInt(profCounter.getText());
        if (profList2.getSelectionModel().getSelectedItem()!=null) {
            String s = (String) profList2.getSelectionModel().getSelectedItem();
            proficiencyList1.add(s);
            proficiencyList2.remove(s);
            profList1.setItems(proficiencyList1);
            profList2.setItems(proficiencyList2);
            a++;
            profCounter.setText(String.valueOf(a));

            skillLabel.setText("");

            for (int i = 0; i < proficiencyList2.size(); i++) {
                if (skillLabel.getText().isBlank()) {
                    skillLabel.setText(proficiencyList2.get(i));
                    skillLabel.setText(skillLabel.getText() + ", ");
                }
                else {
                    skillLabel.setText(skillLabel.getText() + proficiencyList2.get(i));
                }
            }
        }
    }


    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.CHARMAN);
    }
}