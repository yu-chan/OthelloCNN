import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Convolution {
	static final float KEISU = 1.0f / 9.0f;//畳み込み係数
	//畳み込み行列
	float[][] cMat = {{KEISU, KEISU, KEISU},
						{KEISU, KEISU, KEISU}
						{KEISU, KEISU, KEISU}};

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
			//畳み込みをするために、幅・高さにそれぞれ2足してる
			writeImage = new BufferedImage(w + 2, h + 2, BufferedImage.TYPE_INT_RGB);
			// writeImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			// writeResult = ImageIO.write(writeImage, "jpg", new File("result.jpg"));
			// writeResult = ImageIO.write(writeImage, "jpg", readImage);
		} catch (Exception e) {
			System.out.println("Not Write");
			return;
		}

		//まず、読み込んだ画像に外回りを追加したものを用意する
		for(int y = 0; y < h + 2; y++) {
			for(int x = 0; x < w + 2; x++) {
				if(y == 0 || y == h + 1 || x == 0 || x == w + 1) {
					writeImage.setRGB(x, y, 0);
				} else {
					int c = readImage.getRGB(x - 1, y - 1);
					writeImage.setRGB(x - 1, y - 1, c);
				}
				/*int c = readImage.getRGB(x, y);
				int r = 255 - r(c);
				int g = 255 - g(c);
				int b = 255 - b(c);
				int rgb = rgb(r, g, b);
				writeImage.setRGB(x, y, rgb);*/
			}
		}

		//畳み込み
		for(int y = 1; y < h + 1; y++) {
			for(int x = 1; x < w + 1; x++) {
				float wa = readImage.getRGB(x - 1, y - 1) * cMat[0][0] + readImage.getRGB(x, y - 1) * cMat[0][1] + readImage.getRGB(x + 1, y - 1) * cMat[0][2]
						   readImage.getRGB(x - 1, y    ) * cMat[1][0] + readImage.getRGB(x, y    ) * cMat[1][1] + readImage.getRGB(x + 1, y    ) * cMat[1][2]
						   readImage.getRGB(x - 1, y + 1) * cMat[2][0] + readImage.getRGB(x, y + 1) * cMat[2][1] + readImage.getRGB(x + 1, y + 1) * cMat[2][2];
				writeImage.setRGB(x, y, wa);
			}
		}

		//処理し終わったものを、元の大きさに戻す
		BufferedImage writeImage2;
		try {
			writeImage2 = nwe BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		} catch(Exception e) {
			return;
		}
		for(int y = 1; y < h + 1; y++) {
			for(int x = 1; x < w + 1; x++) {
				writeImage2.setRGB(x - 1, y - 1, writeImage.getRGB(x, y));
			}
		}

		try {
			writeResult = ImageIO.write(writeImage2, "jpg", new File("result2.jpg"));
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