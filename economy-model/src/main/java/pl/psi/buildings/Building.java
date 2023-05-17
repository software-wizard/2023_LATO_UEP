package pl.psi.buildings;
import lombok.Getter;
import lombok.Setter;
import pl.psi.mapElements.*;
@Getter
@Setter
public class Building {
    private String name;
    private Type type;
    private int id;
    private Resource cost;
    private Castle.FractionType fraction;

    public Building(String name, Type type, int id, Resource cost, Castle.FractionType fraction) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.cost = cost;
        this.fraction = fraction;
    }

    public Building(String name, Type type, int id, Castle.FractionType fraction) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.fraction = fraction;
    }

    public enum Type {
        RECRUITMENT,
        HALL,
        RESOURCE,
        SPECIAL,
        ARTIFACT,
        OTHER
    }

}
