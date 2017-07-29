package ItemsAndObjects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import Game.Camera;
import Game.Game;

/**
 * The player inventory
 * 
 * @author 071566301
 *
 */
public class Inventory {

	private Camera cam;
	private Game game;
	private LinkedList<String> inventory = new LinkedList<String>();
	private int x, y;

	/**
	 * Constructs the inventory
	 * @param cam the camera to get the x and y
	 * @param game and instance of the game
	 */
	public Inventory(Camera cam, Game game) {

		this.cam = cam;
		this.game = game;

		x = cam.getX();
		y = cam.getY();

	}

	public void update() {
		
	}

	/**
	 * Renders the inventory 
	 */
	public void render() {

		BufferStrategy bs = game.getBufferStrategy();
		if (bs == null) {
			game.createBufferStrategy(3);
			return;
		}

		// Create new Graphics
		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.gray);
		g.fillRect(x, y, 100, 250);
		
		//Displays the inventory in the grey box
		g.setColor(Color.BLACK);
		for (int i = 0; i < inventory.size(); i++) {
			g.drawString(inventory.get(i), x + 10, y + 20 + i * 15);
		}

	}
/**
 * gets the list that holds all the players inventory
 * @return the list of items
 */
	public LinkedList<String> getInventory() {
		return inventory;
	}

	/**
	 * Empties out the inventory
	 */
	public void emptyInventory() {
		for (int i = 0; i < inventory.size();) {
			this.inventory.remove(inventory.get(i));
		}

	}
}
