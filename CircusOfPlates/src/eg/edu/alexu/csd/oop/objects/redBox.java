package eg.edu.alexu.csd.oop.objects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class redBox extends fallingObject{
    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int Serial=5 ;


    public redBox(){
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
            spriteImages[0] = ImageIO.read(redBox.class.getResource("/res/Images/redboxsmall.png"));
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
    public redBox clone(){
        redBox op= new redBox();
        op.setVisible(super.isVisible());
        op.setX(super.getX());
        op.setY(super.getY());
        return op;
    }

    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    public int getWidth(){
        return spriteImages[0].getWidth();
    }

    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    public int getSerial() {
        return Serial;
    }
    
}
