package animals;

public class Gazelle extends TerrestrialAnimal {
    public Gazelle(String nickname){
        super(nickname);
    }


    public boolean isCompatibleWith(Animal animal) {
        return !(animal instanceof Lion);
    }
}
