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
public class GUITest_Ben extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 250;

	private JPanel btmPanel;
	private Canvas drawPanel;
	 
	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public GUITest_Ben(String arg0) throws HeadlessException {
		super(arg0);
		createGUI();
	}
	
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());

	    drawPanel = new Canvas();
	    drawPanel.setBackground(Color.LIGHT_GRAY);
	    
	    add(drawPanel,BorderLayout.CENTER);
	    
	    createControlPanel();
	}

	private void createControlPanel() {
		
	    btmPanel = new JPanel();
	    btmPanel.setBackground(Color.LIGHT_GRAY);
        btmPanel.setLayout(new FlowLayout());

	    JButton loadButton = new JButton("Add Locomotive");
	    loadButton.setBackground(Color.WHITE);
	    loadButton.addActionListener(this);
	    btmPanel.add(loadButton);

	    JButton unLoadButton = new JButton("Add Passenger Cars");
	    unLoadButton.setBackground(Color.WHITE);
	    unLoadButton.addActionListener(this);
	    btmPanel.add(unLoadButton);

	    JButton findButton = new JButton("Add Freight Cars");
	    findButton.setBackground(Color.WHITE);
	    findButton.addActionListener(this);
	    btmPanel.add(findButton);

	    this.getContentPane().add(btmPanel, BorderLayout.SOUTH);		
	}

	public void actionPerformed(ActionEvent e) {
	  String buttonString = e.getActionCommand();

	  if (buttonString.equals("Add Locomotive")) {
		 drawPanel.figure=Canvas.RECTANGLE;
	     drawPanel.repaint();
	  } else if (buttonString.equals("Add Passenger Cars")) {
		 drawPanel.figure=Canvas.SQUARE;
		 drawPanel.repaint();
	  } else if (buttonString.equals("Add Freight Cars")) {
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
		GUITest_Ben gui = new GUITest_Ben("DumbGraphicsExample");
	    gui.setVisible(true);

	}
}
