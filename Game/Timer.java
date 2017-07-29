package Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 * Times how long it takes the player to finish the game
 * 
 * @author 071566301
 *
 */
public class Timer implements Runnable {

	private boolean on = false;
	private int time;
	private Font font = new Font("Comic Sans", Font.BOLD, 25);
	private int x, y;
	private Game game;

	/**
	 * Creates a new timer object
	 * 
	 * @param cam
	 *            instance of the camera
	 * @param game
	 *            instance of the game
	 */
	public Timer(Camera cam, Game game) {
		time = 0;
		this.game = game;
		x = cam.getX();
		y = cam.getY();
	}

	/**
	 * Renders the timer in the top left of the screen
	 */
	public void render() {

		BufferStrategy bs = game.getBufferStrategy();
		if (bs == null) {
			game.createBufferStrategy(3);
			return;
		}

		// Create new Graphics
		Graphics g = bs.getDrawGraphics();
		g.setFont(font);
		g.setColor(Color.BLUE);
		g.drawString("Time: " + (time / 10.0), x + 100, y + 50);
	}

	/**
	 * Runs a separate thread for the timer
	 */
	public void run() {

		while (true) {

			if (on) {
				time++;

				// Small Delay
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void reset() {
		time = 0;
		on = false;
	}

	public void turnOn() {
		on = true;
	}

	public void turnOff() {

		on = false;
	}

	public int getTime() {
		return time;
	}

}
