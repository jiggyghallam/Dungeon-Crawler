package shared;

import java.sql.Time;
import java.util.Arrays;
import java.util.Random;

public class Map {

	private static final int MAX_MAP_SIZE = 15;
	private static final int SECRET_ROOMS = 2;
	private static final int MINI_BOSS_ROOMS = 1;
	private static final int TREASURE_ROOMS = 1;
	private static final int START_POS = 7;
	private static final int DEFAULT_SIZE = 5;

	private int size;
	private int iSecretRooms;
	private int iMiniBossRooms;
	private int iTreasureRooms;
	private Random random = new Random(System.currentTimeMillis());
	private int[][] currentMap = new int[MAX_MAP_SIZE][MAX_MAP_SIZE];

	/**
	 * Constructor, with a size set to 5
	 */
	public Map() {
		setSize(DEFAULT_SIZE);
	}

	/**
	 * Constructor
	 * 
	 * @param {@link int} size of the map (number of rooms)
	 */
	public Map(int size) {
		this.setSize(size);
	}

	/**
	 * Create a map, uses size set by the constructor
	 */
	public void createMap() {
		int iPos = START_POS;
		int jPos = START_POS;
		int mapSize = size;
		System.out.println(mapSize);
		iSecretRooms = SECRET_ROOMS;
		iMiniBossRooms = MINI_BOSS_ROOMS;
		iTreasureRooms = TREASURE_ROOMS;

		// Set start position;
		currentMap[iPos][jPos] = 5;
		mapSize--;
		for (int i = 0; i < mapSize; i++) {
			boolean foundEmpty = false;
			iPos = START_POS;
			jPos = START_POS;
			while (!foundEmpty) {
				Direction direction = Direction.values()[random
						.nextInt(Direction.values().length - 1)];
				if (Global.DEBUG)
					System.out.println(direction);
				if (canPlace(iPos, jPos, direction, 0)) {
					place(iPos, jPos, direction, 0);
					foundEmpty = true;
				} else {
					direction = Direction.values()[random.nextInt(Direction
							.values().length - 1)];
					Node n = getRoom(iPos, jPos, direction);
					if (currentMap[n.i][n.j] != 0) {
						iPos = n.i;
						jPos = n.j;
					}
				}

			}

		}

		for (int i = 0; i < MAX_MAP_SIZE; i++)
			System.out.println(Arrays.toString(currentMap[i]));
	}

	/**
	 * Create a map of set size, will create set amount of rooms
	 * 
	 * @param {@link int} size of the map (number of rooms)
	 */
	public void createMap(int size) {
		setSize(size);
		createMap();
	}

	/**
	 * Size of the map
	 * 
	 * @return {@link int} size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Set size of the map
	 * 
	 * @param size
	 *            {@link int}
	 */
	public void setSize(int size) {
		if (size < MAX_MAP_SIZE * MAX_MAP_SIZE)
			this.size = size;
		else {
			this.size = DEFAULT_SIZE;
			if (Global.DEBUG)
				System.err.println("Map size unchanged, size " + size
						+ " was to large, please use a size less than "
						+ (MAX_MAP_SIZE * MAX_MAP_SIZE - MAX_MAP_SIZE)
						+ " current map has been resized to " + DEFAULT_SIZE);
		}

	}

	/**
	 * Checks if a room can be placed in the direction specified
	 * 
	 * @param iCurrent
	 *            {@link int} i position in array
	 * @param jCurrent
	 *            {@link int} j position in array
	 * @param direction
	 *            {@link int} accepts 0, 1, 2, 3 meaning NORTH, EAST, SOUTH,
	 *            WEST.
	 * @param roomType
	 *            {@link int} type of room to be placed
	 * 
	 * @return {@link boolean} true if a room can be placed
	 */
	private boolean canPlace(int iCurrent, int jCurrent, Direction direction,
			int roomType) {
		Node n = getRoom(iCurrent, jCurrent, direction);
		if (n != null && currentMap[n.i][n.j] == 0)
			return true;
		if (Global.DEBUG)
			System.out.println("Room could not be placed at position " + n.i
					+ " " + n.j);
		return false;
	}

	/**
	 * Places a room, at a in the direction from your current location
	 * 
	 * @param iCurrent
	 *            {@link int}
	 * @param jCurrent
	 *            {@link int}
	 * @param direction
	 *            {@link Direction}
	 * @param roomType
	 * @return {@link Boolean} True if the room was placed successfully
	 */
	private boolean place(int iCurrent, int jCurrent, Direction direction,
			int roomType) {
		Node n = getRoom(iCurrent, jCurrent, direction);
		if (n != null) {
			currentMap[n.i][n.j] = 1;
			return true;
		}
		if (Global.DEBUG)
			System.out.println("Room could not be placed at position" + n.i
					+ " " + n.j);
		return false;
	}

	/**
	 * Returns a node containing the co-ordinates of a room, using current
	 * location and a direction you are moving
	 * 
	 * @param iCurrent
	 *            {@link int}
	 * @param jCurrent
	 *            {@link int}
	 * @param direction
	 *            {@link Direction}
	 * @return {@link Node}
	 */
	private Node getRoom(int iCurrent, int jCurrent, Direction direction) {

		switch (direction) {

		case NORTH:
			if (jCurrent > 0)
				return (new Node(iCurrent - 1, jCurrent));
			break;
		case EAST:
			if (iCurrent < MAX_MAP_SIZE - 1)
				return (new Node(iCurrent, jCurrent + 1));
			break;
		case SOUTH:
			if (jCurrent < MAX_MAP_SIZE - 1)
				return (new Node(iCurrent + 1, jCurrent));
			break;
		case WEST:
			if (iCurrent > 0)
				return (new Node(iCurrent, jCurrent - 1));
			break;
		default:
			return null;
		}
		// Can unable to get that room
		return null;
	}
}
