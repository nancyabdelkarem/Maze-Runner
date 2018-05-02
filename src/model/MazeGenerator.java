package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import Control.Handler;
import model.bombs.BombsFirstType;
import model.bombs.BombsSecondType;
import model.gifts.GiftsFirstType;
import model.gifts.GiftsSecondType;
import model.runner.Runner;
import model.runner.Weapon;
import model.walls.Trees;

public class MazeGenerator {
	private final int x;
	private final int y;
	private final int[][] maze;
	public static ArrayList<ArrayList<Character>> maze1 = new ArrayList<ArrayList<Character>>();
	// Scanner sc = new Scanner(System.in);
	final Context context = new Context(new Play(maze1));
	Point point;
	char dir = 'R'; // direction Right
	Play play;
	static Handler handler;

	@SuppressWarnings("static-access")
	public MazeGenerator(Handler handler, final int x, final int y) {
		this.x = x;
		this.y = y;
		maze = new int[this.x][this.y];
		generateMaze(0, 0);
		this.handler = handler;
	}

	Runner player = Runner.getInstance();
	Weapon weapon = Weapon.getInstance();
	final Trees trees = Trees.getInstance(3);
	final BombsFirstType bomb1 = BombsFirstType.getInstance(1);
	final BombsSecondType bomb2 = BombsSecondType.getInstance(5);
	final GiftsFirstType gift1 = GiftsFirstType.getInstance(0);
	final GiftsSecondType gift2 = GiftsSecondType.getInstance(4);

	public void display() {
		for (int i = 0; i < y; i++) {
			// draw the north edge
			ArrayList<Character> row = new ArrayList<Character>();
			for (int j = 0; j < x; j++) {
				if ((maze[j][i] & 1) == 0) {
					row.add('+');
					row.add('+');
				} else {
					row.add('+');
					row.add(' ');
				}
			}
			row.add('+');
			maze1.add(row);
			// draw the west edge
			row = new ArrayList<Character>();
			for (int j = 0; j < x; j++) {
				if ((maze[j][i] & 8) == 0) {
					row.add('+');
					row.add(' ');
				} else {
					row.add(' ');
					row.add(' ');
				}
			}
			row.add('+');
			maze1.add(row);
		}
		// draw the bottom line
		final ArrayList<Character> row = new ArrayList<Character>();
		for (int j = 0; j < x; j++) {
			row.add('+');
			row.add('+');
		}
		row.add('+');
		maze1.add(row);
		trees.random(maze1);
		bomb1.random(maze1);
		bomb2.random(maze1);
		gift1.random(maze1);
		gift2.random(maze1);
		context.executeStrategyStart();
		print();
	}

	public void weapon(char dir) {
		context.executeStrategy(dir);
	}

	public void play(Handler handler, int x, int y) {
		if (lose()) {
			handler.getGame().stop();
		}
		context.executeStrategy4(handler, x, y);

	}

	public void print() {
		for (int x = 0; x < maze1.size(); x++) {
			for (int y = 0; y < maze1.get(0).size(); y++) {
				System.out.print(maze1.get(x).get(y));
			}
			System.out.println();
		}
		System.out.println("lives  " + player.getLives());
		System.out.println("scores  " + player.getScores());
		System.out.println("bullets  " + weapon.getBullets());
		System.out.println("life   " + player.isLife());
		System.out.println("number of gift1  " + player.getNumFirstTypeGift());
		System.out.println("number of gift2   " + player.getNumSecondTypeGift());
		// System.out.println("number of bomb1 "+factory.numRemainedBomb1);
		// .out.println("number of bomb2 "+factory.numRemainedBomb2);
		System.out.println("===================================");
	}

	private void generateMaze(final int cx, final int cy) {
		final DIR[] dirs = DIR.values();
		Collections.shuffle(Arrays.asList(dirs));// for randomization
		for (final DIR dir : dirs) {
			final int nx = cx + dir.dx;
			final int ny = cy + dir.dy;
			if (between(nx, x) && between(ny, y) && (maze[nx][ny] == 0)) {
				// to add a direction to an existing block:
				maze[cx][cy] |= dir.bit; // b1 |= b2 >> b1 = b1 | b2
				maze[nx][ny] |= dir.opposite.bit;
				generateMaze(nx, ny);
			}
		}
	}

	private static boolean between(final int v, final int upper) {
		return (v >= 0) && (v < upper);
	}

	private enum DIR {
		N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
		private final int bit;
		private final int dx;
		private final int dy;
		private DIR opposite;

		// use the static initializer to resolve forward references
		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}

		private DIR(final int bit, final int dx, final int dy) {
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	};

	public Runner getRunner() {
		return player;
	}

	public boolean lose() {
		return !player.isLife();
	}

	public static ArrayList<ArrayList<Character>> getMaze() {
		return maze1;
	}

	@SuppressWarnings("static-access")
	public void setMaze(ArrayList<ArrayList<Character>> maze1) {
		this.maze1 = maze1;
	}

	public static Handler getHandler() {
		return handler;
	}

}
