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

    @FXML private ListView blistView;
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
    @FXML private Label svStrLabel;
    @FXML private Label svDexLabel;
    @FXML private Label svConLabel;
    @FXML private Label svIntLabel;
    @FXML private Label svWisLabel;
    @FXML private Label svChaLabel;
    @FXML private Label challengeLabel;
    @FXML private Label pasPerceptionLabel;
    @FXML private Label skillLabel;
    @FXML private Label damVulLabel;
    @FXML private Label damResLabel;
    @FXML private Label damImmLabel;
    @FXML private Label condImmLabel;
    @FXML private Label sensesLabel;
    @FXML private Label langLabel;


    private final String sqlAll = "SELECT a.id,\n" +
            "a.locname,\n" +
            "b.name as type, \n" +
            "c.sizeName as size,\n" +
            "d.alignName as align,\n" +
            "a.actype,\n" +
            "a.hp,\n" +
            "a.hpdice,\n" +
            "a.speed,\n" +
            "str,dex,con,intl,wis,cha,\n" +
            "svstr,svdex,svcon,svintl,svwis,svcha," +
            "passiveperception,cr\n" +
            "FROM cl_monstrum a\n" +
            "INNER JOIN cl_monstrtype b ON a.monstertype = b.id\n" +
            "INNER JOIN cl_size c ON a.monstersize = c.id\n" +
            "INNER JOIN cl_alignment d ON a.align = d.id";

    private final String sqlMonsterSkills = "select b.name as skill from monstr_skills a\n" +
            "INNER JOIN cl_skills b ON a.skillid = b.id\n" +
            "where monsterid = ";

    private final String sqlMonsterVul = "SELECT b.name as damageVul FROM monstr_damagevulner a\n" +
            "INNER JOIN cl_damagetype b ON a.damagetype = b.id\n" +
            "WHERE monsterid = ";

    private final String sqlMonsterRes = "SELECT b.name as damRes FROM monstr_damageresistance a\n" +
            "INNER JOIN cl_damagetype b ON a.damagetype = b.id\n" +
            "WHERE monsterid = ";

    private final String sqlMonsterImm = "SELECT b.name as damImm FROM monstr_damageimmunity a\n" +
            "INNER JOIN cl_damagetype b ON a.damagetype = b.id\n" +
            "WHERE monsterid = ";

    private final String sqlMonsterCond = "SELECT b.name as condition FROM monstr_conditionimmunity a\n" +
            "INNER JOIN cl_condition b ON a.conditiontype = b.id\n" +
            "WHERE monsterid = ";

    private final String sqlMonsterSenses = "SELECT b.name ||\" \"||val||\" ft.\" as sense FROM monstr_senses a\n" +
            "INNER JOIN cl_passiveskills b ON a.senseid = b.id\n" +
            "WHERE monsterid = ";

    private final String sqlMonsterLanguage = "select langName as language from monstr_languages a\n"+
            "inner join cl_languages b on a.languageid = b.id\n"+
            "where monsterid = ";

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
                m.setSize(rs.getString("size"));
                m.setType(rs.getString("type"));
                m.setAlign(rs.getString("align"));
                m.setAC(rs.getString("acType"));
                m.setHP(rs.getInt("hp"));
                m.setHPDice(rs.getString("hpDice"));
                m.setSpeed(rs.getString("speed"));
                m.setStr(rs.getInt("str"));
                m.setDex(rs.getInt("dex"));
                m.setCon(rs.getInt("con"));
                m.setIntl(rs.getInt("intl"));
                m.setWis(rs.getInt("wis"));
                m.setCha(rs.getInt("cha"));
                m.setSvStr(rs.getInt("svstr"));
                m.setSvDex(rs.getInt("svdex"));
                m.setSvCon(rs.getInt("svcon"));
                m.setSvIntl(rs.getInt("svintl"));
                m.setSvWis(rs.getInt("svwis"));
                m.setSvCha(rs.getInt("svcha"));
                m.setChallenge(rs.getInt("cr"));
                m.setPassivePerceprion(rs.getInt("passiveperception"));

                //скиллы
                //TODO: убрать в строках 'null, '
                //TODO: настроить форматирование подписей
                PreparedStatement ps2 = c.getPreparedStatement(sqlMonsterSkills + rs.getInt("id"));
                try {
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        m.setSkills(m.getSkills() + ", " + rs2.getString("skill"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    c.closePrepareStatement(ps2);
                }

                //уязвимость
                PreparedStatement ps3 = c.getPreparedStatement(sqlMonsterVul + rs.getInt("id"));
                try {
                    ResultSet rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        m.setDamVul(m.getDamVul() + ", " + rs3.getString("damageVul"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    c.closePrepareStatement(ps3);
                }

                //устойчивость
                PreparedStatement ps4 = c.getPreparedStatement(sqlMonsterRes + rs.getInt("id"));
                try {
                    ResultSet rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        m.setDamRes(m.getDamRes() + ", " + rs4.getString("damRes"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    c.closePrepareStatement(ps4);
                }

                //иммунитет
                PreparedStatement ps5 = c.getPreparedStatement(sqlMonsterImm + rs.getInt("id"));
                try {
                    ResultSet rs5 = ps5.executeQuery();
                    while (rs5.next()) {
                        m.setDamRes(m.getDamImm() + ", " + rs5.getString("damImm"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    c.closePrepareStatement(ps5);
                }

                //состояния
                PreparedStatement ps6 = c.getPreparedStatement(sqlMonsterCond + rs.getInt("id"));
                try {
                    ResultSet rs6 = ps6.executeQuery();
                    while (rs6.next()) {
                        m.setCondImm(m.getCondImm() + ", " + rs6.getString("condition"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    c.closePrepareStatement(ps6);
                }

                //пассивные навыки (senses)
                PreparedStatement ps7 = c.getPreparedStatement(sqlMonsterSenses + rs.getInt("id"));
                try {
                    ResultSet rs7 = ps7.executeQuery();
                    while (rs7.next()) {
                        m.setSenses(m.getSenses() + ", " + rs7.getString("sense"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    c.closePrepareStatement(ps7);
                }

                //языки
                PreparedStatement ps8 = c.getPreparedStatement(sqlMonsterLanguage + rs.getInt("id"));
                try {
                    ResultSet rs8 = ps8.executeQuery();
                    while (rs8.next()) {
                        m.setLangs(m.getLangs() + ", " + rs8.getString("language"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    c.closePrepareStatement(ps8);
                }

                monsterData.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c.closePrepareStatement(ps);
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
            sizeLabel.setText(monster.getSize() + " " + monster.getType() + ", " + monster.getAlign());
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
            //TODO: сделать, чтобы если спабросок = 0, то пустое значение
            svStrLabel.setText(String.valueOf(monster.getSvStr()));
            svDexLabel.setText(String.valueOf(monster.getSvDex()));
            svConLabel.setText(String.valueOf(monster.getSvCon()));
            svIntLabel.setText(String.valueOf(monster.getSvIntl()));
            svWisLabel.setText(String.valueOf(monster.getSvWis()));
            svChaLabel.setText(String.valueOf(monster.getSvCha()));
            challengeLabel.setText(String.valueOf(monster.getChallenge()));
            pasPerceptionLabel.setText(String.valueOf(monster.getPassivePerceprion()));
            skillLabel.setText(monster.getSkills());
            damVulLabel.setText(monster.getDamVul());
            damResLabel.setText(monster.getDamRes());
            damImmLabel.setText(monster.getDamImm());
            condImmLabel.setText(monster.getCondImm());
            sensesLabel.setText(monster.getSenses());
            langLabel.setText(monster.getLangs());
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
            svStrLabel.setText("");
            svDexLabel.setText("");
            svConLabel.setText("");
            svIntLabel.setText("");
            svWisLabel.setText("");
            svChaLabel.setText("");
            challengeLabel.setText("");
            pasPerceptionLabel.setText("");
            skillLabel.setText("");
            damVulLabel.setText("");
            damResLabel.setText("");
            damImmLabel.setText("");
            condImmLabel.setText("");
            sensesLabel.setText("");
            langLabel.setText("");
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