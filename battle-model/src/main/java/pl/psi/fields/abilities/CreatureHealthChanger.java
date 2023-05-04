package pl.psi.fields.abilities;

import pl.psi.creatures.Creature;
import pl.psi.fields.SpecialField;

public class CreatureHealthChanger implements IAbility{

    private Creature creature;
    private int healthChange;

    public CreatureHealthChanger(Creature creature, int healthChange) {
        this.creature = creature;
        this.healthChange = healthChange;
    }

    @Override
    public void apply() {
        //change creature's health
        if (healthChange > 0){
            SpecialField.logs.add("dealt damage!");
        }
        else {
            SpecialField.logs.add("creature healed!");
        }
    }
}

