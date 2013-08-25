package ld27.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ld27.Game;
import ld27.GameEngine;
import ld27.util.ImageLoader;
import ld27.util.Vector2d;

public class Stickman extends CollisionEntity {
	private final static int	FIREBALLCOOLDOWN	= 500;

	private final double		speed				= 0.5;

	private int					animationTime		= 0;
	private boolean				lookLeft			= false;

	private int					fireballCooldown	= FIREBALLCOOLDOWN;

	private BufferedImage		img_move_left		= ImageLoader.stickman_move_left;
	private BufferedImage		img_move_right		= ImageLoader.stickman_move_right;
	
	private int player = 1;

	public Stickman(int player) {
		super(new Vector2d(GameEngine.WIDTH / 2, 100));
		this.player = player;
		
		if(player == 1){
			setCoordinates(20, 100);
		} else {
			lookLeft = true;
			setCoordinates(GameEngine.WIDTH - 20, 100);
		}
	}

	@Override
	public boolean update(int delta) {
		if (player == 1){
				
			if (Game.VK_LEFT) {
				dx = -0.05;
				lookLeft = true;
				animationTime += delta;
			}
			if (Game.VK_RIGHT) {
				dx = 0.05;
				lookLeft = false;
				animationTime += delta;
			}
			if (Game.g.jumpButtonPressed(1) && !isFalling())
				dy = -0.6;
			if (Game.VK_O)
				setCoordinates(GameEngine.WIDTH / 2, GameEngine.HEIGHT / 2);
	
			if (Game.VK_UP && fireballCooldown < 0) {
				shootFireball();
				fireballCooldown = FIREBALLCOOLDOWN;
			}
			
			fireballCooldown -= delta;
	
			if (animationTime >= 100 * 4)
				animationTime = 0;
	
			// return isAlive();
			return super.update(delta);
		} else {
				
			if (Game.VK_A) {
				dx = -0.05;
				lookLeft = true;
				animationTime += delta;
			}
			if (Game.VK_D) {
				dx = 0.05;
				lookLeft = false;
				animationTime += delta;
			}
			if (Game.g.jumpButtonPressed(2) && !isFalling())
				dy = -0.6;
			if (Game.VK_O)
				setCoordinates(GameEngine.WIDTH / 2, GameEngine.HEIGHT / 2);
	
			if (Game.VK_W && fireballCooldown < 0) {
				shootFireball();
				fireballCooldown = FIREBALLCOOLDOWN;
			}
			
			fireballCooldown -= delta;
	
			if (animationTime >= 100 * 4)
				animationTime = 0;
	
			// return isAlive();
			return super.update(delta);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getAnimationImage(lookLeft ? img_move_left : img_move_right), pos.x() - 32, pos.y() - 32, null);

		// g.setColor(Color.red);
		// g.fillRect(pos.x(), pos.y(), 5, 5);
	}

	private BufferedImage getAnimationImage(BufferedImage img) {
		return ImageLoader.getSubImage(img, animationTime / 100, 0, 64);
	}

	public void shootFireball() {
		if(lookLeft)
			Game.g.addEntity(new Fireball(pos.copy(), new Vector2d(-1, 0)));
		else
			Game.g.addEntity(new Fireball(pos.copy(), new Vector2d(1, 0)));
	}
}
