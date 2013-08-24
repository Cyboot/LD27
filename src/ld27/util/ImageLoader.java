package ld27.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ld27.GameEngine;

public class ImageLoader {
	public static BufferedImage	point_red;
	public static BufferedImage	point_green;
	public static BufferedImage	point_blue;

	public static void init() {
		point_red = loadImage("point_red.png");
		point_green = loadImage("point_green.png");
		point_blue = loadImage("point_blue.png");
	}

	private static BufferedImage loadImage(String img) {
		try {
			return ImageIO.read(GameEngine.class.getResource("/" + img));
		} catch (IOException e) {
			throw new IllegalArgumentException("The Image you tried to load was not found!", e);
		}
	}
}
