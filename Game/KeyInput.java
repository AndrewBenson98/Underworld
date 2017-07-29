package Game;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Screens.ScoreScreen;

/**
 * Class for user input through keyboard
 * 
 * @author 071566301
 *
 */
public class KeyInput extends KeyAdapter {

	
	private Handler handler;
	private int speed = 7;
	private Game game;

	/**
	 * Creates an new object that allows keyinput
	 * @param handler
	 * @param game
	 */
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}

	/**
	 * When a key is pressed the game will do something
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		//If the state is the game state
		if (Game.state == Game.STATE.GAME) {

			//Go through all the objects until it finds the player
			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject tempObject = handler.objects.get(i);
				
				//Moves the player by changing its velocity
				if (tempObject.getId() == ID.Player) {
					if (key == KeyEvent.VK_W) {
						tempObject.setVelY(-speed);
					}
					if (key == KeyEvent.VK_S) {
						tempObject.setVelY(speed);
					}
					if (key == KeyEvent.VK_A) {
						tempObject.setImage(1);
						tempObject.setVelX(-speed);
					}
					if (key == KeyEvent.VK_D) {
						tempObject.setImage(0);
						tempObject.setVelX(speed);
					}
				}
			}
		}// end of game
		
		//It the game is in the main menu
		else if (Game.state == Game.STATE.MAIN) {
			
			//If 1 is pressed then start the game
			if (key == KeyEvent.VK_1) {
				Game.state = Game.STATE.LOAD;
				game.loadLevel();

				Game.state = Game.STATE.GAME;
				Game.timer.turnOn();
				
				//If 2 is pressed then open up the help menu
			} else if (key == KeyEvent.VK_2) {
				Game.state = Game.STATE.HELP;

				//If 3 is pressed then exit the game
			} else if (key == KeyEvent.VK_3) {
				System.exit(0);

			}

		}
		// Goes back to main menu
		else if (Game.state == Game.STATE.HELP) {
			if (key == KeyEvent.VK_1) {
				Game.state = Game.STATE.MAIN;

			}
		}
		// Goes back to main menu
		else if (Game.state == Game.STATE.SCORE) {
			if (key == KeyEvent.VK_1) {
				ScoreScreen.gameOver = true;
				Game.state = Game.STATE.MAIN;
				Game.timer.reset();

			}
		}
	}

	/**
	 * When a key is released
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		//Find the player and set its velocity to 0
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject tempObject = handler.objects.get(i);
			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_W) {
					tempObject.setVelY(0);
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setVelY(0);
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(0);
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(0);
				}
			}
		}

	}

}
