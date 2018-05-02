package model.bombs;

import java.util.ArrayList;
import View.Assets;
import Control.Handler;
import model.Context1;
import model.GetRandom;
import model.iterator.RandomNumbers;
import model.player.Player;
import model.runner.Runner;

public class BombsFirstType extends Bombs {

	private int BOMB_NUMBER = 0;

	ArrayList<ArrayList<Character>> maze1 = new ArrayList<ArrayList<Character>>();
	Handler handler;

	private static BombsFirstType bomb1;

	public BombsFirstType(int id) {
		super(Assets.bomb1, id);

	}

	public static BombsFirstType getInstance(int id) {
		if (bomb1 == null) {
			bomb1 = new BombsFirstType(id);
		}
		return bomb1;
	}

	Runner player = Runner.getInstance();

	@Override
	public void random(final ArrayList<ArrayList<Character>> maze1) {
		RandomNumbers bombs = new RandomNumbers(BOMB_NUMBER);
		this.maze1 = maze1;
		final Context1 context = new Context1(new GetRandom());
		context.executeStrategy(maze1, bombs, 'B');
		// final GetRandom bomb = new GetRandom();
		// bomb.random(maze1, bombs, 'B');
	}

	// damage>> decrease in health
	@Override
	public void effect() {
		int lives = player.getLives();
		if (lives > 0) {
			lives--;
			Player.setSpeed(Player.getSpeed() - 1);
			player.setLives(lives);
		}
	}

	@Override
	public void setBombsNum(int BOMB_NUMBER) {
		this.BOMB_NUMBER = BOMB_NUMBER;
	}

	@Override
	public int getBOMB_NUMBER() {
		return BOMB_NUMBER;
	}

}
