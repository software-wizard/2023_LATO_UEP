package pl.psi;

import lombok.Getter;
import lombok.Setter;
import pl.psi.mapElements.MapElement;

// Zamiast dziedziczenia decorator - to samo dla przeszkód
public class Hero implements MapElement {

    @Getter
    private int moveRange = 3; // TODO BUILDER STATISTICS
    @Getter
    @Setter
    private int gold = 0;

    public Hero() {
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
