package model;

import Control.Handler;
import model.bombs.BombsFirstType;
import model.bombs.BombsSecondType;
import model.gifts.GiftsFirstType;
import model.gifts.GiftsSecondType;
import model.runner.Runner;

public class Factory {
	
	
	Handler handler = MazeGenerator.getHandler();
	Runner player = Runner.getInstance();
	BombsFirstType bomb1 = BombsFirstType.getInstance(1);
	BombsSecondType bomb2 = BombsSecondType.getInstance(5);
	int numRemainedBomb1 = bomb1.getBOMB_NUMBER();
	int numRemainedBomb2 = bomb2.getBOMB_NUMBER();

	Object getBomb(final char c) {
		if (c == 'B') {
			numRemainedBomb1--;
			return new BombsFirstType(1);
		} else {
			numRemainedBomb2--;
			return new BombsSecondType(5);
		}

	}

	Object getGift(final char c) {
		if (c == 'G') {
			player.setNumFirstTypeGift(player.getNumFirstTypeGift() + 1);
			return new GiftsFirstType(0);
		} else {
			player.setNumSecondTypeGift(player.getNumSecondTypeGift() + 1);
			return new GiftsSecondType(4);
		}
	}

}
