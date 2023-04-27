package pl.psi.hero;

import com.google.common.collect.HashBiMap;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.psi.Buildings.RecruitmentBuilding;
import pl.psi.creatures.EconomyCreature;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.Resource;
import pl.psi.player.PlayerResources;

import java.util.ArrayList;

// Zamiast dziedziczenia decorator - to samo dla przeszkód
@Builder
@Getter
public class Hero implements MapElement {

    private HeroStatistics heroStatistics;
    private ArrayList<EconomyCreature> heroArmy;

    public Hero(HeroStatistics aHeroStatistics) {
        this.heroStatistics = aHeroStatistics;
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
    public void apply(Hero aHero, HashBiMap map) {
        // TODO exchange army and so on?
        // TODO battle if enemy hero
    }

    public void addCreaturesToArmy(RecruitmentBuilding building, int amount, PlayerResources resources){
        int creaturesCost = building.getCreaturesToRecruit().getAmount()*building.getCreaturesToRecruit().getGoldCost();

        if(creaturesCost<resources.getGold()){
            EconomyCreature armyCreature = building.takeCreaturesFromBuilding(amount);
            heroArmy.add(armyCreature);
            resources.setGold(resources.getGold()-creaturesCost);
//            building.getCreaturesToRecruit().setAmount(building.getCreaturesToRecruit().getAmount()-amount);
        }
    }
}
