package View;

import java.awt.image.BufferedImage;

public class Assets {
	public static BufferedImage wall1, wall2, wall3;
	public static BufferedImage floor1, floor2, floor3;
	public static BufferedImage bar1, bar2, bar3;
	public static BufferedImage gate;
	public static BufferedImage bomb1;
	public static BufferedImage bomb2;
	public static BufferedImage gift1;
	public static BufferedImage gift2;
	public static BufferedImage tree;

	public static BufferedImage[] btns;

	public static void init() {

		SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/Images/assets.png"));
		SpriteSheet sheet3 = new SpriteSheet(ImageLoader.loadImage("/Images/gate.png"));
		SpriteSheet sheet4 = new SpriteSheet(ImageLoader.loadImage("/Images/bomb.png"));
		SpriteSheet sheet5 = new SpriteSheet(ImageLoader.loadImage("/Images/gift2.png"));
		SpriteSheet sheet6 = new SpriteSheet(ImageLoader.loadImage("/Images/tree.png"));

		btns = new BufferedImage[3];
		btns[0] = ImageLoader.loadImage("/Images/easy.png");
		btns[1] = ImageLoader.loadImage("/Images/medium.png");
		btns[2] = ImageLoader.loadImage("/Images/hard.png");
		gate = sheet3.crop(10, 65, 80, 40);
		wall1 = sheet2.crop(3, 203, 35, 35);
		wall2 = sheet2.crop(40, 200, 40, 40);
		wall3 = sheet2.crop(80, 200, 40, 40);
		floor1 = sheet2.crop(162, 242, 36, 36);
		floor2 = sheet2.crop(200, 240, 40, 40);
		floor3 = sheet2.crop(240, 240, 40, 40);
		bar1 = sheet2.crop(320, 200, 40, 40);
		bar2 = sheet2.crop(360, 200, 40, 40);
		bar3 = sheet2.crop(400, 200, 40, 40);
		bomb1 = sheet4.crop(0, 110, 90, 90);
		bomb2 = sheet4.crop(0, 200, 75, 75);
		gift1 = sheet5.crop(0, 5, 50, 46);
		gift2 = sheet5.crop(0, 200, 50, 46);
		tree = sheet6.crop(0, 170, 130, 100);

	}
}