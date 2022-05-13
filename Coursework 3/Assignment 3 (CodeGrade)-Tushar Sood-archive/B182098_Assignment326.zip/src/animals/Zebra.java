package animals;

public class Zebra extends TerrestrialAnimal{
    public Zebra(String nickname){
        super(nickname);
    }

    public boolean isCompatibleWith(Animal animal) {
        return !(animal instanceof Lion);
    }
}
