package model;

import java.util.ArrayList;

import model.iterator.RandomNumbers;

public class Context1 {
	private final Strategy1 strategy;

	public Context1(final Strategy1 strategy1) {
		this.strategy = strategy1;
	}

	public void executeStrategy(final ArrayList<ArrayList<Character>> maze1, final RandomNumbers num, final char c){
		strategy.random(maze1, num, c);;
	}

}
