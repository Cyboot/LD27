package ld27.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ld27.GameEngine;

public class ImageLoader {
	public static BufferedImage	point_red;
	public static BufferedImage	point_green;
	public static BufferedImage	point_blue;
	public static BufferedImage	background_blue;
	public static BufferedImage	ground_grey;

	public static BufferedImage	stickman;
	public static BufferedImage	stickman_move_left;
	public static BufferedImage	stickman_move_right;

	public static void init() {
		point_red = loadImage("point_red.png");
		point_green = loadImage("point_green.png");
		point_blue = loadImage("point_blue.png");
		background_blue = loadImage("background_blue.png");
		ground_grey = loadImage("ground_grey.png");
		stickman = loadImage("stickman.png");
		stickman_move_left = loadImage("stickman_move_left.png");
		stickman_move_right = loadImage("stickman_move_right.png");
	}

	private static BufferedImage loadImage(String img) {
		try {
			return ImageIO.read(GameEngine.class.getResource("/" + img));
		} catch (IOException e) {
			throw new IllegalArgumentException("The Image you tried to load was not found!", e);
		}
	}

	public static BufferedImage getSubImage(BufferedImage img, int x, int y, int width) {
		return img.getSubimage(x * width, y * width, width, width);
	}

	public static BufferedImage getCutImage(BufferedImage img, int width) {
		return img.getSubimage(0, 0, width, img.getHeight());
	}
}
