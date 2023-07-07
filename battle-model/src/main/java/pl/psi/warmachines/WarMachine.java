package pl.psi.warmachines;

import pl.psi.WarMachines.WarMachineStatisticIf;
import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;
import pl.psi.MapObjectIf;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

@Getter
public class WarMachine implements PropertyChangeListener, MapObjectIf {
    private WarMachineDamageCalculatorIF calculator;
    private FirstAidTentIf HPcalculator;
    private WarMachineStatisticIf stats;
    @Setter
    private int amount;
    private int currentHp;
    private final int counterAttackCounter = 0;

    WarMachine() {
    }

    WarMachine(final WarMachineStatisticIf aStats,
               final WarMachineDamageCalculatorIF aCalculator,
               final FirstAidTentIf aHPcalculator,
               final int aAmount) {
        stats = aStats;
        amount = aAmount;
        currentHp = stats.getMaxHp();
        calculator = aCalculator;
        HPcalculator = aHPcalculator;
    }


    // TODO zrobić fabryke maszyn, i wrócic żeby ten namiot zrobić
    // fabryka będzie miec parametr enum i drugi parametr z poziomem skila
//    public boolean canHeal() {
//        boolean canHeal = stats.getName().equals("First Aid Tent");
//        return canHeal;
//    }
//    public boolean canAttack() {
//        return !stats.getName().equals("First Aid Tent") && !stats.getName().equals("Ammo Cart");
//    }

    public boolean isAlive() {
        return getAmount() > 0;
    }

    protected void applyDamage(final MapObjectIf aDefender, final int aDamage) {
        int hpToSubstract = aDamage % aDefender.getMaxHp();
        int amountToSubstract = Math.round(aDamage / aDefender.getMaxHp());

        int hp = aDefender.getCurrentHp() - hpToSubstract;
        if (hp <= 0) {
            aDefender.setCurrentHp(aDefender.getMaxHp() + hp); // - (-) sth gives us +, so hp will be higher than max
            aDefender.setAmount(aDefender.getAmount() - 1);
        } else {
            aDefender.setCurrentHp(hp);
        }
        aDefender.setAmount(aDefender.getAmount() - amountToSubstract);

//        todo sth is wrong with dealing damage, amount subtraction isn't working properly, smt currentHp > maxHp

//        int hpToSubtract = aDamage % aDefender.getMaxHp();
////        int amountToSubtract = Math.round((float) aDamage / aDefender.getMaxHp());
//
//        int hp = aDefender.getCurrentHp() - hpToSubtract;
//        //            System.out.println("HP: " + hp);
//        //            System.out.println("War Machine is dead");
//        //            aDefender.setAmount(aDefender.getAmount() - 1);
//        aDefender.setCurrentHp(Math.max(hp, 0));
////        aDefender.setAmount(aDefender.getAmount() - amountToSubtract);
    }

    public int getMaxHp() {
        return stats.getMaxHp();
    }

    public void setCurrentHp(final int aCurrentHp) {
        currentHp = aCurrentHp;
    }

    @Override
    public int getMoveRange() {
        return 0;
    }

    @Override
    public boolean canHeal() {
        return false;
    }

    @Override
    public boolean canAttack() {
        return false;
    }

    @Override
    public boolean isControllable() {
        return false;
    }


    Range<Integer> getDamage() {
        return stats.getDamage();
    }

    int getAttack() {
        return stats.getAttack();
    }

    public int getArmor() {
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

    public int getHexSize() {return stats.getHexSize();}

    public int getShotRange() {
        return stats.getShotRange();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //todo shouldn't be empty
    }

    public boolean checkIfAlive(MapObjectIf defender) {
        return defender.getAmount() > 0;
        //return defender.getCurrentHp() > 0;
    }

    public static class Builder {
        private int amount = 1;
        private WarMachineDamageCalculatorIF calculator = new WarMachineDamageCalculator();
        private FirstAidTentIf HPcalculator = new FirstAidTentHealPointsCalculator();
        private WarMachineStatisticIf statistic;

        public Builder statistic(final WarMachineStatisticIf aStatistic) {
            statistic = aStatistic;
            return this;
        }

        public Builder amount(final int aAmount) {
            amount = aAmount;
            return this;
        }

        Builder calculator(final WarMachineDamageCalculatorIF aCalc) {
            calculator = aCalc;
            return this;
        }

        public WarMachine build() {
            return new WarMachine(statistic,
                    calculator,
                    HPcalculator,
                    amount);
        }
    }

    @Override
    public String toString() {
        return getName() + System.lineSeparator() + getCurrentHp();
    }

    public HashMap<String, Integer> getSkill() {
        return null;
    }
}
