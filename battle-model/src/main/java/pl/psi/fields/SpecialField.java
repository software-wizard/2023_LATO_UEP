package pl.psi.fields;

import pl.psi.creatures.CreatureStatisticIf;

public class SpecialField {

    private CreatureStatisticIf stat;
    private AbilitiesDecorator abilities;
    private int hp;

    SpecialField() {
    }

    private SpecialField(CreatureStatisticIf stat, AbilitiesDecorator abilities) {
        this.stat = stat;
        this.abilities = abilities;
        this.hp = stat.getMaxHp();
    }

    public void setHp(int hp) {
        this.hp = hp;
        if (!isAlive()) {
            removeField();
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    private void removeField() {
        //remove field from board
    }

    public String getName() {
        return stat.getName();
    }

    public static class Builder {

        private CreatureStatisticIf stats;
        private AbilitiesDecorator abilities;

        public Builder statistics(CreatureStatisticIf stats) {
            this.stats = stats;
            return this;
        }

        public Builder abilities(AbilitiesDecorator abilities) {
            this.abilities = abilities;
            return this;
        }

        public SpecialField build() {
            //return new SpecialField(stats, abilities);
            return new SpecialField(stats, new AbilitiesDecorator());
        }
    }
}
