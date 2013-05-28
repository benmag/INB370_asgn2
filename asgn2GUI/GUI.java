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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

/**
 * @author Ben Maggacis, Corey Thompson (n8383243)
 * 
 *
 */
public class GUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 250;

	private JPanel btmPanel;
	private JPanel trainPanel;
	private JScrollPane scroll;
	private JFrame locoPanel, passPanel, freightPanel, boardPanel;
	private JPanel RollingStockPanel;
	JTextPane status_report;
	private JLabel stockLabel;
	private JTextField grossWeight_field,freightGrossWeight_field,numberOfSeats_field,locoGrossWeight_field,boardPassengers_field;
	@SuppressWarnings("rawtypes")
	private JComboBox engineList, powerList, goodsList;
	private final int STATUS_REPORT_WIDTH = 150;
	private final int STATUS_REPORT_HEIGHT= 100;
	private final int INPUT_PANEL_POS = 5;
	private final int COLUMN_COUNT = 12;
	private final int DEFAULT_POP_PANEL_WIDTH = 300;
	private final int DEFAULT_POP_PANEL_HEIGHT = 200;
	private final int DEFAULT_CARRIAGE_WIDTH = 110;
	private final int DEFAULT_CARRIAGE_HEIGHT = 90;
	private final int DEFAULT_TRAIN_CONTAINER_WIDTH = 610;
	private final int DEFAULT_TRAIN_CONTAINER_HEIGHT = 150;
	private int leftOverPassengers;
	private RollingStock currentCarriage;
	
	
	// TRAIN 
	private DepartingTrain myTrain = new DepartingTrain();

	private String trainClassification;
	private Integer trainGrossWeight;
	
	private Integer grossWeight;
	private Integer numberOfSeats;
	
	/**
	 * Creates our new window 
	 * @param arg0
	 * @throws HeadlessException
	 * @throws TrainException
	 */
	public GUI(String arg0) throws HeadlessException, TrainException {
		super(arg0);
		createGUI();
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
		// Create a new JPanel to hold the train scroll
		JPanel train_container = new JPanel();
		train_container.setLayout(new FlowLayout());
		add(train_container, BorderLayout.WEST);
		
        scroll = new JScrollPane(trainPanel);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(DEFAULT_TRAIN_CONTAINER_WIDTH, DEFAULT_TRAIN_CONTAINER_HEIGHT));
        train_container.add(scroll, BorderLayout.WEST);
        
        // Create status report holder
        status_report = new JTextPane();
        status_report.setPreferredSize(new Dimension(STATUS_REPORT_WIDTH, STATUS_REPORT_HEIGHT));
        add(status_report, BorderLayout.EAST);
        

        
		createAddLoco();
		createAddPass();
		createAddFreight();
		createBoardPassengers();
		drawTrain();
	}

	
	/* 
	 * Sets a few configurations for the GUI 
	 */
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());

	    createControlPanel();
	}
	
	
	/* 
	 * This method loops through all of the carriages on the train and then
	 * draws a "train" into the GUI and updates the `status report` (which is read
	 * by conductors etc)  
	 */
	private void drawTrain() throws TrainException {
		// Setup trainPanel
	    trainPanel = new JPanel();
	    trainPanel.setLayout(new FlowLayout());
	    
	    scroll.getViewport().add(trainPanel);
		RollingStock carriage = myTrain.nextCarriage();
		
		while(carriage != null) {
	
			if(carriage.toString().contains("Loco")){ // locomotive carriage

			    spawnStock(carriage.toString(), Color.YELLOW);

			} else if(carriage.toString().contains("Passenger")) { // passenger carriage

				spawnStock(carriage.toString(), Color.RED);

			} else if(carriage.toString().contains("Freight")) { // freight carriage

				spawnStock(carriage.toString(), Color.GREEN);

			} else { // this shouldn't ever happen, but catch it if it does
				throw new TrainException("Unknown RollingStock type detected in drawTrain()");
			}
			currentCarriage = carriage;
			trainStatus("");
			// Get the next carriage
			carriage = myTrain.nextCarriage();
			
		}
		// All additions to trainPanel have been made, show it!
	    setVisible(true);
	    repaint();
	}
	
	private void trainStatus(String error) {
		
		RollingStock carriage = currentCarriage;
		String trainStatus = "";
		if(carriage != null) {
			if(myTrain.trainCanMove()) {
				trainStatus = "Can move: Yes!";
				status_report.setBackground(Color.white);
				
			} else {
				trainStatus = "Can move: No!";
				status_report.setBackground(Color.ORANGE);
			}
		} else {
			trainStatus = "Can move: No!";
		}
		
		
		// check for an error 
		if(error != "") {
			status_report.setBackground(Color.red);
		} 
		
		
		
		trainStatus += "\n";
		trainStatus += "Full: ";
		int remainingSeats = myTrain.numberOfSeats() - myTrain.numberOnBoard();
		if(remainingSeats > 0) {
			trainStatus += "No ["+myTrain.numberOnBoard()+"/"+myTrain.numberOfSeats()+"]";
		} else {
			trainStatus += "Yes ["+myTrain.numberOnBoard()+"/"+myTrain.numberOfSeats()+"]";;
		}
		if(leftOverPassengers > 0) {
			trainStatus += "\nStranded passengers: " + leftOverPassengers;
		}
		trainStatus += "\n\n" + error;
		status_report.setText(trainStatus);
	}

	
    /**
     * The 'Add locomotive' GUI pop up window. Builds a window with 3 input fields:
     *  engine type (dropdown), power class (drop down) and weight (input)
     * 
     * Adds action listener on save button 
     * 
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createAddLoco() {
    	locoPanel = new JFrame("Add locomotive carriage");
    	//2. Optional: What happens when the frame closes?
    	locoPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    	// Create input panel
    	JPanel inputPanel = new JPanel();
    	inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER, INPUT_PANEL_POS, INPUT_PANEL_POS));

    	// -- Create inputs -- //  	
    	//Create the engine list box
    	JPanel engineType = new JPanel();
    	engineType.add(new Label("Engine Type:"));
    	String[] engineTypes = { "Electric", "Diesel", "Steam" }; // list of engines
    	engineList = new JComboBox(engineTypes); // listbox element
    	engineType.add(engineList); // add it to our input panel
    	inputPanel.add(engineType);
    	
    	//Create the power class list box
    	JPanel enginePower = new JPanel();
    	enginePower.add(new Label("Power class:"));
    	String[] powerClasses = { "1", "2", "3", "4", "5", "6", "7", "8", "9" }; // list of engines
    	powerList = new JComboBox(powerClasses); // listbox element
    	enginePower.add(powerList); // add it to our input panel
    	inputPanel.add(enginePower);
    	
    	//Create the weight input
    	locoGrossWeight_field = new JTextField();
    	locoGrossWeight_field.setColumns(COLUMN_COUNT);

      	JPanel engineWeight = new JPanel(); 
      	engineWeight.add(new Label("Gross Weight:"));
      	engineWeight.add(locoGrossWeight_field);    
      	inputPanel.add(engineWeight);
    	// ----------------- //
    	       
    	
    	locoPanel.getContentPane().add(inputPanel, BorderLayout.CENTER);

    	
    	// Size the frame.
    	locoPanel.pack();
    	locoPanel.setSize(DEFAULT_POP_PANEL_WIDTH,DEFAULT_POP_PANEL_HEIGHT);

    	// Create the bottom 'save' panel
    	btmPanel = new JPanel();
    	btmPanel.setBackground(Color.LIGHT_GRAY);
    	btmPanel.setLayout(new FlowLayout());

    	JButton loadButton = new JButton("Save Locomotive");
    	loadButton.setBackground(Color.WHITE);
    	loadButton.addActionListener(this);
    	btmPanel.add(loadButton);

    	locoPanel.add(btmPanel, BorderLayout.SOUTH);

    	// Hide it to be shown when we're ready.
    	locoPanel.setVisible(false);
	}  
    
	
    /**
     * The 'Add Passenger Carriage' GUI pop up window. Builds a window with 2 input fields:
     *  seats and weight (both inputs) & adds action listener on save button 
     * 
     */
    private void createAddPass() {
    	passPanel = new JFrame("Add Passenger Carriage");
    	passPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    	// Create input panel
    	JPanel inputPanel = new JPanel();
    	inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER, INPUT_PANEL_POS, INPUT_PANEL_POS));

    	// -- Create inputs -- //  	  	
      	grossWeight_field = new JTextField();
      	grossWeight_field.setColumns(COLUMN_COUNT);

      	inputPanel.add(new Label("Gross Weight:"));
      	inputPanel.add(grossWeight_field);           
      	
       	
       	numberOfSeats_field = new JTextField();
       	numberOfSeats_field.setColumns(COLUMN_COUNT);

       	inputPanel.add(new Label("Number of seats:"));
       	inputPanel.add(numberOfSeats_field);           
    	// ------------------ //
       	
       	passPanel.getContentPane().add(inputPanel, BorderLayout.CENTER);
    	
    	// Size the frame.
    	passPanel.pack();
    	passPanel.setSize(DEFAULT_POP_PANEL_WIDTH,DEFAULT_POP_PANEL_HEIGHT);

    	
    	// Create bottom 'save' panel
    	btmPanel = new JPanel();
    	btmPanel.setBackground(Color.LIGHT_GRAY);
    	btmPanel.setLayout(new FlowLayout());

    	JButton loadButton = new JButton("Save Passenger Carriage");
    	loadButton.setBackground(Color.WHITE);
    	loadButton.addActionListener(this);
    	btmPanel.add(loadButton);

    	passPanel.add(btmPanel, BorderLayout.SOUTH);

    	// Hide it to be shown when we're ready.
    	passPanel.setVisible(false);
	}
    
    
    /**
     * The 'Add Freight Carriage' GUI pop up window. Builds a window with 2 input fields:
     * goods type (dropdown) and weight (input) also adds action listener on save button 
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void createAddFreight() {
    	freightPanel = new JFrame("Add freight carriage");
    	freightPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    	// Create input panel
    	JPanel inputPanel = new JPanel();
    	inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER, INPUT_PANEL_POS, INPUT_PANEL_POS));

    	// -- Create inputs -- //  	
    	//Create the goods type list box
    	inputPanel.add(new Label("Goods Type:"));
    	String[] goodTypes = { "General Goods", "Refrigerated Goods", "Dangerous Materials" }; // list of goods types
    	goodsList = new JComboBox(goodTypes); // listbox element

    	inputPanel.add(goodsList); // add it to our input panel
    	
    	
    	// Create the gross weight input box
    	inputPanel.add(new Label("Gross Weight:"));
    	freightGrossWeight_field = new JTextField();
    	freightGrossWeight_field.setColumns(COLUMN_COUNT);

       	inputPanel.add(freightGrossWeight_field);           

       	
    	// ----------------- //
    	
    	
    	freightPanel.getContentPane().add(inputPanel, BorderLayout.CENTER);

    	
    	// Size the frame.
    	freightPanel.pack();
    	freightPanel.setSize(DEFAULT_POP_PANEL_WIDTH,DEFAULT_POP_PANEL_HEIGHT);

    	
    	// Save panel 
    	btmPanel = new JPanel();
    	btmPanel.setBackground(Color.LIGHT_GRAY);
    	btmPanel.setLayout(new FlowLayout());

    	JButton loadButton = new JButton("Save Freight Carriage");
    	loadButton.setBackground(Color.WHITE);
    	loadButton.addActionListener(this);
    	btmPanel.add(loadButton);

    	freightPanel.add(btmPanel, BorderLayout.SOUTH);

    	// Hide it to be shown when we're ready.
    	freightPanel.setVisible(false);
	}  

    private void createBoardPassengers() {
    	boardPanel = new JFrame("Board Passengers");
    	//2. Optional: What happens when the frame closes?
    	boardPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    	// Create input panel
    	JPanel inputPanel = new JPanel();
    	inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER, INPUT_PANEL_POS, INPUT_PANEL_POS));

    	// -- Create inputs -- //  	
    	//Create the engine list box
    	JPanel boardPassengers = new JPanel();
    	boardPassengers.add(new Label("Board Passengers:"));
    	boardPassengers_field = new JTextField();
    	boardPassengers_field.setColumns(COLUMN_COUNT);
      	boardPassengers.add(boardPassengers_field);
      	inputPanel.add(boardPassengers);
    	// ----------------- //       
    	
    	boardPanel.getContentPane().add(inputPanel, BorderLayout.CENTER);

    	
    	//4. Size the frame.
    	boardPanel.pack();
    	boardPanel.setSize(DEFAULT_POP_PANEL_WIDTH,DEFAULT_POP_PANEL_HEIGHT);

    	btmPanel = new JPanel();
    	btmPanel.setBackground(Color.LIGHT_GRAY);
    	btmPanel.setLayout(new FlowLayout());

    	JButton boardButton = new JButton("Board");
    	boardButton.setBackground(Color.WHITE);
    	boardButton.addActionListener(this);
    	btmPanel.add(boardButton);

    	boardPanel.add(btmPanel, BorderLayout.SOUTH);

    	//5. Hide it to be shown when we're ready.
    	boardPanel.setVisible(false);
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
	    
	    JButton boardButton = new JButton("Board Passengers");
	    boardButton.setBackground(Color.WHITE);
	    boardButton.addActionListener(this);
	    btmPanel.add(boardButton);

	    
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
				trainStatus(e1.getMessage());
			}
		} else if(buttonString.equals("Board Passengers")) {
			boardPanel.setVisible(true);
		} else if(buttonString.equals("Save Locomotive")) {
			String EngineType = "";
			// fetch logo info from our input objects
			if((String) engineList.getSelectedItem() == "Electric") {
				EngineType = "E";
			} else if((String) engineList.getSelectedItem() == "Diesel") {
				EngineType = "D";
			} else if((String) engineList.getSelectedItem() == "Steam") {
				EngineType = "S";
			}
			this.trainClassification = (String)(String) powerList.getSelectedItem() + "" + EngineType; 
			this.trainGrossWeight = Integer.parseInt(locoGrossWeight_field.getText());
			
			
			// Create the locomotive carriage
			try {
				Locomotive locomotive = new Locomotive(this.trainGrossWeight, this.trainClassification);
				myTrain.addCarriage(locomotive);
				locoPanel.setVisible(false);
				drawTrain();
			} catch (TrainException e1) {
				trainStatus(e1.getMessage());
			}
					  
		  
	  } else if(buttonString.equals("Save Passenger Carriage")) {
			// fetch info from our input objects
			this.grossWeight = Integer.parseInt(grossWeight_field.getText());
			this.numberOfSeats = Integer.parseInt(numberOfSeats_field.getText());
			
			
			// Create the passenger carriage
			try {
				PassengerCar pass = new PassengerCar(this.grossWeight, this.numberOfSeats);	
				myTrain.addCarriage(pass);
				
				
				// Clear window
				numberOfSeats_field.setText("");
				grossWeight_field.setText("");
				
				passPanel.setVisible(false);
				drawTrain();
			} catch (TrainException e1) {
				trainStatus(e1.getMessage());
			}
					  
		  
	  } else if(buttonString.equals("Save Freight Carriage")) {
			// fetch info from our input objects
			Integer freight_grossWeight = Integer.parseInt(freightGrossWeight_field.getText());
			String freight_goods = "";
			if((String) goodsList.getSelectedItem() == "General Goods") {
				freight_goods = "G";
			} else if((String) goodsList.getSelectedItem() == "Refrigerated Goods") {
				freight_goods = "R";
			} else if((String) goodsList.getSelectedItem() == "Dangerous Materials") {
				freight_goods = "D";
			}

			// Create the carriage
			try {
				FreightCar freight = new FreightCar(freight_grossWeight, freight_goods);	
				myTrain.addCarriage(freight);
				
				
				// Clear window
				freightGrossWeight_field.setText("");
				
				freightPanel.setVisible(false);
				drawTrain();
			} catch (TrainException e1) {
				trainStatus(e1.getMessage());
			}
				
	  } else if(buttonString.equals("Board")) {
			// fetch info from our input objects
			Integer passengersToBoard = Integer.parseInt(boardPassengers_field.getText());

			// Board the passengers
			try {
				leftOverPassengers = myTrain.board(passengersToBoard);

				// Clear window
				boardPassengers_field.setText("");
				boardPanel.setVisible(false);
				drawTrain();
			} catch (TrainException e1) {
				trainStatus(e1.getMessage());
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
		GUI gui = new GUI("Departing Train");
	    gui.setVisible(true);

	}
	
}
