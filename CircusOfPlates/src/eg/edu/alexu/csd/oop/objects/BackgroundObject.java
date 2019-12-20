package eg.edu.alexu.csd.oop.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public class BackgroundObject implements GameObject, Cloneable{
	
	private int x, y;
	private BufferedImage[] bg;
	private String path;
	
	public BackgroundObject(int x, int y, String path) {
		this.path=path;
		setX(x);
		setY(y);
		bg = new BufferedImage[1];
		try {
			bg[0] = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public BackgroundObject clone(){
		BackgroundObject a=new BackgroundObject(getX(),getY(),path);
		return a;
	}
	public int getHeight() {
		return 640;
	}

	public BufferedImage[] getSpriteImages() {
		return bg;
	}

	public int getWidth() {
		return 640;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisible() {
		return true;
	}

	public void setX(int arg0) {
		this.x = arg0;
	}

	public void setY(int arg0) {
		this.y = arg0;
	}
	
	public String getPath() {
		return path;
	}

}
