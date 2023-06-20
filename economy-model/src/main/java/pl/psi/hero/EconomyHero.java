package pl.psi.hero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.psi.artifacts.Artifact;
import pl.psi.buildings.Building;
import pl.psi.buildings.RecruitmentBuilding;
import pl.psi.creatures.EconomyCreature;
import pl.psi.mapElements.Castle;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.Resource;
import pl.psi.player.PlayerResources;

import java.util.ArrayList;


@Builder
@Getter
@AllArgsConstructor
public class EconomyHero implements MapElement {


    // Metoda apply for EconomyArtifact

    private HeroStatistics heroStatistics;

    public Castle.FractionType fraction; // @Q - why static?!
    private HeroEquipment heroEquipment;
    private ArrayList<EconomyCreature> heroArmy;

    public EconomyHero(HeroStatistics aHeroStatistics, Castle.FractionType aFraction, ArrayList<EconomyCreature> aHeroArmy) {
        heroStatistics = aHeroStatistics;
        fraction = aFraction;
        heroArmy = aHeroArmy;
        heroEquipment = new HeroEquipment();
    }

    public EconomyHero(HeroStatistics aStats, ArrayList<EconomyCreature> aCreatures, HeroEquipment aEq) {
        this(aStats, Castle.FractionType.NECROPOLIS, aEq, aCreatures);
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(EconomyHero aEconomyHero) {
        // TODO exchange army and so on?
        // TODO battle if enemy hero
    }

    public void addCreaturesToArmy(RecruitmentBuilding building, int amount, PlayerResources resources) {
        int creaturesCost = amount * building.getCreaturesToRecruit().getGoldCost();

        if (creaturesCost < resources.getGold()) {
            EconomyCreature armyCreature = building.takeCreaturesFromBuilding(amount);
            boolean creatureExists = false;

            for (EconomyCreature creature : heroArmy) {
                if (creature.getName().equals(armyCreature.getName())) {
                    creature.setAmount(creature.getAmount() + amount);
                    creatureExists = true;
                    break;
                }
            }

            if (!creatureExists) {
                heroArmy.add(armyCreature);
            }

            resources.setGold(resources.getGold() - creaturesCost);
        }
    }
//w 82 odjąć od siebie resourcy po zrobieniu adnotacji w PlayerResources wywołująć metodę tej klasy
    public void buyBuilding(int buildingId, PlayerResources resources, Castle currentCastle){
        int buildingCost = 0;
        for(Building building: currentCastle.getBuildingsToBuy()){
            if(building.getId()==buildingId){
                buildingCost = building.getCost().getResourceAmount();
            }
            }
        if(buildingCost < resources.getGold()){
            currentCastle.buildBuilding(currentCastle.getBuildingsToBuy(), currentCastle.getBuildingsOwned(), buildingId);
            resources.setGold(resources.getGold() - buildingCost);
        }
    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return false;
    }

    @Override
    public void endOfTurn() {

    }

    public void addArtifactToBackpack(Artifact aArtifact) {
        //open gui
        this.heroEquipment.addItemToBackpack(aArtifact);
    }

    public ArrayList<EconomyCreature> addCreature(EconomyCreature economyCreature) {
        heroArmy.add(economyCreature);
        return heroArmy;
    }

    public ArrayList<EconomyCreature> getCreatures() {
        return heroArmy;
    }

    //TODO zrobić potem pobieranie skilli
    public Iterable<Object> getSkills() {

        return null;
    }
}


