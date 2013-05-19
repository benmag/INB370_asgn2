package asgn2GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	public static final int RECTANGLE=1;
	public static final int SQUARE=2;
	public static final int STRING=3;
	public int figure=0; 
	
	public Canvas(){ 
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 
		switch(this.figure) {
		case 0:
			// Default, do nothing. We want them to do something to start. It's more fun that way.
			break;
		case 1:
			  g.setColor(Color.GRAY);
			  g.drawRect(20, 20, 100, 100);
			  g.fillRect(20, 20, 100, 100);
			  g.setColor(Color.WHITE);
			  g.drawString("LOCOMOTIVE", 30, 105);
		  break; 
		case 2:
	      g.setColor(Color.BLUE);
		  g.drawRect(20,20,100,100);
		  g.fillRect(20,20,100,100);
		  break;
		case 3:
		  g.setColor(Color.WHITE);
		  g.drawString("Sam the Wonder Dog", 40,40);
		  break;
		}
	}

}
