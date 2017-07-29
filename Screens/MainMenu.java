package Screens;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Game.Game;

/**
 * Renders the main menu
 * @author 071566301
 *
 */
public class MainMenu {

	//Different fonts
	Font font = new Font("Comic Sans", Font.BOLD, 40);
	Font font1 = new Font("Comic Sans", Font.BOLD, 80);
	
	/**
	 * Renders the main menu screen
	 * @param g
	 */
	public void Render(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH * Game.scale, Game.HEIGHT * Game.scale);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("PRESS 1 FOR GAME", 650, 200);
		g.drawString("PRESS 2 FOR HELP", 650, 250);
		g.drawString("PRESS 3 TO QUIT", 650, 300);
		
		//g.drawString("VR: BETA 1.0.2", 50, Game.HEIGHT*Game.scale-200);
		
		g.setFont(font1);
		g.drawString("UNDERWORLD", 550, 100);

	}

}
