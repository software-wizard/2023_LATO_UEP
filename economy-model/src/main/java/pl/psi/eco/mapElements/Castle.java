// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

//package pl.psi.mapElements;
//
//import com.google.common.collect.HashBiMap;
//import pl.psi.creatures.EconomyCreature;
//import pl.psi.creatures.EconomyNecropolisFactory;
//import pl.psi.hero.Hero;
//
//
//public class Castle implements MapElement {
//    // TODO
//
//    public enum FractionType {
//        TOWER("Workshop","Golden pavilion"),
//        NECROPOLIS("Cursed temple", "Hall of darkness");
//
//        private final String tier1Name;
//        private final String tier6Name;
//
//        FractionType(String tier1Name, String tier6Name) {
//            this.tier1Name = tier1Name;
//            this.tier6Name = tier6Name;
//        }
//
//        public String gettier1Name() {
//            return tier1Name;
//        }
//
//        public String gettier6Name() {
//            return tier6Name;
//        }
//    }
//
//    private final Hero owner = null;
//    private EconomyCreature tier1Creatures, tier2Creatures, tier3Creatures, tier4Creatures, tier5Creatures, tier6Creatures;
//
//    public Castle(Castle.FractionType aType) {
//        this.tier1Creatures = createCreatures(aType, 1, 127);
//        this.tier6Creatures = createCreatures(aType, 6, 10);
//    }
//
//    public int getCreaturesAmount(){
//        return this.tier1Creatures.getAmount();
//    }
//
//    @Override
//    public boolean isInteractive() {
//        return true;
//    }
//
//    @Override
//    public void apply(Hero aHero, HashBiMap map) {
//    }
//
//    public EconomyCreature createCreatures(Castle.FractionType type, int tier, int amount){
//        switch(type){
//            case TOWER:
//                break;
//            case NECROPOLIS:
//                EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
//                return factory.create(false, tier, amount);
//        }
//        return null;
//    }
//
//    public void buyCreatures(Hero aHero, Castle.FractionType type, int tier, int amount){
//
//    }
//
//}

package pl.psi.eco.mapElements;
import pl.psi.eco.buildings.Building;
import pl.psi.eco.hero.EconomyHero;
import pl.psi.eco.player.Player;

import java.util.ArrayList;
import java.util.function.Consumer;


public class Castle implements MapElement {
    // TODO

    private ArrayList<Building> buildingsToBuy = new ArrayList<>();
    private ArrayList<Building> buildingsOwned = new ArrayList<>();
    private Consumer<String> fireMethod;
    public ArrayList<Building> getBuildingsToBuy() {
        return buildingsToBuy;
    }

    public ArrayList<Building> getBuildingsOwned() {
        return buildingsOwned;
    }

    public enum FractionType {
        TOWER,
        RAMPART,
        NECROPOLIS;
    }

    private final EconomyHero owner = null;

    public Castle(FractionType fractionType, Consumer<String> aFireEventMethod) {
        buildingsOwned = TownStarter.createBuildingsOwned(fractionType);
        buildingsToBuy = TownStarter.createBuildingsToBuy(fractionType);
        fireMethod = aFireEventMethod;
    }


    public void buildBuilding(ArrayList<Building> buildingsToBuy, ArrayList<Building> buildingsOwned, int buildingID){
        ArrayList<Building> buildingsToBuyCopy = new ArrayList<>(buildingsToBuy);
        for(Building building: buildingsToBuyCopy){
            if(building.getId()==buildingID){
                buildingsOwned.add(building);
                buildingsToBuy.remove(building);
            }
        }
    }

    @Override
    public String getIcon() {
        return "Castle";
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(EconomyHero aEconomyHero, Player aPlayer) {
        fireMethod.accept("OPEN_CASTLE");
    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return false;
    }

    @Override
    public void endOfTurn() {
    }





}