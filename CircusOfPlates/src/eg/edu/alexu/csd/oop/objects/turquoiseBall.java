package eg.edu.alexu.csd.oop.objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class turquoiseBall extends fallingObject{
    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int Serial=7 ;


    public turquoiseBall(){
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
            spriteImages[0] = ImageIO.read(new File("CircusPlates"+ System.getProperty("file.separator") + "res" +System.getProperty("file.separator") + "Images"+ System.getProperty("file.separator")  +"turquoiseeball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public turquoiseBall clone(){
        turquoiseBall op= new turquoiseBall();
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
