package pl.psi.hero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.psi.buildings.Building;
import pl.psi.buildings.RecruitmentBuilding;
import pl.psi.creatures.EconomyCreature;
import pl.psi.mapElements.Castle;
import pl.psi.mapElements.MapElement;
import pl.psi.player.PlayerResources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class EconomyHero implements MapElement {

    // TODO equipment HashMap for artifacts
    // Metoda apply for EconomyArtifact

    private HeroStatistics heroStatistics;

    public static Castle.FractionType Fraction;
    private ArrayList<EconomyCreature> heroArmy;
    private HashMap<String, Object> heroEquipment;
    private ArrayList<MapElement> heroBackpack;

    public EconomyHero(HeroStatistics aHeroStatistics) {
        this.heroStatistics = aHeroStatistics;
        this.heroBackpack = new ArrayList<>();
        this.heroEquipment = new HashMap<>();
        //w innej metodzie wrzucac dopiero type = name
        this.heroEquipment.put("helmet", null);
        this.heroEquipment.put("cape", null);
        this.heroEquipment.put("necklace", null);
        this.heroEquipment.put("rightHand", null);
        this.heroEquipment.put("leftHand", null);
        this.heroEquipment.put("torso", null);
        this.heroEquipment.put("ring", null);
        this.heroEquipment.put("feet", null);
        this.heroEquipment.put("miscellaneous", null);
        this.heroEquipment.put("ballista", null);
        this.heroEquipment.put("ammoCart", null);
        this.heroEquipment.put("firstAidTent", null);
        this.heroEquipment.put("catapult", null);
        this.heroEquipment.put("spellBook", null);
    }
    public EconomyHero(HeroStatistics aHeroStatistics, ArrayList<EconomyCreature> aHeroArmy) {
        this.heroStatistics = aHeroStatistics;
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

    public void addArtifactToBackpack(MapElement aArtifact) {
        this.heroBackpack.add(aArtifact);
    }

    public ArrayList<EconomyCreature> addCreature(EconomyCreature economyCreature) {
        heroArmy.add( economyCreature);
        return heroArmy;
    }

    public ArrayList<EconomyCreature> getCreatures() {
        return heroArmy;
    }
}


