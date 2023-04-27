package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.EconomyCreature;
import pl.psi.hero.Hero;
import pl.psi.hero.HeroStatistics;
import pl.psi.mapElements.*;
import pl.psi.player.PlayerResources;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void shouldCursedTempleExist(){
        Castle playerCastle = new Castle(Castle.FractionType.NECROPOLIS);
        assertEquals("Cursed temple", playerCastle.getBuildingsOwned().get(0).getName());
    }

    @Test
    void shouldCreaturesToRecruitExist(){
        Castle playerCastle = new Castle(Castle.FractionType.NECROPOLIS);
        assertEquals("Skeleton", playerCastle.getBuildingsOwned().get(0).getCreaturesName());
    }

    @Test
    void shouldPlayerBuyCreatures(){
        Castle playerCastle = new Castle(Castle.FractionType.NECROPOLIS);
        PlayerResources resources = PlayerResources.builder()
                .wood(100)
                .ore(50)
                .gold(5000)
                .mercury(10)
                .sulfur(20)
                .crystal(30)
                .gems(5)
                .build();
        ArrayList<EconomyCreature> army = new ArrayList<EconomyCreature>();
        Hero hero = new Hero(HeroStatistics.builder().build(), army);

        hero.addCreaturesToArmy(playerCastle.getBuildingsOwned().get(0), 12, resources);

        assertEquals(12, hero.getHeroArmy().get(0).getAmount());
//        assertEquals(20, playerCastle.getBuildingsOwned().get(0).getCreaturesToRecruit().getAmount());
        assertEquals(3800, resources.getGold());


    }






}
