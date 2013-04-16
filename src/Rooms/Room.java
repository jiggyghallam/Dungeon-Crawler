package Rooms;

import java.awt.Color;
import java.awt.Graphics2D;

public class Room {

	//Screen size = 1280 x 720, highest common factor = 80px
	private int[][] room = new int[16][9];
	public Room(){}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.BLUE);
		for (int i = 0; i < room.length; i++) {
			for (int j = 0; j < room[i].length; j++) {
				g2d.drawRect(i * 80, j * 80, 80, 80);
			}
		}
	}
	
}
