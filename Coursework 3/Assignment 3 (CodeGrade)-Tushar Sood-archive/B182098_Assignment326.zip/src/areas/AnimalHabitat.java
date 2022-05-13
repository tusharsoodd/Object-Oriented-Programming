package areas;

import animals.Animal;

import java.util.ArrayList;

public abstract class AnimalHabitat extends BasicArea implements IArea{
    private int animalHabitatAreaCapacity;

    public Animal[] animalsHere = new Animal[animalHabitatAreaCapacity];

    public AnimalHabitat(int capacity) {
        this.setanimalHabitatAreaCapacity(capacity);
    }

    public void setanimalHabitatAreaCapacity(int capacity) {
        this.animalHabitatAreaCapacity = capacity;
    }

    public int getsetanimalHabitatAreaCapacity(){
        return animalHabitatAreaCapacity;
    }


}
