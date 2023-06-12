package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.buildings.Building;
import pl.psi.buildings.HallBuilding;
import pl.psi.buildings.RecruitmentBuilding;
import pl.psi.creatures.EconomyCreature;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.HeroEquipment;
import pl.psi.hero.HeroStatistics;
import pl.psi.mapElements.*;
import pl.psi.player.PlayerResources;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CastleTest {

//    @Test
//    void shouldCursedTempleExist() {
//        Castle.FractionType playerFraction = Castle.FractionType.NECROPOLIS;
//        assertEquals(playerFraction.gettier1Name(),"Cursed temple");
//    }

//    @Test
//    void shouldTier1CreaturesExist(){
//        Castle playerCastle = new Castle(Castle.FractionType.NECROPOLIS);
//        int amount = playerCastle.getCreaturesAmount();
//        assertEquals(amount, 127);
//    }

//    @Test
//    void shouldCursedTempleExist(){
//        Castle playerCastle = new Castle(Castle.FractionType.NECROPOLIS);
//        assertEquals("Cursed temple", playerCastle.getBuildingsOwned().get(0).getName());
//    }

    @Test
    void shouldCreaturesToRecruitExist(){
        Castle playerCastle = new Castle(Castle.FractionType.NECROPOLIS);
        assertEquals("Skeleton",((RecruitmentBuilding)  playerCastle.getBuildingsOwned().get(1)).getCreaturesToRecruit().getName());
    }

    @Test
    void shouldBuyingCreaturesWork(){
        Castle playerCastle = new Castle(Castle.FractionType.NECROPOLIS);
        PlayerResources resources = PlayerResources.builder()
                .wood(100)
                .ore(50)
                .gold(15000)
                .mercury(10)
                .sulfur(20)
                .crystal(30)
                .gems(5)
                .build();
        ArrayList<EconomyCreature> army = new ArrayList<EconomyCreature>();
        EconomyHero economyHero = new EconomyHero(HeroStatistics.builder().build(), army, HeroEquipment.builder().build());

        economyHero.addCreaturesToArmy((RecruitmentBuilding) playerCastle.getBuildingsOwned().get(1), 12, resources);
        assertEquals(12, economyHero.getHeroArmy().get(0).getAmount());
        assertEquals(8, ((RecruitmentBuilding) playerCastle.getBuildingsOwned().get(1)).getCreaturesToRecruit().getAmount());
        assertEquals(14280, resources.getGold());

        economyHero.addCreaturesToArmy((RecruitmentBuilding) playerCastle.getBuildingsOwned().get(2), 1, resources);
        assertEquals(1, economyHero.getHeroArmy().get(1).getAmount());

        economyHero.addCreaturesToArmy((RecruitmentBuilding) playerCastle.getBuildingsOwned().get(1), 2, resources);
        assertEquals(14, economyHero.getHeroArmy().get(0).getAmount());

        economyHero.addCreaturesToArmy((RecruitmentBuilding) playerCastle.getBuildingsOwned().get(2), 4, resources);
        assertEquals(5, economyHero.getHeroArmy().get(1).getAmount());
    }

    @Test
    void shouldAddingBuildingsToHallWork(){
        Castle playerCastle = new Castle(Castle.FractionType.TOWER);
        HallBuilding hall = (HallBuilding) playerCastle.getBuildingsOwned().get(0);
        hall.setBuildingsAwailable(playerCastle.getBuildingsToBuy());
        RecruitmentBuilding building = (RecruitmentBuilding) hall.getBuildingsAwailable().get(0);
        assertEquals(3, building.getId());
    }

    @Test
    void shouldAddingBuildingsToHallWork2(){
        Castle playerCastle = new Castle(Castle.FractionType.TOWER);
        HallBuilding hall = (HallBuilding) playerCastle.getBuildingsOwned().get(0);
        hall.setBuildingsAwailable(playerCastle.getBuildingsToBuy());
        RecruitmentBuilding building = (RecruitmentBuilding) hall.getBuildingsAwailable().get(0);

        hall.buyBuilding(0);
        assertEquals(3, building.getId());
    }

    @Test
    void shouldBuildingBuildingsWork(){
        Castle currentCastle = new Castle(Castle.FractionType.NECROPOLIS);
        currentCastle.buildBuilding(currentCastle.getBuildingsToBuy(), currentCastle.getBuildingsOwned(), 7);
        assertTrue(currentCastle.getBuildingsToBuy().isEmpty());
    }

    @Test
    void shouldBuyingBuildingsWork(){
        Castle playerCastle = new Castle(Castle.FractionType.NECROPOLIS);
        PlayerResources resources = PlayerResources.builder()
                .wood(100)
                .ore(50)
                .gold(7000)
                .mercury(10)
                .sulfur(20)
                .crystal(30)
                .gems(5)
                .build();
        ArrayList<EconomyCreature> army = new ArrayList<EconomyCreature>();
        EconomyHero economyHero = new EconomyHero(HeroStatistics.builder().build(), army, HeroEquipment.builder().build());

        economyHero.buyBuilding(7, resources, playerCastle);
        assertEquals(2000, resources.getGold());
        assertTrue(playerCastle.getBuildingsToBuy().isEmpty());
    }

}