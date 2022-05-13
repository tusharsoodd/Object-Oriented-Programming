package animals;

public class Lion extends TerrestrialAnimal {
    public Lion(String nickname) {
        super(nickname);
    }


    public boolean isCompatibleWith(Animal animal) {
        if (animal instanceof Gazelle) {
            return false;
        } else if (animal instanceof Zebra) {
            return false;
        } else {
            return true;
        }
    }
}
