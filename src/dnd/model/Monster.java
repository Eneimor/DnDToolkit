package dnd.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Monster {
    private final IntegerProperty id;
    private final StringProperty locName;
    private final IntegerProperty mSize;
    private final StringProperty mType;
    private final IntegerProperty align;
    private final StringProperty ac;
    private final IntegerProperty hp;
    private final StringProperty hpDice;
    private final StringProperty speed;
    private final IntegerProperty str;
    private final IntegerProperty dex;
    private final IntegerProperty con;
    private final IntegerProperty intl;
    private final IntegerProperty wis;
    private final IntegerProperty cha;
    private final StringProperty st;
    private final StringProperty skills;
    private final StringProperty damVul;
    private final StringProperty damRes;
    private final StringProperty damImm;
    private final StringProperty condImm;
    private final StringProperty senses;
    private final StringProperty langs;
    private final IntegerProperty challenge;




    public Monster() {this(0,null,0,null,0, null, 0, null, null,
            0, 0, 0, 0, 0, 0, null,null, null, null, null,
            null, null, null, 0);}

    public Monster(int id, String locName, int mSize, String mType, int align, String ac, int hp, String hpDice,
                   String speed, int str, int dex, int con, int intl, int wis, int cha, String st, String skills,
                   String damVul, String damRes, String damImm, String condImm, String senses, String langs,
                   int challenge) {
        this.id = new SimpleIntegerProperty(id);
        this.locName = new SimpleStringProperty(locName);
        this.mSize = new SimpleIntegerProperty(mSize);
        this.mType = new SimpleStringProperty(mType);
        this.align = new SimpleIntegerProperty(align);
        this.ac = new SimpleStringProperty(ac);
        this.hp = new SimpleIntegerProperty(hp);
        this.hpDice = new SimpleStringProperty(hpDice);
        this.speed = new SimpleStringProperty(speed);
        this.str = new SimpleIntegerProperty(str);
        this.dex = new SimpleIntegerProperty(dex);
        this.con = new SimpleIntegerProperty(con);
        this.intl = new SimpleIntegerProperty(intl);
        this.wis = new SimpleIntegerProperty(wis);
        this.cha = new SimpleIntegerProperty(cha);
        this.st = new SimpleStringProperty(st);
        this.skills = new SimpleStringProperty(skills);
        this.damVul = new SimpleStringProperty(damVul);
        this.damRes = new SimpleStringProperty(damRes);
        this.damImm = new SimpleStringProperty(damImm);
        this.condImm = new SimpleStringProperty(condImm);
        this.senses = new SimpleStringProperty(senses);
        this.langs = new SimpleStringProperty(langs);
        this.challenge = new SimpleIntegerProperty(challenge);

    }

    public int getId() {return id.get();}
    public void setId(int id) {this.id.set(id);}
    public IntegerProperty idProperty() {return id;}

    public String getLocName() { return locName.get();}
    public void setLocName(String locName) {this.locName.set(locName);}
    public StringProperty locNameProperty() {return locName;}

    public int getSize() {return mSize.get();}
    public void setSize(int mSize) {this.mSize.set(mSize);}
    public IntegerProperty sizeProperty() {return mSize;}

    public String getType() { return mType.get();}
    public void setType(String mType) {this.mType.set(mType);}
    public StringProperty typeProperty() {return mType;}

    public int getAlign() {return align.get();}
    public void setAlign(int align) {this.align.set(align);}
    public IntegerProperty alignProperty() {return align;}

    public String getAC() { return ac.get();}
    public void setAC(String ac) {this.ac.set(ac);}
    public StringProperty acProperty() {return ac;}

    public int getHP() {return hp.get();}
    public void setHP(int hp) {this.hp.set(hp);}
    public IntegerProperty hpProperty() {return hp;}

    public String getHPDice() { return hpDice.get();}
    public void setHPDice(String hpDice) {this.hpDice.set(hpDice);}
    public StringProperty hpDiceProperty() {return hpDice;}

    public String getSpeed() { return speed.get();}
    public void setSpeed(String speed) {this.speed.set(speed);}
    public StringProperty speedProperty() {return speed;}

    //сила
    public int getStr() {return str.get();}
    public void setStr(int str) {this.str.set(str);}
    public IntegerProperty strProperty() {return str;}

    //ловкость
    public int getDex() {return dex.get();}
    public void setDex(int dex) {this.dex.set(dex);}
    public IntegerProperty dexProperty() {return dex;}

    //телосложение
    public int getCon() {return con.get();}
    public void setCon(int con) {this.con.set(con);}
    public IntegerProperty conProperty() {return con;}

    //интеллект
    public int getIntl() {return intl.get();}
    public void setIntl(int intl) {this.intl.set(intl);}
    public IntegerProperty intlProperty() {return intl;}

    //мудрость
    public int getWis() {return wis.get();}
    public void setWis(int wis) {this.wis.set(wis);}
    public IntegerProperty wisProperty() {return wis;}

    //харизма
    public int getCha() {return cha.get();}
    public void setCha(int cha) {this.cha.set(cha);}
    public IntegerProperty chaProperty() {return cha;}

    public String getST() { return st.get();}
    public void setST(String st) {this.st.set(st);}
    public StringProperty stProperty() {return st;}

    public String getSkills() { return skills.get();}
    public void setSkills(String skills) {this.skills.set(skills);}
    public StringProperty skillsProperty() {return skills;}

    public String getDamVul() { return damVul.get();}
    public void setDamVul(String damVul) {this.damVul.set(damVul);}
    public StringProperty damVulProperty() {return damVul;}

    public String getDamRes() { return damRes.get();}
    public void setDamRes(String damRes) {this.damRes.set(damRes);}
    public StringProperty damResroperty() {return damRes;}

    public String getDamImm() { return damImm.get();}
    public void setDamImm(String damImm) {this.damImm.set(damImm);}
    public StringProperty damImmProperty() {return damImm;}

    public String getCondImm() { return condImm.get();}
    public void setCondImm(String condImm) {this.condImm.set(condImm);}
    public StringProperty condImmProperty() {return condImm;}

    public String getSenses() { return senses.get();}
    public void setSenses(String senses) {this.senses.set(senses);}
    public StringProperty sensesProperty() {return senses;}

    public String getLangs() { return langs.get();}
    public void setLangs(String langs) {this.langs.set(langs);}
    public StringProperty langsProperty() {return langs;}

    public int getChallenge() {return challenge.get();}
    public void setChallenge(int challenge) {this.challenge.set(challenge);}
    public IntegerProperty challengeProperty() {return challenge;}




}