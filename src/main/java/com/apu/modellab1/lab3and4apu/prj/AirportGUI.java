package com.apu.modellab1.lab3and4apu.prj;

import com.apu.modellab1.lab3and4apu.*;
import com.apu.modellab1.toLab3and4.*;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

import process.Dispatcher;
import process.IModelFactory;
import rnd.Negexp;
import widgets.ChooseData;
import widgets.ChooseRandom;
import widgets.Diagram;
import widgets.stat.StatisticsManager;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;

public class AirportGUI extends JFrame{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private Diagram diagramTicketboxQueueSize = null;
        private Diagram diagramStewardessQueueSize = null;
        private Diagram diagramAirplaneQueueSize = null;
        private Diagram diagramRailwayQueueSize = null;

	private JButton jButtonStart = null;

	private JTabbedPane jTabbedPane = null;

	private JPanel jPanelTest = null;

	private JPanel jPanelModelParameters = null;

	private JCheckBox jCheckBox = null;

	private JPanel jPanelStat = null;

	private ChooseRandom chooseRandomPassInterval = null;
	private ChooseRandom chooseRandomTicketboxHandleTime = null;
        private ChooseRandom chooseRandomFlyInterval = null;
	private ChooseData chooseTicketboxAmount = null;
        private ChooseData chooseQueueMaxSize = null;
        private ChooseData choosePassengersPerFly = null;
	private ChooseData chooseDataFinishTime = null;
	private StatisticsManager statisticsManager;

	public AirportGUI() {
		super();
		initialize();
	}


