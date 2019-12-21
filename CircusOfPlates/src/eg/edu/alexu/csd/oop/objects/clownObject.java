package eg.edu.alexu.csd.oop.objects;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public class clownObject implements GameObject, Cloneable{
	
	private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean visible;
    URL path = clownObject.class.getResource("/res/Images/clown.png");


    public clownObject(int posX, int posY){
        this.x = posX;
        this.y = posY;
        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
            spriteImages[0] = ImageIO.read(path);
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    public clownObject clone(){
        return new clownObject(getX(),getY());
    }


    @Override
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
	
}
