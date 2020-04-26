package dnd.model;

import javafx.beans.property.*;

/**
 * класс-модель для персонажа (Character)
 * @author Igor Pozdnyakov
 */

public class Character {

    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty chClass;
    private final StringProperty race;
    private final StringProperty background;
    private final StringProperty playerName;
    private final StringProperty align;
    private final BooleanProperty sex;


    private IntegerProperty level;
    private IntegerProperty exp;


    private IntegerProperty str;
    private IntegerProperty dex;
    private IntegerProperty con;
    private IntegerProperty intl;
    private IntegerProperty wis;
    private IntegerProperty cha;

//TODO: доделать пол персонажа

    /*
    * Конструктор по умолчанию
    */

    public Character() {
        this(0, null, null, null, null, null, true, 0,
                1,9,9,9,9,9,9);
    }

    /*
     * Конструктор с некоторыми начальными данными
     */
    public Character (int id, String name, String chClass) {

        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        this.chClass = new SimpleStringProperty(chClass);

        //Остальные данные для отображения персонажа
        this.race = new SimpleStringProperty("раса");
        this.background = new SimpleStringProperty("бэк");
        this.playerName = new SimpleStringProperty("имя игрока");
        this.align = new SimpleStringProperty("алайн");
        this.sex = new SimpleBooleanProperty(true);
        this.exp = new SimpleIntegerProperty(0);
        this.level = new SimpleIntegerProperty();
    }

    //Конструктор по умолчанию с учетом характеристик





    //Конструктор с учетом характеристик
    public Character (int id, String name, String chClass, String race, String back, String align, boolean sex,
                      int exp, int level, int str, int dex, int con, int intl, int wis, int cha) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        this.chClass = new SimpleStringProperty(chClass);

        //Остальные данные для отображения персонажа
        this.race = new SimpleStringProperty(race);
        this.background = new SimpleStringProperty(back);
        this.align = new SimpleStringProperty(align);
        this.sex = new SimpleBooleanProperty(true);
        this.exp = new SimpleIntegerProperty(exp);
        this.level = new SimpleIntegerProperty(level);

        this.str = new SimpleIntegerProperty(str);
        this.dex = new SimpleIntegerProperty(dex);
        this.con = new SimpleIntegerProperty(con);
        this.intl = new SimpleIntegerProperty(intl);
        this.wis = new SimpleIntegerProperty(wis);
        this.cha = new SimpleIntegerProperty(cha);

        this.playerName = new SimpleStringProperty("имя игрока");
    }


    public boolean getSex() {
        return sex.get();
    }

    public void setSex(boolean sex) {
        this.sex.set(sex);
    }

    public BooleanProperty sexProperty() {
        return sex;
    }



    //геттеры и сеттеры для переменных

    //имя персонажа
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    //класс
    public String getChClass() {
        return chClass.get();
    }

    public void setChClass(String chClass) {
        this.chClass.set(chClass);
    }
    
    public StringProperty chClassProperty() {
        return chClass;
    }

    //раса
    public String getRace() {
        return race.get();
    }

    public void setRace(String race) {
        this.race.set(race);
    }

    public StringProperty raceProperty() {
        return race;
    }

    //бэк
    public String getBackground() {
        return background.get();
    }

    public void setBackground(String background) {
        this.background.set(background);
    }

    public StringProperty backProperty() {
        return background;
    }

    //имя игрока (TODO: используется логин?)
    public String getPlayerName() {
        return playerName.get();
    }

    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    public StringProperty pnProperty() {
        return playerName;
    }

    //мировоззрение
    public String getAlign() {
        return align.get();
    }

    public void setAlign(String align) {
        this.align.set(align);
    }

    public StringProperty alignProperty() {
        return align;
    }

    //айди записи
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    //опыт
    public int getExp() {
        return exp.get();
    }

    public void setExp(int exp) {
        this.exp.set(exp);
    }

    public IntegerProperty expProperty() {
        return exp;
    }

    //уровень
    public int getLevel() {
        return level.get();
    }

    public void setLevel(int level) {
        this.level.set(level);
    }

    public IntegerProperty levelProperty() {
        return level;
    }




    //сила
    public int getStr() {
        return str.get();
    }

    public void setStr(int str) {
        this.str.set(str);
    }

    public IntegerProperty strProperty() {
        return str;
    }

    //ловкость
    public int getDex() {
        return dex.get();
    }

    public void setDex(int dex) {
        this.dex.set(dex);
    }

    public IntegerProperty dexProperty() {
        return dex;
    }

    //телосложение
    public int getCon() {
        return con.get();
    }

    public void setCon(int con) {
        this.con.set(con);
    }

    public IntegerProperty conProperty() {
        return con;
    }

    //интеллект
    public int getIntl() {
        return intl.get();
    }

    public void setIntl(int intl) {
        this.intl.set(intl);
    }

    public IntegerProperty intlProperty() {
        return intl;
    }

    //мудрость
    public int getWis() {
        return wis.get();
    }

    public void setWis(int wis) {
        this.wis.set(wis);
    }

    public IntegerProperty wisProperty() {
        return wis;
    }

    //харизма
    public int getCha() {
        return cha.get();
    }

    public void setCha(int cha) {
        this.cha.set(cha);
    }

    public IntegerProperty chaProperty() {
        return cha;
    }


}
