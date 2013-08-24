package ld27;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import ld27.entity.Entity;
import ld27.entity.Stickman;

public class Game {
	public static final int		GRID_SIZE	= 64;
	public static final Game	g			= new Game();

	public static boolean		VK_UP;
	public static boolean		VK_DOWN;
	public static boolean		VK_LEFT;
	public static boolean		VK_RIGHT;
	public static boolean		VK_W;
	public static boolean		VK_A;
	public static boolean		VK_CTRL;

	private final int			HEIGHT		= GameEngine.HEIGHT;
	private final int			WIDTH		= GameEngine.WIDTH;

	public Stickman				player		= new Stickman();

	public List<Entity>			entities	= new ArrayList<>();

	private Game() {
	}

	public void update(int delta) {
		player.update(delta);

		List<Entity> dyingEntities = new ArrayList<>();

		for (Entity e : entities) {
			if (!e.update(delta))
				dyingEntities.add(e);
		}

		entities.removeAll(dyingEntities);
	}


	public void render(Graphics g) {
		World.w.render(g);

		player.render(g);

		for (Entity e : entities) {
			e.render(g);
		}

		// World.w.renderGrid(g);
	}

	public boolean jumpButtonPressed(int player) {
		if (player == 1) {
			return false;
		} else if (player == 2) {
			boolean tmp = VK_CTRL;
			VK_CTRL = false;
			return tmp;
		} else {
			return false;
		}

	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

}
