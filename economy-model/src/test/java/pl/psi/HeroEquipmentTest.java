package pl.psi;

import org.junit.jupiter.api.Test;
import pl.psi.hero.Hero;
import pl.psi.hero.HeroEquipment;
import pl.psi.hero.HeroStatistics;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.TestArtifact;


import java.util.ArrayList;
import java.util.HashMap;

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
}
