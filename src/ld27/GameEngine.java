package ld27;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ld27.util.ImageLoader;

public class GameEngine extends Canvas implements KeyListener, Runnable {
	public static final int	WIDTH			= 1024;
	public static final int	HEIGHT			= 656;

	public static long		currentFPS;
	public static final int	FPS_TARGET		= 120;
	public static final int	DELTA_TARGET	= 1000 / FPS_TARGET;

	private boolean			jump1;
	private boolean			jump2;

	public static void main(String[] args) {
		GameEngine engine = new GameEngine();
		JFrame frame = new JFrame("Game");
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(engine);
		frame.setContentPane(panel);
		frame.pack();
		frame.setResizable(false);
		// frame.setLocationRelativeTo(null);
		frame.setLocation(2000, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		engine.start();
	}


	public GameEngine() {

		Dimension dim = new Dimension(WIDTH - 10, HEIGHT - 10);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.addKeyListener(this);
	}

	public void start() {
		Thread t = new Thread(this);
		ImageLoader.init();
		t.start();
	}

	@Override
	public void run() {
		long delta = 0;

		while (true) {
			long start = System.currentTimeMillis();

			update((int) delta);
			BufferStrategy bs = getBufferStrategy();
			if (bs == null) {
				createBufferStrategy(3);
				continue;
			}
			render(bs.getDrawGraphics());

			if (bs != null)
				bs.show();

			long timepassed = System.currentTimeMillis() - start;
			if (timepassed < DELTA_TARGET) {
				try {
					Thread.sleep(DELTA_TARGET - timepassed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			delta = System.currentTimeMillis() - start;
			if (delta != 0)
				currentFPS = 1000 / delta;
		}

	}

	private void update(int delta) {
		Game.g.update(delta);
	}

	private void render(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.red);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		Game.g.render(g);

		g.setColor(Color.yellow);
		g.setFont(getFont());
		g.drawString("FPS: " + currentFPS, WIDTH - 80, 20);

		g.dispose();
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void keyPressed(KeyEvent e) {


		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			Game.VK_UP = true;
			break;
		case KeyEvent.VK_DOWN:
			Game.VK_DOWN = true;
			break;
		case KeyEvent.VK_LEFT:
			Game.VK_LEFT = true;
			break;
		case KeyEvent.VK_RIGHT:
			Game.VK_RIGHT = true;
			break;
		case KeyEvent.VK_W:
			Game.VK_W = true;
			break;
		case KeyEvent.VK_A:
			Game.VK_A = true;
			break;
		case KeyEvent.VK_CONTROL:
			if (!jump2)
				Game.VK_CTRL = true;
			jump2 = true;
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			Game.VK_UP = false;
			break;
		case KeyEvent.VK_DOWN:
			Game.VK_DOWN = false;
			break;
		case KeyEvent.VK_LEFT:
			Game.VK_LEFT = false;
			break;
		case KeyEvent.VK_RIGHT:
			Game.VK_RIGHT = false;
			break;
		case KeyEvent.VK_W:
			Game.VK_W = false;
			break;
		case KeyEvent.VK_A:
			Game.VK_A = false;
			break;
		case KeyEvent.VK_CONTROL:
			Game.VK_CTRL = false;
			jump2 = false;
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyChar());
	}
}
