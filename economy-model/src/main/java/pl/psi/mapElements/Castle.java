//package pl.psi.mapElements;
//
//import com.google.common.collect.HashBiMap;
//import pl.psi.creatures.EconomyCreature;
//import pl.psi.creatures.EconomyNecropolisFactory;
//import pl.psi.hero.Hero;
//
//
//public class Castle implements MapElement {
//    // TODO
//
//    public enum FractionType {
//        TOWER("Workshop","Golden pavilion"),
//        NECROPOLIS("Cursed temple", "Hall of darkness");
//
//        private final String tier1Name;
//        private final String tier6Name;
//
//        FractionType(String tier1Name, String tier6Name) {
//            this.tier1Name = tier1Name;
//            this.tier6Name = tier6Name;
//        }
//
//        public String gettier1Name() {
//            return tier1Name;
//        }
//
//        public String gettier6Name() {
//            return tier6Name;
//        }
//    }
//
//    private final Hero owner = null;
//    private EconomyCreature tier1Creatures, tier2Creatures, tier3Creatures, tier4Creatures, tier5Creatures, tier6Creatures;
//
//    public Castle(Castle.FractionType aType) {
//        this.tier1Creatures = createCreatures(aType, 1, 127);
//        this.tier6Creatures = createCreatures(aType, 6, 10);
//    }
//
//    public int getCreaturesAmount(){
//        return this.tier1Creatures.getAmount();
//    }
//
//    @Override
//    public boolean isInteractive() {
//        return true;
//    }
//
//    @Override
//    public void apply(Hero aHero, HashBiMap map) {
//    }
//
//    public EconomyCreature createCreatures(Castle.FractionType type, int tier, int amount){
//        switch(type){
//            case TOWER:
//                break;
//            case NECROPOLIS:
//                EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
//                return factory.create(false, tier, amount);
//        }
//        return null;
//    }
//
//    public void buyCreatures(Hero aHero, Castle.FractionType type, int tier, int amount){
//
//    }
//
//}

package pl.psi.mapElements;
import pl.psi.Buildings.Building;
import pl.psi.Buildings.RecruitmentBuilding;
import pl.psi.hero.Hero;
import java.util.ArrayList;
import static pl.psi.Resource.ResourceType;


public class Castle implements MapElement {
    // TODO

    private ArrayList<RecruitmentBuilding> buildingsToBuy = new ArrayList<>();
    private ArrayList<RecruitmentBuilding> buildingsOwned = new ArrayList<>();

    public ArrayList<RecruitmentBuilding> getBuildingsToBuy() {
        return buildingsToBuy;
    }

    public ArrayList<RecruitmentBuilding> getBuildingsOwned() {
        return buildingsOwned;
    }

    public enum FractionType {
        TOWER,
        NECROPOLIS;
    }

    private final Hero owner = null;

    public Castle(FractionType fractionType) {
        switch(fractionType) {
            case TOWER:
                buildingsOwned.add(new RecruitmentBuilding("Workshop", Building.Type.RECRUITMENT, 1, 1, fractionType));
                buildingsOwned.add(new RecruitmentBuilding("Parapet", Building.Type.RECRUITMENT, 2, 2, fractionType));
                buildingsToBuy.add(new RecruitmentBuilding("Golem factory", Building.Type.RECRUITMENT, 3, new Resource(ResourceType.GOLD, 1500), 3, fractionType));
                buildingsToBuy.add(new RecruitmentBuilding("Mage tower", Building.Type.RECRUITMENT, 4, new Resource(ResourceType.GOLD, 2000), 4, fractionType));
                buildingsToBuy.add(new RecruitmentBuilding("Altar of Wishes", Building.Type.RECRUITMENT, 4, new Resource(ResourceType.GOLD, 2000), 5, fractionType));
                buildingsToBuy.add(new RecruitmentBuilding("Golden pavilion", Building.Type.RECRUITMENT, 4, new Resource(ResourceType.GOLD, 6000), 6, fractionType));
                buildingsToBuy.add(new RecruitmentBuilding("Cloud temple", Building.Type.RECRUITMENT, 4, new Resource(ResourceType.GOLD, 10000), 7, fractionType));
                break;
            case NECROPOLIS:
                buildingsOwned.add(new RecruitmentBuilding("Cursed temple", Building.Type.RECRUITMENT, 1, 1, fractionType));
                buildingsOwned.add(new RecruitmentBuilding("Graveyard", Building.Type.RECRUITMENT, 2, 2, fractionType));
                buildingsToBuy.add(new RecruitmentBuilding("Tomb of souls", Building.Type.RECRUITMENT, 3, new Resource(ResourceType.GOLD, 1500), 3, fractionType));
                buildingsToBuy.add(new RecruitmentBuilding("Estate", Building.Type.RECRUITMENT, 4, new Resource(ResourceType.GOLD, 2000), 4, fractionType));
                buildingsToBuy.add(new RecruitmentBuilding("Mausoleum", Building.Type.RECRUITMENT, 4, new Resource(ResourceType.GOLD, 2000), 5, fractionType));
                buildingsToBuy.add(new RecruitmentBuilding("Hall of Darkness", Building.Type.RECRUITMENT, 4, new Resource(ResourceType.GOLD, 6000), 6, fractionType));
                buildingsToBuy.add(new RecruitmentBuilding("Dragon vault", Building.Type.RECRUITMENT, 4, new Resource(ResourceType.GOLD, 10000), 7, fractionType));
                break;
                //factory do tego z innym słowkiem na castle
        }
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public void apply(Hero aHero) {

    }

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return false;
    }

    @Override
    public void endOfTurn() {

    }

}
