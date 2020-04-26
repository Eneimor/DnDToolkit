package dnd.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Monster {
    private final IntegerProperty id;
    private final StringProperty locName;
    private final StringProperty mSize;
    private final StringProperty mType;
    private final StringProperty align;
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
    private final IntegerProperty svStr;
    private final IntegerProperty svDex;
    private final IntegerProperty svCon;
    private final IntegerProperty svInt;
    private final IntegerProperty svWis;
    private final IntegerProperty svCha;
    private final IntegerProperty challenge;
    private final IntegerProperty pasPerception;

    private final StringProperty skills;
    private final StringProperty damVul;
    private final StringProperty damRes;
    private final StringProperty damImm;
    private final StringProperty condImm;
    private final StringProperty senses;
    private final StringProperty langs;






    public Monster() {this(0,null,null,null,null, null, 0, null,
            null, 0, 0, 0, 0, 0, 0, 0,0,0,0,0,
            0,0,0, null, null, null, null, null, null, null);}

    public Monster(int id, String locName, String mSize, String mType, String align, String ac, int hp, String hpDice,
                   String speed, int str, int dex, int con, int intl, int wis, int cha, int svStr, int svDex, int svCon,
                   int svInt, int svWis, int svCha,int challenge, int pasPerceprion, String skills, String damVul,
                   String damRes, String damImm, String condImm, String senses, String langs

                   ) {
        this.id = new SimpleIntegerProperty(id);
        this.locName = new SimpleStringProperty(locName);
        this.mSize = new SimpleStringProperty(mSize);
        this.mType = new SimpleStringProperty(mType);
        this.align = new SimpleStringProperty(align);
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
        this.svStr = new SimpleIntegerProperty(str);
        this.svDex = new SimpleIntegerProperty(dex);
        this.svCon = new SimpleIntegerProperty(con);
        this.svInt = new SimpleIntegerProperty(intl);
        this.svWis = new SimpleIntegerProperty(wis);
        this.svCha = new SimpleIntegerProperty(cha);
        this.challenge = new SimpleIntegerProperty(challenge);
        this.pasPerception = new SimpleIntegerProperty(pasPerceprion);
        this.skills = new SimpleStringProperty(skills);
        this.damVul = new SimpleStringProperty(damVul);
        this.damRes = new SimpleStringProperty(damRes);
        this.damImm = new SimpleStringProperty(damImm);
        this.condImm = new SimpleStringProperty(condImm);
        this.senses = new SimpleStringProperty(senses);
        this.langs = new SimpleStringProperty(langs);


        /*





        */

    }

    public int getId() {return id.get();}
    public void setId(int id) {this.id.set(id);}
    public IntegerProperty idProperty() {return id;}

    public String getLocName() { return locName.get();}
    public void setLocName(String locName) {this.locName.set(locName);}
    public StringProperty locNameProperty() {return locName;}

    public String getSize() {return mSize.get();}
    public void setSize(String mSize) {this.mSize.set(mSize);}
    public StringProperty sizeProperty() {return mSize;}

    public String getType() { return mType.get();}
    public void setType(String mType) {this.mType.set(mType);}
    public StringProperty typeProperty() {return mType;}

    public String getAlign() {return align.get();}
    public void setAlign(String align) {this.align.set(align);}
    public StringProperty alignProperty() {return align;}


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

    //спассброски
    public int getSvStr() {return svStr.get();}
    public void setSvStr(int svStr) {this.svStr.set(svStr);}
    public IntegerProperty svStrProperty() {return svStr;}

    public int getSvDex() {return svDex.get();}
    public void setSvDex(int svDex) {this.svDex.set(svDex);}
    public IntegerProperty svDexProperty() {return svDex;}

    public int getSvCon() {return svCon.get();}
    public void setSvCon(int svCon) {this.svCon.set(svCon);}
    public IntegerProperty svConProperty() {return svCon;}

    public int getSvIntl() {return svInt.get();}
    public void setSvIntl(int svInt) {this.svInt.set(svInt);}
    public IntegerProperty svIntProperty() {return svInt;}

    public int getSvWis() {return svWis.get();}
    public void setSvWis(int svWis) {this.svWis.set(svWis);}
    public IntegerProperty svWisProperty() {return svWis;}

    public int getSvCha() {return svCha.get();}
    public void setSvCha(int svCha) {this.svCha.set(svCha);}
    public IntegerProperty svChaProperty() {return svCha;}

    public int getChallenge() {return challenge.get();}
    public void setChallenge(int challenge) {this.challenge.set(challenge);}
    public IntegerProperty challengeProperty() {return challenge;}

    public int getPassivePerceprion() {return pasPerception.get();}
    public void setPassivePerceprion(int pasPerception) {this.pasPerception.set(pasPerception);}
    public IntegerProperty passivePerceprionProperty() {return pasPerception;}

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
}