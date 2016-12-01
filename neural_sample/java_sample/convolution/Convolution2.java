import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;

/*import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;*/

public class Convolution2 {
	 public static void main(String args[]) throws Exception {
       /* Java2Dで画像をぼかす */
       BufferedImage img = ImageIO.read(new File("building.jpg"));
       BufferedImage img2 = new BufferedImage(
           img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB
       );
       Graphics2D gr = img2.createGraphics();
       gr.drawImage(img,0,0,null);
       gr.dispose();

       float kernel[] = new float[3*3];
       for(int li=0;li<3*3;li++){
           kernel[li] = 1F/(3*3);
       }

       BufferedImageOp bio = new ConvolveOp(new Kernel(3, 3, kernel),
           ConvolveOp.EDGE_NO_OP, new RenderingHints(new HashMap()));
       BufferedImage img3 = bio.filter(img2, null);
       ImageIO.write(img3, "jpg", new File("sample511a.jpg"));

   }
}