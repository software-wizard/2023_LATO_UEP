package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.hero.Hero;
import pl.psi.hero.HeroEquipment;
import pl.psi.hero.HeroStatistics;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.TestArtifact;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HeroEquipmentTest {
    @Test
    void shouldAddItemToBackpack() {
        final Hero hero = new Hero(HeroStatistics.builder().build(),
                new HeroEquipment());
        MapElement testArtifact1 = new TestArtifact("helmet","skullHelmet");
        MapElement testArtifact2 = new TestArtifact("helmet","thunderHelmet");
        hero.addArtifactToBackpack(testArtifact1);
        assertTrue(hero.getHeroEquipment().getHeroBackpack().contains(testArtifact1));
    }

    @Test
    void shouldMoveItemFromBackpackToInventory() {
        final HeroEquipment heroEquipment = new HeroEquipment();
        MapElement testArtifact1 = new TestArtifact("helmet","skullHelmet");
        MapElement testArtifact2 = new TestArtifact("helmet","thunderHelmet");
        heroEquipment.addItemToBackpack(testArtifact1);
        heroEquipment.addItemToBackpack(testArtifact2);
        heroEquipment.moveFromBackpackToInventory((TestArtifact) testArtifact1);
        heroEquipment.moveFromBackpackToInventory((TestArtifact) testArtifact2);
        assertTrue(heroEquipment.getHeroInventory().containsValue(testArtifact1));
        assertFalse(heroEquipment.getHeroBackpack().contains(testArtifact1));
    }

    @Test
    void shouldMoveItemFromInventoryToBackpack() {
        final HeroEquipment heroEquipment = new HeroEquipment();
        MapElement testArtifact1 = new TestArtifact("helmet","skullHelmet");
        MapElement testArtifact2 = new TestArtifact("helmet","thunderHelmet");
        heroEquipment.addItemToBackpack(testArtifact1);
        heroEquipment.addItemToBackpack(testArtifact2);
        heroEquipment.moveFromBackpackToInventory((TestArtifact) testArtifact1);
        heroEquipment.moveFromInventoryToBackpack((TestArtifact) testArtifact1);
        
        assertTrue(heroEquipment.getHeroInventory().get(((TestArtifact) testArtifact1).getType())==null);
        assertTrue(heroEquipment.getHeroBackpack().contains(testArtifact1));
        assertTrue(heroEquipment.getHeroBackpack().contains(testArtifact2));
    }
}
