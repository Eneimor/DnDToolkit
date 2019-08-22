package dnd.Utils;

import javafx.scene.control.Label;

public class Buttons {
    public void abilityPlus(Label abilityLabel, Label totalLabel) {
        int abilityCost = 0;
        int abilityScore = Integer.parseInt(abilityLabel.getText());
        int totalPoints = Integer.parseInt(totalLabel.getText());
        switch (abilityScore) {
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                abilityCost = 1;
                break;
            case 13:
            case 14:
                abilityCost = 2;
                break;
        }
        if (totalPoints >= abilityCost && abilityScore < 15) {
            abilityScore++;
            totalPoints -= abilityCost;
            abilityLabel.setText(String.valueOf(abilityScore));
            totalLabel.setText(String.valueOf(totalPoints));
        }
    }

    public void abilityMinus(Label abilityLabel, Label totalLabel) {
        int abilityCost = 0;
        int abilityScore = Integer.parseInt(abilityLabel.getText());
        int totalPoints = Integer.parseInt(totalLabel.getText());
        switch (abilityScore) {
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                abilityCost = 1;
                break;
            case 14:
            case 15:
                abilityCost = 2;
                break;
        }
        if (abilityScore > 8) {
            abilityScore--;
            totalPoints += abilityCost;
            abilityLabel.setText(String.valueOf(abilityScore));
            totalLabel.setText(String.valueOf(totalPoints));
        }
    }
}
