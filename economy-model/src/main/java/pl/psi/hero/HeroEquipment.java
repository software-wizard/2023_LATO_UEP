package pl.psi.hero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.psi.mapElements.MapElement;

import java.util.ArrayList;
import java.util.HashMap;
@AllArgsConstructor
@Builder
@Getter
public class HeroEquipment {

    private final ArrayList<MapElement> heroBackpack;
    private final HashMap<String, Object> heroInventory;

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

    public void addItem(MapElement item){
        this.heroBackpack.add(item);
    }
}
