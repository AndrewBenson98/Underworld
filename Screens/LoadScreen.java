package Screens;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Game.Game;

/**
 * The loading screen
 * 
 * @author 071566301
 *
 */
public class LoadScreen {

	Font font = new Font("Comic Sans", Font.BOLD, 40);

	/**
	 * Render the word "Loading" on the screen
	 * 
	 * @param g
	 */
	public void Render(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH * Game.scale, Game.HEIGHT * Game.scale);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("LOADING", Game.WIDTH * Game.scale / 2 - 100, Game.HEIGHT
				* Game.scale / 2 - 50);

	}

}
