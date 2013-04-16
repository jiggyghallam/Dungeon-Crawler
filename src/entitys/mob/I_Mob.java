package entitys.mob;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;


public interface I_Mob {
	
	/**
	 * Sets the players name
	 * @param name {@link String} name of player
	 */
	public void setName(String name);
	
	/**
	 * Gets the name of the player
	 * @return {@link String} name of the player
	 */
	public String getName();

	/**
	 * Sets the players Hit points
	 * @param HP
	 */
	public void setHP(int HP);
	
	/**
	 * Gets hit points of the player
	 * @return {@link int} Hitpoints of the player
	 */
	public int getHP();
	
	/**
	 * Adds a set number of HP to player
	 * @param HP {@link int} Hit points
	 */
	public void addHP(int HP);
	
	/**
	 * Sets max HP for the player
	 * @param MAX_HP {@link int} max hitpoints for a player
	 */
	public void setMaxHP(int MAX_HP);
	
	/**
	 * Gets the players X position on the map
	 * @return {@link int} X pos
	 */
	public int getXPosition();
	
	/**
	 * Sets the X position on the map
	 * @param X_POS {@link int} X position
	 */
	public void setXPosition(int X_POS);
	
	/**
	 * Gets the players Y position on the map
	 * @return {@link int} Y pos
	 */
	public int getYPosition();
	
	/**
	 * Sets the Y position on the map
	 * @param Y_POS {@link int} Y position
	 */
	public void setYPosition(int Y_POS);
	
	/**
	 * Sets the players Speed
	 * @param SPD {@link int} Speed
	 */
	public void setSpeed(int SPD);
	
	/**
	 * Increases players speed by set amount
	 * @param SPD_UP {@link int} Speed to increase by
	 */
	public void increaseSpeed(int SPD_UP);
	
	/**
	 * Decreases players speed by set amount
	 * @param SPD_DOWN {@link int} Speed to slow down by
	 */
	public void decreaseSpeed(int SPD_DOWN);
	
	/**
	 * Player moves left
	 * @param LFT {@link boolean} True will move player left
	 */
	public void moveLeft(boolean LFT);
	
	/**
	 * Player moves right
	 * @param RGT {@link boolean} True will move player right
	 */
	public void moveRight(boolean RGT);
	
	/**
	 * Player moves up
	 * @param UP {@link boolean} True will move player up
	 */
	public void moveUp(boolean UP);
	
	/**
	 * Player moves down
	 * @param DWN {@link boolean} True will move player down
	 */
	public void moveDown(boolean DWN);
	
	/**
	 * Sets the players direction, must be in degrees from 0 to 359
	 * @param dir {@link int} direction 0 - 359
	 */
	public void setDirection(int dir);
	
	/**
	 * Sets the players direction it face the X and Y co-ords given
	 * @param x {@link int} X co-ord
	 * @param y {@link int} Y co-ord
	 */
	public void setDirection(int x, int y);
	
	/**
	 * Gets the players direction
	 * @return {@link int} direction player is facing
	 */
	public int getDirection();
	
	/**
	 * Sets weapons for a player
	 * @param WPN {@link ArrayList}<{@link I_Weapon}> 
	 */
	//public void setWeapons(ArrayList<Weapon> WPN);
	
	/**
	 * Paints the player to the screen
	 * @param g {@link Graphics2D} 
	 */
	public void render(Graphics2D g);
	
	/**
	 * If the object is alive it will be drawn
	 * @return {@link boolean} {@link true} if it is alive
	 */
	public boolean isAlive();

}
