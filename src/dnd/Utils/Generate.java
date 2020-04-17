package dnd.Utils;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Generate {
    private int[] sourceabilities = new int[6];     //массив для исходных абилок (первоначально рассчитанных)
    private int[] abilities = new int[6];           //массив для абилок
    private int[] mods = new int[6];                //массив для модификаторов
    private int[] bonchars = new int[6];                    //массив где хранятся итоговые бонусы от расы
    private int[] subraceBonuses = new int[6];              //массив для бонусов подрас

    private String raceName;
    private String subraceName;
    private String alignment;


    //переменные для расы
    private int raceid,minage,maxage,minheight,maxheight,minweight,maxweight,racesize,speed;
    //переменные для подрасы
    private int subraceid,subraceSpeedbon;
    //общие переменные
    private int level;
    private int proficiencyBonus;
    private int maxhp;
    //переменные для классов
    private int classid, hitDice, hitDiceCount;
    private String classname, savingThrows, hitDiceName;



    public void setStr(int str) {
        this.abilities[0] = str;
    }

    public int getStr() {
        return abilities[0];
    }

    public void setDex(int dex) {
        this.abilities[1] = dex;
    }

    public int getDex() {
        return abilities[1];
    }

    public void setCon(int con) {
        this.abilities[2] = con;
    }

    public int getCon() {
        return abilities[2];
    }

    public void setIntl(int intl) {
        this.abilities[3] = intl;
    }

    public int getIntl() {
        return abilities[3];
    }

    public void setWis(int wis) {
        this.abilities[4] = wis;
    }

    public int getWis() {
        return abilities[4];
    }

    public void setCha(int cha) {
        this.abilities[5] = cha;
    }

    public int getCha() {
        return abilities[5];
    }

    public void setStrMod(int strmod) {
        this.mods[0] = strmod;
    }

    public int getStrMod() {
        return mods[0];
    }

    public void setDexMod(int dexmod) {
        this.mods[1] = dexmod;
    }

    public int getDexMod() {
        return mods[1];
    }

    public void setConMod(int conmod) {
        this.mods[2] = conmod;
    }

    public int getConMod() {
        return mods[2];
    }

    public void setIntlMod(int intlmod) {
        this.mods[3] = intlmod;
    }

    public int getIntlMod() {
        return mods[3];
    }

    public void setWisMod(int wismod) {
        this.mods[4] = wismod;
    }

    public int getWisMod() {
        return mods[4];
    }

    public void setChaMod(int chamod) {
        this.mods[5] = chamod;
    }

    public int getChaMod() {
        return mods[5];
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceid(int raceid) {
        this.raceid = raceid;
    }

    public int getRaceid() {
        return raceid;
    }

    public void setStrbon(int strbon) {
        this.bonchars[0] = strbon;
    }

    public int getStrbon() {
        return bonchars[0];
    }

    public void setDexbon(int dexbon) {
        this.bonchars[1] = dexbon;
    }

    public int getDexbon() {
        return bonchars[1];
    }

    public void setConbon(int conbon) {
        this.bonchars[2] = conbon;
    }

    public int getConbon() {
        return bonchars[2];
    }

    public void setIntbon(int intlbon) {
        this.bonchars[3] = intlbon;
    }

    public int getIntbon() {
        return bonchars[3];
    }

    public void setWisbon(int wisbon) {
        this.bonchars[4] = wisbon;
    }

    public int getWisbon() {
        return bonchars[4];
    }

    public void setChabon(int chabon) {
        this.bonchars[5] = chabon;
    }

    public int getChabon() {
        return bonchars[5];
    }

    public void setMinage(int minage) {
        this.minage = minage;
    }

    public int getMinage() {
        return minage;
    }

    public void setMaxage(int maxage) {
        this.maxage = maxage;
    }

    public int getMaxage() {
        return maxage;
    }

    public void setMinheight(int minheight) {
        this.minheight = minheight;
    }

    public int getMinheight() {
        return minheight;
    }

    public void setMaxheight(int maxheight) {
        this.maxheight = maxheight;
    }

    public int getMaxheight() {
        return maxheight;
    }

    public void setMinweight(int minweight) {
        this.minweight = minweight;
    }

    public int getMinweight() {
        return minweight;
    }

    public void setMaxweight(int maxweight) {
        this.maxweight = maxweight;
    }

    public int getMaxweight() {
        return maxweight;
    }

    public void setRacesize(int racesize) {
        this.racesize = racesize;
    }

    public int getRacesize() {
        return racesize;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSubraceid(int subraceid) {
        this.subraceid = subraceid;
    }

    public int getSubraceid() {
        return subraceid;
    }

    public void setSRStrbon(int srstrbon) {
        this.subraceBonuses[0] = srstrbon;
    }

    public int getSRStrbon() {
        return subraceBonuses[0];
    }

    public void setSRDexbon(int srdexbon) {
        this.subraceBonuses[1] = srdexbon;
    }

    public int getSRDexbon() {
        return subraceBonuses[1];
    }

    public void setSRConbon(int srconbon) {
        this.subraceBonuses[2] = srconbon;
    }

    public int getSRConbon() {
        return subraceBonuses[2];
    }

    public void setSRIntbon(int srintlbon) {
        this.subraceBonuses[3] = srintlbon;
    }

    public int getSRIntbon() {
        return subraceBonuses[3];
    }

    public void setSRWisbon(int srwisbon) {
        this.subraceBonuses[4] = srwisbon;
    }

    public int getSRWisbon() {
        return subraceBonuses[4];
    }

    public void setSRChabon(int srchabon) {
        this.subraceBonuses[5] = srchabon;
    }

    public int getSRChabon() {
        return subraceBonuses[5];
    }

    public void setSubraceSpeedbon(int subraceSpeedbon) {
        this.subraceSpeedbon = subraceSpeedbon;
    }

    public int getSubraceSpeedbon() {
        return subraceSpeedbon;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public int getClassid() {
        return classid;
    }

    public void setHitDice(int hitDice) {
        this.hitDice = hitDice;
    }

    public int getHitDice() {
        return hitDice;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassname() {
        return classname;
    }

    public void setSavingThrows(String savingThrows) {
        this.savingThrows = savingThrows;
    }

    public String getSavingThrows() {
        return savingThrows;
    }

    public void setHitDiceCount(int hitDiceCount) {
        this.hitDiceCount = hitDiceCount;
    }

    public int getHitDiceCount() {
        return hitDiceCount;
    }

    public void setHitDiceName(int hd) {
        hd = getHitDice();
        this.hitDiceName = "d" + hd;
    }

    public String getHitDiceName() {
        return hitDiceName;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setProficiencyBonus(int proficiencyBonus) {
        this.proficiencyBonus = proficiencyBonus;
    }

    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    public void setMaxhp(int level,int conMod,int hitDice) {
        /*
         * 1 уровень - Максимум хитов и модификатор телосложения, все последующие - рандом и модификатор телосложения
         */
        int minusLevel = level--;
        int healthPoints = hitDice + conMod;
        Random rnd = new Random();
        for (int i = 0; i < minusLevel; i++) {
            healthPoints += rnd.nextInt(hitDice+1)*i;
        }
        this.maxhp = healthPoints;
        }

    public int getMaxhp() {
        return maxhp;
    }

    public void setSubraceName(String subraceName) {
        this.subraceName = subraceName;
    }

    public String getSubraceName() {
        return subraceName;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getAlignment() {
        return alignment;
    }

    private String armorType;
    private String weaponType;
    private String tools;

    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    public String getArmorType() {
        return armorType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    //TODO: продумать как выбирать случайный инструмент при такой возможности.
    public String getTools() {
        return tools;
    }


    //TODO: доделать генератор персонежей




    /**
     * Обозначаем какие нужны переменные:
     * Массив с характеристиками ++
     * Проф. бонус ++
     * Здоровье ++
     * Количество хитдайсов ++
     * Тип хитдайсов
     *
     *
    * 1. Считаем характеристики  ++
    * 2. Выбираем расу           ++
     * Прибавляем к характеристикам бонусы ++
     * Присваиваем профишенси бонус переменной ++
    * 3. Выбираем класс ++
     * Устанавливаем броню класса ++
     * Устанавливаем оружие класса ++
     * Навыки (на выбор) ++
     * Выбираем снаряжение
     *
     *
    * */

    final String sqlRaces = "SELECT id, racename, strbon, dexbon, conbon,intlbon,wisbon,chabon,minage, \n" +
            "maxage, minheight, maxheight, minweight, maxweight, racesize, speed \n" +
            "FROM cl_race ORDER BY RANDOM() LIMIT 1";

    final String sqlSubraces = "SELECT id,subracename,strbon,dexbon,conbon,intlbon,wisbon,chabon,speed FROM cl_subrace WHERE raceid = ? ORDER BY RANDOM() LIMIT 1";

    final String sqlClasses = "SELECT id, name, hitDice, savingThrows FROM cl_class ORDER BY RANDOM() LIMIT 1";





    //TODO: доделать выбор мировоззрения
    final String sqlAlign = "SELECT alignName FROM cl_alignment WHERE id < 10 ORDER BY RANDOM() LIMIT 1";


    //TODO: посмотреть как чтобы вопросики в запросах ставить. везде.
    final String sqlArmor = "SELECT b.locname as name from class_armor a\n" +
            "LEFT JOIN cl_armortypes b on a.typeid = b.id\n" +
            "LEFT JOIN cl_class c on a.classid = c.id\n" +
            "where a.classid = ?";

    final String sqlWeapon = "SELECT b.locname as name from class_weapon a\n" +
            "LEFT JOIN cl_weapontypes b on a.typeid = b.id\n" +
            "LEFT JOIN cl_class c on a.classid = c.id\n" +
            "where a.classid = ?";




    public Generate() {
        DR dr = new DR();
        Random rnd = new Random();

        //расчет первичных характеристик
        dr.DR();
        for (int i = 0; i < 6; i++) {
            abilities[i] = dr.getChars(i);
            sourceabilities[i] = abilities[i];
        }


        Connect c = new Connect();
        //1. Выбираем случайную расу
        PreparedStatement psRaces = c.getPreparedStatement(sqlRaces);
        try {
            ResultSet rs = psRaces.executeQuery();
            setRaceName(rs.getString("raceName"));
            setRaceid(rs.getInt("id"));
            setStrbon(rs.getInt("strbon"));
            setDexbon(rs.getInt("dexbon"));
            setConbon(rs.getInt("conbon"));
            setIntbon(rs.getInt("intlbon"));
            setWisbon(rs.getInt("wisbon"));
            setChabon(rs.getInt("chabon"));
            setMinage(rs.getInt("minage"));
            setMaxage(rs.getInt("maxage"));
            setMinheight(rs.getInt("minheight"));
            setMaxheight(rs.getInt("maxheight"));
            setMinweight(rs.getInt("minweight"));
            setMaxweight(rs.getInt("maxweight"));
            setRacesize(rs.getInt("racesize"));
            setSpeed(rs.getInt("speed"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c.closePrepareStatement(psRaces);
        }

        //2. Выбираем случайную подрасу с учетом выбранной расы
        PreparedStatement psSubraces = c.getPreparedStatement(sqlSubraces);
        try {
            psSubraces.setInt(1, getRaceid());
            ResultSet rs2 = psSubraces.executeQuery();
            if (!rs2.isBeforeFirst()) {
                setSubraceid(0);
                setSubraceName("");
                setSRStrbon(0);
                setSRDexbon(0);
                setSRConbon(0);
                setSRIntbon(0);
                setSRWisbon(0);
                setSRChabon(0);
                setSubraceSpeedbon(0);
            } else {
                setSubraceid(rs2.getInt("id"));
                setSubraceName(rs2.getString("subracename"));
                setSRStrbon(rs2.getInt("strbon"));
                setSRDexbon(rs2.getInt("dexbon"));
                setSRConbon(rs2.getInt("conbon"));
                setSRIntbon(rs2.getInt("intlbon"));
                setSRWisbon(rs2.getInt("wisbon"));
                setSRChabon(rs2.getInt("chabon"));
                setSubraceSpeedbon(rs2.getInt("speed"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c.closePrepareStatement(psSubraces);
        }

        // 3.Выбираем случайный класс
        PreparedStatement psClass = c.getPreparedStatement(sqlClasses);
        try {
            ResultSet rsClass = psClass.executeQuery();
            setClassid(rsClass.getInt("id"));
            setClassname(rsClass.getString("name"));
            setHitDice(rsClass.getInt("hitDice"));
            setSavingThrows(rsClass.getString("savingThrows"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c.closePrepareStatement(psClass);
        }

        //Выбираем владение броней
        PreparedStatement psArmor = c.getPreparedStatement(sqlArmor);
        try {
            psArmor.setInt(1, getClassid());
            ResultSet rsArmor = psArmor.executeQuery();
            while (rsArmor.next()) {
                setArmorType(getArmorType() + ", " + rsArmor.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //setArmorType("");
        } finally {
            c.closePrepareStatement(psArmor);
        }

        //Выбираем владение оружием
        PreparedStatement psWeapon = c.getPreparedStatement(sqlWeapon);
        try {
            psWeapon.setInt(1, getClassid());
            ResultSet rsWeapon = psWeapon.executeQuery();
            while (rsWeapon.next()) {
                setWeaponType(getWeaponType() + ", " + rsWeapon.getString("name"));
            }
        } catch (SQLException e) {
            setWeaponType("");
        } finally {
            c.closePrepareStatement(psWeapon);
        }

        //Мировоззрение
        PreparedStatement psAlign = c.getPreparedStatement(sqlAlign);
        try {
            ResultSet rsAlign = psAlign.executeQuery();
            setAlignment(rsAlign.getString("alignName"));
        } catch (SQLException e) {
            setAlignment("");
        } finally {
            c.closePrepareStatement(psAlign);
        }

        //Выбираем стартовый эквип

        //Прибавляем бонусы от расы
        int[] boncharstemp = new int[6];  //временный для расчетов
        ArrayList<Integer> bonIndex = new ArrayList<>();
        int boncounter = 0;
        int bonvalue = 0;
        boncharstemp[0] = getStrbon();
        boncharstemp[1] = getDexbon();
        boncharstemp[2] = getConbon();
        boncharstemp[3] = getIntbon();
        boncharstemp[4] = getWisbon();
        boncharstemp[5] = getChabon();

        for (int i = 0; i < boncharstemp.length; i++) {
            if (boncharstemp[i] > 0) {
                bonchars[i] = boncharstemp[i];
                boncharstemp[i] = 0;
            }
            if (boncharstemp[i] < 0){
                boncounter ++;
                bonvalue = Math.abs(boncharstemp[i]);
                bonchars[i] = 0;
            }
        }
        if (bonvalue > 0) {
            for (int i = 0; i < bonvalue; i++) {
                bonIndex.add(rnd.nextInt(boncounter));
            }
            for (int i = 0; i < bonIndex.size(); i++) {
                bonchars[bonIndex.get(i)] = 1;
            }
        }


        /**
         * ИТОГОВОЕ НАПОЛНЕНИЕ ПЕРЕМЕННЫХ ХАРАКТЕРИСТИК
         */
        //Сложение абилок
        for (int i = 0; i < 6; i++) {
            abilities[i] += bonchars[i];
            abilities[i] += subraceBonuses[i];
        }
        //Расчет модификторов
        for (int i = 0; i < 6; i++) {
            mods[i] = setMod(abilities[i]);
        }

        //TODO уровень -- пока что единичка. Потом доработаем
        setLevel(1);
        setHitDiceCount(getLevel());
        setHitDiceName(getHitDice());
        //профбонус
        setProficiencyBonus(setProfBonus(getLevel()));
        //максхп
        setMaxhp(getLevel(),getConMod(),getHitDice());
        setSpeed(getSpeed() + getSubraceSpeedbon());

        //TODO: хп на следующем уровне 1 хит дайс + модификатор телосложения за каждый уровень после первого


        //**************************************************************************************************************
        //TODO: дополнительно повесить слушателей с расчетом расовых бонусов и занесением их в надписи. Плюс сделать метод для расчета расовых бонусов
        //**************************************************************************************************************
        }

    //Маленький метод для расчета модификаторов
    public int setMod(int t) {
        int e = (int) Math.floor((t - 10) / 2);
        return e;
    }

    //Маленький метод для рассчета бонуса мастерства
    public int setProfBonus(int t) {
        int e = 0;
        switch (t) {
            case 1:
            case 2:
            case 3:
            case 4:
                e = 2;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                e = 3;
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                e = 4;
                break;
            case 13:
            case 14:
            case 15:
            case 16:
                e = 5;
                break;
            case 17:
            case 18:
            case 19:
            case 20:
                e = 6;
                break;
        }
        return e;
    }
}

