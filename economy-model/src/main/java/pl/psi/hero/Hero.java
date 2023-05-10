package pl.psi.hero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.psi.Buildings.RecruitmentBuilding;
import pl.psi.creatures.EconomyCreature;
import pl.psi.mapElements.MapElement;
import pl.psi.player.PlayerResources;

import java.util.ArrayList;
import java.util.HashMap;
@AllArgsConstructor
@Builder
@Getter
public class Hero implements MapElement {


    // Metoda apply for EconomyArtifact

    private HeroStatistics heroStatistics;
    private HeroEquipment heroEquipment;
    private ArrayList<EconomyCreature> heroArmy;
    //klasa dla eq backpack/ osobno pod i otwiera eq z herosem i zmienia eq
    //metody do wkładania i sciagania z eq tetsty w klasie / jeśli juz jest załozone to exception czy sciagac tamto
    //gui osobno w gui i laczy sie z herosem

    //ecoLauncherScene Controller - switchToMap otweira nowa scene


    public Hero(HeroStatistics aHeroStatistics, HeroEquipment aHeroEquipment) {
        this.heroStatistics = aHeroStatistics;
        this.heroEquipment = aHeroEquipment;
    }
    public Hero(HeroStatistics aHeroStatistics, ArrayList<EconomyCreature> aHeroArmy) {
        this.heroStatistics = aHeroStatistics;
        this.heroArmy = aHeroArmy;
    }


    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(Hero aHero) {
        // TODO exchange army and so on?
        // TODO battle if enemy hero
    }

    public void addCreaturesToArmy(RecruitmentBuilding building, int amount, PlayerResources resources) {
        int creaturesCost = building.getCreaturesToRecruit().getAmount() * building.getCreaturesToRecruit().getGoldCost();

        if (creaturesCost < resources.getGold()) {
            EconomyCreature armyCreature = building.takeCreaturesFromBuilding(amount);
            heroArmy.add(armyCreature);
            resources.setGold(resources.getGold() - creaturesCost);
//            building.getCreaturesToRecruit().setAmount(building.getCreaturesToRecruit().getAmount()-amount);
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
        //open gui
        this.heroEquipment.addItem(aArtifact);
    }
}
