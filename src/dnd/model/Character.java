package dnd.model;

import dnd.Utils.Connect;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
    private final IntegerProperty level;
    private final IntegerProperty exp;

    /*
    * Конструктор по умолчанию
    */

    public Character() {
        this(0, null, null);
    }

    /**
     * Конструктор с некоторыми начальными данными
     */
    Connect connect;
    public Character (int id, String name, String chClass) {

        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        this.chClass = new SimpleStringProperty(chClass);

        //Остальные данные для отображения персонажа
        this.race = new SimpleStringProperty("раса");
        this.background = new SimpleStringProperty("бэк");
        this.playerName = new SimpleStringProperty("имя игрока");
        this.align = new SimpleStringProperty("алайн");
        this.exp = new SimpleIntegerProperty(150);
        this.level = new SimpleIntegerProperty();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getChClass() {
        return chClass.get();
    }

    public void setChClass(String chClass) {
        this.chClass.set(chClass);
    }

    
    public StringProperty chClassProperty() {
        return chClass;
    }


    public String getRace() {
        return race.get();
    }

    public void setRace(String race) {
        this.race.set(race);
    }

    public StringProperty raceProperty() {
        return race;
    }

    public String getBackground() {
        return background.get();
    }

    public void setBackground(String background) {
        this.background.set(background);
    }

    public StringProperty backProperty() {
        return background;
    }

    public String getPlayerName() {
        return playerName.get();
    }

    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    public StringProperty pnProperty() {
        return playerName;
    }

    public String getAlign() {
        return align.get();
    }

    public void setAlign(String align) {
        this.align.set(align);
    }

    public StringProperty alignProperty() {
        return align;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getExp() {return exp.get();}

    public void setExp(int exp) {this.exp.set(exp);}

    public IntegerProperty expProperty() {
        return exp;
    }

    public int getLevel() {
        return level.get();
    }

    public void setLevel(int level) {
        this.level.set(level);
    }

    public IntegerProperty levelProperty() {
        return level;
    }
}
