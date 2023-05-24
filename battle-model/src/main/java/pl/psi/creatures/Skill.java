package pl.psi.creatures;

import com.google.common.annotations.VisibleForTesting;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@Getter
public class Skill extends AbstractCalculateDamageStrategy{
    public void apply(Creature creature) {
    }
}

//    private void applyAllSkills( Creature creature, List<Skill> aList){
//        for (int i = 0; i<=aList.size(); i++){
//            aList.get(i).apply(creature);
//        }
//    }

