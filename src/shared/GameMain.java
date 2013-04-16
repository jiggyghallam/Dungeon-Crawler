package shared;

import Rooms.Room;
import entitys.mob.Player;

public class GameMain extends Base {

	private Map map;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameMain() {
		super();
	}

	public void Startup() {

		map = new Map(20);
		map.createMap();

		addEntity(new Player(null, 10, 10, keys, buttons));
	}
	
	@Override
	public void gameAction() {
		renderBackground(new Room());
	}

}
