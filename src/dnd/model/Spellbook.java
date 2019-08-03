package dnd.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Spellbook {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty casttime;
    private final StringProperty distance;
    private final StringProperty components;
    private final StringProperty duration;
    private final StringProperty description;
    private final IntegerProperty lvl;

    //Конструктор по умолчанию
    public Spellbook() {this(0, null, null, null, null, null, null, 0);}

    /*
     * Конструктор с некоторыми начальными данными
     */
    public Spellbook(int id, String name, String casttime, String distance, String components, String duration, String description, int lvl) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.casttime = new SimpleStringProperty(casttime);
        this.distance = new SimpleStringProperty(distance);
        this.components = new SimpleStringProperty(components);
        this.duration = new SimpleStringProperty(duration);
        this.description = new SimpleStringProperty(description);
        this.lvl = new SimpleIntegerProperty(lvl);
    }

    //Геттеры и сеттеры
    public int getId() {return id.get();}
    public void setId(int id) {this.id.set(id);}
    public IntegerProperty idPropperty() {return id;}

    public String getName() {return name.get();}
    public void setName(String name) {this.name.set(name);}
    public StringProperty namePropperty() {return name;}

    public String getCasttime() {return casttime.get();}
    public void setCasttime(String casttime) {this.casttime.set(casttime);}
    public StringProperty casttimePropperty() {return casttime;}

    public String getDistance() {return distance.get();}
    public void setDistance(String distance) {this.distance.set(distance);}
    public StringProperty distancePropperty() {return distance;}

    public String getComponents() {return components.get();}
    public void setComponents(String components) {this.components.set(components);}
    public StringProperty componentsPropperty() {return components;}

    public String getDuration() {return duration.get();}
    public void setDuration(String duration) {this.duration.set(duration);}
    public StringProperty durationPropperty() {return duration;}

    public String getDescription() {return description.get();}
    public void setDescription(String description) {this.description.set(description);}
    public StringProperty descriptionPropperty() {return description;}

    public int getLvl() {return lvl.get();}
    public void setLvl(int lvl) {this.lvl.set(lvl);}
    public IntegerProperty lvlPropperty() {return lvl;}
}
