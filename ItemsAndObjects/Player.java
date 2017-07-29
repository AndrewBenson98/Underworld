package ItemsAndObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.Game;
import Game.GameObject;
import Game.Handler;
import Game.ID;
import Screens.ScoreScreen;

/**
 * Play class
 * 
 * @author 071566301
 *
 */
public class Player extends GameObject {

	private Handler handler;
	private Inventory inventory;
	private Image player;
	private String[] animation = { "res/PlayerRight.gif", "res/PlayerLeft.png" };
	private int orbCount = 0;

	/**
	 * Creates the player
	 * 
	 * @param x
	 *            the x coordinates
	 * @param y
	 *            the y coordinates
	 * @param id
	 *            the ID of the player
	 * @param handler
	 *            an instance of handler
	 * @param inventory
	 *            the players inventory
	 */
	public Player(int x, int y, ID id, Handler handler, Inventory inventory) {
		super(x, y, id);
		this.handler = handler;
		this.inventory = inventory;
		image = 0;
		try {
			player = ImageIO.read(new File(animation[0]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Updates the players coordinates
	 */
	public void update() {

		try {
			player = ImageIO.read(new File(animation[image]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x += velX;
		y += velY;
		collision();

	}

	/**
	 * Renders the player to the screen
	 * 
	 * @param g
	 *            the graphics object
	 */
	public void render(Graphics g) {

		g.drawImage(player, x, y, 64, 64, null);

	}

	/**
	 * Handles all collision for the player
	 */
	public void collision() {
		for (int i = 0; i < handler.objects.size(); i++) {

			GameObject tempObject = handler.objects.get(i);

			// If it encounters a wall
			if (tempObject.getId() == ID.Wall) {
				if (getBoundsUp().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 128;
				}
				if (getBoundsDown().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - 65;
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 128;
				}
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - 64;
				}
				// If it encounters a door
			} else if (tempObject.getId() == ID.bronzeDoor) {
				if (getBoundsUp().intersects(tempObject.getBounds())
						&& !hasItem("bronzeKey")) {
					y = tempObject.getY() + 64;
				} else if (getBoundsDown().intersects(tempObject.getBounds())
						&& !hasItem("bronzeKey")) {
					y = tempObject.getY() - 64;
				} else if (getBounds().intersects(tempObject.getBounds())
						&& hasItem("bronzeKey")) {

					handler.remove(tempObject);
					inventory.getInventory().remove("bronzeKey");
				}
			} else if (tempObject.getId() == ID.silverDoor) {
				if (getBoundsUp().intersects(tempObject.getBounds())
						&& !hasItem("silverKey")) {
					y = tempObject.getY() + 64;
				} else if (getBoundsDown().intersects(tempObject.getBounds())
						&& !hasItem("silverKey")) {
					y = tempObject.getY() - 64;
				} else if (getBounds().intersects(tempObject.getBounds())
						&& hasItem("silverKey")) {
					inventory.getInventory().remove("silverKey");
					handler.remove(tempObject);
				}
			} else if (tempObject.getId() == ID.goldDoor) {
				if (getBoundsUp().intersects(tempObject.getBounds())
						&& !hasItem("goldKey")) {
					y = tempObject.getY() + 64;
				} else if (getBoundsDown().intersects(tempObject.getBounds())
						&& !hasItem("goldKey")) {
					y = tempObject.getY() - 64;
				} else if (getBounds().intersects(tempObject.getBounds())
						&& hasItem("goldKey")) {
					inventory.getInventory().remove("goldKey");
					handler.remove(tempObject);
				}

				// If the player collides with a key or an orb, then add it to
				// the inventory and remove it from the map
			} else if (tempObject.getId() == ID.bronzeKey) {
				if (getBounds().intersects(tempObject.getBounds())) {

					pickUp("bronzeKey");
					handler.remove(tempObject);
				}
			} else if (tempObject.getId() == ID.silverKey) {
				if (getBounds().intersects(tempObject.getBounds())) {
					pickUp("silverKey");
					handler.remove(tempObject);
				}
			} else if (tempObject.getId() == ID.goldKey) {
				if (getBounds().intersects(tempObject.getBounds())) {
					pickUp("goldKey");
					handler.remove(tempObject);
				}
			} else if (tempObject.getId() == ID.Orb) {
				if (getBounds().intersects(tempObject.getBounds())) {
					pickUp("Orb");
					orbCount++;
					handler.remove(tempObject);
				}
			}

			//When the player runs into a trap and doesnt have a rewire tool
			else if (tempObject.getId() == ID.Trap) {
				if (getBounds().intersects(tempObject.getBounds())
						&& !hasItem("RewireTool")) {
					
					//Stop the timer, change the game state, and remove all the objects in the game
					Game.timer.turnOff();
					Game.state = Game.STATE.SCORE;
					inventory.emptyInventory();
					handler.removeAll();
				} else if (getBounds().intersects(tempObject.getBounds())
						&& hasItem("RewireTool")) {
					inventory.getInventory().remove("RewireTool");
					handler.remove(tempObject);

				}
			}

			//When the player picks up a rewire tool
			else if (tempObject.getId() == ID.RewireTool) {
				if (getBounds().intersects(tempObject.getBounds())) {
					pickUp("RewireTool");
					handler.remove(tempObject);
				}
				
			//If they get to the end and have all 3 orbs
			} else if (tempObject.getId() == ID.EndDoor) {
				if (getBounds().intersects(tempObject.getBounds())) {

					if (orbCount == 3) {
						Game.timer.turnOff();
						ScoreScreen.gameOver = false;
						Game.state = Game.STATE.SCORE;
						handler.removeAll();
						inventory.emptyInventory();

					}
				}
				
				//When the player collides with an enmy
			} else if (tempObject.getId() == ID.Enemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					
					//Turn off the timer, change the game state, remove all objects/inventory
					Game.timer.turnOff();
					Game.state = Game.STATE.SCORE;
					handler.removeAll();
					inventory.emptyInventory();
				}
			}
		}
	}

	private boolean hasItem(String i) {
		// Check inventory to see it has key

		for (String item : inventory.getInventory()) {
			if (item.equals(i))
				return true;
		}

		return false;
	}

	public void pickUp(String item) {

		inventory.getInventory().add(item);
	}

	public void Use(String item) {
		inventory.getInventory().remove(item);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}

	public Rectangle getBoundsUp() {
		return new Rectangle(x + 7, y, 50, 5);
	}

	public Rectangle getBoundsDown() {
		return new Rectangle(x + 7, y + 59, 50, 5);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle(x, y + 5, 5, 54);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle(x + 59, y + 5, 5, 54);
	}

}
