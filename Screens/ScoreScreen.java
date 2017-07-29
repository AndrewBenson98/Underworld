package Screens;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Game.Game;

/**
 * The score screen
 * @author 071566301
 *
 */
public class ScoreScreen {

	Font font = new Font("Comic Sans", Font.BOLD, 40);
	Font font1 = new Font("Comic Sans", Font.BOLD, 70);
	
	//This variable changes depending on if the player wins or loses
	public static boolean gameOver=true;
	
	/**
	 * Renders the score screen
	 * @param g
	 */
	public void Render(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH * Game.scale, Game.HEIGHT * Game.scale);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("SCORE SCREEN", 50, 50);

		g.drawString("PRESS 1 FOR MAIN MENU", 25, Game.HEIGHT * Game.scale
				- 250);

		g.setFont(font1);
		
		//If they lose
		if(gameOver)
		g.drawString("GAME OVER", 500, Game.HEIGHT * Game.scale / 2 - 300);
		
		//If they win
		else if(!gameOver)
			g.drawString(" YOU WIN!", 500, Game.HEIGHT * Game.scale / 2 - 300);
		g.drawString("Time:" + Game.timer.getTime() / 10.0 + " Seconds", 420,
				Game.HEIGHT * Game.scale / 2);

	}

}
