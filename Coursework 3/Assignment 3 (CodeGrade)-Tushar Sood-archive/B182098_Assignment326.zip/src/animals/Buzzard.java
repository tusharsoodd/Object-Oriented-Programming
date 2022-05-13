package animals;

public class Buzzard extends AerialAnimal{
    public Buzzard(String nickname){
        super(nickname);
    }


    public boolean isCompatibleWith(Animal animal) {
        return !(animal instanceof Parrot);
    }
}
