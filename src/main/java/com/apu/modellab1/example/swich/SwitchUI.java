package com.apu.modellab1.example.swich;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import rnd.Erlang;
import rnd.Negexp;
import rnd.Randomable;
import stat.StatTables;
import widgets.ChooseRandom;
import widgets.Diagram;
import widgets.Painter;

public class SwitchUI extends JFrame implements ITestSwitchUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private ChooseRandom chooseRandomFileStream = null;

	private ChooseRandom chooseRandomFileSize = null;

	private Diagram diagram1 = null;

	private JButton jButtonStart = null;

	private TestSwitchModel model = new TestSwitchModel();

	private Diagram diagram2 = null;

	private Erlang erlangForFileSize = null; // @jve:decl-index=0:visual-constraint=""

	private JPanel jPanelParameters = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JPanel jPanel = null;

	private JLabel jLabel2 = null;

	private JTextField jTextFieldNumberOfPort = null;

	private JPanel jPanel1 = null;

	private JLabel jLabel3 = null;

	private JTextField jTextPackageSize = null;

	private JPanel jPanel2 = null;

	private JLabel jLabel4 = null;

	private JTextField jTextFieldSwitchCapacity = null;

	private JPanel jPanel3 = null;

	private JPanel jPanel4 = null;

	private JLabel jLabel5 = null;

	private JTextField jTextFieldFinishTime = null;

	private JPanel jPanel5 = null;

	private JPanel jPanel6 = null;

	private JLabel jLabel6 = null;

	private JTextField jTextFieldBufferSize = null;

	private JPanel jPanelChargeCoeff = null;

	private JLabel jLabelChargeCoeff = null;

	private JPanel jPanelCurrentTime = null;

	private JTextField jTextFieldCurrentTime = null;

	/**
	 * This method initializes chooseRandomTransGen
	 * 
	 * @return rnd.ChooseRandom
	 */
	private ChooseRandom getChooseRandomFileStream() {
		if (chooseRandomFileStream == null) {
			Negexp negexp = new Negexp();
			negexp.setM(0.5D);
			chooseRandomFileStream = new ChooseRandom();
			chooseRandomFileStream.setPreferredSize(new java.awt.Dimension(293,
					35));
			chooseRandomFileStream.setRandom(negexp);
			chooseRandomFileStream.setBounds(new java.awt.Rectangle(11, 43,
					239, 35));
		}
		return chooseRandomFileStream;
	}

	/**
	 * This method initializes chooseRandomGetPut
	 * 
	 * @return rnd.ChooseRandom
	 */
	private ChooseRandom getChooseRandomFileSize() {
		if (chooseRandomFileSize == null) {
			chooseRandomFileSize = new ChooseRandom();
			chooseRandomFileSize.setBounds(new java.awt.Rectangle(12, 106, 239,
					35));
			chooseRandomFileSize.setRandom(getErlangForFileSize());
		}
		return chooseRandomFileSize;
	}

	/**
	 * This method initializes diagram1
	 * 
	 * @return paint.Diagram
	 */
	private Diagram getDiagram1() {
		if (diagram1 == null) {
			diagram1 = new Diagram();
			diagram1.setTitleText("Заповнення буферу (Мбайт)");
			diagram1.setHorizontalMaxText("600");
			diagram1.setBounds(new java.awt.Rectangle(274, 9, 317, 141));
			diagram1.setVerticalMaxText("50");
			diagram1.setVerticalMinEnabled(false);
			diagram1.setHorizontalMaxEnabled(false);
			diagram1.setHorizontalMinEnabled(false);
			diagram1.setVerticalMaxEnabled(false);
			diagram1.setPainterColor(java.awt.Color.magenta);

		}
		return diagram1;
	}

	/**
	 * This method initializes jButtonStart
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonStart() {
		if (jButtonStart == null) {
			jButtonStart = new JButton();
			jButtonStart.setText("Старт");
			jButtonStart.setBounds(new java.awt.Rectangle(195, 20, 110, 27));
			jButtonStart.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					start();
				}
			});
		}
		return jButtonStart;
	}

	protected void start() {
		model.setUi(this);
		Thread t = new Thread() {
			public void run() {
//				model.getDispatcher().addChangeTimeListener(
//						new ChangeTimeListener(){
//
//							public void onChangeTime(ChangeTimeEvent evt) {
//								getJTextFieldCurrentTime().setText(String.valueOf(StatTables.roundTo(evt.getCurrentTime(),1)));
//								
//							}
//							
//						}
//						);
				getDiagram1().clear();
				getJButtonStart().setEnabled(false);
//				getJTextFieldFinishTime().setVisible(false);
//				getJTextFieldCurrentTime().setVisible(true);
				getDiagram1().setTitleText("Заповнення буферу (Мбайт)");
				jLabelChargeCoeff.setText(chargeCoeff());
				double max = Double.valueOf(getDiagram2()
						.getHorizontalMaxText());
				model.getHisto().initFromTo(0, max, 25);
				model.start();
				try {
					model.getDispatcher().getThread().join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getJButtonStart().setEnabled(true);
//				getJTextFieldFinishTime().setVisible(true);
//				getJTextFieldCurrentTime().setVisible(false);
				float fillCoeff=(float)StatTables.roundTo(model.getHisto().average()/getBufferSize(),0.0001);
				getDiagram1().setTitleText("Коефіцієнт заповнення буферу: "+fillCoeff);
				double[] p = new double[model.getHisto().getBorders().length + 1];
				double[] a = new double[model.getHisto().getBorders().length + 1];
				p[0] = 0;
				for (int i = 1; i < p.length; i++) {
					p[i] = model.getHisto().getBorders()[i - 1];

				}
				a[0] = 1;
				for (int i = 1; i < a.length; i++) {
					a[i] = 1 - model.getHisto().accumFrequency()[i - 1];

				}

				getDiagram2().drawDependency(p, a, Color.blue, false);

			}
		};
		t.start();
	}

	/**
	 * This method initializes diagram2
	 * 
	 * @return paint.Diagram
	 */
	private Diagram getDiagram2() {
		if (diagram2 == null) {
			diagram2 = new Diagram();
			diagram2.setBounds(new java.awt.Rectangle(274, 152, 317, 141));
			diagram2
					.setTitleText("Вирогідність втрати пакету від розміру буфера");
			diagram2.setVerticalMaxText("0.25");
			diagram2.setHorizontalMaxText("50");
			diagram2.setHorizontalMaxEnabled(false);
			diagram2.setHorizontalMinEnabled(false);
			diagram2.setVerticalMinEnabled(false);
			diagram2.setVerticalMaxEnabled(false);
			diagram2.setPainterColor(java.awt.Color.magenta);
		}
		return diagram2;
	}

	/**
	 * This method initializes erlangForFileSize
	 * 
	 * @return rnd.Erlang
	 */
	private Erlang getErlangForFileSize() {
		if (erlangForFileSize == null) {
			erlangForFileSize = new Erlang();
			erlangForFileSize.setK(3);
			erlangForFileSize.setM(10.0D);
		}
		return erlangForFileSize;
	}

	/**
	 * This method initializes jPanelParameters
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelParameters() {
		if (jPanelParameters == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new java.awt.Rectangle(12, 89, 239, 16));
			jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel1.setText("Випадковий розмір файлів (Мбайт)");
			jLabel = new JLabel();
			jLabel.setBounds(new java.awt.Rectangle(12, 28, 239, 16));
			jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel.setText("Випадковий інтервал між файлами (мін)");
			jPanelParameters = new JPanel();
			jPanelParameters
					.setBorder(javax.swing.BorderFactory
							.createCompoundBorder(
									new javax.swing.border.SoftBevelBorder(
											javax.swing.border.SoftBevelBorder.RAISED),
									javax.swing.BorderFactory
											.createTitledBorder(
													null,
													"Параметри потоку файлів на порт",
													javax.swing.border.TitledBorder.CENTER,
													javax.swing.border.TitledBorder.DEFAULT_POSITION,
													new java.awt.Font("Dialog",
															java.awt.Font.BOLD,
															12),
													new java.awt.Color(51, 51,
															51))));
			jPanelParameters.setLayout(null);
			jPanelParameters.setBounds(new java.awt.Rectangle(6, 9, 264, 192));
			jPanelParameters.add(getChooseRandomFileStream(), null);
			jPanelParameters.add(jLabel, null);
			jPanelParameters.add(jLabel1, null);
			jPanelParameters.add(getChooseRandomFileSize(), null);
			jPanelParameters.add(getJPanel1(), null);
		}
		return jPanelParameters;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel2 = new JLabel();
			jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel2.setMaximumSize(new java.awt.Dimension(195, 25));
			jLabel2.setPreferredSize(new java.awt.Dimension(95, 20));
			jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.gray, 0));
			jLabel2.setText("Кількість портів:");
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jPanel.setBounds(new java.awt.Rectangle(13, 26, 239, 33));
			jPanel.add(jLabel2, null);
			jPanel.add(getJTextFieldNumberOfPort(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNumberOfPort() {
		if (jTextFieldNumberOfPort == null) {
			jTextFieldNumberOfPort = new JTextField();
			jTextFieldNumberOfPort.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			jTextFieldNumberOfPort
					.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextFieldNumberOfPort.setMaximumSize(new java.awt.Dimension(100,
					21));
			jTextFieldNumberOfPort.setText("8");
			jTextFieldNumberOfPort
					.addCaretListener(new javax.swing.event.CaretListener() {
						public void caretUpdate(javax.swing.event.CaretEvent e) {
							jLabelChargeCoeff.setText(chargeCoeff());
						}
					});
		}
		return jTextFieldNumberOfPort;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel3 = new JLabel();
			jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.gray, 0));
			jLabel3.setPreferredSize(new java.awt.Dimension(95, 20));
			jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel3.setText("Розмір пакету (байтів):");
			jLabel3.setMaximumSize(new java.awt.Dimension(195, 25));
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BoxLayout(getJPanel1(), BoxLayout.X_AXIS));
			jPanel1.setBounds(new java.awt.Rectangle(11, 148, 239, 33));
			jPanel1.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jPanel1.add(jLabel3, null);
			jPanel1.add(getJTextPackageSize(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jTextPackageSize
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextPackageSize() {
		if (jTextPackageSize == null) {
			jTextPackageSize = new JTextField();
			jTextPackageSize.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			jTextPackageSize.setText("64");
			jTextPackageSize
					.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextPackageSize.setMaximumSize(new java.awt.Dimension(100, 21));
		}
		return jTextPackageSize;
	}

	/**
	 * This method initializes jPanel2
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel4 = new JLabel();
			jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.gray, 0));
			jLabel4.setPreferredSize(new java.awt.Dimension(95, 20));
			jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel4.setText("Швидкість(МБіт/сек):");
			jLabel4.setMaximumSize(new java.awt.Dimension(195, 25));
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BoxLayout(getJPanel2(), BoxLayout.X_AXIS));
			jPanel2.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jPanel2.setBounds(new java.awt.Rectangle(13, 68, 239, 33));
			jPanel2.add(jLabel4, null);
			jPanel2.add(getJTextFieldSwitchCapacity(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jTextField2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldSwitchCapacity() {
		if (jTextFieldSwitchCapacity == null) {
			jTextFieldSwitchCapacity = new JTextField();
			jTextFieldSwitchCapacity.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			jTextFieldSwitchCapacity.setText("100");
			jTextFieldSwitchCapacity
					.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextFieldSwitchCapacity.setMaximumSize(new java.awt.Dimension(100,
					21));
			jTextFieldSwitchCapacity
					.addCaretListener(new javax.swing.event.CaretListener() {
						public void caretUpdate(javax.swing.event.CaretEvent e) {
							jLabelChargeCoeff.setText(chargeCoeff());
						}
					});
		}
		return jTextFieldSwitchCapacity;
	}

	/**
	 * This method initializes jPanel3
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.setBounds(new java.awt.Rectangle(274, 297, 317, 61));
			jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(
					new javax.swing.border.SoftBevelBorder(
							javax.swing.border.SoftBevelBorder.RAISED),
					javax.swing.BorderFactory.createTitledBorder(null,
							"Параметри моделювання",
							javax.swing.border.TitledBorder.CENTER,
							javax.swing.border.TitledBorder.DEFAULT_POSITION,
							null, null)));
			jPanel3.add(getJPanel4(), null);
			jPanel3.add(getJButtonStart(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel4
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jLabel5 = new JLabel();
			jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.gray, 0));
			jLabel5.setPreferredSize(new java.awt.Dimension(95, 20));
			jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel5.setText("Час роботи (мін):");
			jLabel5.setMaximumSize(new java.awt.Dimension(195, 25));
			jPanel4 = new JPanel();
			jPanel4.setLayout(new BoxLayout(getJPanel4(), BoxLayout.X_AXIS));
			jPanel4.setBounds(new java.awt.Rectangle(11, 18, 178, 30));
			jPanel4.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			jPanel4.add(jLabel5, null);
			jPanel4.add(getJPanelCurrentTime(), null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jTextField3
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldFinishTime() {
		if (jTextFieldFinishTime == null) {
			jTextFieldFinishTime = new JTextField();
			jTextFieldFinishTime.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			jTextFieldFinishTime.setText("600");
			jTextFieldFinishTime
					.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextFieldFinishTime.setName("jTextFieldFinishTime");
			jTextFieldFinishTime
					.setMaximumSize(new java.awt.Dimension(100, 21));
			jTextFieldFinishTime
					.addCaretListener(new javax.swing.event.CaretListener() {
						public void caretUpdate(javax.swing.event.CaretEvent e) {
							getDiagram1().setHorizontalMaxText(
									getJTextFieldFinishTime().getText());
						}
					});
		}
		return jTextFieldFinishTime;
	}

	/**
	 * This method initializes jPanel5
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setLayout(null);
			jPanel5.setBounds(new java.awt.Rectangle(6, 205, 264, 153));
			jPanel5
					.setBorder(javax.swing.BorderFactory
							.createCompoundBorder(
									new javax.swing.border.SoftBevelBorder(
											javax.swing.border.SoftBevelBorder.RAISED),
									javax.swing.BorderFactory
											.createTitledBorder(
													null,
													"Параметри комутатора",
													javax.swing.border.TitledBorder.CENTER,
													javax.swing.border.TitledBorder.DEFAULT_POSITION,
													new java.awt.Font("Dialog",
															java.awt.Font.BOLD,
															12),
													new java.awt.Color(51, 51,
															51))));
			jPanel5.add(getJPanel(), null);
			jPanel5.add(getJPanel2(), null);
			jPanel5.add(getJPanel6(), null);
		}
		return jPanel5;
	}

	/**
	 * This method initializes jPanel6
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setBorder(BorderFactory.createLineBorder(Color.gray, 0));
			jLabel6.setPreferredSize(new Dimension(95, 20));
			jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel6.setText("Розмір буферу (MБайт):");
			jLabel6.setMaximumSize(new Dimension(195, 25));
			jPanel6 = new JPanel();
			jPanel6.setLayout(new BoxLayout(getJPanel6(), BoxLayout.X_AXIS));
			jPanel6.setBounds(new java.awt.Rectangle(13, 110, 239, 30));
			jPanel6.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));
			jPanel6.add(jLabel6, null);
			jPanel6.add(getJTextFieldBufferSize(), null);
		}
		return jPanel6;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldBufferSize() {
		if (jTextFieldBufferSize == null) {
			jTextFieldBufferSize = new JTextField();
			jTextFieldBufferSize.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));
			jTextFieldBufferSize.setText("50");
			jTextFieldBufferSize.setHorizontalAlignment(JTextField.CENTER);
			jTextFieldBufferSize.setMaximumSize(new Dimension(100, 21));
			jTextFieldBufferSize
					.addCaretListener(new javax.swing.event.CaretListener() {
						public void caretUpdate(javax.swing.event.CaretEvent e) {
							getDiagram1().setVerticalMaxText(
									getJTextFieldBufferSize().getText());
							getDiagram1().setVerticalMaxText(
									getJTextFieldBufferSize().getText());
							getDiagram2().setHorizontalMaxText(
									getJTextFieldBufferSize().getText());
						}
					});
		}
		return jTextFieldBufferSize;
	}

	/**
	 * This method initializes jPanelChargeCoeff
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelChargeCoeff() {
		if (jPanelChargeCoeff == null) {
			jLabelChargeCoeff = new JLabel();
			jLabelChargeCoeff
					.setText("Коефіціент завантаження системи при таких налаштуваннях дорівнює 0.010666666666666666");
			jLabelChargeCoeff
					.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabelChargeCoeff.setName("jLabelChargeCoeff");
			jPanelChargeCoeff = new JPanel();
			jPanelChargeCoeff.setLayout(new CardLayout());
			jPanelChargeCoeff
					.setBounds(new java.awt.Rectangle(6, 366, 585, 21));
			jPanelChargeCoeff.setBorder(javax.swing.BorderFactory
					.createLineBorder(java.awt.Color.gray, 0));
			jPanelChargeCoeff.add(jLabelChargeCoeff, jLabelChargeCoeff
					.getName());
		}
		return jPanelChargeCoeff;
	}

	/**
	 * This method initializes jPanelCurrentTime	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelCurrentTime() {
		if (jPanelCurrentTime == null) {
			jPanelCurrentTime = new JPanel();
			jPanelCurrentTime.setLayout(new CardLayout());
			jPanelCurrentTime.setMaximumSize(new java.awt.Dimension(120,21));
			jPanelCurrentTime.add(getJTextFieldFinishTime(), getJTextFieldFinishTime().getName());
			jPanelCurrentTime.add(getJTextFieldCurrentTime(), getJTextFieldCurrentTime().getName());
		}
		return jPanelCurrentTime;
	}

	/**
	 * This method initializes jTextFieldCurrentTime	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldCurrentTime() {
		if (jTextFieldCurrentTime == null) {
			jTextFieldCurrentTime = new JTextField();
			jTextFieldCurrentTime.setName("jTextFieldCurrentTime");
			jTextFieldCurrentTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextFieldCurrentTime.setVisible(false);
		}
		return jTextFieldCurrentTime;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwitchUI application = new SwitchUI();
		application.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public SwitchUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(606, 436);
		this.setContentPane(getJContentPane());
		this.setTitle("Дослідження пропускної спроможності комутатора");

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
			jContentPane.add(getDiagram1(), null);
			jContentPane.add(getDiagram2(), null);
			jContentPane.add(getJPanelParameters(), null);
			jContentPane.add(getJPanel3(), null);
			jContentPane.add(getJPanel5(), null);
			jContentPane.add(getJPanelChargeCoeff(), null);
		}
		return jContentPane;
	}

	public Painter getPainter1() {
		return getDiagram1().getPainter();
	}

	public double getFinishTime() {

		return Double.valueOf(getJTextFieldFinishTime().getText());
	}

	public Randomable getRandomFileStream() {
		return getChooseRandomFileStream().getRandom();
	}

	public Randomable getRandomFileSize() {
		return getChooseRandomFileSize().getRandom();
	}

	public double getSwitchCapacity() {
		try {
			return Double.valueOf(getJTextFieldSwitchCapacity().getText());
		} catch (Exception e) {
			return 0;
		}	
	}

	public int getNumberOfPort() {
		try {
			return Integer.valueOf(getJTextFieldNumberOfPort().getText());
		} catch (Exception e) {
			return 0;
		}
	}

	public int getPackageSize() {
		try {
			return Integer.valueOf(getJTextPackageSize().getText());
		} catch (Exception e) {
			return 0;
		}

	}
public double getBufferSize() {
	try {
		return Double.valueOf(getJTextFieldBufferSize().getText());
	} catch (Exception e) {
		return 0;
	}
	
}
	private String chargeCoeff() {
		try {
			double fileSizeInMBit = getChooseRandomFileSize().getRandom()
					.average() * 8;
			double numberFilePerSecond = 1 / getChooseRandomFileStream()
					.getRandom().average() / 60;
			double coeff = getNumberOfPort() * numberFilePerSecond
					* fileSizeInMBit / getSwitchCapacity();
			return "Коефіціент завантаження системи при таких налаштуваннях дорівнює "
					+ String.valueOf(coeff);
		} catch (Exception e) {
			return "";
		}

	}

} // @jve:decl-index=0:visual-constraint="5,20"
