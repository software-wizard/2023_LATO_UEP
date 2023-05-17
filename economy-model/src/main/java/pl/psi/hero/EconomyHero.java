package pl.psi.hero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.psi.buildings.Building;
import pl.psi.buildings.RecruitmentBuilding;
import pl.psi.creatures.EconomyCreature;
import pl.psi.mapElements.Castle;
import pl.psi.mapElements.Artifact;
import pl.psi.mapElements.MapElement;
import pl.psi.player.PlayerResources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@AllArgsConstructor
@Builder
@Getter
public class EconomyHero implements MapElement {


    // Metoda apply for EconomyArtifact

    private HeroStatistics heroStatistics;

    public static Castle.FractionType Fraction;
    private HeroEquipment heroEquipment;
    private ArrayList<EconomyCreature> heroArmy;


    public EconomyHero(HeroStatistics aHeroStatistics,ArrayList<EconomyCreature> aHeroArmy,HeroEquipment aHeroEquipment) {
        this.heroStatistics = aHeroStatistics;
        this.heroEquipment = aHeroEquipment;
        this.heroArmy = aHeroArmy;
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

    public void addCreaturesToArmy(RecruitmentBuilding building, int amount, PlayerResources resources){
        int creaturesCost = building.getCreaturesToRecruit().getAmount()*building.getCreaturesToRecruit().getGoldCost();

        if(creaturesCost<resources.getGold()){
            EconomyCreature armyCreature = building.takeCreaturesFromBuilding(amount);
            heroArmy.add(armyCreature);
            resources.setGold(resources.getGold()-creaturesCost);
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
        heroArmy.add( economyCreature);
        return heroArmy;
    }

    public ArrayList<EconomyCreature> getCreatures() {
        return heroArmy;
    }
}


