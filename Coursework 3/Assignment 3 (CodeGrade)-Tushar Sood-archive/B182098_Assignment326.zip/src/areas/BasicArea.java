package areas;

import java.util.ArrayList;

public abstract class BasicArea implements IArea {
    public ArrayList<Integer> adjacentAreas = new ArrayList<>();

    public ArrayList<Integer> getAdjacentAreas() {
        return this.adjacentAreas;
    }
    public BasicArea(){}
}
