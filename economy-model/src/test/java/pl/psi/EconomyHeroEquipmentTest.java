package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.hero.EconomyHero;
import pl.psi.hero.EconomyHeroStatistics;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.TestArtifact;


import static org.junit.jupiter.api.Assertions.assertTrue;


public class EconomyHeroEquipmentTest {
    @Test
    void shouldAddItemToBackpack() {
        final EconomyHero economyHero = new EconomyHero(EconomyHeroStatistics.builder().build());;//new Hero(HeroStatistics.builder().build());
        MapElement testArtifact1 = new TestArtifact("helmet","skullHelmet");
        MapElement testArtifact2 = new TestArtifact("helmet","thunderHelmet");
        economyHero.addArtifactToBackpack(testArtifact1);
        assertTrue(economyHero.getHeroBackpack().contains(testArtifact1));
    }
}
