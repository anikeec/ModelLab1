package com.apu.modellab1.example.competition;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import process.Dispatcher;
import rnd.Negexp;
import rnd.Randomable;
import widgets.ChooseRandom;
import widgets.Diagram;
import widgets.Painter;

/**
 * This type was generated by a SmartGuide.
 */
public class VisualPart extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel ivjJFrameContentPane = null;

	private JPanel ivjVisualExampleForProcessPane = null;

	private widgets.Diagram ivjDiagramForResult = null;

	private JLabel ivjJLabelNTeam = null;

	private JPanel ivjJPanel1 = null;

	private JTextField ivjJTextFieldNTeam = null;

	private ChooseRandom ivjChooseRandom = null;

	private JLabel ivjJLabel1 = null;

	private JLabel ivjJLabelNTask = null;

	private JPanel ivjJPanel11 = null;

	private JPanel ivjJPanel2 = null;

	private JPanel ivjJPanel3 = null;

	private JTextField ivjJTextFieldNTask = null;

	private JButton ivjJButtonStart = null;

	private JTextArea ivjJTextAreaForResult = null;

	private JScrollPane ivjJScrollPane1 = null;

	private JPanel jPanel = null;

	private JTextField ivjNGamer = null;

	private JLabel jLabel = null;

	private CompetitionModel model = null;// @jve:decl-index=0:visual-constraint="46,423"

	private Dispatcher dispatcher = null; 

	/**
	 * VisualExampleForProcess constructor comment.
	 */
	public VisualPart() {
		super();
		initialize();
	}

	/**
	 * VisualExampleForProcess constructor comment.
	 * 
	 * @param title
	 *            java.lang.String
	 */
	public VisualPart(String title) {
		super(title);
	}

	/**
	 * Return the ChooseRandom1 property value.
	 * 
	 * @return rnd.ChooseRandom
	 */

	private widgets.ChooseRandom getChooseRandom() {
		if (ivjChooseRandom == null) {
			try {
				ivjChooseRandom = new widgets.ChooseRandom();
				ivjChooseRandom.setName("chooseRandom");
				ivjChooseRandom
						.setPreferredSize(new java.awt.Dimension(210, 30));
				ivjChooseRandom.setMinimumSize(new java.awt.Dimension(210, 30));
				ivjChooseRandom.setRandom(new Negexp(1));
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjChooseRandom;
	}

	/**
	 * Return the DiagramForResult property value.
	 * 
	 * @return paint.Diagram
	 */

	private widgets.Diagram getDiagramForResult() {
		if (ivjDiagramForResult == null) {
			try {
				ivjDiagramForResult = new widgets.Diagram();
				ivjDiagramForResult.setName("DiagramForResult");
				ivjDiagramForResult.setGridByX(5);
				ivjDiagramForResult.setVerticalMaxEnabled(false);
				ivjDiagramForResult.setHorizontalMaxText("5");
				ivjDiagramForResult.setTitleText("Діаграми виконання завдань");
				ivjDiagramForResult.setPainterColor(java.awt.Color.blue);
				ivjDiagramForResult.setVerticalMaxText("100");
				ivjDiagramForResult.setHorizontalMaxEnabled(false);
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjDiagramForResult;
	}

	/**
	 * Return the JButton1 property value.
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getJButtonStart() {
		if (ivjJButtonStart == null) {
			try {
				ivjJButtonStart = new javax.swing.JButton();
				ivjJButtonStart.setName("JButtonStart");
				ivjJButtonStart
						.setPreferredSize(new java.awt.Dimension(89, 25));
				ivjJButtonStart.setText("Пуск");
				ivjJButtonStart.setMinimumSize(new java.awt.Dimension(89, 25));
				ivjJButtonStart.setMaximumSize(new java.awt.Dimension(89, 25));
				// user code begin {1}
				ivjJButtonStart
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(
									java.awt.event.ActionEvent e) {
								competitionStart();
							}
						});
			} catch (java.lang.Throwable ivjExc) {
				handleException(ivjExc);
			}
		}
		return ivjJButtonStart;
	}

	/**
	 * Return the JFrameContentPane property value.
	 * 
	 * @return javax.swing.JPanel
	 */

	private javax.swing.JPanel getJFrameContentPane() {
		if (ivjJFrameContentPane == null) {
			try {
				ivjJFrameContentPane = new javax.swing.JPanel();
				ivjJFrameContentPane.setName("JFrameContentPane");
				ivjJFrameContentPane.setLayout(new java.awt.BorderLayout());
				getJFrameContentPane().add(getVisualExampleForProcessPane(),
						"Center");
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJFrameContentPane;
	}

	/**
	 * Return the JLabel1 property value.
	 * 
	 * @return javax.swing.JLabel
	 */

	private javax.swing.JLabel getJLabel1() {
		if (ivjJLabel1 == null) {
			try {
				ivjJLabel1 = new javax.swing.JLabel();
				ivjJLabel1.setName("JLabel1");
				ivjJLabel1.setText("Розподіл для часу виконання завдань");
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabel1;
	}

	/**
	 * Return the JLabelNTask property value.
	 * 
	 * @return javax.swing.JLabel
	 */

	private javax.swing.JLabel getJLabelNTask() {
		if (ivjJLabelNTask == null) {
			try {
				ivjJLabelNTask = new javax.swing.JLabel();
				ivjJLabelNTask.setName("JLabelNTask");
				ivjJLabelNTask.setText("Кількість завдань");
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabelNTask;
	}

	/**
	 * Return the JLabelNTeam property value.
	 * 
	 * @return javax.swing.JLabel
	 */

	private javax.swing.JLabel getJLabelNTeam() {
		if (ivjJLabelNTeam == null) {
			try {
				ivjJLabelNTeam = new javax.swing.JLabel();
				ivjJLabelNTeam.setName("JLabelNTeam");
				ivjJLabelNTeam.setText("Кількість команд");
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJLabelNTeam;
	}

	/**
	 * Return the JPanel1 property value.
	 * 
	 * @return javax.swing.JPanel
	 */

	private javax.swing.JPanel getJPanel1() {
		if (ivjJPanel1 == null) {
			try {
				ivjJPanel1 = new javax.swing.JPanel();
				ivjJPanel1.setName("JPanel1");
				ivjJPanel1.setPreferredSize(new java.awt.Dimension(160, 22));
				ivjJPanel1.setLayout(new java.awt.GridBagLayout());
				ivjJPanel1.setMinimumSize(new java.awt.Dimension(160, 22));

				java.awt.GridBagConstraints constraintsNTeam = new java.awt.GridBagConstraints();
				constraintsNTeam.gridx = 1;
				constraintsNTeam.gridy = 0;
				constraintsNTeam.anchor = java.awt.GridBagConstraints.EAST;
				constraintsNTeam.insets = new java.awt.Insets(1, 4, 1, 4);
				getJPanel1().add(getJTextFieldNTeam(), constraintsNTeam);

				java.awt.GridBagConstraints constraintsJLabelNTeam = new java.awt.GridBagConstraints();
				constraintsJLabelNTeam.gridx = 0;
				constraintsJLabelNTeam.gridy = 0;
				constraintsJLabelNTeam.insets = new java.awt.Insets(4, 4, 4, 4);
				ivjJPanel1.add(getJLabelNTeam(), constraintsJLabelNTeam);
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJPanel1;
	}

	/**
	 * Return the JPanel11 property value.
	 * 
	 * @return javax.swing.JPanel
	 */

	private javax.swing.JPanel getJPanel11() {
		if (ivjJPanel11 == null) {
			try {
				ivjJPanel11 = new javax.swing.JPanel();
				ivjJPanel11.setName("JPanel11");
				ivjJPanel11.setPreferredSize(new java.awt.Dimension(170, 22));
				ivjJPanel11.setLayout(new java.awt.GridBagLayout());
				ivjJPanel11.setMinimumSize(new java.awt.Dimension(170, 22));

				java.awt.GridBagConstraints constraintsTargetNumber = new java.awt.GridBagConstraints();
				constraintsTargetNumber.gridx = 0;
				constraintsTargetNumber.gridy = 0;
				constraintsTargetNumber.anchor = java.awt.GridBagConstraints.EAST;
				constraintsTargetNumber.weightx = 1.0;
				constraintsTargetNumber.insets = new java.awt.Insets(2, 4, 2, 4);
				getJPanel11()
						.add(getJTextFieldNTask(), constraintsTargetNumber);

				java.awt.GridBagConstraints constraintsJLabelNTask = new java.awt.GridBagConstraints();
				constraintsJLabelNTask.gridx = 0;
				constraintsJLabelNTask.gridy = 0;
				constraintsJLabelNTask.anchor = java.awt.GridBagConstraints.WEST;
				constraintsJLabelNTask.insets = new java.awt.Insets(4, 4, 4, 4);
				getJPanel11().add(getJLabelNTask(), constraintsJLabelNTask);
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJPanel11;
	}

	/**
	 * Return the JPanel2 property value.
	 * 
	 * @return javax.swing.JPanel
	 */

	private javax.swing.JPanel getJPanel2() {
		if (ivjJPanel2 == null) {
			try {
				ivjJPanel2 = new javax.swing.JPanel();
				ivjJPanel2.setName("JPanel2");
				ivjJPanel2.setLayout(new java.awt.GridBagLayout());

				java.awt.GridBagConstraints constraintsJLabel1 = new java.awt.GridBagConstraints();
				constraintsJLabel1.gridx = 0;
				constraintsJLabel1.gridy = 0;
				constraintsJLabel1.insets = new java.awt.Insets(2, 4, 1, 4);
				getJPanel2().add(getJLabel1(), constraintsJLabel1);

				java.awt.GridBagConstraints constraintsChooseRandom1 = new java.awt.GridBagConstraints();
				constraintsChooseRandom1.gridx = 0;
				constraintsChooseRandom1.gridy = 1;
				constraintsChooseRandom1.weightx = 1.0;
				constraintsChooseRandom1.weighty = 1.0;
				constraintsChooseRandom1.insets = new java.awt.Insets(2, 4, 1,
						4);
				getJPanel2().add(getChooseRandom(), constraintsChooseRandom1);
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJPanel2;
	}

	/**
	 * Return the JPanel3 property value.
	 * 
	 * @return javax.swing.JPanel
	 */

	private javax.swing.JPanel getJPanel3() {
		if (ivjJPanel3 == null) {
			try {
				GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
				gridBagConstraints7.insets = new java.awt.Insets(4, 19, 3, 26);
				gridBagConstraints7.gridy = 1;
				gridBagConstraints7.ipadx = 49;
				gridBagConstraints7.gridx = 0;
				GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
				gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
				gridBagConstraints6.gridx = 0;
				gridBagConstraints6.gridy = 4;
				gridBagConstraints6.ipadx = 124;
				gridBagConstraints6.ipady = 82;
				gridBagConstraints6.weightx = 1.0;
				gridBagConstraints6.weighty = 1.0;
				gridBagConstraints6.insets = new java.awt.Insets(2, 9, 2, 4);
				GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
				gridBagConstraints5.insets = new java.awt.Insets(3, 78, 10, 87);
				gridBagConstraints5.gridy = 5;
				gridBagConstraints5.gridx = 0;
				GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
				gridBagConstraints4.insets = new java.awt.Insets(4, 9, 2, 6);
				gridBagConstraints4.gridy = 3;
				gridBagConstraints4.ipadx = 4;
				gridBagConstraints4.gridx = 0;
				GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
				gridBagConstraints3.insets = new java.awt.Insets(3, 41, 4, 53);
				gridBagConstraints3.gridy = 2;
				gridBagConstraints3.ipadx = -10;
				gridBagConstraints3.gridx = 0;
				GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
				gridBagConstraints2.insets = new java.awt.Insets(6, 41, 4, 53);
				gridBagConstraints2.gridy = 0;
				gridBagConstraints2.gridx = 0;
				ivjJPanel3 = new javax.swing.JPanel();
				ivjJPanel3.setName("JPanel3");
				ivjJPanel3.setBorder(new javax.swing.border.EtchedBorder());
				ivjJPanel3.setLayout(new GridBagLayout());

				ivjJPanel3.add(getJPanel1(), gridBagConstraints2);
				ivjJPanel3.add(getJPanel11(), gridBagConstraints3);
				ivjJPanel3.add(getJPanel2(), gridBagConstraints4);
				ivjJPanel3.add(getJButtonStart(), gridBagConstraints5);
				ivjJPanel3.add(getJScrollPane1(), gridBagConstraints6);
				ivjJPanel3.add(getJPanel(), gridBagConstraints7);
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJPanel3;
	}

	/**
	 * Return the JScrollPane1 property value.
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private javax.swing.JScrollPane getJScrollPane1() {
		if (ivjJScrollPane1 == null) {
			try {
				ivjJScrollPane1 = new javax.swing.JScrollPane();
				ivjJScrollPane1.setName("JScrollPane1");
				ivjJScrollPane1.setViewportView(getJTextAreaForResult());
				// user code end
			} catch (java.lang.Throwable ivjExc) {
				handleException(ivjExc);
			}
		}
		return ivjJScrollPane1;
	}

	/**
	 * Return the TargetNumber property value.
	 * 
	 * @return javax.swing.JTextField
	 */

	private javax.swing.JTextField getJTextFieldNTask() {
		if (ivjJTextFieldNTask == null) {
			try {
				ivjJTextFieldNTask = new javax.swing.JTextField();
				ivjJTextFieldNTask.setName("JTextFieldTarget");
				ivjJTextFieldNTask.setPreferredSize(new java.awt.Dimension(30,
						18));
				ivjJTextFieldNTask.setText("100");
				ivjJTextFieldNTask
						.setMinimumSize(new java.awt.Dimension(30, 18));
				ivjJTextFieldNTask
						.setHorizontalAlignment(javax.swing.JTextField.CENTER);
				ivjJTextFieldNTask
						.addCaretListener(new javax.swing.event.CaretListener() {
							public void caretUpdate(
									javax.swing.event.CaretEvent e) {
								// getDiagramForResult().setVerticalMinText("0");
								String s = getJTextFieldNTask().getText();
								getDiagramForResult().setVerticalMaxText(s);
							}
						});
			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTextFieldNTask;
	}

	/**
	 * Return the NTeam property value.
	 * 
	 * @return javax.swing.JTextField
	 */

	private javax.swing.JTextField getJTextFieldNTeam() {
		if (ivjJTextFieldNTeam == null) {
			try {
				ivjJTextFieldNTeam = new javax.swing.JTextField();
				ivjJTextFieldNTeam.setName("JTextFieldTeam");
				ivjJTextFieldNTeam.setText("4");
				ivjJTextFieldNTeam.setPreferredSize(new java.awt.Dimension(30,
						18));
				ivjJTextFieldNTeam
						.setMinimumSize(new java.awt.Dimension(30, 18));
				ivjJTextFieldNTeam
						.setHorizontalAlignment(javax.swing.JTextField.CENTER);
				ivjJTextFieldNTeam.setEditable(true);
				ivjJTextFieldNTeam
						.addCaretListener(new javax.swing.event.CaretListener() {
							public void caretUpdate(
									javax.swing.event.CaretEvent e) {
								try {
									String s = getJTextFieldNTeam().getText();
									int n = Integer.parseInt(s);
									getDiagramForResult().setGridByX(n + 1);
									s = String.valueOf(n + 1);
									getDiagramForResult().setHorizontalMaxText(
											s);
								} catch (java.lang.Throwable ivjExc) {
									handleException(ivjExc);
								}
							}
						});
			} catch (java.lang.Throwable ivjExc) {
				handleException(ivjExc);
			}
		}
		return ivjJTextFieldNTeam;
	}

	/**
	 * Return the TextAreaForResult property value.
	 * 
	 * @return javax.swing.JTextArea
	 */

	private javax.swing.JTextArea getJTextAreaForResult() {
		if (ivjJTextAreaForResult == null) {
			try {
				ivjJTextAreaForResult = new javax.swing.JTextArea();
				ivjJTextAreaForResult.setName("TextAreaForResult");
				ivjJTextAreaForResult.setText("Результати змагань");
				ivjJTextAreaForResult.setFont(new java.awt.Font("Courier New",
						java.awt.Font.PLAIN, 12));

			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjJTextAreaForResult;
	}

	/**
	 * Return the VisualExampleForProcessPane property value.
	 * 
	 * @return javax.swing.JPanel
	 */

	private javax.swing.JPanel getVisualExampleForProcessPane() {
		if (ivjVisualExampleForProcessPane == null) {
			try {
				GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
				gridBagConstraints9.insets = new java.awt.Insets(15, 5, 8, 10);
				gridBagConstraints9.gridy = 0;
				gridBagConstraints9.ipadx = -12;
				gridBagConstraints9.ipady = 5;
				gridBagConstraints9.fill = java.awt.GridBagConstraints.BOTH;
				gridBagConstraints9.weightx = 1.0D;
				gridBagConstraints9.weighty = 1.0D;
				gridBagConstraints9.anchor = java.awt.GridBagConstraints.EAST;
				gridBagConstraints9.gridx = 1;
				GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
				gridBagConstraints8.insets = new java.awt.Insets(15, 1, 8, 5);
				gridBagConstraints8.gridy = 0;
				gridBagConstraints8.ipadx = 30;
				gridBagConstraints8.ipady = 116;
				gridBagConstraints8.fill = java.awt.GridBagConstraints.BOTH;
				gridBagConstraints8.weightx = 1.0D;
				gridBagConstraints8.weighty = 1.0D;
				gridBagConstraints8.anchor = java.awt.GridBagConstraints.WEST;
				gridBagConstraints8.gridx = 0;
				ivjVisualExampleForProcessPane = new javax.swing.JPanel();
				ivjVisualExampleForProcessPane
						.setName("VisualExampleForProcessPane");
				ivjVisualExampleForProcessPane.setLayout(new GridBagLayout());

				ivjVisualExampleForProcessPane.add(getDiagramForResult(),
						gridBagConstraints8);
				ivjVisualExampleForProcessPane.add(getJPanel3(),
						gridBagConstraints9);

			} catch (java.lang.Throwable ivjExc) {
				// user code begin {2}
				// user code end
				handleException(ivjExc);
			}
		}
		return ivjVisualExampleForProcessPane;
	}

	/**
	 * Called whenever the part throws an exception.
	 * 
	 * @param exception
	 *            java.lang.Throwable
	 */
	private void handleException(java.lang.Throwable exception) {

		/* Uncomment the following lines to print uncaught exceptions to stdout */
		// System.out.println("--------- UNCAUGHT EXCEPTION ---------");
		// exception.printStackTrace(System.out);
	}

	/**
	 * Initialize the class.
	 */

	private void initialize() {
		try {
			setName("VisualExampleForProcess");
			setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
			setSize(564, 355);
			setTitle("Модель з псевдопаралельними процесами");
			setContentPane(getJFrameContentPane());
		} catch (java.lang.Throwable ivjExc) {
			handleException(ivjExc);
		}
		// user code begin {2}
		// user code end
	}

	/**
	 * Starts the application.
	 * 
	 * @param args
	 *            an array of command-line arguments
	 */
	public static void main(java.lang.String[] args) {
		try {
			/* Set native look and feel */
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			/* Create the frame */
			VisualPart aVisualExampleForProcess = new VisualPart();
			/* Add a windowListener for the windowClosedEvent */
			aVisualExampleForProcess
					.addWindowListener(new java.awt.event.WindowAdapter() {
						public void windowClosed(java.awt.event.WindowEvent e) {
							System.exit(0);
						};
					});
			aVisualExampleForProcess.setVisible(true);
		} catch (Throwable exception) {
			System.err
					.println("Exception occurred in mainForAllLab() of VisualExampleForProcess");
			exception.printStackTrace(System.out);
		}
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new java.awt.Insets(3, 10, 3, 5);
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = 9;
			gridBagConstraints1.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 7);
			jLabel = new JLabel();
			jLabel.setName("JLabelNTeam");
			jLabel.setText("Кількість членів команди");
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.setName("JPanel1");
			jPanel.setMinimumSize(new Dimension(160, 22));
			jPanel.setPreferredSize(new Dimension(160, 22));
			jPanel.add(getJTextFieldNGamer(), gridBagConstraints);
			jPanel.add(jLabel, gridBagConstraints1);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNGamer() {
		if (ivjNGamer == null) {
			ivjNGamer = new JTextField();
			ivjNGamer.setName("JTextFieldNgamer");
			ivjNGamer.setPreferredSize(new Dimension(30, 18));
			ivjNGamer.setText("2");
			ivjNGamer.setEditable(true);
			ivjNGamer.setHorizontalAlignment(JTextField.CENTER);
			ivjNGamer.setMinimumSize(new Dimension(30, 18));
		}
		return ivjNGamer;
	}

	private void competitionStart() {
		getDiagramForResult().clear();
		getJTextAreaForResult().setText("Результати змагань");
		model=new CompetitionModel();
		model.setDispatcher(getDispatcher());
		model.setUi(this);
		model.teamsToStartList();
		getDispatcher().setProtocolFileName("Console");
		getDispatcher().start();
	}

	public int nTeam() {
		return Integer.parseInt(getJTextFieldNTeam().getText());
	}

	public int nGamer() {
		return Integer.parseInt(getJTextFieldNGamer().getText());

	}

	public int nTask() {

		return Integer.parseInt(getJTextFieldNTask().getText());
	}

	public Randomable random() {

		return getChooseRandom().getRandom();
	}

	public Diagram diagram() {

		return getDiagramForResult();
	}

	public JTextArea textArea() {

		return getJTextAreaForResult();
	}

	public int nTeamMember() {
		return Integer.parseInt(getJTextFieldNGamer().getText());
	}

	public void drawGamerTaskReady(TeamMember member, double taskTime) {
		int num = member.getNumber();
		int tNum = member.getTeam().getTeamNumber();
		// "Художник", который будет отрисовывать на диаграмме процесс
		// выполнения заданий
		Painter painter = getDiagramForResult().getPainter();
		painter.setColor(member.getTeam().getTeamColor());
		float dx = (float) (0.5 / getChooseRandom().max() * taskTime);
		// четных участников отрисовываем влево, а нечетных вправо
		if (num % 2 == 0)
			dx = -dx;
		painter.placeToXY(tNum, member.getN() - 1);
		// Шаг налево или направо, а затем назад и на 1 вверх
		painter.drawToXY(tNum + dx, member.getN() - 1);
		painter.drawToXY(tNum, member.getN() - 0.5f);

	}

	public void printTeamResult(Team team) {
		String str = "\nКоманда " + team.getTeamNumber() + ", час "
				+ getDispatcher().getCurrentTime();
		getJTextAreaForResult().append(str);
	}

	public Dispatcher getDispatcher() {
		if(dispatcher==null) dispatcher=new Dispatcher();
		return dispatcher;
	}


//	public void setModel(CompetitionModel model) {
//		this.model = model;
//	}

	/**
	 * This method initializes competitionModel1
	 * 
	 * @return example.competition.CompetitionModel
	 */
//	private CompetitionModel getCompetitionModel() {
//		if (competitionModel == null) {
//			competitionModel = new CompetitionModel();
//		}
//		return competitionModel;
//	}

}