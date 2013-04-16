package entitys.mob;

import java.awt.Graphics;
import java.util.ArrayList;

import entitys.Entity;



public class Mob extends Entity implements I_Mob {
	
	protected String name;
	protected int HP;
	protected int MAX_HP;
	//protected ArrayList<Weapon> weaponsList;
	protected int X_POS;
	protected int Y_POS;
	private int speed;
	private boolean isAlive;
	protected int direction;
	

	/**
	 * Creates a player
	 * 
	 * @param name
	 *            {@link String} Player name
	 * @param HP
	 *            {@link int} Start health
	 * @param XP
	 *            {@link int}Start XP
	 * @param weapons
	 *            ArrayList of {@link I_Weapon} that the player will start with
	 */
	public Mob(String name, int MAX_HP) {
		super();
		//setWeapons(weapons);
		setName(name);
		setMaxHP(MAX_HP);
		setHP(MAX_HP);
		isAlive = true;
	}

	public void setHP(int HP) {
		this.HP = HP;
	}

	public int getHP() {
		return HP;
	}

	public void addHP(int HP) {
		this.HP = this.HP += HP > MAX_HP ? MAX_HP : this.HP + HP;
	}

	public void setMaxHP(int MAX_HP) {
		this.MAX_HP = MAX_HP;
	}

	public int getXPosition() {
		return X_POS;
	}

	public int getYPosition() {
		return Y_POS;
	}

	public void setSpeed(int SPD) {
		this.speed = SPD;
	}

	public void increaseSpeed(int SPD_UP) {
		speed += SPD_UP;
	}

	public void decreaseSpeed(int SPD_DOWN) {
		speed -= SPD_DOWN;
	}

	public void moveLeft(boolean LFT) {
		if (LFT) {
			X_POS -= speed;
		}

	}

	public void moveRight(boolean RGT) {
		if (RGT) {
			X_POS += speed;
		}
	}

	public void moveUp(boolean UP) {
		if (UP) {
			Y_POS -= speed;
		}
	}

	public void moveDown(boolean DWN) {
		if (DWN) {
			Y_POS += speed;
		}
	}

	public void setDirection(int dir) {
		direction = dir;
	}

	public void setDirection(int x, int y) {
		direction = angleToTurn(x, y);
	}
		
	private int angleToTurn(int x, int y) {
		double xDist = x - X_POS;
		double yDist = y - Y_POS;
		return (int) Math.toDegrees(Math.atan2(xDist, yDist));
	}

	public int getDirection() {
		return direction;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/*public void setWeapons(ArrayList<Weapon> WPN) {
		weaponsList = WPN;
	}*/

	@Override
	public void render(Graphics g) {

	}

	public void setXPosition(int X_POS) {
		this.X_POS = X_POS;
	}

	public void setYPosition(int Y_POS) {
		this.Y_POS = Y_POS;
	}
	
	public boolean isAlive() {
		return isAlive;
	}

}
