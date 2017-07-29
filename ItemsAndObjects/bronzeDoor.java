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
 * Door Object
 * 
 * @author 071566301
 *
 */
public class bronzeDoor extends GameObject {

	private Image door;

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
	public bronzeDoor(int x, int y, ID id) {
		super(x, y, id);

		try {
			door = ImageIO.read(new File("res/BronzeDoor.png"));
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
		// TODO Auto-generated method stub

	}

	@Override
	/**Renders the object to the screen at it's x and y coordinate
	 * @param g the graphics object
	 */
	public void render(Graphics g) {

		// g.setColor(Color.ORANGE);
		// g.fillRect(x, y, 128, 64);

		g.drawImage(door, x, y, 128, 64, null);

	}

	@Override
	/**
	 * @returns a rectangle that represents the object. This rectangle is a collision box
	 */
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 128, 64);
	}

}
