package dnd.Utils;

public class lvlcntr {
    private int cntr = 1;
    private static int xp = 0;

    //Геттеры и сеттеры
    public int getCntr() {return cntr;}
    public int getXp() {return xp;}

    public void setCntr(int c) {cntr = c;}
    public void setXp(int x) {xp = x;}

    //метод для расчета уровня с учетом опыта. ДОРАБОТАТЬ, слишком много ифов
    public lvlcntr() {
        if ( xp >= 300) cntr++;
        if ( xp >= 900) cntr++;
        if ( xp >= 2700) cntr++;
        if ( xp >= 6500) cntr++;
        if ( xp >= 14000) cntr++;
        if ( xp >= 23000) cntr++;
        if ( xp >= 34000) cntr++;
        if ( xp >= 48000) cntr++;
        if ( xp >= 64000) cntr++;
        if ( xp >= 85000) cntr++;
        if ( xp >= 100000) cntr++;
        if ( xp >= 120000) cntr++;
        if ( xp >= 140000) cntr++;
        if ( xp >= 165000) cntr++;
        if ( xp >= 195000) cntr++;
        if ( xp >= 225000) cntr++;
        if ( xp >= 265000) cntr++;
        if ( xp >= 305000) cntr++;
        if ( xp >= 355000) cntr = 20;
    }
}