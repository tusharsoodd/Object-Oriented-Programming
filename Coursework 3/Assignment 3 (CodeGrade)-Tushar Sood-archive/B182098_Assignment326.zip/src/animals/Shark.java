package animals;

public class Shark extends AquaticAnimal{
    public Shark(String nickname){
        super(nickname);
    }


    public boolean isCompatibleWith(Animal animal) {
        return !(animal instanceof Seal);
    }
}
