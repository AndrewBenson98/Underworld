package ItemsAndObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GameObject;
import Game.ID;

/**
 * 
 * @author Andrew Benson
 * 
 */
public class Wall extends GameObject {

	private Image wall;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param id
	 *            the objects ID
	 */
	public Wall(int x, int y, ID id) {
		super(x, y, id);

		try {
			wall = ImageIO.read(new File("res/Wall.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	/**
	 * Updates the object e.g updates x and y
	 */
	public void update() {
	}

	@Override
	/**Renders the object to the screen at it's x and y coordinate
	 * @param g the graphics object
	 */
	public void render(Graphics g) {

		// g.setColor(Color.GREEN);
		//
		// g.fillRect(x,y,128,128);
		g.drawImage(wall, x, y, 128, 128, null);

	}

	/**
	 * @returns a rectangle that represents the object. This rectangle is a collision box
	 */
	public Rectangle getBounds() {

		return new Rectangle(x, y, 128, 128);
	}

}
