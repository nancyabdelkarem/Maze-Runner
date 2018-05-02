package Control;

import java.awt.Graphics;

import View.Assets;

public class MenuState extends State {
	private Assets assets;

	public MenuState(Handler handler) {
		super(handler);

	}

	public void tick() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("static-access")
	public void render(Graphics g) {
		
		g.drawImage(assets.btns[0], 100, 100, 500, 500, null);
		g.drawImage(assets.btns[1], 600, 100, 500, 500, null);
		g.drawImage(assets.btns[2], 1100, 100, 500, 500, null);
	}

}