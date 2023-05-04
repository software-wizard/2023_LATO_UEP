package pl.psi.fields.abilities;


import pl.psi.creatures.Creature;
import pl.psi.fields.SpecialField;

import java.util.List;

public class AbilityStrategy implements AbilityActionIf {

    public AbilityStrategy(int amount, Creature creature) {
        changeCreatureHealth(amount, creature);
    }

    public AbilityStrategy(int amount, List<Creature> creaturesList) {
        changeMoreCreaturesHealth(amount, creaturesList);
    }

    public AbilityStrategy(SpecialField specialField) {
        makeFieldImmortal(specialField);
    }

    public AbilityStrategy(Creature creature, Object spell) {
        applyMagicOnCreature(creature, spell);
    }

    public AbilityStrategy(List<Creature> creaturesList, Object spell) {
        applyMagicOnMoreCreatures(creaturesList, spell);
    }

    @Override
    public void applyMagicOnCreature(Creature creature, Object spell) {
        MagicAbility magicAbility = new MagicAbility(spell);
        magicAbility.apply();
    }

    @Override
    public void applyMagicOnMoreCreatures(List<Creature> creatures, Object spell) {
        MagicAbility magicAbility = new MagicAbility(spell);
        for (Creature creature: creatures){
            magicAbility.apply();
        }
    }

    @Override
    public void changeCreatureHealth(int amount, Creature creature) {
        CreatureHealthChanger changer = new CreatureHealthChanger(creature, amount);
        changer.apply();
    }

    @Override
    public void changeMoreCreaturesHealth(int amount, List<Creature> creatures) {
        for (Creature creature: creatures) {
            CreatureHealthChanger changer = new CreatureHealthChanger(creature, amount);
            changer.apply();
        }
    }

    @Override
    public void makeFieldImmortal(SpecialField specialField) {

    }
}
