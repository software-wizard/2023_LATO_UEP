package pl.psi.fields;


//to develop
public class AbilitiesDecorator {
    private boolean isAddedMagic = false;

    public AbilitiesDecorator(){
        addMagic();
    }

    public void addMagic(){
        System.out.println("Added magic!");
        isAddedMagic = true;
    }
}
