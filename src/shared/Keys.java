package shared;

import java.util.ArrayList;

public class Keys {
    public final class Key {
        public boolean isDown = false;

        public Key() {
            all.add(this);
        }

        public boolean isPressed() {
            return isDown;
        }

		public void release() {
			isDown = false;			
		}    
        
    }

    private ArrayList<Key> all = new ArrayList<Key>();

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key fire = new Key();

    public void release() {
        for (Key key : all)
            key.release();
    }

    public ArrayList<Key> getAll() {
        return all;
    }
}
