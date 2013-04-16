package shared;

import java.util.Arrays;
import java.util.Random;

public class Map {

	private static final int MAX_MAP_SIZE = 15;
	private static final int SECRET_ROOMS = 2;
	private static final int START_POS = 7;
	private static final int DEFAULT_SIZE = 5;

	private int size;
	private int lowerLimit;
	private int iSecretRooms;
	private int iMiniBossRooms;
	private int iTreasureRooms;
	private int iShops;
	private boolean bHasBossRoom = false;
	private Random random = new Random(System.currentTimeMillis());
	private RoomTypes[][] roomMap;

	/**
	 * Constructor, with a size set to 5
	 */
	public Map() {
		setSize(DEFAULT_SIZE);
		init();
	}

	/**
	 * Constructor
	 * 
	 * @param {@link int} size of the map (number of rooms)
	 */
	public Map(int size) {
		this.setSize(size);
		init();
	}

	/**
	 * Initialises the class
	 */
	private void init() {
		roomMap = new RoomTypes[MAX_MAP_SIZE][MAX_MAP_SIZE];
		for (int i = 0; i < MAX_MAP_SIZE; i++)
			Arrays.fill(roomMap[i], RoomTypes.NULL);
		iSecretRooms = SECRET_ROOMS;
		iMiniBossRooms = size < 10 ? 0 : 2 + random.nextInt(size / 10);
		iTreasureRooms = size < 10 ? 1 : 2 + random.nextInt(size / 10);
		iShops = size < 10 ? 1 : 2 + random.nextInt(size / 10);
		lowerLimit = size < 15 ? 4 : 4 + size / 20;
	}

	/**
	 * Create a map, uses size set by the constructor
	 */
	public void createMap() {
		int iPos = START_POS;
		int jPos = START_POS;
		int mapSize = size;
		if (Global.DEBUG)
			System.out.println(mapSize);

		// Set start position;
		roomMap[iPos][jPos] = RoomTypes.START;
		mapSize--;
		for (int i = 0; i < mapSize; i++) {
			boolean foundEmpty = false;
			iPos = START_POS;
			jPos = START_POS;
			while (!foundEmpty) {
				if (Global.DEBUG)
					System.out.println("Inside " + iPos + " " + jPos + " " + i);
				Direction direction = Direction.values()[random
						.nextInt(Direction.values().length - 1)];
				RoomTypes room = chooseRoom(i);
				if (Global.DEBUG)
					System.out.println(direction);
				if (canPlace(iPos, jPos, direction, room)) {
					place(iPos, jPos, direction, room);
					break;
				}

				direction = Direction.values()[random.nextInt(Direction
						.values().length - 1)];
				Node n = getRoom(iPos, jPos, direction);
				if (n != null) {
					if (canMove(n.i, n.j)) {
						iPos = n.i;
						jPos = n.j;
					}
				}
			}

		}

		if (Global.DEBUG) {
			for (int i = 0; i < MAX_MAP_SIZE; i++)
				System.out.println(Arrays.toString(roomMap[i]));	
			System.out.println();
		}
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
		if (size < MAX_MAP_SIZE * MAX_MAP_SIZE && !(size < 2))
			this.size = size;
		else {
			this.size = DEFAULT_SIZE;
			if (Global.DEBUG)
				System.err.println("Map size unchanged, size " + size
						+ " is not alowed, please use a size between 2 and "
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
			RoomTypes roomType) {
		Node n = getRoom(iCurrent, jCurrent, direction);
		if (n != null && roomMap[n.i][n.j] == RoomTypes.NULL)
			return true;
		if (Global.DEBUG)
			System.out.println("Room could not be placed at position");
		return false;
	}

	/**
	 * Checks if you can move into this room so that you can place more rooms
	 * coming off it
	 * 
	 * @param i
	 *            {@link int} current room i
	 * @param j
	 *            {@link int} current room j
	 * @return {@link Boolean} True if can place
	 */
	private boolean canMove(int i, int j) {
		RoomTypes room = roomMap[i][j];
		if (room == RoomTypes.NORMAL || room == RoomTypes.EMPTY
				|| room == RoomTypes.START) {
			return true;
		}
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
			RoomTypes roomType) {
		Node n = getRoom(iCurrent, jCurrent, direction);
		if (n != null) {
			roomMap[n.i][n.j] = roomType;
			return true;
		}
		if (Global.DEBUG)
			System.out.println("Room " + roomType
					+ " could not be placed at position" + n.i + " " + n.j);
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
			if (iCurrent > 0)
				return (new Node(iCurrent - 1, jCurrent));
			break;
		case EAST:
			if (jCurrent < MAX_MAP_SIZE - 1)
				return (new Node(iCurrent, jCurrent + 1));
			break;
		case SOUTH:
			if (iCurrent < MAX_MAP_SIZE - 1)
				return (new Node(iCurrent + 1, jCurrent));
			break;
		case WEST:
			if (jCurrent > 0)
				return (new Node(iCurrent, jCurrent - 1));
			break;
		default:
			return null;
		}
		// Can unable to get that room
		return null;
	}

	/**
	 * Chooses a random room based on how many rooms have already been placed, a
	 * boss will always be placed as long as the map is > 2
	 * 
	 * @param i
	 *            {@link int} how many rooms have already been placed
	 * @return {@link RoomTypes}
	 */
	private RoomTypes chooseRoom(int i) {
		// Randomly selects a room, -2 used because can not place START or NULL
		int rooms = random.nextInt(RoomTypes.values().length - 2);

		// Always place a boss!
		if (i == (size - 2) && !bHasBossRoom)
			return RoomTypes.BOSS;

		RoomTypes roomToPlace;
			
		if (i < lowerLimit)
			roomToPlace = RoomTypes.values()[rooms % (RoomTypes.values().length - 7)];
		else
			roomToPlace = RoomTypes.values()[rooms % (RoomTypes.values().length - 2)];
		
		
		if (roomToPlace == RoomTypes.BOSS && !bHasBossRoom) {
			bHasBossRoom = true;
			return RoomTypes.BOSS;
		}
		if (roomToPlace == RoomTypes.MINIBOSS && iMiniBossRooms > 0) {
			iMiniBossRooms--;
			return RoomTypes.MINIBOSS;
		}
		if (roomToPlace == RoomTypes.TREASURE && iTreasureRooms > 0) {
			iTreasureRooms--;
			return RoomTypes.TREASURE;
		}
		if (roomToPlace == RoomTypes.SHOP && iShops > 0) {
			iShops--;
			return RoomTypes.SHOP;
		}
		if (roomToPlace == RoomTypes.SECRET && iSecretRooms > 0) {
			iSecretRooms--;
			return RoomTypes.SECRET;
		}

		return RoomTypes.values()[rooms % 2];
	}
}