	/**
	 * This method initializes jButtonStart
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonStart() {
		if (jButtonStart == null) {
			jButtonStart = new JButton();
			jButtonStart.setText("Start");
			jButtonStart.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					startTest();
				}
			});
		}
		return jButtonStart;
	}

	private void startTest() {
		getJButtonStart().setEnabled(false);
		getDiagramTicketboxQueue().clear();
                getDiagramStewardessQueue().clear();
                getDiagramAirplaneQueue().clear();
                getDiagramRailwayQueue().clear();
		Dispatcher dispatcher = new Dispatcher();		
		dispatcher.addDispatcherFinishListener(
				()->getJButtonStart().setEnabled(true));
		IModelFactory factory = (d)-> new AirportModel(d, this);
		AirportModel model =(AirportModel) factory.createModel(dispatcher);
		model.initForTest();
		dispatcher.start();
	}

	


	/**
	 * This method initializes jTabbedPane
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setName("");
			jTabbedPane.setVisible(true);
			jTabbedPane.setFont(new java.awt.Font("Courier New",
					java.awt.Font.PLAIN, 14));
			jTabbedPane.addTab("Test", null, getJPanelTest(), null);
			jTabbedPane.addTab("Stat", null, getJPanelStat(),
					"Statistic characteristics");

		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanelTest
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelTest() {
		if (jPanelTest == null) {
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.insets = new Insets(4, 8, 3, 5);
			gridBagConstraints13.gridy = 2;
			gridBagConstraints13.gridx = 0;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.insets = new Insets(2, 3, 0, 4);
			gridBagConstraints12.gridy = 2;
			gridBagConstraints12.ipadx = 10;
			gridBagConstraints12.ipady = -1;
			gridBagConstraints12.anchor = GridBagConstraints.EAST;
			gridBagConstraints12.gridx = 1;
                        
			GridBagConstraints gbc_diagramTicketboxQueueSize = new GridBagConstraints();
			gbc_diagramTicketboxQueueSize.insets = new Insets(4, 3, 5, 4);
			gbc_diagramTicketboxQueueSize.gridx = 0;
			gbc_diagramTicketboxQueueSize.gridy = 0;
//			gbc_diagramTicketboxQueueSize.ipadx = 183;
//			gbc_diagramTicketboxQueueSize.ipady = -86;
			gbc_diagramTicketboxQueueSize.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gbc_diagramTicketboxQueueSize.weightx = 0.5D;
			gbc_diagramTicketboxQueueSize.weighty = 2.0D;
			gbc_diagramTicketboxQueueSize.gridwidth = 1;
                        
                        GridBagConstraints gbc_diagramStewardessQueueSize = new GridBagConstraints();
			gbc_diagramStewardessQueueSize.insets = new Insets(4, 3, 5, 4);
			gbc_diagramStewardessQueueSize.gridx = 1;
			gbc_diagramStewardessQueueSize.gridy = 0;
//			gbc_diagramStewardessQueueSize.ipadx = 183;
//			gbc_diagramStewardessQueueSize.ipady = -86;
			gbc_diagramStewardessQueueSize.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gbc_diagramStewardessQueueSize.weightx = 0.5D;
			gbc_diagramStewardessQueueSize.weighty = 2.0D;
			gbc_diagramStewardessQueueSize.gridwidth = 1;
                        
                        GridBagConstraints gbc_diagramAirplaneQueueSize = new GridBagConstraints();
			gbc_diagramAirplaneQueueSize.insets = new Insets(4, 3, 5, 4);
			gbc_diagramAirplaneQueueSize.gridx = 0;
			gbc_diagramAirplaneQueueSize.gridy = 1;
			gbc_diagramAirplaneQueueSize.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gbc_diagramAirplaneQueueSize.weightx = 0.5D;
			gbc_diagramAirplaneQueueSize.weighty = 2.0D;
			gbc_diagramAirplaneQueueSize.gridwidth = 1;
                        
                        GridBagConstraints gbc_diagramRailwayQueueSize = new GridBagConstraints();
			gbc_diagramRailwayQueueSize.insets = new Insets(4, 3, 5, 4);
			gbc_diagramRailwayQueueSize.gridx = 1;
			gbc_diagramRailwayQueueSize.gridy = 1;
			gbc_diagramRailwayQueueSize.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gbc_diagramRailwayQueueSize.weightx = 0.5D;
			gbc_diagramRailwayQueueSize.weighty = 2.0D;
			gbc_diagramRailwayQueueSize.gridwidth = 1;
                        
			jPanelTest = new JPanel();
			GridBagLayout gbl_jPanelTest = new GridBagLayout();
			gbl_jPanelTest.rowWeights = new double[]{0.0, 0.0};
			gbl_jPanelTest.rowHeights = new int[]{0, 0};
			jPanelTest.setLayout(gbl_jPanelTest);
                        
			jPanelTest.add(getDiagramTicketboxQueue(), gbc_diagramTicketboxQueueSize);
                        jPanelTest.add(getDiagramStewardessQueue(), gbc_diagramStewardessQueueSize);
                        jPanelTest.add(getDiagramAirplaneQueue(), gbc_diagramAirplaneQueueSize);
                        jPanelTest.add(getDiagramRailwayQueue(), gbc_diagramRailwayQueueSize);
			jPanelTest.add(getJButtonStart(), gridBagConstraints12);
			jPanelTest.add(getJCheckBox(), gridBagConstraints13);
			jPanelTest
			.addComponentListener(new java.awt.event.ComponentAdapter() {
				public void componentShown(
						java.awt.event.ComponentEvent e) {
					onPanelTestComponentShown();
				}
			});
		}
		return jPanelTest;
	}

	/**
	 * This method initializes jPanelModelParameters
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelModelParameters() {
		if (jPanelModelParameters == null) {
			jPanelModelParameters = new JPanel();
			jPanelModelParameters.setLayout(null);
			jPanelModelParameters
					.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Research airport process", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
			jPanelModelParameters.setPreferredSize(new Dimension(262, 436));
			jPanelModelParameters.setMinimumSize(new Dimension(262, 436));
			jPanelModelParameters.add(getChooseRandomPassInterval(), null);
			jPanelModelParameters.add(getChooseRandomTicketboxHandleTime(), null);
			jPanelModelParameters.add(getChooseTicketboxAmount(), null);
                        jPanelModelParameters.add(getChooseQueueMaxSize(), null);
                        jPanelModelParameters.add(getChooseRandomFlyInterval(), null);
                        jPanelModelParameters.add(getChoosePassengersPerFly(), null);
			jPanelModelParameters.add(getChooseModellingFinishTime(), null);
		}
		return jPanelModelParameters;
	}

	/**
	 * This method initializes jCheckBox
	 * 
	 * @return javax.swing.JCheckBox
	 */
	public JCheckBox getJCheckBox() {
		if (jCheckBox == null) {
			jCheckBox = new JCheckBox();
			jCheckBox
					.setActionCommand("Put protocol to console");
			jCheckBox.setText("Protocol to console");
			jCheckBox.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
		}
		return jCheckBox;
	}

