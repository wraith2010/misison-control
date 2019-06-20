package com.ten31f.mission.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	private int width;
	private int height;

	public int[] pixels;

	public SpriteSheet(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (image == null) {
			return;
		}

		setPath(path);
		setWidth(image.getWidth());
		setHeight(image.getHeight());

		setPixels(image.getRGB(0, 0, getWidth(), getHeight(), null, 0, getWidth()));

		for (int i = 0; i < getPixels().length; i++) {
			getPixels()[i] = (getPixels()[i] & 0xff) / 64;
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

}
