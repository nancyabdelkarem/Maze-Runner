package Control;

import java.awt.Graphics;
import java.util.ArrayList;

import model.MazeGenerator;
import model.bombs.BombsFirstType;
import model.bombs.BombsSecondType;
import model.gifts.GiftsFirstType;
import model.gifts.GiftsSecondType;
import model.tiles.Tile;
import model.walls.GameObject;
import model.walls.Trees;

public class World {
	Handler handler;
	private static int width;
	private static int height;
	private static char[][] tiles;
	private static ArrayList<ArrayList<Character>> mazee;
	public static MazeGenerator maze;
//	long time = myGame.getTime();

	@SuppressWarnings("static-access")
	public World(Handler handler) {
		int x = 17;
		int y = 17;
		// trees = 30;
		// gifts1 = 6;
		// gifts2 = 6;
		// bombs1 = 8;
		// bombs2 = 8;
		int trees = 10;
		int gifts1 = 10;
		int gifts2 = 10;
		int bombs1 = 15;
		int bombs2 = 15;
		maze = new MazeGenerator(handler, x, y);
		Trees tree = Trees.getInstance(3);
		GiftsFirstType gift1 = GiftsFirstType.getInstance(0);
		GiftsSecondType gift2 = GiftsSecondType.getInstance(4);
		BombsFirstType bomb1 = BombsFirstType.getInstance(1);
		BombsSecondType bomb2 = BombsSecondType.getInstance(5);
		tree.setTreesNum(trees);
		gift1.setGiftsNum(gifts1);
		gift2.setGiftsNum(gifts2);
		bomb1.setBombsNum(bombs1);
		bomb2.setBombsNum(bombs2);
		maze.display();
		// MazeGenerator g = new MazeGenerator(20, 20);
		// g.display();
		mazee = maze.maze1;
		loadWorld();
		this.handler = handler;

	}

	public void tick() {

	}

	public void render(Graphics g) {
		// loadWorld();
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_LENGTH);
		int xEnd = (int) Math.min(width,
				(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yEnd = (int) Math.min(height,
				(handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_LENGTH + 1);

		for (int i = xStart; i < xEnd; i++) {
			for (int j = yStart; j < yEnd; j++) {
				getTile(i, j).render(g, (int) (i * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int) (j * Tile.TILE_LENGTH - handler.getGameCamera().getyOffset()));
				GameObject o = getObject(i, j);
				if (o != null) {
					// loadWorld();
					o.render(g, (int) (i * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
							(int) (j * Tile.TILE_LENGTH - handler.getGameCamera().getyOffset()));
				}

			}
		}

	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.floorTile;
		}
		Tile t = null;
		if (tiles[x][y] == 'W') {
			t = Tile.tiles[2];
		} else {
			t = Tile.tiles[0];
		}
		if (t == null) {
			return Tile.stoneTile;
		}
		return t;
	}

	public GameObject getObject(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return GameObject.Gift;
		}
		GameObject t = null;
		if (tiles[x][y] == 'H') {
			t = GameObject.objects[5];
		} else if (tiles[x][y] == 'B') {
			t = GameObject.objects[1];
		} else if (tiles[x][y] == 'G') {
			t = GameObject.objects[0];
		} else if (tiles[x][y] == 'J') {
			t = GameObject.objects[4];
		} else if (tiles[x][y] == 'T') {
			t = GameObject.objects[3];
		} else if (tiles[x][y] == 'P') {
			t = GameObject.objects[2];
		}

		return t;
	}

	public static void loadWorld() {
		width = mazee.size();
		height = mazee.size();
		tiles = new char[width][height];
		for (int i = 0; i < mazee.size(); i++) {
			for (int j = 0; j < mazee.size(); j++) {
				char x1 = mazee.get(i).get(j);
				if (x1 == 'H') {
					tiles[i][j] = 'H';
				} else if (x1 == 'B') {
					tiles[i][j] = 'B';
				} else if (x1 == 'G') {
					tiles[i][j] = 'G';
				} else if (x1 == 'J') {
					tiles[i][j] = 'J';
				} else if (x1 == 'T') {
					tiles[i][j] = 'T';
				} else if (x1 == '+') {
					if (j == 1 && i == 0) {
						tiles[i][j] = 'P';
					} else if (j == mazee.size() - 2 && i == mazee.size() - 1) {
						tiles[i][j] = 'P';
					} else {
						tiles[i][j] = 'W';
					}
				} else {
					tiles[i][j] = ' ';
				}
			}
		}

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static MazeGenerator getMaze() {
		return maze;
	}

}
