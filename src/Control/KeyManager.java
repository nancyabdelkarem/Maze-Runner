package Control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import model.MazeGenerator;
import model.Save;
import model.player.Player;

public class KeyManager implements KeyListener {
	private static boolean keys[];
	public static boolean up;
	public static boolean down;
	public static boolean right;
	public static boolean left;
	public static boolean space;
	public static boolean save;

	public KeyManager() {
		keys = new boolean[256];

	}

	public boolean checkWeapon() {
		return space;
	}

	public void setWeapon(boolean setWeapon) {
		space = setWeapon;
	}

	public static void tick() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		right = keys[KeyEvent.VK_RIGHT];
		left = keys[KeyEvent.VK_LEFT];
		space = keys[KeyEvent.VK_SPACE];
		save = keys[KeyEvent.VK_S];
		if (space) {
			space = false;
		}
		if (save) {
			save = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Player.fire();
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			ArrayList<ArrayList<Character>> maze1 = MazeGenerator.getMaze();
			Save save = new Save();
			save.saveMaze(maze1);
			save.saveDetails();
		}
		keys[e.getKeyCode()] = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
