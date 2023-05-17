package pl.psi.creatures;

import java.util.Random;

public class KnowladgeSkill {
    public KnowladgeSkill(){
    }

    public int increaseByLevel(int manaMax, int level) {
        manaMax = 0;
        for (int i = 1; i >= level; i++) {
            manaMax += 10;
        }
        return manaMax  ;
    }
}
