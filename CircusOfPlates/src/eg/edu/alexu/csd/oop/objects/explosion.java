package eg.edu.alexu.csd.oop.objects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public class explosion extends fallingObject {
	private static final int MAX_MSTATE = 12;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int x;
	private int y;
	private boolean visible;



	public explosion(int posX, int posY){
		this.x = posX;
		this.y = posY;
		this.visible = true;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		try {
			for(int i=1;i<=12;i++){
				spriteImages[i-1] = ImageIO.read(explosion.class.getResource("/res/Images/explosion"+i+".png"));
			}

		} catch (IOException e) {
//			e.printStackTrace();
		}
	}

	@Override//Images/explosion1.png
	public int getX() {
		return x;
	}

	@Override
	public void setX(int mX) {
		this.x = mX;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int mY) {
		this.y = mY;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth(){
		return spriteImages[0].getWidth();
	}

	@Override
	public int getHeight() {
		return spriteImages[0].getHeight();
	}

	@Override
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}

	@Override
	public GameObject clone() {
		explosion a=new explosion(this.x,this.y);
		a.setVisible(this.isVisible());
		return  a;
	}

	@Override
	public int getSerial() {
		return -1;
	}


}
