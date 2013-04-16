package entitys.mob;

import java.awt.Color;
import java.awt.Graphics;
import shared.Keys;
import shared.Buttons;



public class Player extends Mob implements I_Player {

	private int XP;
	private Keys keys;
	private Buttons buttons;
	//private BufferedImage image;
	private int currentWeapon;

	public Player(String playerName, int MAX_HP, int XP, Keys keys, Buttons buttons) {
		super(playerName, MAX_HP);
		this.keys = keys;
		this.buttons = buttons;
		direction = 180;
		X_POS = 100;
		Y_POS = 100;
		currentWeapon = 0;
		setSpeed(4);
		setXP(XP);
	}

	public void tick() {

		if (keys.down.isDown)
			moveDown(true);
		if (keys.up.isDown)
			moveUp(true);
		if (keys.left.isDown)
			moveLeft(true);
		if (keys.right.isDown)
			moveRight(true);
	}

	public void render(Graphics g) {
			g.setColor(Color.BLUE);
			g.drawRect(X_POS, Y_POS, 10, 10);

	}

	public void setXP(int XP) {
		this.XP = XP;
	}

	public int getXP() {
		return XP;
	}

	public void addXP(int XP) {
		this.XP += XP;
	}

	public void setLevel(int level) {
		// TODO Auto-generated method stub

	}

	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

}
