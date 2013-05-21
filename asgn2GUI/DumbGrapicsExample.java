/**
 * 
 */
package asgn2GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author hogan
 *
 */
public class DumbGrapicsExample extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 300;

	private JPanel btmPanel;
	private JPanel trainPanel; 
	private Canvas drawPanel;
	 
	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public DumbGrapicsExample(String arg0) throws HeadlessException {
		super(arg0);
		createGUI();
	}
	
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    //setLayout(new FlowLayout());
	    
	    trainPanel = new JPanel();
	    trainPanel.setLayout(new FlowLayout());
	    add(trainPanel,BorderLayout.WEST);
	    
	    
	    JPanel dickSuck = new JPanel();
	    dickSuck.setBackground(Color.BLUE);
	    dickSuck.setPreferredSize(new Dimension(90, 90));
	    JLabel test = new JLabel();
	    test.setText("dsds");
	    dickSuck.add(test);
	    trainPanel.add(dickSuck);
	    
	    JPanel dickSuck2 = new JPanel();
	    dickSuck2.setBackground(Color.GREEN);
	    dickSuck2.setPreferredSize(new Dimension(90, 90));
	    trainPanel.add(dickSuck2);
	    
	    JPanel dickSuck3 = new JPanel();
	    dickSuck3.setBackground(Color.BLUE);
	    dickSuck3.setPreferredSize(new Dimension(90, 90));
	    trainPanel.add(dickSuck3);
	    
	    JPanel dickSuck4 = new JPanel();
	    dickSuck4.setBackground(Color.GREEN);
	    dickSuck4.setPreferredSize(new Dimension(90, 90));
	    trainPanel.add(dickSuck4);
	    
	    
	    
	    /*drawPanel = new Canvas();
	    drawPanel.setPreferredSize(new Dimension(240,140));
	    drawPanel.figure=Canvas.RECTANGLE;
	    trainPanel.add(drawPanel,BorderLayout.WEST);
	    
	    drawPanel = new Canvas();
	    drawPanel.setPreferredSize(new Dimension(240,140));
	    drawPanel.figure=Canvas.RECTANGLE;
	    trainPanel.add(drawPanel,BorderLayout.WEST);
	    
	    drawPanel = new Canvas();
	    drawPanel.setPreferredSize(new Dimension(240,140));
	    drawPanel.figure=Canvas.RECTANGLE;
	    trainPanel.add(drawPanel,BorderLayout.WEST);*/
	    
	    
	   // drawPanel.repaint();
	     	    
	   

	    
	    // Boxes ~~
	    /*drawPanel = new Canvas();
	    drawPanel.setBackground(Color.LIGHT_GRAY);
	    trainPanel.add(drawPanel,BorderLayout.CENTER);
	    
	     
	    drawPanel = new Canvas();
	    drawPanel.setBackground(Color.RED);
	    trainPanel.add(drawPanel,BorderLayout.CENTER);*/
	    // ~~~~~~~
	    
	    //drawPanel = new Canvas();
	    //drawPanel.setBackground(Color.LIGHT_GRAY);
	    
	    //add(drawPanel,BorderLayout.CENTER);

	    btmPanel = new JPanel();
	    btmPanel.setBackground(Color.LIGHT_GRAY);
        btmPanel.setLayout(new FlowLayout());

	    JButton loadButton = new JButton("Rect");
	    loadButton.setBackground(Color.WHITE);
	    loadButton.addActionListener(this);
	    btmPanel.add(loadButton);

	    JButton unLoadButton = new JButton("Square");
	    unLoadButton.setBackground(Color.WHITE);
	    unLoadButton.addActionListener(this);
	    btmPanel.add(unLoadButton);

	    JButton findButton = new JButton("String");
	    findButton.setBackground(Color.WHITE);
	    findButton.addActionListener(this);
	    btmPanel.add(findButton);

	    this.getContentPane().add(btmPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
	  String buttonString = e.getActionCommand();

	  if (buttonString.equals("Rect")) {
		  
		  drawPanel.figure=Canvas.RECTANGLE;
	      drawPanel.repaint();
		     
	  } else if (buttonString.equals("Square")) {
		 drawPanel.figure=Canvas.SQUARE;
		 drawPanel.repaint();
	  } else if (buttonString.equals("String")) {
		 drawPanel.figure=Canvas.STRING;
		 drawPanel.repaint();
	  } else {
	     System.err.println("Unexpected Error");
	  }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DumbGrapicsExample gui = new DumbGrapicsExample("DumbGraphicsExample");
	    gui.setVisible(true);

	}
}
