package Control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;
import View.Assets;
import View.Display;
import View.ImageLoader;
import View.Splash;
import model.runner.Runner;

public class myGame implements Runnable {
	private Display display;
	private int width, height;
	private long startTime = 0;
	private long endTime = 0;
	private static long time = 0;

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	String title;
	private Thread thread;
	private boolean running = false;
	int x = 0;
	private BufferStrategy bs;
	private Graphics g;
	private KeyManager keyManager;
	private MouseInput mouseManager;
	private GameCamera gameCamera;
	private Handler handler;
	static Clip clip = null;
	static int clipSize;
	ArrayList<ArrayList<Character>> maze;
	int widt = 0;
	int lent = 0;
	public State gameState;
	public State menuState;
	Runner player = Runner.getInstance();

	public myGame(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseInput();
		startTime = System.nanoTime();
		Assets.init();

	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getCanvas().setBackground(new Color(204, 179, 255));
		display.getCanvas().addMouseListener(mouseManager);
		final BufferedImage image = ImageLoader.loadImage("/Images/splash.jpg");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Splash(image, 6000);
			}
		});
		try {
			clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(myGame.class.getResourceAsStream("/Images/music.WAV"));
			clip.open(inputStream);
			myGame.clipSize = (int) (clip.getMicrosecondLength() / 1000);
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(gameState);
	}

	private void tick() {
		KeyManager.tick();
		// maze.play();
		if (State.getState() != null) {
			State.getState().tick();
		}
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		if (State.getState() != null) {
			State.getState().render(g);
		}
		bs.show();
		g.dispose();
	}

	@Override
	public void run() {
		init();
		int fbs = 60;
		double timePerTick = 1000000000 / fbs;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				delta--;
			}
		}
		stop();
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	/* start the thread */
	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/* stop the thread */
	public synchronized void stop() {
		if (!running) {
			return;
		}
		if (!player.isLife()) {
			final BufferedImage image = ImageLoader.loadImage("/Images/lose.png");
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new Splash(image, 9000);
				}
			});
		} else {
			final BufferedImage image = ImageLoader.loadImage("/Images/win.png");
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new Splash(image, 9000);
				}
			});
		}
		running = false;
		endTime = System.nanoTime();
		time = (endTime - startTime) / 1000000000;
		System.out.println(time);
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static long getTime() {
		return time;
	}

}
