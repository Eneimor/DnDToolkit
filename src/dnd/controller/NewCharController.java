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
import javafx.scene.layout.AnchorPane;
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


    @FXML private ListView profList1;
    @FXML private ListView profList2;
    @FXML private Label profCounter;
    @FXML private Label skillLabel;

    @FXML private ScrollPane dsa;

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
    ObservableList<String> proficiencyList1 = FXCollections.observableArrayList();
    ObservableList<String> proficiencyList2 = FXCollections.observableArrayList();

    public final String sqlPersTraits = "SELECT description FROM cl_perstraits WHERE backid= ";
    public final String sqlIdeals = "SELECT description FROM cl_ideals WHERE backid = ";
    public final String sqlBonds = "SELECT description FROM cl_bonds WHERE backid = ";
    public final String sqlFlaws = "SELECT description FROM cl_flaws WHERE backid = ";

    public final String sqlRaces = "SELECT racename FROM cl_race";
    public final String sqlSubraces = "SELECT subracename FROM cl_subrace WHERE raceid = (SELECT id FROM cl_race WHERE racename = ";
    public final String sqlClasses = "SELECT name FROM cl_class";
    public final String sqlAlign = "SELECT alignName FROM cl_alignment WHERE id < 10";
    public final String sqlBack = "SELECT name FROM cl_background";
    public final String sqlSkills = "SELECT b.locname FROM class_skills a INNER JOIN cl_skills b ON a.skillid = b.id\n" +
                                    "WHERE classid = (SELECT id FROM cl_class WHERE name = ";
    public final String sqlSkillcounter = "SELECT skillcount FROM class_skillscount WHERE classid = (SELECT id FROM cl_class WHERE name = ";
    public final String sqlEquipment = "select equipvar, description from class_equip where classid = ";

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
                PreparedStatement psSkills = connect.getPreparedStatement(sqlSkills + "\"" + getClassVal + "\");");
                PreparedStatement psClassCount = connect.getPreparedStatement(sqlSkillcounter + "\"" + getClassVal + "\");");
                try {
                    ResultSet rsSkills = psSkills.executeQuery();
                    ResultSet rsSkillCount = psClassCount.executeQuery();
                    ResultSet rs3 = ps3.executeQuery();
                    clTxt.setText(rs3.getString("description"));
                    if (!proficiencyList1.isEmpty()) proficiencyList1.clear();
                    while (rsSkills.next()) {
                        proficiencyList1.add(rsSkills.getString("locname"));
                    }
                    profList1.setItems(proficiencyList1);
                    profCounter.setText(String.valueOf(rsSkillCount.getInt("skillcount")));
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    connect.closePrepareStatement(psSkills);
                    connect.closePrepareStatement(psClassCount);
                    connect.closePrepareStatement(ps3);
                }


                //Делаем отображение инвентаря
                for (int i = 0; i < 3; i++) {
                    dsa.setContent(new Pane());
                    dsa.getContent().setId("sdf");
                }

                Label l = new Label();
                dsa.setContent(l);
                l.setText("Test");




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



    //TODO: доработать эти два метода. В целом работают, но снова проблема с пустыми значениями в Label
    @FXML
    void addSkill() {
        int a = Integer.parseInt(profCounter.getText());
        if (a > 0) {
            String s = (String) profList1.getSelectionModel().getSelectedItem();
            proficiencyList2.add(s);
            proficiencyList1.remove(s);
            profList1.setItems(proficiencyList1);
            profList2.setItems(proficiencyList2);
            a--;
            profCounter.setText(String.valueOf(a));
            skillLabel.setText(skillLabel.getText() + ", " + s);
        }
    }

    ///TODO: лайфстайл добавить, физические характеристики (волосы, кожа, глаза, высота, вес, возраст, гендер
    // TODO: заметки: ORGANIZATIONS, ALLIES, ENEMIES, BACKSTORY, OTHER
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
                if (skillLabel.getText().isEmpty()) {
                    skillLabel.setText(proficiencyList2.get(i));
                    skillLabel.setText(skillLabel.getText() + ", ");
                }
                else {
                    skillLabel.setText(skillLabel.getText() + ", " + proficiencyList2.get(i));
                }
            }


        }
    }



    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.CHARMAN);
    }
}