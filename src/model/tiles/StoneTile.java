package model.tiles;

import View.Assets;

public class StoneTile extends Tile {

	public StoneTile(int id) {
		super(Assets.wall1, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
