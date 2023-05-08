package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.hero.Hero;
import pl.psi.hero.HeroStatistics;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.TestArtifact;


import static org.junit.jupiter.api.Assertions.assertTrue;


public class HeroEquipmentTest {
    @Test
    void shouldAddItemToBackpack() {
        final Hero hero = new Hero(HeroStatistics.builder().build());;//new Hero(HeroStatistics.builder().build());
        MapElement testArtifact1 = new TestArtifact("helmet","skullHelmet");
        MapElement testArtifact2 = new TestArtifact("helmet","thunderHelmet");
        hero.addArtifactToBackpack(testArtifact1);
        assertTrue(hero.getHeroBackpack().contains(testArtifact1));
    }
}
