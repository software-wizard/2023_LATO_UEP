// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi.eco.buildings;

import pl.psi.eco.mapElements.Castle;

import java.util.ArrayList;

public class HallBuilding extends Building{

    public HallBuilding(String name, Type type, int id, Castle.FractionType fraction) {
        super(name, type, id, fraction);
    }

    private ArrayList<Building> buildingsAwailable = new ArrayList<>();

    public ArrayList<Building> getBuildingsAwailable() {
        return buildingsAwailable;
    }

    public void setBuildingsAwailable(ArrayList<Building> buildingsAwailable) {
        this.buildingsAwailable = buildingsAwailable;
    }

    public void buyBuilding(int id){
        for(Building building: buildingsAwailable){

        }
        this.buildingsAwailable.get(id);
    }
}