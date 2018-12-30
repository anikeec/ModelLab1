package com.apu.modellab1.example.market;

import java.awt.CardLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import process.Dispatcher;
import rnd.Erlang;
import rnd.Negexp;
import widgets.ChooseData;
import widgets.ChooseRandom;
import widgets.Diagram;

public class MarketGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private ChooseRandom chooseRandomAdvertising = null;
	private ChooseRandom chooseRandomContact = null;
	private Diagram diagram = null;
	private JButton jButtonStartExperiment = null;
	private JPanel jPanelAdvertisingRnd = null;
	private JPanel jPanelContactRnd = null;
	private ChooseData chooseDataNumberOfClients = null;
	private ChooseData chooseDataFinishTime = null;

	/**
	 * This method initializes chooseRandomAdvertising
	 * 
	 * @return rnd.ChooseRandom
	 */
	public ChooseRandom getChooseRandomAdvertising() {
		if (chooseRandomAdvertising == null) {
			chooseRandomAdvertising = new ChooseRandom();
			chooseRandomAdvertising.setRandom(new Erlang(5D, 2));
		}
		return chooseRandomAdvertising;
	}

	/**
	 * This method initializes chooseRandomContact
	 * 
	 * @return rnd.ChooseRandom
	 */
	public ChooseRandom getChooseRandomContact() {
		if (chooseRandomContact == null) {
			chooseRandomContact = new ChooseRandom();
			chooseRandomContact.setRandom(new Negexp(1));
		}
		return chooseRandomContact;
	}

	/**
	 * This method initializes diagram
	 * 
	 * @return paint.Diagram
	 */
	public Diagram getDiagram() {
		if (diagram == null) {
			diagram = new Diagram();
			diagram.setBounds(new Rectangle(244, 13, 292, 203));
			diagram.setVerticalMaxText("500");
			diagram.setPainterColor(java.awt.Color.magenta);
			diagram.setGridByX(6);
			diagram.setTitleText("Кількість залучених");
			diagram.setHorizontalMaxText("12");
		}
		return diagram;
	}

	/**
	 * This method initializes jButtonStartExperiment
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonStartExperiment() {
		if (jButtonStartExperiment == null) {
			jButtonStartExperiment = new JButton();
			jButtonStartExperiment.setBounds(new Rectangle(334, 241, 83, 26));
			jButtonStartExperiment.setText("Старт");
			jButtonStartExperiment
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							onClickButtonStart();
						}
					});
		}
		return jButtonStartExperiment;
	}

	/**
	 * This method initializes jPanelIdvertisingRnd
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelIdvertisingRnd() {
		if (jPanelAdvertisingRnd == null) {
			jPanelAdvertisingRnd = new JPanel();
			jPanelAdvertisingRnd.setLayout(new CardLayout());
			jPanelAdvertisingRnd.setBounds(new Rectangle(14, 73, 215, 68));
			jPanelAdvertisingRnd
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									new javax.swing.border.SoftBevelBorder(
											javax.swing.border.SoftBevelBorder.RAISED),
									"Вплив реклами",
									javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									new java.awt.Font("Dialog",
											java.awt.Font.BOLD, 12),
									new java.awt.Color(51, 51, 51)));
			jPanelAdvertisingRnd.add(getChooseRandomAdvertising(),
					getChooseRandomAdvertising().getName());
		}
		return jPanelAdvertisingRnd;
	}

	/**
	 * This method initializes jPanelContactRnd
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelContactRnd() {
		if (jPanelContactRnd == null) {
			jPanelContactRnd = new JPanel();
			jPanelContactRnd.setLayout(new CardLayout());
			jPanelContactRnd.setBounds(new Rectangle(17, 149, 210, 69));
			jPanelContactRnd
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									new javax.swing.border.SoftBevelBorder(
											javax.swing.border.SoftBevelBorder.RAISED),
									"Вплив контактів",
									javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									null, null));
			jPanelContactRnd.add(getChooseRandomContact(),
					getChooseRandomContact().getName());
		}
		return jPanelContactRnd;
	}

	/**
	 * This method initializes chooseDataNumberOfClients
	 * 
	 * @return widgets.ChooseData
	 */
	public ChooseData getChooseDataNumberOfClients() {
		if (chooseDataNumberOfClients == null) {
			chooseDataNumberOfClients = new ChooseData();
			chooseDataNumberOfClients.setBounds(new Rectangle(64, 15, 114, 43));
			chooseDataNumberOfClients.setText("500");
			chooseDataNumberOfClients.setHorizontalAlignment(JTextField.CENTER);
			chooseDataNumberOfClients.setTitle("Кількість клієнтів");
			chooseDataNumberOfClients.addCaretListener(new CaretListener() {

				public void caretUpdate(CaretEvent e) {
					if (getDiagram().getVerticalMaxText() != getChooseDataNumberOfClients()
							.getText())
						getDiagram().setVerticalMaxText(
								getChooseDataNumberOfClients().getText());
				}
			});
		}
		return chooseDataNumberOfClients;
	}

	/**
	 * This method initializes chooseDataFinishTime
	 * 
	 * @return widgets.ChooseData
	 */
	public ChooseData getChooseDataFinishTime() {
		if (chooseDataFinishTime == null) {
			chooseDataFinishTime = new ChooseData();
			chooseDataFinishTime.setBounds(new Rectangle(65, 229, 117, 43));
			chooseDataFinishTime.setHorizontalAlignment(JTextField.CENTER);
			chooseDataFinishTime.setTitle("Час моделювання");
			chooseDataFinishTime.setText("12");
			chooseDataFinishTime.addCaretListener(new CaretListener() {

				public void caretUpdate(CaretEvent e) {
					if (getDiagram().getHorizontalMaxText() != getChooseDataFinishTime()
							.getText())
						getDiagram().setHorizontalMaxText(
								getChooseDataFinishTime().getText());
				}
			});

		}
		return chooseDataFinishTime;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MarketGUI application = new MarketGUI();
		application.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public MarketGUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(558, 319);
		this.setContentPane(getJContentPane());
		this.setTitle("Маркетингове дослідження");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getDiagram(), null);
			jContentPane.add(getJButtonStartExperiment(), null);
			jContentPane.add(getJPanelIdvertisingRnd(), null);
			jContentPane.add(getJPanelContactRnd(), null);
			jContentPane.add(getChooseDataNumberOfClients(), null);
			jContentPane.add(getChooseDataFinishTime(), null);
		}
		return jContentPane;
	}

	private void onClickButtonStart() {
		getDiagram().getPainter().placeToXY(0, 0);
		final Dispatcher dispatcher = new Dispatcher();
		MarketModel model = new MarketModel(dispatcher, this);
		model.initForStart();
		getJButtonStartExperiment().setEnabled(false);
		dispatcher.start();
		new Thread(){
			public void run(){
				try {
					dispatcher.getThread().join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getJButtonStartExperiment().setEnabled(true);
			}
		}.start();

	}
} // @jve:decl-index=0:visual-constraint="66,39"
