// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.buildings;

import pl.psi.eco.creatures.EconomyCreature;
import pl.psi.eco.creatures.EconomyFactory;
import pl.psi.eco.mapElements.Castle;
import pl.psi.eco.mapElements.Resource;


public class RecruitmentBuilding extends Building{
    public RecruitmentBuilding(String name, Type type, int id, Resource cost, Castle.FractionType fraction) {
        super(name, type, id, cost, fraction);
    }

    private int tier;
    private EconomyCreature creaturesToRecruit;
    EconomyFactory factory = new EconomyFactory();

    public RecruitmentBuilding(String name, Type type, int id, Resource cost, int tier, Castle.FractionType fraction) {
        super(name, type, id, cost, fraction);
        this.tier = tier;
    }

    public RecruitmentBuilding(String name, Type type, int id, int tier, Castle.FractionType fraction) {
        super(name, type, id, fraction);
        this.tier = tier;
        this.creaturesToRecruit = factory.create(this.getFraction(), false, tier, 20);
    }


//    private EconomyCreature createCreatures(Castle.FractionType fraction, int tier, int amount){
//        switch(fraction){
//            case TOWER:
//                break;
//            case NECROPOLIS:
//                EconomyFactory factory = new EconomyFactory();
//                return factory.create(this.getFraction(), false, tier, amount);
//        }
//        return null;
//    }

    public String getCreaturesName(){
        return this.creaturesToRecruit.getName();
    }


    public EconomyCreature takeCreaturesFromBuilding(int amount){
        if(amount <= this.creaturesToRecruit.getAmount()){
            creaturesToRecruit.setAmount(this.creaturesToRecruit.getAmount() - amount);
            return factory.create(this.getFraction(), false, tier, amount);
        }
        return null;
    }

    public EconomyCreature getCreaturesToRecruit() {
        return creaturesToRecruit;
    }
}