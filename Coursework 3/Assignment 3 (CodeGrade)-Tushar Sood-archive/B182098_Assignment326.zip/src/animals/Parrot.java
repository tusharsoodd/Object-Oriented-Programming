package animals;

public class Parrot extends AerialAnimal{
    public Parrot(String nickname){
        super(nickname);
    }


    public boolean isCompatibleWith(Animal animal) {
        return !(animal instanceof Buzzard);
    }
}
