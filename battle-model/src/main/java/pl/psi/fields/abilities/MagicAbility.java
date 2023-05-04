package pl.psi.fields.abilities;

import pl.psi.creatures.Creature;
import pl.psi.fields.SpecialField;

public class MagicAbility implements IAbility{

    private Object spell;
    private Creature creature;

    public MagicAbility(Object spell) {
        this.spell = spell;
    }

    @Override
    public void apply() {
        SpecialField.logs.add("added magic!");
        //cast spell on a creature
    }
}
