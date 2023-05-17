package pl.psi.creatures;


public class KnowladgeSkill {
    public KnowladgeSkill(){
    }

    public int increaseByLevel(int manaMax, int level) {
        manaMax =+ level*10;
        return manaMax  ;
    }
}
