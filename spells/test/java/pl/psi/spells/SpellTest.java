package pl.psi.spells;

import org.junit.Test;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;

import static org.junit.Assert.assertEquals;
import static pl.psi.spells.Spell.CalculateSpellDamage;

public class SpellTest {
    @Test
     public void CreatureTakesDamage(){
        final Creature creature1 = new Creature.Builder().statistic( CreatureStats.builder()
                        .maxHp(50)
                        .build() )
                .build();
        final Creature creature2 = new Creature.Builder().statistic( CreatureStats.builder()
                        .maxHp(100)
                        .build() )
                .build();
        final Creature creature3 = new Creature.Builder().statistic( CreatureStats.builder()
                        .maxHp(120)
                        .build() )
                .build();

        final Spell spell1 = new Spell.SpellBuilder()
                .Damage(10)
                .Multiplier(10)
                .Duration(0)
                .Cost(5)
                .build();
        final Spell spell2 = new Spell.SpellBuilder()
                .Damage(20)
                .Multiplier(20)
                .build();
        final Spell spell3 = new Spell.SpellBuilder()
                .Damage(30)
                .Multiplier(30)
                .build();

        assertEquals(30, CalculateSpellDamage(spell1, creature1));
        assertEquals(60, CalculateSpellDamage(spell2, creature2));
        assertEquals(60, CalculateSpellDamage(spell3, creature3));

    }
}
