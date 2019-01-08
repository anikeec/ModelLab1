package com.apu.modellab1.lab3and4apu;

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

public class GUI extends JFrame{

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private Diagram diagramQueueSize = null;

	private JButton jButtonStart = null;

	private JTabbedPane jTabbedPane = null;

	private JPanel jPanelTest = null;

	private JPanel jPanelModelParameters = null;

	private JCheckBox jCheckBox = null;

	private JPanel jPanelStat = null;

	private ChooseRandom chooseRandomGen = null;

	private ChooseRandom chooseRandomDev = null;

	private ChooseData chooseDataNdevice = null;

	private ChooseData chooseDataFinishTime = null;
	private StatisticsManager statisticsManager;

	public GUI() {
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
		getDiagramQueue().clear();
		Dispatcher dispatcher = new Dispatcher();		
		dispatcher.addDispatcherFinishListener(
				()->getJButtonStart().setEnabled(true));
		IModelFactory factory = (d)-> new Model(d, this);
		Model model =(Model) factory.createModel(dispatcher);
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
			gridBagConstraints13.gridy = 1;
			gridBagConstraints13.gridx = 0;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.insets = new Insets(2, 3, 0, 4);
			gridBagConstraints12.gridy = 1;
			gridBagConstraints12.ipadx = 10;
			gridBagConstraints12.ipady = -1;
			gridBagConstraints12.anchor = GridBagConstraints.EAST;
			gridBagConstraints12.gridx = 1;
			GridBagConstraints gbc_diagramQueueSize = new GridBagConstraints();
			gbc_diagramQueueSize.insets = new Insets(4, 3, 5, 4);
			gbc_diagramQueueSize.gridx = 0;
			gbc_diagramQueueSize.gridy = 0;
			gbc_diagramQueueSize.ipadx = 183;
			gbc_diagramQueueSize.ipady = -86;
			gbc_diagramQueueSize.fill = java.awt.GridBagConstraints.BOTH;
			gbc_diagramQueueSize.weightx = 1.0D;
			gbc_diagramQueueSize.weighty = 2.0D;
			gbc_diagramQueueSize.gridwidth = 2;
			jPanelTest = new JPanel();
			GridBagLayout gbl_jPanelTest = new GridBagLayout();
			gbl_jPanelTest.rowWeights = new double[]{0.0, 0.0};
			gbl_jPanelTest.rowHeights = new int[]{0, 0};
			jPanelTest.setLayout(gbl_jPanelTest);
			jPanelTest.add(getDiagramQueue(), gbc_diagramQueueSize);
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
					.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u041F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u0438 \u0441\u0438\u0441\u0442\u0435\u043C\u0438", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
			jPanelModelParameters.setPreferredSize(new Dimension(262, 436));
			jPanelModelParameters.setMinimumSize(new Dimension(262, 436));
			jPanelModelParameters.add(getChooseRandomGen(), null);
			jPanelModelParameters.add(getChooseRandomDev(), null);
			jPanelModelParameters.add(getChooseDataNdevice(), null);
			jPanelModelParameters.add(getChooseDataFinishTime(), null);
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
					.setActionCommand("\u0412\u044b\u0432\u043e\u0434 \u043f\u0440\u043e\u0442\u043e\u043a\u043e\u043b\u0430 \u043d\u0430 \u043a\u043e\u043d\u0441\u043e\u043b\u044c");
			jCheckBox.setText("Протокол на консоль");
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
	public ChooseRandom getChooseRandomGen() {
		if (chooseRandomGen == null) {
			chooseRandomGen = new ChooseRandom();
			chooseRandomGen.setRandom(new Negexp(1));
			chooseRandomGen.setTitle("\u0406\u043D\u0442\u0435\u0440\u0432\u0430\u043B \u043C\u0456\u0436 \u043F\u043E\u044F\u0432\u0430\u043C\u0438 \u0442\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0456\u0439");
			chooseRandomGen.setBounds(new Rectangle(4, 30, 201, 52));
		}
		return chooseRandomGen;
	}


	/**
	 * This method initializes rndLoader	
	 * 	
	 * @return widgets.ChooseRandom	
	 */
	public ChooseRandom getChooseRandomDev() {
		if (chooseRandomDev == null) {
			chooseRandomDev = new ChooseRandom();
			chooseRandomDev.setRandom(new Negexp(0.8));
			chooseRandomDev.setTitle("\u0427\u0430\u0441 \u043E\u0431\u0440\u043E\u0431\u043A\u0438 \u0442\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0456\u0457");
			chooseRandomDev.setBounds(new Rectangle(4, 90, 201, 52));
		}
		return chooseRandomDev;
	}


	/**
	 * This method initializes chooseDataHeapMaxSize	
	 * 	
	 * @return widgets.ChooseData	
	 */
	public ChooseData getChooseDataNdevice() {
		if (chooseDataNdevice == null) {
			chooseDataNdevice = new ChooseData();
			chooseDataNdevice.setBounds(new Rectangle(4, 148, 201, 53));
			chooseDataNdevice.setTitle("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u043E\u0431\u0441\u043B. \u043F\u0440\u0438\u0441\u0442\u0440\u043E\u0457\u0432");
			chooseDataNdevice.setText("1");
	

		}
		return chooseDataNdevice;
	}


	/**
	 * This method initializes chooseDataFinishTime	
	 * 	
	 * @return widgets.ChooseData	
	 */
	public ChooseData getChooseDataFinishTime() {
		if (chooseDataFinishTime == null) {
			chooseDataFinishTime = new ChooseData();
			chooseDataFinishTime.setBounds(new Rectangle(4, 207, 201, 53));
			chooseDataFinishTime.setTitle("Час моделювання");
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
		GUI application = new GUI();
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
		this
				.setTitle("\u0414\u043E\u0441\u043B\u0456\u0434\u0436\u0435\u043D\u043D\u044F \u043F\u0440\u043E\u0441\u0442\u043E\u0457 \u0421\u041C\u041E \u0448\u043B\u044F\u0445\u043E\u043C \u0456\u043C\u0456\u0442\u0430\u0446\u0456\u0439\u043D\u043E\u0433\u043E \u043C\u043E\u0434\u0435\u043B\u044E\u0432\u0430\u043D\u043D\u044F");
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
	public Diagram getDiagramQueue() {
		if (diagramQueueSize == null) {
			diagramQueueSize = new Diagram();
			diagramQueueSize.setVerticalMaxText("20");
			diagramQueueSize.setHorizontalMaxText("500");
			diagramQueueSize.setTitleText("\u0420\u043E\u0437\u043C\u0456\u0440 \u0447\u0435\u0440\u0433\u0438");
			diagramQueueSize.setPainterColor(new java.awt.Color(204, 102, 0));
		}
		return diagramQueueSize;
	}




	private StatisticsManager getStatisticsManager() {
		if (statisticsManager == null) {
			statisticsManager = new StatisticsManager();
			statisticsManager.setFactory((d)-> new Model(d, this));
		}
		return statisticsManager;
	}
	
	protected void onTimeCaretUpdate() {
		if (getJPanelTest().isShowing()) {
			getDiagramQueue().setHorizontalMaxText(
					chooseDataFinishTime.getText());
		}
	}


	protected void onPanelTestComponentShown() {
		// Штучно формуємо подію CaretUpdate,
		// щоб обновити налаштування діаграми
		getChooseDataFinishTime().select(0,0);
	}
}  
