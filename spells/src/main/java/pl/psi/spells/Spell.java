package pl.psi.spells;

import lombok.Getter;
import pl.psi.creatures.Creature;

@Getter
public class Spell {
    private String name;
    private String type;
    private int cost;
    private int duration;
    private int multiplier;
    private int damage;

    private Spell(SpellBuilder builder){
        this.name = builder.name;
        this.type = builder.type;
        this.cost = builder.cost;
        this.duration = builder.duration;
        this.multiplier = builder.multiplier;
        this.damage = builder.damage;
    }

    protected static int CalculateSpellDamage(Spell spell, Creature creature){
        final int spellDamage = ((1 * spell.multiplier) + spell.damage);

        return creature.getCurrentHp() - spellDamage;
    }

    public static class SpellBuilder{
        private String name;
        private String type;
        private int cost;
        private int duration;
        private int multiplier;
        private int damage;

        public SpellBuilder(){
        }

        public SpellBuilder Name(String name){
            this.name = name;
            return this;
        }

        public SpellBuilder Multiplier(int amount){
            this.multiplier = amount;
            return this;
        }

        public SpellBuilder Damage(int amount){
            this.damage = amount;
            return this;
        }
        public SpellBuilder Duration(int amount){
            this.duration = amount;
            return this;
        }

        public SpellBuilder Cost(int amount){
            this.cost = amount;
            return this;
        }

        public SpellBuilder Type(String type){
            this.type = type;
            return this;
        }

        public Spell build(){
            return new Spell(this);
        }
    }
}