package ld27.entity;

import java.awt.Color;
import java.awt.Graphics;

import ld27.GameEngine;
import ld27.util.Vector2d;

public class Fireball extends Entity {

	private Vector2d	dir;

	public Fireball(Vector2d pos, Vector2d dir) {
		super(pos);
		this.dir = dir;
	}

	@Override
	public boolean update(int delta) {
		pos.x += dir.x * delta;

		if (pos.x > GameEngine.WIDTH || pos.x < 0) {
			kill();
		}


		return isAlive();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(pos.x(), pos.y(), 15, 10);
	}
}
