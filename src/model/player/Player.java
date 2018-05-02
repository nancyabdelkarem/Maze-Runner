package model.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import View.Animation;
import Control.Handler;
import View.ImageLoader;
import View.SpriteSheet;
import Control.World;
import model.MazeGenerator;

public class Player extends Creature {
	private SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/Images/mychar.jpg"));
	private Animation animation;
	private BufferedImage[] forwardSpirite;
	private BufferedImage[] backwardSpirite;
	private BufferedImage[] leftSpirite;
	private BufferedImage[] rightSpirite;
	static char dir = 'R';

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		bounds.x = 32;
		bounds.y = 50;
		bounds.width = 15;
		bounds.height = 27;

		forwardSpirite = new BufferedImage[8];
		backwardSpirite = new BufferedImage[8];
		leftSpirite = new BufferedImage[8];
		rightSpirite = new BufferedImage[8];

		for (int i = 0; i < forwardSpirite.length; i++) {
			forwardSpirite[i] = sheet1.crop(i * 60, 0, 60, 60);
		}
		for (int i = 0; i < leftSpirite.length; i++) {
			leftSpirite[i] = sheet1.crop(i * 60, 60, 60, 60);
		}
		for (int i = 0; i < rightSpirite.length; i++) {
			rightSpirite[i] = sheet1.crop(i * 60, 120, 60, 60);
		}
		for (int i = 0; i < backwardSpirite.length; i++) {
			backwardSpirite[i] = sheet1.crop(i * 60, 180, 60, 60);
		}
		animation = new Animation();
	}

	@Override
	public void tick() {
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);

	}

	@SuppressWarnings("static-access")
	public void getInput() {
		xMove = 0;
		yMove = 0;
		
		if (handler.getKeyManager().up) {
			animation.setFrames(backwardSpirite);
			animation.setDelay(100);
			yMove = -currentSpeed;
			dir = 'L';
		}
		if (handler.getKeyManager().down) {
			animation.setFrames(forwardSpirite);
			animation.setDelay(100);
			yMove = currentSpeed;
			dir = 'R';
		}
		if (handler.getKeyManager().left) {
			animation.setFrames(leftSpirite);
			animation.setDelay(100);
			xMove = -currentSpeed;
			dir = 'U';
		}
		if (handler.getKeyManager().right) {
			animation.setFrames(rightSpirite);
			animation.setDelay(100);
			xMove = currentSpeed;
			dir = 'D';
		}

		animation.update();

	}

	public static void fire() {
		MazeGenerator maze = World.getMaze();
		maze.weapon(getDir());
		World.loadWorld();
	}

	public static char getDir() {
		return dir;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(animation.getImage(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), 80, 80, null);
	}

}
