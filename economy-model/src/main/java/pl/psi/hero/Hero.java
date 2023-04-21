package pl.psi.hero;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.psi.mapElements.MapElement;

// Zamiast dziedziczenia decorator - to samo dla przeszkód
@Builder
@Getter
public class Hero implements MapElement {

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
}
