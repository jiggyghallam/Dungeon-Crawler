package shared;

import java.util.ArrayList;

public class Buttons {
	
	public final class Button {
		
		boolean isDown = false;
		int x = 0;
		int y = 0;
		
		public Button() {
			all.add(this);
		}
		
		public boolean isPressed() {
			return isDown;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public void release() {
			isDown = false;
		}
		
		
	}
	
	private ArrayList<Button> all = new ArrayList<Button>();
	
	public Button leftClick = new Button();
	public Button rightClick = new Button();
	public Button leftDrag = new Button();
	
	public void release() {
		for (Button buttons: all){
			buttons.release();
		}
	}
	
	public ArrayList<Button> getAll() {
		return all;
	}
	

}
