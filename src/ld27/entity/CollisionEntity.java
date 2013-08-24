package ld27.entity;

import ld27.Game;
import ld27.GameEngine;
import ld27.World;
import ld27.util.Vector2d;

public abstract class CollisionEntity extends Entity {
	private int			gridX;
	private int			gridY;
	private double		ratioX;
	private double		ratioY;

	protected double	dx;
	protected double	dy;

	private double		friction	= 0.92;
	private double		gravity		= 0.02;

	public CollisionEntity(Vector2d pos) {
		super(pos);

		setCoordinates(pos.x, pos.y);
	}

	public void setCoordinates(double x, double y) {
		pos.x = x;
		pos.y = y;
		gridX = (int) (x / Game.GRID_SIZE);
		gridY = (int) (y / Game.GRID_SIZE);

		ratioX = (x - gridX * Game.GRID_SIZE) / Game.GRID_SIZE;
		ratioY = (y - gridY * Game.GRID_SIZE) / Game.GRID_SIZE;
	}

	@Override
	public boolean update(int delta) {
		// X component
		ratioX += dx;
		dx *= friction;
		if (hasCollision(gridX - 1, gridY) && ratioX <= 0.3) {
			dx = 0;
			ratioX = 0.3;
		}
		if (hasCollision(gridX + 1, gridY) && ratioX >= 0.7) {
			dx = 0;
			ratioX = 0.7;
		}
		while (ratioX < 0) {
			gridX--;
			ratioX++;
		}
		while (ratioX > 1) {
			gridX++;
			ratioX--;
		}

		// Y component
		dy += gravity + 0.0001;
		ratioY += dy;
		dy *= friction;
		if (hasCollision(gridX, gridY - 1) && ratioY <= 0.4) {
			dy = 0;
			ratioY = 0.4;
		}
		if (hasCollision(gridX, gridY + 1) && ratioY >= 0.5) {
			dy = 0;
			ratioY = 0.5;
		}
		while (ratioY < 0) {
			gridY--;
			ratioY++;
		}
		while (ratioY > 1) {
			gridY++;
			ratioY--;
		}


		pos.x = (gridX + ratioX) * Game.GRID_SIZE;
		pos.y = (gridY + ratioY) * Game.GRID_SIZE;

		return isAlive();
	}

	protected boolean isFalling() {
		return Math.abs(dy) > 0;
	}

	public boolean hasCollision(int gridX, int gridY) {
		if (gridX < 0 || gridY < 0 || gridX * Game.GRID_SIZE > GameEngine.WIDTH - Game.GRID_SIZE
				|| gridY * Game.GRID_SIZE > GameEngine.HEIGHT - Game.GRID_SIZE)
			return true;

		return !World.w.tiles[gridX][gridY];
	}
}
