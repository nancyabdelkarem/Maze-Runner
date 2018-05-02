package model;

import Control.Handler;

public interface Strategy {

	void move(Handler handler, int x, int y);

	void start();

	void useWeapon(final char dir);

}
