package pl.psi.hero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.psi.mapElements.Artifact;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.TestArtifact;

import java.util.ArrayList;
import java.util.HashMap;
@AllArgsConstructor
@Builder
@Getter
public class HeroEquipment {

    private final ArrayList<Artifact> heroBackpack;
    private final HashMap<String, Artifact> heroInventory;

    public HeroEquipment() {

        this.heroBackpack = new ArrayList<>();
        this.heroInventory = new HashMap<>();
        //w innej metodzie wrzucac dopiero type = name
        this.heroInventory.put("helmet", null);
        this.heroInventory.put("cape", null);
        this.heroInventory.put("necklace", null);
        this.heroInventory.put("rightHand", null);
        this.heroInventory.put("leftHand", null);
        this.heroInventory.put("torso", null);
        this.heroInventory.put("ring", null);
        this.heroInventory.put("feet", null);
        this.heroInventory.put("miscellaneous", null);
        this.heroInventory.put("ballista", null);
        this.heroInventory.put("ammoCart", null);
        this.heroInventory.put("firstAidTent", null);
        this.heroInventory.put("catapult", null);
        this.heroInventory.put("spellBook", null);
    }

    public void addItemToBackpack(Artifact item){
        this.heroBackpack.add(item);
    }

    public void moveFromBackpackToInventory(Artifact backpackItem){
        if (heroInventory.get(backpackItem.getType())!=null){}
        else {
            heroInventory.put(backpackItem.getType(),backpackItem);
            heroBackpack.remove(backpackItem);
        }
    }

    public void moveFromInventoryToBackpack(Artifact inventoryItem){
        heroBackpack.add(inventoryItem);
        heroInventory.put(inventoryItem.getType(),null);
    }
}
