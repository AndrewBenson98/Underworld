package ItemsAndObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GameObject;
import Game.Handler;
import Game.ID;

/**
 * Enemy class
 * 
 * @author 071566301
 *
 */
public class Enemy extends GameObject implements Runnable {

	private int velX = 0, velY = 0;
	private Handler handler;
	private boolean alive = true;
	private int speed = 3;
	private boolean inRange = false;
	private Image enemy;

	// Holds the enemy imagesq
	private String[] animation = { "res/EnemyRight.png", "res/EnemyLeft.png" };

	/**
	 * Constructor
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param id
	 *            the enemy ID
	 * @param handler
	 *            an instance of handler
	 */
	public Enemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		// Randomly choose whether the enemy moves vertically or horizontally
		if (Math.random() <= 0.5) {
			velX = speed;
		} else {
			velY = speed;
		}

		// Set the current image to 0
		image = 0;

		// Try to load the image
		try {
			enemy = ImageIO.read(new File(animation[0]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * The loop for the enemy thread
	 */
	public void run() {
		while (alive) {

			// Update
			update();

			// 25 millisecond delay
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				enemy = ImageIO.read(new File(animation[image]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	/**
	 * Updates the x and y of the enemy
	 */
	public void update() {

		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject tempObject = handler.objects.get(i);
			if (tempObject.getId() == ID.Player) {

				// If within 250 pixels of the player
				int range = 350;

				if (Math.abs(x - tempObject.getX()) < range
						&& Math.abs(y - tempObject.getY()) < range) {

					inRange = true;

					// If the objects x values are within a certain range then
					// the enemy will move towards the player
					if (x < tempObject.getX()) {
						velX = speed;
						velY = 0;
						x += velX;
						image = 0;
					} else if (x > tempObject.getX()) {
						velX = speed;
						velY = 0;
						x -= velX;
						image = 1;

					}

					// If the objects y values are within a certain range then
					// the enemy will move towards the player
					if (y < tempObject.getY()) {
						velY = speed;
						velX = 0;
						y += velY;

					} else if (y > tempObject.getY()) {
						velY = speed;
						velX = 0;
						y -= velY;
					}
				} else {
					x += velX;
					y += velY;
					inRange = false;
				}
			}
		}

		collision();
	}

	@Override
	/**
	 * Renders the objects image
	 */
	public void render(Graphics g) {

		g.drawImage(enemy, x, y, 64, 64, null);

	}

	/**
	 * Handles what the object does when it collides with another object
	 */
	public void collision() {

		// Enemies have four collision boxes. Top, bottom, left, and right. When
		// one of these boxes intersects with another object, the enemy will do
		// something. e.g. its x value will be set so that it cant move through
		// the object it collides with.

		for (int i = 0; i < handler.objects.size(); i++) {

			GameObject tempObject = handler.objects.get(i);

			// If it collides with a wall
			if (tempObject.getId() == ID.Wall
					|| tempObject.getId() == ID.bronzeDoor
					|| tempObject.getId() == ID.silverDoor
					|| tempObject.getId() == ID.goldDoor) {
				if (getBoundsUp().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 128;
					velY *= -1;

				}
				if (getBoundsDown().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - 65;
					velY *= -1;
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 128;
					velX *= -1;
					image = 0;
				}
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - 64;
					velX *= -1;
					image = 1;
				}

				// If it collides with another enemy
			} else if (tempObject.getId() == ID.Enemy) {
				if (getBoundsUp().intersects(tempObject.getBounds())) {
					if (!inRange) {
						y = tempObject.getY() + 64;
						velY *= -1;
					} else
						y = tempObject.getY() + 64;

				}
				if (getBoundsDown().intersects(tempObject.getBounds())) {

					if (!inRange) {
						y = tempObject.getY() - 64;
						velY *= -1;
					} else
						y = tempObject.getY() - 64;

				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {

					if (!inRange) {
						x = tempObject.getX() + 64;
						velX *= -1;
					} else
						x = tempObject.getX() + 64;

				}
				if (getBoundsRight().intersects(tempObject.getBounds())) {

					if (!inRange) {
						x = tempObject.getX() - 64;
						velX *= -1;
					} else
						x = tempObject.getX() - 64;

				}
			}
		}
	}

	// Collision boxes

	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}

	public Rectangle getBoundsUp() {
		return new Rectangle(x + 5, y, 54, 5);
	}

	public Rectangle getBoundsDown() {
		return new Rectangle(x + 5, y + 59, 55, 5);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle(x, y + 5, 5, 54);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle(x + 59, y + 5, 5, 54);
	}

}
