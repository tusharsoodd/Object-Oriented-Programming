package areas;

import zoo.Zoo;

import java.util.ArrayList;

/**
 * This file must remain exactly as it is.
 */
public interface IArea
{
	public int maxCapacity = 5;

	/**
	 * @return Returns the IDs of the areas adjacent to this one.
	 */

	public ArrayList<Integer> connections = new ArrayList();


	public ArrayList<Integer> getAdjacentAreas();

	public default void addConnection(Integer connectToAreaID, Zoo zoo) {
		this.connections.add(connectToAreaID);
	}
}
