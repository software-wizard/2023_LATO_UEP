package pl.psi.spells;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SpellMenu {

    public enum Name{

        HASTE("Haste"),
        VIEW_AIR("View Air"),
        MAGIC_ARROW("Magic Arrow");

        @Getter
        private final String name;
        Name(String aName) { name  = aName; }
    }

    public Spell chooseSpell(Name name) {
        switch (name) {
            case HASTE:
                return new Spell.SpellBuilder().name("Haste").type("Combat").cost(6).duration(1).damage(0,0).build();
            case VIEW_AIR:
                return new Spell.SpellBuilder().name("View Air").type("Adventure").cost(2).duration(0).damage(0,0).build();
            case MAGIC_ARROW:
                return new Spell.SpellBuilder().name("Magic Arrow").type("Combat").cost(5).duration(0).damage(10,10).build();
            default:
                throw new IllegalArgumentException("Can't choose this spell.");
        }
    }








}
