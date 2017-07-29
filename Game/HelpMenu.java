package Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The help menu
 * @author 071566301
 *
 */
public class HelpMenu {

	//Differnet fonts
	Font font = new Font("Comic Sans", Font.BOLD, 40);
	Font font1 = new Font("Comic Sans", Font.PLAIN, 30);

	/**
	 * Renders the help menu
	 * @param g the graphics image
	 */
	public void Render(Graphics g) {

		//Sets a black background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH * Game.scale, Game.HEIGHT * Game.scale);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("HELP MENU", 50, 50);
		g.setFont(font1);
		g.drawString("PRESS 1 FOR MAIN MENU", 25, Game.HEIGHT*Game.scale - 250);

		g.drawString(
				"Collect all three orbs         in the map and reach the end portal         to escape.",
				50, 150);
		g.drawString("Avoid enemies         and traps        ", 50, 200);
		g.drawString(
				"Traps can be disarmed by if you have a Rewire tool         in your inventory",
				50, 250);
		g.drawString("Use Keys to unlock doors", 50, 300);
		g.drawString("Bronze keys         unlock bronze doors    ", 50, 350);
		g.drawString("Silver keys         unlock silver doors    ", 50, 400);
		g.drawString("Gold keys         unlock gold doors    ", 50, 450);
		g.drawString("Get through the level in the shortest amount of time possible", 50, 500);

		//Draw all the images
		try {
			g.drawImage(ImageIO.read(new File("res/Orb.png")), 335, 125, 35,
					35, null);
			g.drawImage(ImageIO.read(new File("res/EndDoor.png")), 875, 125,
					35, 35, null);
			g.drawImage(ImageIO.read(new File("res/Trap.png")), 450, 170, 35,
					35, null);
			g.drawImage(ImageIO.read(new File("res/RewireTool.png")), 740, 225,
					35, 35, null);
			g.drawImage(ImageIO.read(new File("res/BronzeKey.png")), 230, 325,
					35, 35, null);
			g.drawImage(ImageIO.read(new File("res/BronzeDoor.png")), 580, 325,
					35, 35, null);
			g.drawImage(ImageIO.read(new File("res/SilverKey .png")), 220, 375,
					35, 35, null);
			g.drawImage(ImageIO.read(new File("res/SilverDoor.png")), 540, 375,
					35, 35, null);
			g.drawImage(ImageIO.read(new File("res/GoldKey.png")), 200, 425,
					35, 35, null);
			g.drawImage(ImageIO.read(new File("res/GoldDoor.png")), 520, 425,
					35, 35, null);
			g.drawImage(ImageIO.read(new File("res/EnemyRight.png")), 260, 175, 35, 35, null);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
