package model.walls;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Control.Handler;
import model.MazeGenerator;
import model.bombs.BombsFirstType;
import model.bombs.BombsSecondType;
import model.gifts.GiftsFirstType;
import model.gifts.GiftsSecondType;

public class GameObject {
	static Handler handler = MazeGenerator.getHandler();
	public static GameObject[] objects = new GameObject[265];
	public static GameObject Gift = GiftsFirstType.getInstance(0);
	public static GameObject secondGift = GiftsSecondType.getInstance(4);
	public static GameObject secondBomb = BombsSecondType.getInstance(5);
	public static GameObject Bomb = BombsFirstType.getInstance(1);
	public static GameObject gate = Gate.getInstance(2);
	public static GameObject tree = Trees.getInstance(3);
	public static final int TILE_WIDTH = 42;
	public static final int TILE_LENGTH = 42;
	protected BufferedImage texture;
	protected int id = 0;

	public GameObject(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		objects[id] = this;
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_LENGTH, null);
	}

	public boolean isSolid() {
		return false;
	}

	public int getID() {
		return id;
	}

	public void random(ArrayList<ArrayList<Character>> maze1) {
	}

	public boolean damage() {
		return false;
	}

	public void setTreesNum(int TREE_NUMBER) {
	}
}
