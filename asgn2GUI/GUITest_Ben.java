/**
 *
 */
package asgn2GUI;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
	private JPanel trainPanel;
	private JFrame locoPanel;
	private JPanel RollingStockPanel;
	private JLabel stockLabel;
	private JTextField locoPowerText;
	private final int DEFAULT_CARRIAGE_WIDTH = 90;
	private final int DEFAULT_CARRIAGE_HEIGHT = 90;

	private DepartingTrain myTrain = new DepartingTrain();

	/**
	 * @param arg0
	 * @throws HeadlessException
	 * @throws TrainException
	 */
	public GUITest_Ben(String arg0) throws HeadlessException, TrainException {
		super(arg0);
		createGUI();
		createAddLoco();
	}


	private void drawTrain() throws TrainException {

		Integer defaultWeight = 50;
		Integer defaultTooHeavyWeight = 500000;
		Integer defaultSeats = 50;
	    String defaultGoods = "G";
	    String defaultClassification = "4S";


		// ~~~~~~~~~~~~ test train setup ~~~~~~~~~~~~~ //
		Locomotive loco = new Locomotive(defaultWeight, defaultClassification);

		PassengerCar pass1 = new PassengerCar(defaultTooHeavyWeight, defaultSeats);
		FreightCar freight1 = new FreightCar(defaultWeight, defaultGoods);
		FreightCar freight2 = new FreightCar(defaultWeight, defaultGoods);
		FreightCar freight3 = new FreightCar(defaultWeight, defaultGoods);
		FreightCar freight4 = new FreightCar(defaultWeight, defaultGoods);


		myTrain.addCarriage(loco);
		myTrain.addCarriage(pass1);
		myTrain.addCarriage(freight1);
		myTrain.addCarriage(freight2);
		myTrain.addCarriage(freight3);
		myTrain.addCarriage(freight4);
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //


		// Setup trainPanel
	    trainPanel = new JPanel();
	    trainPanel.setLayout(new FlowLayout());
	    add(trainPanel,BorderLayout.WEST);

		RollingStock carriage = myTrain.nextCarriage();

		while(carriage != null) {

			if(carriage.toString().contains("Loco")){ // locomotive carriage

			    spawnStock(carriage.toString(), Color.YELLOW);

			} else if(carriage.toString().contains("Passenger")) { // passenger carriage

				spawnStock(carriage.toString(), Color.RED);

			} else if(carriage.toString().contains("Freight")) {

				spawnStock(carriage.toString(), Color.GREEN);

			} else {
				throw new TrainException("Unknown RollingStock type detected in drawTrain()");
			}


			// Get the next carriage
			carriage = myTrain.nextCarriage();

		}


		// All additions to trainPanel have been made, show it!
	    setVisible(true);

	}

    private void createAddLoco() {
	    locoPanel = new JFrame();
	    locoPowerText = new JTextField();
	    locoPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;

		locoPanel.setSize(300, 500);
	    locoPanel.setBackground(Color.GRAY);
	    locoPanel.setVisible(false);
	}

	private void spawnStock(String stockText, Color selectedColor) {

		// Create loco panel
		RollingStockPanel = new JPanel();
		RollingStockPanel.setBackground(selectedColor);
		RollingStockPanel.setPreferredSize(new Dimension(DEFAULT_CARRIAGE_WIDTH, DEFAULT_CARRIAGE_HEIGHT));

		// Add label
		stockLabel = new JLabel();
	    stockLabel.setText(stockText);
	    RollingStockPanel.add(stockLabel);

	    // Add panel to the train panel
	    trainPanel.add(RollingStockPanel);
	}


	private void createGUI() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());



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
			try {
			    locoPanel.setVisible(true);
				drawTrain();
			} catch (TrainException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	 * @throws TrainException
	 * @throws HeadlessException
	 */
	public static void main(String[] args) throws HeadlessException, TrainException {
		GUITest_Ben gui = new GUITest_Ben("DumbGraphicsExample");
	    gui.setVisible(true);

	}
}
