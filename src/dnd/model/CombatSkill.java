package dnd.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CombatSkill {
        private final StringProperty name;
        private final StringProperty description;
        private final IntegerProperty level;

    public CombatSkill() {
        this(null,null,0);
    }

    public CombatSkill(String name, String description, int level) {
        this.level = new SimpleIntegerProperty(level);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
    }

    public int getLevel() {return level.get();}
    public void setLevel(int level) {this.level.set(level);}
    public IntegerProperty levelProperty() {return level;}

    public String getName() { return name.get();}
    public void setName(String name) {this.name.set(name);}
    public StringProperty nameProperty() {return name;}

    public String getDescription() { return description.get();}
    public void setDescription(String description) {this.description.set(description);}
    public StringProperty descriptionProperty() {return description;}
}
