package pl.psi.hero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.psi.mapElements.MapElement;

import java.util.ArrayList;
import java.util.HashMap;
@AllArgsConstructor
@Builder
@Getter
public class Hero implements MapElement {

    // TODO equipment HashMap for artifacts
    // Metoda apply for EconomyArtifact

    private HeroStatistics heroStatistics;
    private HashMap<String, Object> heroEquipment;
    private ArrayList<MapElement> heroBackpack;

    public Hero(HeroStatistics aHeroStatistics) {
        this.heroStatistics = aHeroStatistics;
        this.heroBackpack = new ArrayList<>();
        this.heroEquipment = new HashMap<>();
        //w innej metodzie wrzucac dopiero type = name
        this.heroEquipment.put("helmet", null);
        this.heroEquipment.put("cape", null);
        this.heroEquipment.put("necklace", null);
        this.heroEquipment.put("rightHand", null);
        this.heroEquipment.put("leftHand", null);
        this.heroEquipment.put("torso", null);
        this.heroEquipment.put("ring", null);
        this.heroEquipment.put("feet", null);
        this.heroEquipment.put("miscellaneous", null);
        this.heroEquipment.put("ballista", null);
        this.heroEquipment.put("ammoCart", null);
        this.heroEquipment.put("firstAidTent", null);
        this.heroEquipment.put("catapult", null);
        this.heroEquipment.put("spellBook", null);
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

    @Override
    public boolean shouldBeRemoveAfterAction() {
        return false;
    }

    @Override
    public void endOfTurn() {

    }

    public void addArtifactToBackpack(MapElement aArtifact) {
        this.heroBackpack.add(aArtifact);
    }
}
