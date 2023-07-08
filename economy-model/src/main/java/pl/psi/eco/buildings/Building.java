// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.buildings;
import lombok.Getter;
import lombok.Setter;
import pl.psi.eco.mapElements.Castle;
import pl.psi.eco.mapElements.Resource;
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
