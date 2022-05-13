package animals;


public abstract class TerrestrialAnimal extends Animal{
    public TerrestrialAnimal(String nickname) {
        super(nickname);
    }
    public abstract boolean isCompatibleWith(Animal animal);

}
