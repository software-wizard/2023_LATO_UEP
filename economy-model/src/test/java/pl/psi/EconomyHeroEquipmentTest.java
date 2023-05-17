package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.EconomyCreature;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.HeroEquipment;
import pl.psi.hero.HeroStatistics;
import pl.psi.mapElements.Artifact;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.TestArtifact;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EconomyHeroEquipmentTest {
    @Test
    void shouldAddItemToBackpack() {
        final EconomyHero hero = new EconomyHero(HeroStatistics.builder().build(),
                new ArrayList<EconomyCreature>(),
                new HeroEquipment());
        Artifact testArtifact1 = new Artifact("helmet","skullHelmet");
        Artifact testArtifact2 = new Artifact("helmet","thunderHelmet");
        hero.addArtifactToBackpack(testArtifact1);
        assertTrue(hero.getHeroEquipment().getHeroBackpack().contains(testArtifact1));
    }

    @Test
    void shouldMoveItemFromBackpackToInventory() {
        final HeroEquipment heroEquipment = new HeroEquipment();
        Artifact testArtifact1 = new Artifact("helmet","skullHelmet");
        Artifact testArtifact2 = new Artifact("helmet","thunderHelmet");
        heroEquipment.addItemToBackpack(testArtifact1);
        heroEquipment.addItemToBackpack(testArtifact2);
        heroEquipment.moveFromBackpackToInventory(testArtifact1);
        heroEquipment.moveFromBackpackToInventory(testArtifact2);
        assertTrue(heroEquipment.getHeroInventory().containsValue(testArtifact1));
        assertFalse(heroEquipment.getHeroBackpack().contains(testArtifact1));
    }

    @Test
    void shouldMoveItemFromInventoryToBackpack() {
        final HeroEquipment heroEquipment = new HeroEquipment();
        Artifact testArtifact1 = new Artifact("helmet","skullHelmet");
        Artifact testArtifact2 = new Artifact("helmet","thunderHelmet");
        heroEquipment.addItemToBackpack(testArtifact1);
        heroEquipment.addItemToBackpack(testArtifact2);
        heroEquipment.moveFromBackpackToInventory(testArtifact1);
        heroEquipment.moveFromInventoryToBackpack( testArtifact1);

        assertTrue(heroEquipment.getHeroInventory().get(testArtifact1.getType())==null);
        assertTrue(heroEquipment.getHeroBackpack().contains(testArtifact1));
        assertTrue(heroEquipment.getHeroBackpack().contains(testArtifact2));
    }
}
