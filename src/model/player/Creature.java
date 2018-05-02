package model.player;

import Control.Handler;
import Control.World;
import model.MazeGenerator;
import model.tiles.Tile;
import model.walls.GameObject;

public abstract class Creature extends Entity {

//	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 6.0f;
	protected int health;
	protected static float currentSpeed;
	protected float xMove, yMove;
	public static final int DEFAULT_CREATURE_WIDTH = 58;
	public static final int DEFAULT_CREATURE_HEIGHT = 58;
	public Handler handler;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		currentSpeed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		this.handler = handler;

	}

	/* Move creature */
	public void move() {
		moveX();
		moveY();

	}

	public void moveX() {
		if (xMove > 0) {// right
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
			if (!collisionWithTile(tx, (int) ((y + bounds.y + bounds.height)) / Tile.TILE_LENGTH)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_LENGTH)) {
				if (!collisionWithTree(tx, (int) ((y + bounds.y + bounds.height)) / Tile.TILE_LENGTH)
						&& !collisionWithTree(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_LENGTH)) {
					x += xMove;
					MazeGenerator maze = World.getMaze();
					maze.play(handler,tx, (int) ((y + bounds.y + bounds.height)) / Tile.TILE_LENGTH);

					maze.print();
					World.loadWorld();
				}

			} else {
				x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
			}
		} else if (xMove < 0) {// left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;

			if (!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_LENGTH)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_LENGTH)) {
				if (!collisionWithTree(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_LENGTH)
						&& !collisionWithTree(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_LENGTH)) {
					x += xMove;
					MazeGenerator maze = World.getMaze();
					maze.play(handler,tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_LENGTH);
					maze.print();
					World.loadWorld();

				}
			} else {
				x = tx * Tile.TILE_WIDTH - bounds.x + Tile.TILE_WIDTH;
			}

		}

	}

	public void moveY() {
		if (yMove < 0) {// Up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILE_LENGTH;

			if (!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				if (!collisionWithTree((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)
						&& !collisionWithTree((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
					y += yMove;
					MazeGenerator maze = World.getMaze();
					maze.play(handler,(int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty);
					World.loadWorld();
					maze.print();
				}
			} else {
				// x = tx * Tile.TILE_WIDTH;
				y = ty * Tile.TILE_LENGTH + Tile.TILE_LENGTH - bounds.y;
			}
		} else if (yMove > 0) {// Down
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_LENGTH;

			if (!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				if (!collisionWithTree((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)
						&& !collisionWithTree((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
					y += yMove;
					MazeGenerator maze = World.getMaze();
					maze.play(handler,(int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty);
					maze.print();
					World.loadWorld();
				}
			} else {
				y = ty * Tile.TILE_LENGTH - bounds.y - bounds.height - 1;
			}
		}
		// y+= yMove;
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	protected boolean collisionWithTree(int x, int y) {
		GameObject o = handler.getWorld().getObject(x, y);
		if (o != null) {
			return handler.getWorld().getObject(x, y).isSolid();
		}
		return false;
	}

	protected boolean checkObject(int x, int y) {
		GameObject o = handler.getWorld().getObject(x, y);
		if (o != null) {
			return true;
		}
		return false;
	}

	/* GETTERS AND SETTERS */
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public static float getSpeed() {
		return currentSpeed;
	}

	public static void setSpeed(float speed) {
		currentSpeed = speed;
	}

}
