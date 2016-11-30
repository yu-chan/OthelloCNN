import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Convolution {
	static float KEISU = 1.0f / 9.0f;//convolution keisu
	//convolution matrix
	static float cMat[][] = {{KEISU, KEISU, KEISU},{KEISU, KEISU, KEISU},{KEISU, KEISU, KEISU}};

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
		// BufferedImage writeImage2;
		boolean writeResult;
		
		try {
			writeImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			// writeImage2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			// writeImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			// writeResult = ImageIO.write(writeImage, "jpg", new File("result.jpg"));
			// writeResult = ImageIO.write(writeImage, "jpg", readImage);
		} catch (Exception e) {
			System.out.println("Not Write");
			return;
		}

		// for(int y = 0; y < h; y++) {
		// 	for(int x = 0; x < w + 2; x++) {
		// 		if(y == 0 || y == h + 1 || x == 0 || x == w + 1) {
		// 			writeImage.setRGB(x, y, 0);
		// 		} else {
		// 			int c = readImage.getRGB(x - 1, y - 1);
		// 			writeImage.setRGB(x - 1, y - 1, c);
		// 		}
		// 		/*int c = readImage.getRGB(x, y);
		// 		int r = 255 - r(c);
		// 		int g = 255 - g(c);
		// 		int b = 255 - b(c);
		// 		int rgb = rgb(r, g, b);
		// 		writeImage.setRGB(x, y, rgb);*/
		// 	}
		// }

		//convolution
		for(int y = 1; y < h - 1; y++) {
			for(int x = 1; x < w - 1; x++) {
				/*float wa = writeImage.getRGB(x - 1, y - 1) * cMat[0][0] + writeImage.getRGB(x, y - 1) * cMat[0][1] + writeImage.getRGB(x + 1, y - 1) * cMat[0][2] +
						   writeImage.getRGB(x - 1, y    ) * cMat[1][0] + writeImage.getRGB(x, y    ) * cMat[1][1] + writeImage.getRGB(x + 1, y    ) * cMat[1][2] +
						   writeImage.getRGB(x - 1, y + 1) * cMat[2][0] + writeImage.getRGB(x, y + 1) * cMat[2][1] + writeImage.getRGB(x + 1, y + 1) * cMat[2][2];
				writeImage2.setRGB(x, y, (int)wa);*/
				float wa = readImage.getRGB(x - 1, y - 1) * cMat[0][0] + readImage.getRGB(x, y - 1) * cMat[0][1] + readImage.getRGB(x + 1, y - 1) * cMat[0][2] +
						   readImage.getRGB(x - 1, y    ) * cMat[1][0] + readImage.getRGB(x, y    ) * cMat[1][1] + readImage.getRGB(x + 1, y    ) * cMat[1][2] +
						   readImage.getRGB(x - 1, y + 1) * cMat[2][0] + readImage.getRGB(x, y + 1) * cMat[2][1] + readImage.getRGB(x + 1, y + 1) * cMat[2][2];
				writeImage.setRGB(x, y, (int)wa);
			}
		}

		//original big
		/*BufferedImage writeImage3;
		try {
			writeImage3 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		} catch(Exception e) {
			return;
		}
		for(int y = 1; y < h + 1; y++) {
			for(int x = 1; x < w + 1; x++) {
				writeImage3.setRGB(x - 1, y - 1, writeImage2.getRGB(x, y));
			}
		}*/

		try {
			writeResult = ImageIO.write(writeImage, "jpg", new File("result3.jpg"));
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