package pl.psi.creatures;


import java.util.Random;

public class ArmourerSkill extends Skill{

    protected ArmourerSkill(Random aRand) {
        super(aRand);
    }

    private double calculateInfluence (int level) {
       if (level == 0){
           return 1;
       } else if (level == 1) {
           return 0.95;
       } else if (level == 2) {
           return 0.90;
       } else if (level == 3){
           return 0.85;
       } else {
           return 1;
       }
    }

    @Override
    public int calculateDamage(Creature aAttacker, Creature aDefender) {
        return calculateOutcome(super.calculateDamage(aAttacker, aDefender));
    }

    //basic offence
    @Override
    public int calculateOutcome(int i) {
        return (int)(i * calculateInfluence(level));
    }

   public void apply (Creature attacker){
        attacker.setLambda(calculateInfluence(level));
   }

}
