import dnd.Utils.Connect;
import dnd.Utils.Generate;
import dnd.model.Monster;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BeastManController {

    @FXML
    private ListView blistView;
    @FXML private Label nameLabel;
    @FXML private Label sizeLabel;
    @FXML private Label ACLabel;
    @FXML private Label HPLabel;
    @FXML private Label speedLabel;
    @FXML private Label strLabel;
    @FXML private Label dexLabel;
    @FXML private Label conLabel;
    @FXML private Label intLabel;
    @FXML private Label wisLabel;
    @FXML private Label chaLabel;
    @FXML private Label strModLabel;
    @FXML private Label dexModLabel;
    @FXML private Label conModLabel;
    @FXML private Label intModModLabel;
    @FXML private Label wisModLabel;
    @FXML private Label chaModLabel;
    @FXML private Label STLable;
    @FXML private Label skillLabel;
    @FXML private Label damVulLabel;
    @FXML private Label damResLabel;
    @FXML private Label damImmLabel;
    @FXML private Label condImmLabel;
    @FXML private Label sensesLabel;
    @FXML private Label langLabel;
    @FXML private Label challengeLabel;


    @FXML private Label idLabel;

    private final String sqlAll = "SELECT id, locName, MSize, MType, Align, AC, HP, hpDice, speed, " +
            "str,dex,con,intl,wis,cha,sThr,skills,damVul,damRes,damImm,condImm,senses,langs,challenge " +
            "FROM cl_monstrum ORDER BY locName";

    private static ObservableList<Monster> monsterData = FXCollections.observableArrayList();

    public void initialize() {
        Connect c = new Connect();
        PreparedStatement ps = c.getPreparedStatement(sqlAll);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Monster m = new Monster();
                m.setId(rs.getInt("id"));
                m.setLocName(rs.getString("locName"));
                m.setSize(rs.getInt("MSize"));
                m.setType(rs.getString("MType"));
                m.setAlign(rs.getInt("Align"));
                m.setAC(rs.getString("AC"));
                m.setHP(rs.getInt("HP"));
                m.setHPDice(rs.getString("hpDice"));
                m.setSpeed(rs.getString("speed"));
                m.setStr(rs.getInt("str"));
                m.setDex(rs.getInt("dex"));
                m.setCon(rs.getInt("con"));
                m.setIntl(rs.getInt("intl"));
                m.setWis(rs.getInt("wis"));
                m.setCha(rs.getInt("cha"));
                m.setST(rs.getString("sThr"));
                m.setSkills(rs.getString("skills"));
                m.setDamVul(rs.getString("damVul"));
                m.setDamRes(rs.getString("damRes"));
                m.setDamImm(rs.getString("damImm"));
                m.setCondImm(rs.getString("condImm"));
                m.setSenses(rs.getString("senses"));
                m.setLangs(rs.getString("langs"));
                m.setChallenge(rs.getInt("challenge"));
                monsterData.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        blistView.setItems(monsterData);

        //Отображаем данные в ListView
        blistView.setCellFactory(param -> new ListCell<Monster>() {
            @Override
            protected void updateItem(Monster item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getLocName() == null) {
                    setText(null);
                } else {
                    setText(item.getLocName());
                }
            }
        });

        blistView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Monster>() {
            @Override
            public void changed(ObservableValue<? extends Monster> observable, Monster oldValue, Monster newValue) {
                showMonsterData(newValue);
            }
        });

    }


    public void showMonsterData(Monster monster) {
        Generate g = new Generate();
        if (monster != null) {
            nameLabel.setText(monster.getLocName());
            sizeLabel.setText(String.valueOf(monster.getSize()) + " " + monster.getType() + ", " + String.valueOf(monster.getAlign()));
            ACLabel.setText(monster.getAC());
            HPLabel.setText(String.valueOf(monster.getHP()) + " (" + monster.getHPDice() + ")");
            speedLabel.setText(monster.getSpeed());
            strLabel.setText(String.valueOf(monster.getStr()));
            dexLabel.setText(String.valueOf(monster.getDex()));
            conLabel.setText(String.valueOf(monster.getCon()));
            intLabel.setText(String.valueOf(monster.getIntl()));
            wisLabel.setText(String.valueOf(monster.getWis()));
            chaLabel.setText(String.valueOf(monster.getCha()));
            strModLabel.setText(String.valueOf(g.setMod(monster.getStr())));
            dexModLabel.setText(String.valueOf(g.setMod(monster.getDex())));
            conModLabel.setText(String.valueOf(g.setMod(monster.getCon())));
            intModModLabel.setText(String.valueOf(g.setMod(monster.getIntl())));
            wisModLabel.setText(String.valueOf(g.setMod(monster.getWis())));
            chaModLabel.setText(String.valueOf(g.setMod(monster.getCha())));
            STLable.setText(monster.getST());
            skillLabel.setText(monster.getSkills());
            damVulLabel.setText(monster.getDamVul());
            damResLabel.setText(monster.getDamRes());
            damImmLabel.setText(monster.getDamImm());
            condImmLabel.setText(monster.getCondImm());
            sensesLabel.setText(monster.getSenses());
            langLabel.setText(monster.getLangs());
            challengeLabel.setText(String.valueOf(monster.getChallenge()));

        } else {
            nameLabel.setText("");
            sizeLabel.setText("");
            ACLabel.setText("");
            HPLabel.setText("");
            speedLabel.setText("");
            strLabel.setText("");
            dexLabel.setText("");
            conLabel.setText("");
            intLabel.setText("");
            wisLabel.setText("");
            chaLabel.setText("");
            strModLabel.setText("");
            dexModLabel.setText("");
            conModLabel.setText("");
            intModModLabel.setText("");
            wisModLabel.setText("");
            chaModLabel.setText("");
            STLable.setText("");
            skillLabel.setText("");
            damVulLabel.setText("");
            damResLabel.setText("");
            damImmLabel.setText("");
            condImmLabel.setText("");
            sensesLabel.setText("");
            langLabel.setText("");
            challengeLabel.setText("");
        }

    }

    @FXML
    void previousPane(ActionEvent event) {
        Navigator.loadScreen(Navigator.MMENU);
    }

    @FXML
    void newBeast(ActionEvent event) {
        Navigator.loadScreen(Navigator.NWBST);
    }

}