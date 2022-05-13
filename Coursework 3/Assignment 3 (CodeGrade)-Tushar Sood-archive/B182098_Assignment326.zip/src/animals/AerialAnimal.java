package animals;

public abstract class AerialAnimal extends Animal {
    public AerialAnimal(String nickname) {
        super(nickname);
    }
    public abstract boolean isCompatibleWith(Animal animal);
}
