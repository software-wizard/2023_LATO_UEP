package pl.psi.spells;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpellTest {
    @Test
     public void calculateSpellDamage(){
        final Spell spell1 = new Spell.SpellBuilder()
                .damage(10, 20)
                .duration(0)
                .cost(5)
                .build();
        final Spell spell2 = new Spell.SpellBuilder()
                .damage(20, 20)
                .build();
        final Spell spell3 = new Spell.SpellBuilder()
                .damage(30, 20)
                .build();

        assertEquals(30, spell1.getDamage());
        assertEquals(40, spell2.getDamage());
        assertEquals(50, spell3.getDamage());
    }
}
