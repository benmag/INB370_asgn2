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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	
	
	private static final int MULTIPLY = 100;

	private JPanel btmPanel;
	private Canvas drawPanel;
	private JPanel trainPanel;
	private JScrollPane scroll;
	private JFrame locoPanel, passPanel, freightPanel;
	private JPanel RollingStockPanel;
	private JLabel stockLabel;
	private JTextField locoPowerText,grossWeight_field,freightGrossWeight_field,numberOfSeats_field;
	private JComboBox engineList, powerList, goodsList;
	private final int DEFAULT_CARRIAGE_WIDTH = 110;
	private final int DEFAULT_CARRIAGE_HEIGHT = 90;
	private final int DEFAULT_TRAIN_CONTAINER_WIDTH = 400;
	private final int DEFAULT_TRAIN_CONTAINER_HEIGHT = 150;
	
	
	// TRAIN 
	private DepartingTrain myTrain = new DepartingTrain();

	private String trainClassification;
	private Integer trainGrossWeight;
	
	private Integer grossWeight;
	private Integer numberOfSeats;
	
	/**
	 * @param arg0
	 * @throws HeadlessException
	 * @throws TrainException
	 */
	public GUITest_Ben(String arg0) throws HeadlessException, TrainException {
		super(arg0);
		createGUI();
		
		setPreferredSize(new Dimension(200, 250));
        
		// Create a new JPanel to hold the train scroll
		JPanel train_container = new JPanel();
		train_container.setLayout(new FlowLayout());
		add(train_container, BorderLayout.WEST);
		
        scroll = new JScrollPane(trainPanel);
        scroll.setHorizontalScrollBarPolicy(scroll.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(DEFAULT_TRAIN_CONTAINER_WIDTH, DEFAULT_TRAIN_CONTAINER_HEIGHT));
        train_container.add(scroll, BorderLayout.WEST);
        
        
        // Create status report holder
		JPanel status_report = new JPanel();
		status_report.setLayout(new FlowLayout());
		status_report.setPreferredSize(new Dimension(150, 100));
		status_report.setBackground(Color.BLUE);
		
		// Write the status report 
		JLabel canMove = new JLabel();
		canMove.setText("Fill me with text... And a nicer colour");
		
		add(status_report, BorderLayout.EAST);
		
		
		
		createAddLocov2();
		createAddPass();
		createAddFreight();
		drawTrain();
	}


	private void drawTrain() throws TrainException {
		// Setup trainPanel
	    trainPanel = new JPanel();
	    trainPanel.setLayout(new FlowLayout());
	    //scroll.add(trainPanel);
	    //scroll.getViewport().add(comp)
	    scroll.getViewport().add(trainPanel);
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
	    repaint();
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

    
    /**
     * 
     */
    private void createAddLocov2() {
    	locoPanel = new JFrame("Add locomotive carriage");
    	//2. Optional: What happens when the frame closes?
    	locoPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Create input panel
    	JPanel inputPanel = new JPanel();
    	inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER, 5,5));

    	// -- Create inputs -- //  	
    	//Create the engine list box
    	String[] engineTypes = { "E", "D", "S" }; // list of engines
    	engineList = new JComboBox(engineTypes); // listbox element
    	inputPanel.add(engineList); // add it to our input panel
    	
    	//Create the power class list box
    	String[] powerClasses = { "1", "2", "3", "4", "5", "6", "7", "8", "9" }; // list of engines
    	powerList = new JComboBox(powerClasses); // listbox element
    	inputPanel.add(powerList); // add it to our input panel
    	
    	// ----------------- //
    	
    	
    	/* DONT NEED THIS
    	JTextField tf = new JTextField();
       	tf.setColumns( 20 );

       	inputPanel.add(tf);*/           
    	
    	locoPanel.getContentPane().add(inputPanel, BorderLayout.CENTER);

    	
    	//4. Size the frame.
    	locoPanel.pack();
    	locoPanel.setSize(300,120);

    	btmPanel = new JPanel();
    	btmPanel.setBackground(Color.LIGHT_GRAY);
    	btmPanel.setLayout(new FlowLayout());

    	JButton loadButton = new JButton("Save Locomotive");
    	loadButton.setBackground(Color.WHITE);
    	loadButton.addActionListener(this);
    	btmPanel.add(loadButton);

    	locoPanel.add(btmPanel, BorderLayout.SOUTH);

    	//5. Hide it to be shown when we're ready.
    	locoPanel.setVisible(false);
	}  
    
    private void createAddPass() {
    	passPanel = new JFrame("Add Passenger Carriage");
    	//2. Optional: What happens when the frame closes?
    	passPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Create input panel
    	JPanel inputPanel = new JPanel();
    	inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER, 5,5));

    	// -- Create inputs -- //  	  	
      	grossWeight_field = new JTextField();
      	grossWeight_field.setColumns( 20 );

      	inputPanel.add(new Label("Gross Weight"));
      	inputPanel.add(grossWeight_field);           
      	
       	
       	numberOfSeats_field = new JTextField();
       	numberOfSeats_field.setColumns( 20 );

       	inputPanel.add(new Label("Number of seats (in tonnes)"));
       	inputPanel.add(numberOfSeats_field);           
    	
    	// ------------------ //
       	
       	passPanel.getContentPane().add(inputPanel, BorderLayout.CENTER);
    	
    	//4. Size the frame.
    	passPanel.pack();
    	passPanel.setSize(300,200);

    	btmPanel = new JPanel();
    	btmPanel.setBackground(Color.LIGHT_GRAY);
    	btmPanel.setLayout(new FlowLayout());

    	JButton loadButton = new JButton("Save Passenger Carriage");
    	loadButton.setBackground(Color.WHITE);
    	loadButton.addActionListener(this);
    	btmPanel.add(loadButton);

    	passPanel.add(btmPanel, BorderLayout.SOUTH);

    	//5. Hide it to be shown when we're ready.
    	passPanel.setVisible(false);
	}
    
    
    
    private void createAddFreight() {
    	freightPanel = new JFrame("Add freight carriage");
    	//2. Optional: What happens when the frame closes?
    	freightPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Create input panel
    	JPanel inputPanel = new JPanel();
    	inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER, 5,5));

    	// -- Create inputs -- //  	
    	//Create the goods type list box
    	String[] goodTypes = { "G", "D", "R" }; // list of engines
    	goodsList = new JComboBox(goodTypes); // listbox element
    	inputPanel.add(goodsList); // add it to our input panel
    	
    	
    	// Create the gross weight input box
    	freightGrossWeight_field = new JTextField();
    	freightGrossWeight_field.setColumns( 20 );

       	inputPanel.add(freightGrossWeight_field);           

       	
    	// ----------------- //
    	
    	
    	freightPanel.getContentPane().add(inputPanel, BorderLayout.CENTER);

    	
    	//4. Size the frame.
    	freightPanel.pack();
    	freightPanel.setSize(300,120);

    	btmPanel = new JPanel();
    	btmPanel.setBackground(Color.LIGHT_GRAY);
    	btmPanel.setLayout(new FlowLayout());

    	JButton loadButton = new JButton("Save Freight Carriage");
    	loadButton.setBackground(Color.WHITE);
    	loadButton.addActionListener(this);
    	btmPanel.add(loadButton);

    	freightPanel.add(btmPanel, BorderLayout.SOUTH);

    	//5. Hide it to be shown when we're ready.
    	freightPanel.setVisible(false);
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

	    JButton removeButton = new JButton("Remove last Carriage");
	    removeButton.setBackground(Color.WHITE);
	    removeButton.addActionListener(this);
	    btmPanel.add(removeButton);

	    
	    this.getContentPane().add(btmPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		
		String buttonString = e.getActionCommand();

		if (buttonString.equals("Add Locomotive")) {
			locoPanel.setVisible(true);
		} else if (buttonString.equals("Add Passenger Cars")) {
			passPanel.setVisible(true);
		} else if (buttonString.equals("Add Freight Cars")) {
			freightPanel.setVisible(true);
		} else if(buttonString.equals("Remove last Carriage")) {
			try {
				remove(trainPanel);
				myTrain.removeCarriage();
				drawTrain();
			} catch (TrainException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(buttonString.equals("Save Locomotive")) {
		  
			// fetch logo info from our input objects
			this.trainClassification = (String)(String) powerList.getSelectedItem() + "" + (String) engineList.getSelectedItem(); 
			this.trainGrossWeight = Integer.parseInt((String) powerList.getSelectedItem()) * MULTIPLY;
			
			
			// Create the loco motive carriage
			try {
				Locomotive locomotive = new Locomotive(this.trainGrossWeight, this.trainClassification);
				myTrain.addCarriage(locomotive);
				locoPanel.setVisible(false);
				drawTrain();
			} catch (TrainException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					  
		  
	  } else if(buttonString.equals("Save Passenger Carriage")) {
			// fetch logo info from our input objects
			this.grossWeight = Integer.parseInt(grossWeight_field.getText());
			this.numberOfSeats = Integer.parseInt(numberOfSeats_field.getText());
			
			
			// Create the loco motive carriage
			try {
				PassengerCar pass = new PassengerCar(this.grossWeight, this.numberOfSeats);	
				myTrain.addCarriage(pass);
				
				
				// Clear passPanel window
				numberOfSeats_field.setText("");
				grossWeight_field.setText("");
				
				passPanel.setVisible(false);
				drawTrain();
			} catch (TrainException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					  
		  
	  } else if(buttonString.equals("Save Freight Carriage")) {
			// fetch logo info from our input objects
			Integer freight_grossWeight = Integer.parseInt(freightGrossWeight_field.getText());
			String freight_goods = (String) goodsList.getSelectedItem();
			
			
			// Create the loco motive carriage
			try {
				FreightCar freight = new FreightCar(freight_grossWeight, freight_goods);	
				myTrain.addCarriage(freight);
				
				
				// Clear passPanel window
				freightGrossWeight_field.setText("");
				
				freightPanel.setVisible(false);
				drawTrain();
			} catch (TrainException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
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
