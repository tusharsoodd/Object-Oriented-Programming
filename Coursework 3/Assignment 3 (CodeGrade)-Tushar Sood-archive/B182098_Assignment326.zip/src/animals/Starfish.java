package animals;

public class Starfish extends AquaticAnimal{
    public Starfish(String nickname){
        super(nickname);
    }


    public boolean isCompatibleWith(Animal animal) {
        return true;
    }
}
