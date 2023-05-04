package WarMachines;

public class FirstAidTentMachine {

    public FirstAidTentMachine(){

    }

    // 0 - no skill; 1 - basic gives control heal 1-50 points of damage; 2 - advanced heal 1 - 75; 3 - expert heal 1 - 100
    private int heroFirstAidSkill;
    public int calculateHealPoints(int heroFirstAidSkill, int currentHp){
        switch (heroFirstAidSkill){
            case 0: currentHp += calculateUpperBoundary(25); break;
            case 1: currentHp += calculateUpperBoundary(50); break;
            case 2: currentHp += calculateUpperBoundary(75); break;
            case 3: currentHp += calculateUpperBoundary(100); break;
        }
        return currentHp;
    }
    private int calculateUpperBoundary(int upperBoundary){
        return (int) (Math.random() * upperBoundary) + 1;
    }

    public void chooseCreatureToHeal(){

    }
}
