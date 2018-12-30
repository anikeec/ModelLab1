package com.apu.modellab1.toLab1;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import stat.DiscretHisto;
import stat.Histo;
import widgets.ChooseRandom;
import widgets.Diagram;

public class App extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private Diagram diagram = null;

	private ChooseRandom chooseRandom = null;

	private Histo histo = null; // @jve:decl-index=0:visual-constraint="235,283"

	private DiscretHisto discretHisto = null; // @jve:decl-index=0:visual-constraint="297,283"

	private JPanel jPanel = null;

	private JTextField jTextFieldV = null;

	private JButton jButtonHisto = null;

	private JButton jButtonDiscretHisto = null;

	private JButton jButtonIntegral = null;

	private JPanel jPanel1 = null;

	private JTextField jTextFieldFrom = null;

	private JPanel jPanel2 = null;

	private JTextField jTextFieldTo = null;

	private JPanel jPanel3 = null;

	private JTextField jTextFieldStep = null;

	private JScrollPane jScrollPane = null;

	private JTextArea jTextArea = null;

	/**
	 * This method initializes diagram
	 * 
	 * @return paint.Diagram
	 */
	private Diagram getDiagram() {
		if (diagram == null) {
			diagram = new Diagram();
			diagram.setTitleText("Результат");
		}
		return diagram;
	}

	/**
	 * This method initializes chooseRandom
	 * 
	 * @return rnd.ChooseRandom
	 */
	private ChooseRandom getChooseRandom() {
		if (chooseRandom == null) {
			chooseRandom = new ChooseRandom();
			chooseRandom.setTitle("Налаштовано розподіл");
		}
		return chooseRandom;
	}

	/**
	 * This method initializes histo
	 * 
	 * @return stat.Histo
	 */
	private Histo getHisto() {
		if (histo == null) {
			histo = new Histo();
		}
		return histo;
	}

	/**
	 * This method initializes discretHisto
	 * 
	 * @return stat.DiscretHisto
	 */
	private DiscretHisto getDiscretHisto() {
		if (discretHisto == null) {
			discretHisto = new DiscretHisto();
		}
		return discretHisto;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new CardLayout());
			jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Обсяг вибірки",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			jPanel.add(getJTextFieldV(), getJTextFieldV().getName());
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextFieldV
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldV() {
		if (jTextFieldV == null) {
			jTextFieldV = new JTextField();
			jTextFieldV.setName("jTextFieldV");
			jTextFieldV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextFieldV.setText("1000");
		}
		return jTextFieldV;
	}

	/**
	 * This method initializes jButtonHisto
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonHisto() {
		if (jButtonHisto == null) {
			jButtonHisto = new JButton();
			jButtonHisto.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			jButtonHisto.setText("Гістограма");
			jButtonHisto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					onHisto();
				}

				protected void onHisto() {
					int v = Integer.parseInt(getJTextFieldV().getText());
					getHisto().init();
					for (int i = 0; i < v; i++)
						getHisto().add(getChooseRandom().next());
					getHisto().showRelFrec(getDiagram());
					getJTextArea().setText(getHisto().toString());
					getJTextArea().select(0, 0);
				}
			});
		}
		return jButtonHisto;
	}

	/**
	 * This method initializes jButtonDiscretHisto
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonDiscretHisto() {
		if (jButtonDiscretHisto == null) {
			jButtonDiscretHisto = new JButton();
			jButtonDiscretHisto
					.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.SoftBevelBorder.RAISED));
			jButtonDiscretHisto.setText("Дискретна гістограма");
			jButtonDiscretHisto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					onDiscretHisto();
				}

				protected void onDiscretHisto() {
					int v = Integer.parseInt(getJTextFieldV().getText());
					getDiscretHisto().init();
					if (getChooseRandom().getRandom().getClass().getName() == "rnd.Discret") {
						for (int i = 0; i < v; i++)
							getDiscretHisto().addFrequencyForValue(1, getChooseRandom().next());
						getDiscretHisto().showRelFrec(getDiagram());
						getJTextArea().setText(getDiscretHisto().toString());
						getJTextArea().select(0, 0);
					}
				}
			});
		}
		return jButtonDiscretHisto;
	}

	/**
	 * This method initializes jButtonIntegral
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonIntegral() {
		if (jButtonIntegral == null) {
			jButtonIntegral = new JButton();
			jButtonIntegral.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			jButtonIntegral.setText("Інтегральна функція");
			jButtonIntegral.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					onIntegralFunction();
				}

				protected void onIntegralFunction() {
					float from = Float.parseFloat(getJTextFieldFrom().getText());
					float to = Float.parseFloat(getJTextFieldTo().getText());
					float step = Float.parseFloat(getJTextFieldStep().getText());
					int n = Math.round((to - from) / step);
					double[] x = new double[n + 1];
					double[] y = new double[n + 1];
					for (int i = 0; i <= n; i++) {
						x[i] = from + step * i;
						y[i] = getChooseRandom().probability(x[i]);
					}
					getDiagram().drawDependency(x, y, Color.RED, true);
					getJTextArea().setText("");
				}
			});
		}
		return jButtonIntegral;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new CardLayout());
			jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Від",
					javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
					new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51, 51, 51)));
			jPanel1.add(getJTextFieldFrom(), getJTextFieldFrom().getName());
		}
		return jPanel1;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldFrom() {
		if (jTextFieldFrom == null) {
			jTextFieldFrom = new JTextField();
			jTextFieldFrom.setName("jTextFieldV");
			jTextFieldFrom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jTextFieldFrom.setText("0");
		}
		return jTextFieldFrom;
	}

	/**
	 * This method initializes jPanel2
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new CardLayout());
			jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "До",
					javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
					new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51, 51, 51)));
			jPanel2.add(getJTextFieldTo(), getJTextFieldTo().getName());
		}
		return jPanel2;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldTo() {
		if (jTextFieldTo == null) {
			jTextFieldTo = new JTextField();
			jTextFieldTo.setName("jTextFieldV");
			jTextFieldTo.setHorizontalAlignment(JTextField.CENTER);
			jTextFieldTo.setText("10");
		}
		return jTextFieldTo;
	}

	/**
	 * This method initializes jPanel3
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new CardLayout());
			jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Крок",
					javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
					new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51, 51, 51)));
			jPanel3.add(getJTextFieldStep(), getJTextFieldStep().getName());
		}
		return jPanel3;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldStep() {
		if (jTextFieldStep == null) {
			jTextFieldStep = new JTextField();
			jTextFieldStep.setName("jTextFieldV");
			jTextFieldStep.setHorizontalAlignment(JTextField.CENTER);
			jTextFieldStep.setText("0.01");
		}
		return jTextFieldStep;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTextArea
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setMargin(new Insets(5, 5, 0, 0));
		}
		return jTextArea;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		App application = new App();
		application.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public App() {
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
		this.setSize(663, 268);
		this.setContentPane(getJContentPane());
		this.setTitle("ApplicationToLab1");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			GridBagLayout gbl_jContentPane = new GridBagLayout();
			gbl_jContentPane.columnWidths = new int[] { 50, 50, 55, 221, 225, 0 };
			gbl_jContentPane.rowHeights = new int[] { 53, 24, 24, 46, 24, 0 };
			gbl_jContentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_jContentPane.rowWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
			jContentPane.setLayout(gbl_jContentPane);
			GridBagConstraints gbc_jPanel = new GridBagConstraints();
			gbc_jPanel.fill = GridBagConstraints.HORIZONTAL;
			gbc_jPanel.insets = new Insets(0, 0, 5, 5);
			gbc_jPanel.gridwidth = 3;
			gbc_jPanel.gridx = 0;
			gbc_jPanel.gridy = 0;
			jContentPane.add(getJPanel(), gbc_jPanel);
			GridBagConstraints gbc_chooseRandom = new GridBagConstraints();
			gbc_chooseRandom.fill = GridBagConstraints.HORIZONTAL;
			gbc_chooseRandom.insets = new Insets(0, 0, 5, 5);
			gbc_chooseRandom.gridx = 3;
			gbc_chooseRandom.gridy = 0;
			jContentPane.add(getChooseRandom(), gbc_chooseRandom);
			GridBagConstraints gbc_jScrollPane = new GridBagConstraints();
			gbc_jScrollPane.insets = new Insets(5, 5, 5, 5);
			gbc_jScrollPane.fill = GridBagConstraints.BOTH;
			gbc_jScrollPane.gridheight = 5;
			gbc_jScrollPane.gridx = 4;
			gbc_jScrollPane.gridy = 0;
			jContentPane.add(getJScrollPane(), gbc_jScrollPane);
			GridBagConstraints gbc_jButtonHisto = new GridBagConstraints();
			gbc_jButtonHisto.fill = GridBagConstraints.BOTH;
			gbc_jButtonHisto.insets = new Insets(10, 0, 5, 5);
			gbc_jButtonHisto.gridwidth = 3;
			gbc_jButtonHisto.gridx = 0;
			gbc_jButtonHisto.gridy = 1;
			jContentPane.add(getJButtonHisto(), gbc_jButtonHisto);
			GridBagConstraints gbc_diagram = new GridBagConstraints();
			gbc_diagram.fill = GridBagConstraints.BOTH;
			gbc_diagram.insets = new Insets(0, 0, 5, 5);
			gbc_diagram.gridheight = 4;
			gbc_diagram.gridx = 3;
			gbc_diagram.gridy = 1;
			jContentPane.add(getDiagram(), gbc_diagram);
			GridBagConstraints gbc_jButtonDiscretHisto = new GridBagConstraints();
			gbc_jButtonDiscretHisto.fill = GridBagConstraints.BOTH;
			gbc_jButtonDiscretHisto.insets = new Insets(0, 0, 5, 5);
			gbc_jButtonDiscretHisto.gridwidth = 3;
			gbc_jButtonDiscretHisto.gridx = 0;
			gbc_jButtonDiscretHisto.gridy = 2;
			jContentPane.add(getJButtonDiscretHisto(), gbc_jButtonDiscretHisto);
			GridBagConstraints gbc_jPanel1 = new GridBagConstraints();
			gbc_jPanel1.fill = GridBagConstraints.HORIZONTAL;
			gbc_jPanel1.insets = new Insets(0, 0, 5, 5);
			gbc_jPanel1.gridx = 0;
			gbc_jPanel1.gridy = 3;
			jContentPane.add(getJPanel1(), gbc_jPanel1);
			GridBagConstraints gbc_jPanel2 = new GridBagConstraints();
			gbc_jPanel2.fill = GridBagConstraints.HORIZONTAL;
			gbc_jPanel2.insets = new Insets(0, 0, 5, 5);
			gbc_jPanel2.gridx = 1;
			gbc_jPanel2.gridy = 3;
			jContentPane.add(getJPanel2(), gbc_jPanel2);
			GridBagConstraints gbc_jPanel3 = new GridBagConstraints();
			gbc_jPanel3.fill = GridBagConstraints.HORIZONTAL;
			gbc_jPanel3.insets = new Insets(0, 0, 5, 5);
			gbc_jPanel3.gridx = 2;
			gbc_jPanel3.gridy = 3;
			jContentPane.add(getJPanel3(), gbc_jPanel3);
			GridBagConstraints gbc_jButtonIntegral = new GridBagConstraints();
			gbc_jButtonIntegral.fill = GridBagConstraints.BOTH;
			gbc_jButtonIntegral.insets = new Insets(5, 0, 5, 5);
			gbc_jButtonIntegral.gridwidth = 3;
			gbc_jButtonIntegral.gridx = 0;
			gbc_jButtonIntegral.gridy = 4;
			jContentPane.add(getJButtonIntegral(), gbc_jButtonIntegral);
		}
		return jContentPane;
	}

} // @jve:decl-index=0:visual-constraint="34,2"
