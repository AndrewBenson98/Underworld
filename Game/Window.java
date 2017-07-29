package Game;
import java.awt.Dimension;

import javax.swing.JFrame;



/**
 * The JPanel and JFrame class to display the screen
 * @author 071566301
 *
 */
public class Window {

	private int width, height;
	private String title;
	private int  scale;
	
	/**
	 * Creates a new window
	 * @param width1 the width
	 * @param height1 the height
	 * @param title1 the title
	 * @param scale1 the scale
	 * @param game instance of the game
	 */
public Window(int width1,int height1, String title1, int scale1, Game game){
		
		scale= scale1;
		width=width1*scale;
		height = height1*scale;
		title = title1;	
		
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.requestFocus();
		game.start();
			
		
	}
	
}
