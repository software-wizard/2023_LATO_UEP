package pl.psi.Buildings;
import lombok.Getter;
import lombok.Setter;
import pl.psi.hero.Hero;
import pl.psi.player.PlayerResources;
import pl.psi.mapElements.*;
@Getter
@Setter
public class Building {
    private String name;
    private Type type;
    private int spot;
    private Resource cost;
    private Castle.FractionType fraction;

    public Building(String name, Type type, int spot, Resource cost, Castle.FractionType fraction) {
        this.name = name;
        this.type = type;
        this.spot = spot;
        this.cost = cost;
        this.fraction = fraction;
    }

    public Building(String name, Type type, int spot, Castle.FractionType fraction) {
        this.name = name;
        this.type = type;
        this.spot = spot;
        this.fraction = fraction;
    }

    public enum Type {
        RECRUITMENT,
        RESOURCE,
        SPECIAL,
        ARTIFACT,
        OTHER
    }

}
