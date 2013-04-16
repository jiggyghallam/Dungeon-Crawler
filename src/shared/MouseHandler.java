package shared;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.event.MouseInputListener;

import shared.Buttons.Button;



public class MouseHandler implements MouseInputListener {

	private Map<Integer, Button> mappings = new HashMap<Integer, Button>();

	public MouseHandler(Buttons buttons) {
		mappings.put(0, buttons.leftDrag);
		mappings.put(MouseEvent.BUTTON1, buttons.leftClick);
		mappings.put(MouseEvent.BUTTON3, buttons.rightClick);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		toggle(e, true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		toggle(e, false);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void toggle(MouseEvent e, boolean state) {
		Button b = mappings.get(e.getButton());
		if (b != null) {
			b.isDown = state;
			b.x = e.getX();
			b.y = e.getY();
		}
		if (e.getButton() == MouseEvent.BUTTON1 && !state) {
			b = mappings.get(0);
			b.isDown = state;
			b.x = e.getX();
			b.y = e.getY();

		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		toggle(e, true);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}
