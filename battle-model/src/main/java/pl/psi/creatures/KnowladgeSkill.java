package pl.psi.creatures;


public class KnowladgeSkill {
    public KnowladgeSkill(){
    }

    public int increaseByLevel(int manaMax, int level) {
        manaMax = 0;
        for (int i = 1; level >= i; i++) {
            manaMax += 10;
        }
        return manaMax  ;
    }
}
