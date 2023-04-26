package pl.psi.hero;

import com.google.common.collect.HashBiMap;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.psi.mapElements.MapElement;

@Builder
@Getter
public class Hero implements MapElement {

    // TODO equipment HashMap for artifacts
    // Metoda apply for EconomyArtifact

    private HeroStatistics heroStatistics;

    public Hero(HeroStatistics aHeroStatistics) {
        this.heroStatistics = aHeroStatistics;
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

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return false;
    }

    @Override
    public void endOfTurn() {

    }
}
