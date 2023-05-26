package pl.psi.warmachines;

import pl.psi.WarMachines.WarMachineStatisticIf;
import com.google.common.collect.Range;
import lombok.Getter;
import lombok.Setter;
import pl.psi.AttackerIF;
import pl.psi.HealerIF;
import pl.psi.Hero;
import pl.psi.MapObjectIf;
//import pl.psi.TurnQueue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Getter
public class WarMachine implements PropertyChangeListener, MapObjectIf, AttackerIF, HealerIF {
    private WarMachineDamageCalculatorIF calculator;
    private FirstAidTentIf HPcalculator;
    private WarMachineStatisticIf stats;
    @Setter
    private int amount;
    private int currentHp;
    @Setter
    @Getter
    private Hero hero;

    WarMachine(){

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

//    WarMachine(Hero aHero){
//        this.hero = aHero;
//    }
    public void attack(final MapObjectIf aDefender) throws Exception {
        if (isAlive()) {
            final int damage = getCalculator().calculateDamage(this, aDefender);
            applyDamage(aDefender, damage);
        }
    }

    public void heal(MapObjectIf ally) throws Exception {
        if (isAlive()) {
            final int hp = getHPcalculator().calculateHealPoint(this, ally, ally.getCurrentHp());
            ally.setCurrentHp(hp);
            if ((ally.getCurrentHp() > ally.getMaxHp())){
                ally.setCurrentHp(ally.getMaxHp());
            }
        }
    }

    public boolean isAlive() {
        return getAmount() > 0;
    }

    private void applyDamage(final MapObjectIf aDefender, final int aDamage) {
        int hpToSubtract = aDamage % aDefender.getMaxHp();
//        int amountToSubtract = Math.round((float) aDamage / aDefender.getMaxHp());

        int hp = aDefender.getCurrentHp() - hpToSubtract;
        //            System.out.println("HP: " + hp);
        //            System.out.println("War Machine is dead");
        //            aDefender.setAmount(aDefender.getAmount() - 1);
        aDefender.setCurrentHp(Math.max(hp, 0));
//        aDefender.setAmount(aDefender.getAmount() - amountToSubtract);
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
    public boolean canAttackFromDistance() {
        return true;
    }

    public boolean canAttack() {
        return stats.canAttack();
    }

    @Override
    public boolean canHeal() {
        return stats.canHeal();
    }

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

    public int getHexSize() {return stats.getHexSize();}

    public int getShotRange() {
        return stats.getShotRange();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //todo shouldn't be empty
    }

    public boolean checkIfAlive(MapObjectIf defender) {
        return defender.getCurrentHp() > 0;
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

        public WarMachine build() { return new WarMachine(statistic,
                calculator,
                HPcalculator,
                amount); }
    }

    @Override
    public String toString() {
        return getName() + System.lineSeparator() + getCurrentHp();
    }
}
