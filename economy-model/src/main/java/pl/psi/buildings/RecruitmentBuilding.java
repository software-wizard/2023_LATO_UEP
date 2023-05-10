package pl.psi.buildings;

import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.mapElements.Castle;
import pl.psi.mapElements.Resource;


public class RecruitmentBuilding extends Building{
    public RecruitmentBuilding(String name, Type type, int spot, Resource cost, Castle.FractionType fraction) {
        super(name, type, spot, cost, fraction);
    }

    private int tier;
    private EconomyCreature creaturesToRecruit;

    public RecruitmentBuilding(String name, Type type, int spot, Resource cost, int tier, Castle.FractionType fraction) {
        super(name, type, spot, cost, fraction);
        this.tier = tier;
    }

    public RecruitmentBuilding(String name, Type type, int spot, int tier, Castle.FractionType fraction) {
        super(name, type, spot, fraction);
        this.tier = tier;
        this.creaturesToRecruit = createCreatures(this.getFraction(), this.tier, 20);
    }


    //inna nazwa metody, stworzyć fabryke jako pole zamiast tej metody
    private EconomyCreature createCreatures(Castle.FractionType fraction, int tier, int amount){
        switch(fraction){
            case TOWER:
                break;
            case NECROPOLIS:
                EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
                return factory.create(false, tier, amount);
        }
        return null;
    }

    public String getCreaturesName(){
        return this.creaturesToRecruit.getName();
    }


    public EconomyCreature takeCreaturesFromBuilding(int amount){
        if(amount <= this.creaturesToRecruit.getAmount()){
            EconomyCreature creaturesToArmy = creaturesToRecruit;
            creaturesToArmy.setAmount(amount);
//            this.getCreaturesToRecruit().setAmount(this.getCreaturesToRecruit().getAmount()-amount);
            return creaturesToArmy;
        }
        return null;
    }

    public EconomyCreature getCreaturesToRecruit() {
        return creaturesToRecruit;
    }
}
