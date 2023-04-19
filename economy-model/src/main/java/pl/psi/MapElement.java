package pl.psi;

public abstract class MapElement {

// TODO oddzielić element od heroes. Jak wtedy przekazywać konstruktor
//interfejs MapElement - jedna metoda - getIcon implementujemy w herosie i w kapliczce.
//Klasy loginczne kapliczki, zamki, kopalnie, wrogowie,
//Przekazuje interfejs. GetIcon zwraca grafikę i czy obiekt interaktywny.
//Pole ma metodę MapElement.apply(heroes) - Board nic nie wie.
//Odpowiedzialność dla MapElement
//Klasa Resource extends MapElement ValueObject dodawać tylko resc któy dotyczy pola. Dodać do Player
//Klasa Artefakt itd.
//Klasa kopalnia itd.
    private boolean isAction;
    // TODO enum

    public boolean isAction() {
        return isAction;
    }

    public MapElement(boolean aIsAction) {
        this.isAction = aIsAction; // TODO źle, jak to zmienić?
    }

}