	/**
	 * This method initializes jPanel3
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelStat() {
		if (jPanelStat == null) {
			jPanelStat = new JPanel();
			jPanelStat.setLayout(new CardLayout(0, 0));
			jPanelStat.add(getStatisticsManager(), "name_131147950583608");
		}
		return jPanelStat;
	}

	/**
	 * This method initializes rndBuldo	
	 * 	
	 * @return widgets.ChooseRandom	
	 */
	public ChooseRandom getChooseRandomPassInterval() {
		if (chooseRandomPassInterval == null) {
			chooseRandomPassInterval = new ChooseRandom();
			chooseRandomPassInterval.setRandom(new Negexp(1));
			chooseRandomPassInterval.setTitle("Timeout between passengers");
			chooseRandomPassInterval.setBounds(new Rectangle(4, 30, 201, 52));
		}
		return chooseRandomPassInterval;
	}


	/**
	 * This method initializes rndLoader	
	 * 	
	 * @return widgets.ChooseRandom	
	 */
	public ChooseRandom getChooseRandomTicketboxHandleTime() {
		if (chooseRandomTicketboxHandleTime == null) {
			chooseRandomTicketboxHandleTime = new ChooseRandom();
			chooseRandomTicketboxHandleTime.setRandom(new Negexp(0.8));
			chooseRandomTicketboxHandleTime.setTitle("Ticketbox handling time");
			chooseRandomTicketboxHandleTime.setBounds(new Rectangle(4, 90, 201, 52));
		}
		return chooseRandomTicketboxHandleTime;
	}
        
        public ChooseRandom getChooseRandomFlyInterval() {
		if (chooseRandomFlyInterval == null) {
			chooseRandomFlyInterval = new ChooseRandom();
			chooseRandomFlyInterval.setRandom(new Negexp(1));
			chooseRandomFlyInterval.setTitle("Airplane fly time");
			chooseRandomFlyInterval.setBounds(new Rectangle(4, 325, 201, 52));
		}
		return chooseRandomFlyInterval;
	}        

	/**
	 * This method initializes chooseDataHeapMaxSize	
	 * 	
	 * @return widgets.ChooseData	
	 */
	public ChooseData getChooseTicketboxAmount() {
		if (chooseTicketboxAmount == null) {
			chooseTicketboxAmount = new ChooseData();
			chooseTicketboxAmount.setBounds(new Rectangle(4, 148, 201, 53));
			chooseTicketboxAmount.setTitle("Ticketbox amount");
			chooseTicketboxAmount.setText("1");
		}
		return chooseTicketboxAmount;
	}
        
        public ChooseData getChooseQueueMaxSize() {
		if (chooseQueueMaxSize == null) {
			chooseQueueMaxSize = new ChooseData();
			chooseQueueMaxSize.setBounds(new Rectangle(4, 207, 201, 53));
			chooseQueueMaxSize.setTitle("Queue max size");
			chooseQueueMaxSize.setText("1");
		}
		return chooseQueueMaxSize;
	}
        
        public ChooseData getChoosePassengersPerFly() {
		if (choosePassengersPerFly == null) {
			choosePassengersPerFly = new ChooseData();
			choosePassengersPerFly.setBounds(new Rectangle(4, 384, 201, 53));
			choosePassengersPerFly.setTitle("Passengers per fly");
			choosePassengersPerFly.setText("10");
		}
		return choosePassengersPerFly;
	}        

