package animals;

public class Seal extends AquaticAnimal{
    public Seal(String nickname){
        super(nickname);
    }



    public boolean isCompatibleWith(Animal animal) {
        return !(animal instanceof Shark);
    }
}
