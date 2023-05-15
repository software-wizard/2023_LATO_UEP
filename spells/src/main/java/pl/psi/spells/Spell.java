package pl.psi.spells;

import lombok.Getter;
//dodac metode cast przyjmujaca punkt, wziac kreature z tego punktu
@Getter
public class Spell {
    private final String name;
    private final String type;
    private final int manaCost;
    private final int duration;
    private final int damage;

    private Spell(SpellBuilder builder){
        this.name = builder.name;
        this.type = builder.type;
        this.manaCost = builder.cost;
        this.duration = builder.duration;
        this.damage = builder.damage;
    }

    public static class SpellBuilder{
        private String name;
        private String type;
        private int cost;
        private int duration;
        private int damage;

        public SpellBuilder(){
        }

        public SpellBuilder name(String aName){
            this.name = aName;
            return this;
        }

        public SpellBuilder damage(int aMultiplier, int aAddition){
            final int spellDamage = ((1 * aMultiplier) + aAddition);
            this.damage = spellDamage;
            return this;
        }
        public SpellBuilder duration(int aAmount){
            this.duration = aAmount;
            return this;
        }

        public SpellBuilder cost(int aAmount){
            this.cost = aAmount;
            return this;
        }

        public SpellBuilder type(String aType){
            this.type = aType;
            return this;
        }

        public Spell build(){
            return new Spell(this);
        }
    }
}