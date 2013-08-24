package ld27;

import java.awt.Color;
import java.awt.Graphics;

import ld27.util.ImageLoader;

public class World {
	public static final World	w		= new World();

	private final int			HEIGHT	= GameEngine.HEIGHT;
	private final int			WIDTH	= GameEngine.WIDTH;

	public boolean				tiles[][];

	public World() {
		tiles = new boolean[WIDTH / Game.GRID_SIZE][(HEIGHT / Game.GRID_SIZE) + 1];

		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				tiles[x][y] = true;
			}
		}

		tiles[2][7] = false;
		tiles[3][7] = false;
		tiles[4][7] = false;

		tiles[6][5] = false;
		tiles[7][5] = false;
		tiles[8][5] = false;
		tiles[9][5] = false;

		tiles[11][7] = false;
		tiles[12][7] = false;
		tiles[13][7] = false;
	}

	public void update(int delta) {

	}

	public void render(Graphics g) {
		g.drawImage(ImageLoader.background_blue, 0, 0, null);

		g.drawImage(ImageLoader.ground_grey, 0, HEIGHT - 15, null);
		renderPlattforms(g);
	}

	private void renderPlattforms(Graphics g) {

		int maxX = WIDTH / Game.GRID_SIZE;
		int maxY = HEIGHT / Game.GRID_SIZE + 1;

		g.setColor(Color.DARK_GRAY);

		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				if (!tiles[x][y])
					g.fillRect(x * Game.GRID_SIZE, y * Game.GRID_SIZE, Game.GRID_SIZE, Game.GRID_SIZE - 48);
			}
		}

	}

	public void renderGrid(Graphics g) {
		int maxX = WIDTH / Game.GRID_SIZE;
		int maxY = HEIGHT / Game.GRID_SIZE + 1;

		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				if (tiles[x][y])
					g.setColor(Color.green);
				else
					g.setColor(Color.red);

				g.drawRect(x * Game.GRID_SIZE, y * Game.GRID_SIZE, Game.GRID_SIZE - 1, Game.GRID_SIZE - 1);
			}
		}
	}
}
