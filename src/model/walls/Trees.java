package model.walls;

import java.util.ArrayList;
import View.Assets;
import model.GetRandom;
import model.iterator.RandomNumbers;

public class Trees extends GameObject {
	private int TREE_NUMBER = 0;
	ArrayList<ArrayList<Character>> maze1 = new ArrayList<ArrayList<Character>>();
	private static Trees tree;

	public Trees(int id) {
		super(Assets.tree, id);
	}

	public boolean isSolid() {
		return true;
	}

	public static Trees getInstance(int id) {
		if (tree == null) {
			tree = new Trees(id);
		}
		return tree;
	}

	@Override
	public void random(ArrayList<ArrayList<Character>> maze1) {
		RandomNumbers trees = new RandomNumbers(TREE_NUMBER);
		this.maze1 = maze1;
		GetRandom bomb = new GetRandom();
		bomb.random(maze1, trees, 'T');
	}

	@Override
	public boolean damage() {
		return true;
	}

	@Override
	public void setTreesNum(int TREE_NUMBER) {
		this.TREE_NUMBER = TREE_NUMBER;
	}
}
