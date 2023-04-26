package WarMachines;

import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;
//import pl.psi.TurnQueue;

import java.beans.PropertyChangeEvent;
import java.util.Random;

@Getter
public class WarMachine {
    private WarMachineStatisticIf stats;
    @Setter
    private int amount;
    private int currentHp;

    WarMachine(){

    }

    WarMachine(final WarMachineStatisticIf aStats,
              // final DamageCalculatorIf aCalculator,
               final int aAmount) {
        stats = aStats;
        amount = aAmount;
        currentHp = stats.getMaxHp();
        //calculator = aCalculator;

    }

//    public void attack(final WarMachine aDefender) {
//        if (isAlive()) {
//            final int damage = getCalculator().calculateDamage(this, aDefender);
//            applyDamage(aDefender, damage);
////            if (canCounterAttack(aDefender)) {
////                counterAttack(aDefender);
////            }
//        }
//    }

    public boolean isAlive() {
        return getAmount() > 0;
    }

    private void applyDamage(final WarMachine aDefender, final int aDamage) {
        int hpToSubstract = aDamage % aDefender.getMaxHp();
        int amountToSubstract = Math.round(aDamage / aDefender.getMaxHp());

        int hp = aDefender.getCurrentHp() - hpToSubstract;
        if (hp <= 0) {
            aDefender.setCurrentHp(aDefender.getMaxHp() - hp);
            aDefender.setAmount(aDefender.getAmount() - 1);
        }
        else{
            aDefender.setCurrentHp(hp);
        }
        aDefender.setAmount(aDefender.getAmount() - amountToSubstract);
    }

    public int getMaxHp() {
        return stats.getMaxHp();
    }

    protected void setCurrentHp(final int aCurrentHp) {
        currentHp = aCurrentHp;
    }

    //TODO chyba zadna maszyna nie może kontratakować - do wywalenia
//    private boolean canCounterAttack(final Creature aDefender) {
//        return aDefender.getCounterAttackCounter() > 0 && aDefender.getCurrentHp() > 0;
//    }

//    private void counterAttack(final Creature aAttacker) {
//        final int damage = aAttacker.getCalculator()
//                .calculateDamage(aAttacker, this);
//        applyDamage(this, damage);
//        aAttacker.counterAttackCounter--;
//    }

    Range<Integer> getDamage() {
        return stats.getDamage();
    }

    int getAttack() {
        return stats.getAttack();
    }

    int getArmor() {
        return stats.getArmor();
    }

//    @Override
//    public void propertyChange(final PropertyChangeEvent evt) {
//        if (TurnQueue.END_OF_TURN.equals(evt.getPropertyName())) {
//            counterAttackCounter = 1;
//        }
//    }

    protected void restoreCurrentHpToMax() {
        currentHp = stats.getMaxHp();
    }

    public String getName() {
        return stats.getName();
    }

    public int getShotRange() {
        return stats.getShotRange();
    }

    public static class Builder {
        private int amount = 1;
        //private DamageCalculatorIf calculator = new DefaultDamageCalculator(new Random());
        private WarMachineStatisticIf statistic;

        public Builder statistic(final WarMachineStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }

//        Builder calculator(final DamageCalculatorIf aCalc) {
//            calculator = aCalc;
//            return this;
//        }

        public WarMachine build() { return new WarMachine(statistic,
                //calculator,
                amount); }
    }

    @Override
    public String toString() {
        return getName() + System.lineSeparator() + getAmount();
    }
}
