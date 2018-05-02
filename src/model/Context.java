package model;

import Control.Handler;

public class Context {
	private final Strategy strategy;

	public Context(final Strategy strategy) {
		this.strategy = strategy;
	}

	public void executeStrategy(final Character dir) {
		strategy.useWeapon(dir);
	}

	public void executeStrategy4(Handler handler, int x, int y) {
		strategy.move(handler, x, y);
	}

	public void executeStrategyStart() {
		strategy.start();
	}
}
