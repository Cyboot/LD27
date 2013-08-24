package ld27.entity;

import java.awt.Graphics;

import ld27.util.Vector2d;

public abstract class Entity {
	private boolean		isAlive	= true;
	protected Vector2d	pos;

	public Entity() {
		this(0, 0);
	}

	public Entity(Vector2d pos) {
		this.pos = pos;
	}

	public Entity(double x, double y) {
		this(new Vector2d(x, y));
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void kill() {
		isAlive = false;
		onKilled();
	}

	protected void onKilled() {
	}

	/**
	 * 
	 * @param delta
	 * @return if Entity is alive
	 */
	public abstract boolean update(int delta);

	public abstract void render(Graphics g);
}
