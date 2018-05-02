package model.gifts;

import java.util.ArrayList;

import View.Assets;
import Control.Handler;
import model.Context1;
import model.GetRandom;
import model.iterator.RandomNumbers;
import model.player.Player;
import model.runner.Runner;

public class GiftsFirstType extends Gifts{

	private int GIFT_NUMBER = 0;
	ArrayList<ArrayList<Character>> maze1=new ArrayList<ArrayList<Character>>();
	Handler handler;
	
	private static GiftsFirstType gift1;
	public GiftsFirstType(int id) {
		super(Assets.gift2, id);
		
	}
    public static GiftsFirstType getInstance(int id) {
                                if (gift1 == null)
                              {
                                       gift1 = new  GiftsFirstType(id);
                              }
                    return gift1;
        }
    Runner player = Runner.getInstance();
	@Override
	public void random(final ArrayList<ArrayList<Character>> maze1) {
		RandomNumbers gifts = new RandomNumbers(GIFT_NUMBER);
		this.maze1 = maze1;
		final Context1 context = new Context1(new GetRandom());
		context.executeStrategy(maze1, gifts, 'G');
//		final GetRandom gift = new GetRandom();
//		gift.random(maze1, gifts, 'G');
	}
	//first type >> increase player's lives
	@Override
	public void effect(){
		final int lives = player.getLives();
		long scores = player.getScores();
		scores = scores+1 ;//to be updated
		player.setLives(lives+1);
		Player.setSpeed(Player.getSpeed() + 1);
		player.setScores(scores);
	}

	@Override
	public void setGiftsNum (int GIFT_NUMBER) {
		this.GIFT_NUMBER = GIFT_NUMBER;
	}

	@Override
	public int getGiftsNum () {
		return GIFT_NUMBER;
	}

}
