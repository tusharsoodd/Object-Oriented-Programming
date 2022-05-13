package animals;

public abstract class AquaticAnimal extends Animal{
    public AquaticAnimal(String nickname) {
        super(nickname);
    }

    @Override
    public abstract boolean isCompatibleWith(Animal animal);

}
