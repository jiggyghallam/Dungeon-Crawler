package entitys;

import java.awt.Graphics;

import shared.Base;


public class Entity {
	
	public Base pBase;
	private boolean isBlocking = true;
	
	public void tick(){};
	
    public final void init(Base pBase) {
        this.pBase = pBase;
        init();
    }

    public void init() {
    }

	
	public void render(Graphics g) {

	}
	
	public boolean isAlive() {
		return false;
	}
	
    public final boolean blocks(Entity e) {
        return isBlocking && e.isBlocking && shouldBlock(e) && e.shouldBlock(this);
    }

    protected boolean shouldBlock(Entity e) {
        return true;
    }
    
    public void handleCollision(Entity entity, double xa, double ya) {
        if (this.blocks(entity)) {
            this.collide(entity, xa, ya);
            entity.collide(this, -xa, -ya);
        }
    }
	
    public void collide(Entity entity, double xa, double ya) {
    }

//    public void hurt(Projectile p) {
//    }

}
