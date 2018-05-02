package model.walls;

import View.Assets;

public class Gate extends GameObject {
	private static Gate gate;
	public Gate(int id) {
		super(Assets.gate, id);
	}
	public static Gate getInstance(int id) {
		if (gate == null) {
			gate = new Gate(id);
		}
		return gate;
	}
}
