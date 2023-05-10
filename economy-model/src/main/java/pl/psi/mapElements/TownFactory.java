package pl.psi.mapElements;

import pl.psi.buildings.Building;
import pl.psi.buildings.HallBuilding;
import pl.psi.buildings.RecruitmentBuilding;

import java.util.ArrayList;

import static pl.psi.ResourceType.GOLD;

public class TownFactory {
    public static ArrayList<Building> createBuildingsOwned(Castle.FractionType fraction) {

        ArrayList<Building> buildings = new ArrayList<Building>();
        switch (fraction)
        {
            case NECROPOLIS:
                buildings.add(new HallBuilding("Hall", Building.Type.HALL, 0, fraction));
                buildings.add(new RecruitmentBuilding("Cursed temple", Building.Type.RECRUITMENT, 1, 1, fraction));
                buildings.add(new RecruitmentBuilding("Graveyard", Building.Type.RECRUITMENT, 2, 2, fraction));
                buildings.add(new RecruitmentBuilding("Tomb of souls", Building.Type.RECRUITMENT, 3, 3, fraction));
                buildings.add(new RecruitmentBuilding("Estate", Building.Type.RECRUITMENT, 4, 4, fraction));
                buildings.add(new RecruitmentBuilding("Mausoleum", Building.Type.RECRUITMENT, 4, 5, fraction));
                buildings.add(new RecruitmentBuilding("Hall of Darkness", Building.Type.RECRUITMENT, 4, 6, fraction));
                buildings.add(new RecruitmentBuilding("Dragon vault", Building.Type.RECRUITMENT, 4, 7, fraction));
                return buildings;
            case TOWER:
                buildings.add(new HallBuilding("Hall", Building.Type.HALL, 0, fraction));
                buildings.add(new RecruitmentBuilding("Workshop", Building.Type.RECRUITMENT, 1, 1, fraction));
                buildings.add(new RecruitmentBuilding("Parapet", Building.Type.RECRUITMENT, 2, 2, fraction));
                return buildings;
        }
        return null;
    }

    public static ArrayList<Building> createBuildingsToBuy(Castle.FractionType fraction) {
        ArrayList<Building> buildings = new ArrayList<Building>();
        switch (fraction)
        {
            case NECROPOLIS:
                return buildings;
            case TOWER:
                buildings.add(new RecruitmentBuilding("Golem factory", Building.Type.RECRUITMENT, 3, new Resource(GOLD, 1500), 3, fraction));
                buildings.add(new RecruitmentBuilding("Mage tower", Building.Type.RECRUITMENT, 4, new Resource(GOLD, 2000), 4, fraction));
                return buildings;
        }
        return null;
    }
}
