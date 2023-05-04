package pl.psi.fields.abilities;

import pl.psi.creatures.Creature;
import pl.psi.fields.SpecialField;

import java.util.List;

public interface AbilityActionIf {
    void applyMagicOnCreature(Creature creature, Object spell);
    void applyMagicOnMoreCreatures(List<Creature> creatures, Object spell);
    void changeCreatureHealth(int amount, Creature creature);
    void changeMoreCreaturesHealth(int amount, List<Creature> creatures);
    void makeFieldImmortal(SpecialField specialField);
}
