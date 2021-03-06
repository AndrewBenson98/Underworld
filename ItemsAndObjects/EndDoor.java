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
public class EndDoor extends GameObject {

	private Image endDoor;
	
	
	/**
	 * Constructor 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param id the objects ID
	 */
	public EndDoor(int x, int y, ID id) {
		super(x, y, id);
		try {
			endDoor = ImageIO.read(new File("res/EndDoor.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// TODO Auto-generated constructor stub
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
	g.setColor(Color.magenta);
	g.fillRect(x,y,64,64);
		
	g.drawImage(endDoor, x, y, 64, 64, null);
	
	}

	@Override
	/**
	 * @returns a rectangle that represents the object. This rectangle is a collision box
	 */
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,64,64);
	}

}
