package dnd.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Race extends Character {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty locName;

    //расовые бонусы
    private IntegerProperty strRaceBonus;
    private IntegerProperty dexRaceBonus;
    private IntegerProperty conRaceBonus;
    private IntegerProperty intRaceBonus;
    private IntegerProperty wisRaceBonus;
    private IntegerProperty chaRaceBonus;

    //вторичные показатели
    private IntegerProperty minAge;
    private IntegerProperty maxAge;
    private IntegerProperty size;
    private IntegerProperty minHeight;
    private IntegerProperty maxHeight;
    private StringProperty languages;
    private IntegerProperty speed; //скорость
    private IntegerProperty maxHP;
    private IntegerProperty hpPerLvl;
    private IntegerProperty darkVision;

    //черты и навыки
    private StringProperty feats;
    private StringProperty weaponFeats;


    public Race() {
        this(0, null, null, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, null, 0, 0, 0,
                0, null, null);
    }

    public Race(int id, String name, String locName, int strBonus, int dexBonus, int conBonus, int intBonus, int wisBonus, int chaBonus,
                int minAge, int maxAge, int size, int minHeight, int maxHeight, String languages, int speed, int maxHP, int hpPerLvl,
                int darkVision, String feats, String weaponFeats
    ) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.locName = new SimpleStringProperty(locName);
        this.strRaceBonus = new SimpleIntegerProperty(strBonus);
        this.dexRaceBonus = new SimpleIntegerProperty(dexBonus);
        this.conRaceBonus = new SimpleIntegerProperty(conBonus);
        this.intRaceBonus = new SimpleIntegerProperty(intBonus);
        this.wisRaceBonus = new SimpleIntegerProperty(wisBonus);
        this.chaRaceBonus = new SimpleIntegerProperty(chaBonus);
        this.minAge = new SimpleIntegerProperty(minAge);
        this.maxAge = new SimpleIntegerProperty(maxAge);
        this.size = new SimpleIntegerProperty(size);
        this.minHeight = new SimpleIntegerProperty(minHeight);
        this.maxHeight = new SimpleIntegerProperty(maxHeight);
        this.languages = new SimpleStringProperty(languages);
        this.speed = new SimpleIntegerProperty(speed);
        this.maxHP = new SimpleIntegerProperty(maxHP);
        this.hpPerLvl = new SimpleIntegerProperty(hpPerLvl);
        this.darkVision = new SimpleIntegerProperty(darkVision);
        this.feats = new SimpleStringProperty(feats);
        this.weaponFeats = new SimpleStringProperty(weaponFeats);
    }

    //Геттеры и сеттеры


    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
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

    public String getLocName() {
        return locName.get();
    }

    public void setLocName(String locName) {
        this.locName.set(locName);
    }

    public StringProperty locNameProperty() {
        return locName;
    }

    public int getStrRaceBonus() {
        return strRaceBonus.get();
    }

    public void setStrRaceBonus(int strRaceBonus) {
        this.strRaceBonus.set(strRaceBonus);
    }

    public IntegerProperty strRaceBonusProperty() {
        return strRaceBonus;
    }

    public int getDexRaceBonus() {
        return dexRaceBonus.get();
    }

    public void setDexRaceBonus(int dexRaceBonus) {
        this.dexRaceBonus.set(dexRaceBonus);
    }

    public IntegerProperty dexRaceBonusProperty() {
        return dexRaceBonus;
    }

    public int getConRaceBonus() {
        return conRaceBonus.get();
    }

    public void setConRaceBonus(int conRaceBonus) {
        this.conRaceBonus.set(conRaceBonus);
    }

    public IntegerProperty conRaceBonusProperty() {
        return conRaceBonus;
    }

    public int getIntRaceBonus() {
        return intRaceBonus.get();
    }

    public void setIntRaceBonus(int intRaceBonus) {
        this.intRaceBonus.set(intRaceBonus);
    }

    public IntegerProperty intRaceBonusProperty() {
        return intRaceBonus;
    }

    public int getWisRaceBonus() {
        return wisRaceBonus.get();
    }

    public void setWisRaceBonus(int wisRaceBonus) {
        this.wisRaceBonus.set(wisRaceBonus);
    }

    public IntegerProperty wisRaceBonusProperty() {
        return wisRaceBonus;
    }

    public int getChaRaceBonus() {
        return chaRaceBonus.get();
    }

    public void setChaRaceBonus(int chaRaceBonus) {
        this.chaRaceBonus.set(chaRaceBonus);
    }

    public IntegerProperty chaRaceBonusProperty() {
        return chaRaceBonus;
    }

    public int getMinAge() {
        return minAge.get();
    }

    public void setMinAge(int minAge) {
        this.minAge.set(minAge);
    }

    public IntegerProperty minAgeProperty() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge.get();
    }

    public void setMaxAge(int maxAge) {
        this.maxAge.set(maxAge);
    }

    public IntegerProperty maxAgeProperty() {
        return maxAge;
    }

    public int getSize() {
        return size.get();
    }

    public void setSize(int size) {
        this.size.set(size);
    }

    public IntegerProperty sizeProperty() {
        return size;
    }

    public int getMinHeight() {
        return minHeight.get();
    }

    public void setMinHeight(int minHeight) {
        this.minHeight.set(minHeight);
    }

    public IntegerProperty minHeightProperty() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight.get();
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight.set(maxHeight);
    }

    public IntegerProperty maxHeightProperty() {
        return maxHeight;
    }

    public String getLanguages() {
        return languages.get();
    }

    public void setLanguages(String languages) {
        this.languages.set(languages);
    }

    public StringProperty languagesProperty() {
        return languages;
    }

    public int getSpeed() {
        return speed.get();
    }

    public void setSpeed(int speed) {
        this.speed.set(speed);
    }

    public IntegerProperty speedProperty() {
        return speed;
    }

    public int getMaxHP() {
        return maxHP.get();
    }

    public void setMaxHP(int maxHP) {
        this.maxHP.set(maxHP);
    }

    public IntegerProperty maxHPProperty() {
        return maxHP;
    }

    public int getHPPerLvl() {
        return hpPerLvl.get();
    }

    public void setHPPerLvl(int hpPerLvl) {
        this.hpPerLvl.set(hpPerLvl);
    }

    public IntegerProperty hpPerLvlProperty() {
        return hpPerLvl;
    }

    public int getDarkVision() {
        return darkVision.get();
    }

    public void setDarkVision(int darkVision) {
        this.darkVision.set(darkVision);
    }

    public IntegerProperty darkVisionProperty() {
        return darkVision;
    }

    public String getFeats() {
        return feats.get();
    }

    public void setFeats(String feats) {
        this.feats.set(feats);
    }

    public StringProperty featsProperty() {
        return feats;
    }

    public String getWeaponFeats() {
        return weaponFeats.get();
    }

    public void setWeaponFeats(String weaponFeats) {
        this.weaponFeats.set(weaponFeats);
    }

    public StringProperty weaponFeatsProperty() {
        return weaponFeats;
    }
}
