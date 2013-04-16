package entitys.mob;

public interface I_Player {
	
	/**
	 * Sets a players level, will override experience points
	 * @param level {@link int} Level
	 */
	public void setLevel(int level);
	
	/**
	 * Gets the players current level
	 * @return {@link int} Level of player
	 */
	public int getLevel();
	
	/**
	 * Sets the players experience points
	 * @param XP {@link int} Experince points
	 */
	public void setXP(int XP);
	
	/**
	 * Gets the players Experience points
	 * @return {@link int} Experience points
	 */
	public int getXP();
	
	/**
	 * Adds a set number of Experience Points to a player
	 * @param XP {@link int} Experience points
	 */
	public void addXP(int XP);

}
