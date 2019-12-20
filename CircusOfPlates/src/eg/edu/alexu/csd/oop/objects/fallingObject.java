package eg.edu.alexu.csd.oop.objects;

import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class fallingObject implements GameObject, Cloneable{

	private boolean visible;
	private int x, y;
	
	public fallingObject() {
		this.visible = true;
	}

	public abstract int getHeight();

	public abstract BufferedImage[] getSpriteImages();

	public abstract int getWidth();

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisible() {
		return this.visible;
	}

	public void setX(int arg0) {
		this.x = arg0;
	}

	public void setY(int arg0) {
		this.y = arg0;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public abstract GameObject clone();
	
	public abstract int getSerial();
    
}
