import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Convolution {
	public static void main(String args[]) {
		File f = new File("xy_regend.jpg");
		BufferedImage readImage;

		try {
			readImage = ImageIO.read(f);
		} catch (Exception e) {
			System.out.println("Not Read");
			return;
		}

		int w = readImage.getWidth();
		int h = readImage.getHeight();

		BufferedImage writeImage;
		boolean writeResult;
		
		try {
			writeImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			// writeResult = ImageIO.write(writeImage, "jpg", new File("result.jpg"));
			// writeResult = ImageIO.write(writeImage, "jpg", readImage);
		} catch (Exception e) {
			System.out.println("Not Write");
			return;
		}

		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				int c = readImage.getRGB(x, y);
				int r = 255 - r(c);
				int g = 255 - g(c);
				int b = 255 - b(c);
				int rgb = rgb(r, g, b);
				writeImage.setRGB(x, y, rgb);
			}
		}

		try {
			writeResult = ImageIO.write(writeImage, "jpg", new File("result.jpg"));
		} catch (Exception e) {
		}

		// File f2 = new File("ret.jpg");
		// ImageIO.write(write, "jpg", f2);
	}

	public static int a(int c) {
		return c >>> 24;
	}

	public static int r(int c) {
		return c>>16&0xff;
	}

	public static int g(int c) {
		return c>>8&0xff;
	}

	public static int b(int c) {
		return c>>4&0xff;
	}

	public static int rgb(int r, int g, int b) {
		return 0xff000000 | r << 16 | g << 8 | b;
	}

	public static int argb(int a, int r, int g, int b) {
		return a << 24 | r << 16 | g << 8 | b;
	}
}