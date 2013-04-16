package shared;

/**
 * Holds an i and j value, can also hold a direction
 * @author Jiggy
 *
 */
public class Node {
	
	
	public int i;
	public int j;
	public Direction direction;
	
	/**
	 * Create a node with an i and j value, direction is set to null
	 * @param i {@link int}
	 * @param j {@link int}
	 */
	public Node (int i, int j) {
		this.i = i;
		this.j = j;
		direction = Direction.NULL;
		
	}
	
	/**
	 * Create a node with an i, j value and a direction 
	 * @param i {@link int}
	 * @param j {@link int}
	 * @param direction {@link Direction}
	 */
	public Node (int i, int j, Direction direction) {
		this.i = i;
		this.j = j;
		this.direction = direction;
	}

}
