package pl.psi;

import lombok.Getter;
import lombok.Setter;

// Zamiast dziedziczenia decorator - to samo dla przeszkód
public class Hero extends MapElement {

    @Getter
    private int moveRange = 3; // TODO BUILDER STATISTICS
    @Getter
    @Setter
    private int gold = 0;

    public Hero() {
        super(true); // TODO jak inaczej?
    }
}