	/**
	 * This method initializes chooseDataFinishTime	
	 * 	
	 * @return widgets.ChooseData	
	 */
	public ChooseData getChooseModellingFinishTime() {
		if (chooseDataFinishTime == null) {
			chooseDataFinishTime = new ChooseData();
			chooseDataFinishTime.setBounds(new Rectangle(4, 266, 201, 53));
			chooseDataFinishTime.setTitle("Modelling timeout");
			chooseDataFinishTime.setText("500");
			chooseDataFinishTime
			.addCaretListener(new javax.swing.event.CaretListener() {
				public void caretUpdate(javax.swing.event.CaretEvent e) {
					onTimeCaretUpdate();
				}


			});

		}
		return chooseDataFinishTime;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AirportGUI application = new AirportGUI();
		application.setVisible(true);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(667, 317);
		this.setContentPane(getJContentPane());
		this.setTitle("Simple SMO research by imitation way");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.ipadx = -50;
			gridBagConstraints7.anchor = GridBagConstraints.WEST;
			gridBagConstraints7.insets = new Insets(9, 10, 7, 2);
			gridBagConstraints7.gridy = 0;
			gridBagConstraints7.gridx = 0;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.gridx = 1;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.ipadx = -96;
			gridBagConstraints6.ipady = -294;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.weighty = 1.0;
			gridBagConstraints6.insets = new Insets(8, 3, 6, 7);
			jContentPane = new JPanel();
			GridBagLayout gbl_jContentPane = new GridBagLayout();
			gbl_jContentPane.columnWeights = new double[]{0.0, 0.0};
			gbl_jContentPane.columnWidths = new int[]{204, 0};
			jContentPane.setLayout(gbl_jContentPane);
			jContentPane.add(getJTabbedPane(), gridBagConstraints6);
			jContentPane.add(getJPanelModelParameters(), gridBagConstraints7);
		}
		return jContentPane;
	}




	/**
	 * This method initializes diagramHeepSize
	 * 
	 * @see buldo2011.IBuldoGUI
	 * @return paint.Diagram
	 */
	public Diagram getDiagramTicketboxQueue() {
		if (diagramTicketboxQueueSize == null) {
			diagramTicketboxQueueSize = new Diagram();
			diagramTicketboxQueueSize.setVerticalMaxText("20");
			diagramTicketboxQueueSize.setHorizontalMaxText("500");
			diagramTicketboxQueueSize.setTitleText("Ticketbox queue size");
			diagramTicketboxQueueSize.setPainterColor(new java.awt.Color(204, 102, 0));
		}
		return diagramTicketboxQueueSize;
	}

        public Diagram getDiagramStewardessQueue() {
		if (diagramStewardessQueueSize == null) {
			diagramStewardessQueueSize = new Diagram();
			diagramStewardessQueueSize.setVerticalMaxText("20");
			diagramStewardessQueueSize.setHorizontalMaxText("500");
			diagramStewardessQueueSize.setTitleText("Stewardess queue size");
			diagramStewardessQueueSize.setPainterColor(new java.awt.Color(204, 102, 0));
		}
		return diagramStewardessQueueSize;
	}
        
        public Diagram getDiagramAirplaneQueue() {
		if (diagramAirplaneQueueSize == null) {
			diagramAirplaneQueueSize = new Diagram();
			diagramAirplaneQueueSize.setVerticalMaxText("20");
			diagramAirplaneQueueSize.setHorizontalMaxText("500");
			diagramAirplaneQueueSize.setTitleText("Airplane queue size");
			diagramAirplaneQueueSize.setPainterColor(new java.awt.Color(204, 102, 0));
		}
		return diagramAirplaneQueueSize;
	}
        
        public Diagram getDiagramRailwayQueue() {
		if (diagramRailwayQueueSize == null) {
			diagramRailwayQueueSize = new Diagram();
			diagramRailwayQueueSize.setVerticalMaxText("20");
			diagramRailwayQueueSize.setHorizontalMaxText("500");
			diagramRailwayQueueSize.setTitleText("Railway queue size");
			diagramRailwayQueueSize.setPainterColor(new java.awt.Color(204, 102, 0));
		}
		return diagramRailwayQueueSize;
	}


	private StatisticsManager getStatisticsManager() {
		if (statisticsManager == null) {
			statisticsManager = new StatisticsManager();
			statisticsManager.setFactory((d)-> new AirportModel(d, this));
		}
		return statisticsManager;
	}
	
	protected void onTimeCaretUpdate() {
		if (getJPanelTest().isShowing()) {
			getDiagramTicketboxQueue().setHorizontalMaxText(
					chooseDataFinishTime.getText());
		}
	}


	protected void onPanelTestComponentShown() {
		// Штучно формуємо подію CaretUpdate,
		// щоб обновити налаштування діаграми
		getChooseModellingFinishTime().select(0,0);
	}
}  
