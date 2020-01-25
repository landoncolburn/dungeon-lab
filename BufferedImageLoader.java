import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BufferedImageLoader {

  private BufferedImage image;

  public BufferedImage loadImage(String path){
    try{
      image = ImageIO.read(new File(path));
    } catch(Exception e){
      e.printStackTrace();
    }
    return image;
  }
}
