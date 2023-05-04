package pl.psi.fields;

import pl.psi.fields.abilities.AbilityStrategy;

import java.util.ArrayList;
import java.util.List;

public class SpecialField {

    private FieldStatisticIf stat;
    private int hp;

    //just to see if adding abilities work
    public static List<String> logs = new ArrayList<>();

    SpecialField() {
    }

    private SpecialField(FieldStatisticIf stat, AbilityStrategy... ability) {
        this.stat = stat;
        this.hp = stat.getMaxHp();
    }

    public static void resetLogs() {
        logs.clear();
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

        private FieldStatisticIf stats;
        private AbilityStrategy[] ability;

        public Builder statistics(FieldStatisticIf stats) {
            this.stats = stats;
            return this;
        }

        public Builder abilities(AbilityStrategy... ability) {
            this.ability = ability;
            return this;
        }

        public SpecialField build() {
            //return new SpecialField(stats, abilities);
            return new SpecialField(stats, ability);
        }
    }
}
