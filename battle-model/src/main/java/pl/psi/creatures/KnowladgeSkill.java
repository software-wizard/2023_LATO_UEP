package pl.psi.creatures;

import java.util.Random;

public class KnowladgeSkill {
    public KnowladgeSkill(){
    }

    public int increaseByLevel(int mana, int level) {
        mana = 0;
        for (int i = 1; i >= level; i++) {
            mana = mana + 10;
        }
        return mana;
    }
}
