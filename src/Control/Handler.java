package Control;

public class Handler {

	private myGame game;
	private World world;

	public Handler(myGame game) {
		this.game = game;
	}

	public int getWidth() {
		return game.getWidth();
	}

	public int getHeight() {
		return game.getHeight();
	}

	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}

	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}

	public myGame getGame() {
		return game;
	}

	public void setGame(myGame game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
