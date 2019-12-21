package eg.edu.alexu.csd.oop.objects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public class bonusObj extends fallingObject {
    private static final int MAX_MSTATE = 8;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean visible;



    public bonusObj(int posX, int posY){
        this.x = posX;
        this.y = posY;
        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
            for(int i=0;i<4;i++){
                spriteImages[i] = ImageIO.read(bonusObj.class.getResource("/res/Images/bonus"+i+".png"));
            }
            for(int i=4;i<8;i++){
                spriteImages[i] = ImageIO.read(bonusObj.class.getResource("/res/Images/bonus"+(7-i)+".png"));
            }


        } catch (IOException e) {
//            e.printStackTrace();
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
        bonusObj a=new bonusObj(this.x,this.y);
        a.setVisible(this.isVisible());
        return a;
    }

    @Override
    public int getSerial() {
        return -1;
    }


}
