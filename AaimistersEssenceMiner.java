/**
 * @author Aaimister
 * @version 1.31 ©2010-2011 Aaimister, No one except Aaimister has the right to
 *          modify and/or spread this script without the permission of Aaimister.
 *          I'm not held responsible for any damage that may occur to your
 *          property.
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.rsbot.event.events.MessageEvent;
import org.rsbot.event.listeners.MessageListener;
import org.rsbot.event.listeners.PaintListener;
import org.rsbot.gui.AccountManager;
import org.rsbot.script.Script;
import org.rsbot.script.ScriptManifest;
import org.rsbot.script.util.Filter;
import org.rsbot.script.wrappers.RSArea;
import org.rsbot.script.wrappers.RSComponent;
import org.rsbot.script.wrappers.RSItem;
import org.rsbot.script.wrappers.RSNPC;
import org.rsbot.script.wrappers.RSObject;
import org.rsbot.script.wrappers.RSPlayer;
import org.rsbot.script.wrappers.RSTile;
import org.rsbot.script.wrappers.RSWeb;

@ScriptManifest(authors = { "Aaimister" }, name = "Aaimisters Essence Miner", keywords = "Mining", version = 1.31, description = "Mines Essence.", website = "http://www.powerbot.org/vb/showthread.php?t=651251", requiresVersion = 244)
public class AaimistersEssenceMiner extends Script implements PaintListener,
		MessageListener, MouseListener {

	public class AaimistersGUI {

		private JFrame AaimistersGUI;

		private JPanel contentPane;

		private JComboBox colorBox;

		private JComboBox locationBox;

		private JCheckBox antibanBox;

		private JCheckBox paintBox;
		private JCheckBox restBox;
		private JCheckBox breakBox;
		private JCheckBox randomBox;
		private JTextArea maxTimeBeBox;
		private JTextArea minTimeBeBox;
		private JTextArea maxBreakBox;
		private JTextArea minBreakBox;
		private JPanel panel;
		private JPanel panel_1;
		private JPanel panel_2;
		private JPanel panel_4;
		private JLabel lblAaimistersEssenceMiner;
		private JLabel lblPaintColor;
		private JLabel lblTimeBetweenBreaks;
		private JLabel lblBreakLengths;
		private JLabel lblTo;
		private JLabel lblMins;
		private JLabel lblLocation;
		private JLabel label_3;
		private JLabel label_5;
		private JLabel label_4;
		private JLabel label_6;
		private JTabbedPane tabbedPane;
		private JButton submit;

		private AaimistersGUI() {
			initComponents();
		}

		private void breakBoxActionPerformed(final ActionEvent e) {
			doBreak = breakBox.isSelected();
			randomBreaks = randomBox.isSelected();
			if (!doBreak) {
				randomBox.setEnabled(false);
				randomBox.setSelected(false);
				maxTimeBeBox.setEnabled(false);
				minTimeBeBox.setEnabled(false);
				maxBreakBox.setEnabled(false);
				minBreakBox.setEnabled(false);
			} else {
				randomBox.setEnabled(true);
				if (!randomBreaks) {
					maxTimeBeBox.setEnabled(true);
					minTimeBeBox.setEnabled(true);
					maxBreakBox.setEnabled(true);
					minBreakBox.setEnabled(true);
				}
			}
		}

		public void initComponents() {
			AaimistersGUI = new JFrame();
			contentPane = new JPanel();
			colorBox = new JComboBox();
			locationBox = new JComboBox();
			antibanBox = new JCheckBox();
			restBox = new JCheckBox();
			paintBox = new JCheckBox();
			breakBox = new JCheckBox();
			randomBox = new JCheckBox();
			maxTimeBeBox = new JTextArea();
			minTimeBeBox = new JTextArea();
			maxBreakBox = new JTextArea();
			minBreakBox = new JTextArea();
			panel = new JPanel();
			panel_1 = new JPanel();
			panel_2 = new JPanel();
			panel_4 = new JPanel();
			lblAaimistersEssenceMiner = new JLabel();
			lblPaintColor = new JLabel();
			lblLocation = new JLabel();
			lblTimeBetweenBreaks = new JLabel();
			lblBreakLengths = new JLabel();
			lblTo = new JLabel();
			lblMins = new JLabel();
			label_3 = new JLabel();
			label_4 = new JLabel();
			label_5 = new JLabel();
			label_6 = new JLabel();
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			submit = new JButton();

			// Listeners
			AaimistersGUI.addWindowListener(new WindowAdapter() {
				public void windowClosing(final WindowEvent e) {
					closed = true;
				}
			});

			AaimistersGUI.setTitle("Aaimister's Essence Miner v1.31");
			AaimistersGUI.setForeground(new Color(255, 255, 255));
			AaimistersGUI.setBackground(Color.LIGHT_GRAY);
			AaimistersGUI.setResizable(false);
			AaimistersGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			AaimistersGUI.setBounds(100, 100, 300, 350);
			contentPane = new JPanel();
			contentPane.setBackground(SystemColor.menu);
			contentPane.setForeground(Color.LIGHT_GRAY);
			contentPane.setFont(new Font("Cambria Math", Font.PLAIN, 17));
			contentPane.setBorder(new EmptyBorder(5, 8, 8, 8));
			AaimistersGUI.setContentPane(contentPane);
			contentPane.setLayout(null);

			panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			panel.setBounds(4, 0, 296, 40);
			contentPane.add(panel);
			panel.setLayout(null);

			lblAaimistersEssenceMiner.setText("Aaimister's Essence Miner v1.31");
			lblAaimistersEssenceMiner.setBounds(0, 0, 286, 40);
			panel.add(lblAaimistersEssenceMiner);
			lblAaimistersEssenceMiner.setHorizontalAlignment(SwingConstants.CENTER);
			lblAaimistersEssenceMiner.setForeground(SystemColor.infoText);
			lblAaimistersEssenceMiner.setFont(new Font("Calibri", Font.BOLD, 20));

			tabbedPane.setBounds(4, 51, 286, 228);
			contentPane.add(tabbedPane);

			tabbedPane.addTab("General", null, panel_1, null);

			restBox.setText("Use Rest");
			restBox.setForeground(Color.BLACK);
			restBox.setFont(new Font("Cambria Math", Font.PLAIN, 12));
			restBox.setSelected(true);

			paintBox.setText("Enable Anti-Aliasing");
			paintBox.setForeground(Color.BLACK);
			paintBox.setFont(new Font("Cambria Math", Font.PLAIN, 12));
			paintBox.setSelected(true);

			lblPaintColor.setText("Paint Color:");
			lblPaintColor.setForeground(Color.BLACK);
			lblPaintColor.setFont(new Font("Cambria Math", Font.PLAIN, 15));

			colorBox.setModel(new DefaultComboBoxModel(colorstring));

			antibanBox.setText("Use Anti-Ban");
			antibanBox.setSelected(true);
			antibanBox.setForeground(Color.BLACK);
			antibanBox.setFont(new Font("Cambria Math", Font.PLAIN, 12));

			lblLocation.setText("Location:");
			lblLocation.setForeground(Color.BLACK);
			lblLocation.setFont(new Font("Cambria Math", Font.PLAIN, 15));

			locationBox.setModel(new DefaultComboBoxModel(locationstring));

			final GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup().addGap(28).addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup().addGap(37).addComponent(paintBox, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE).addGap(45)).addGroup(gl_panel_1.createSequentialGroup().addComponent(restBox, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(antibanBox, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)).addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(lblPaintColor, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE).addComponent(lblLocation, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)).addGap(30).addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(colorBox, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE).addComponent(locationBox, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))).addContainerGap()));
			gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup().addGap(26).addComponent(paintBox).addGap(18).addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(restBox).addComponent(antibanBox)).addGap(28).addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblLocation, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE).addComponent(locationBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(18).addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblPaintColor).addComponent(colorBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(79)));
			panel_1.setLayout(gl_panel_1);

			tabbedPane.addTab("Breaks", null, panel_2, null);

			breakBox.setText("Use Custom Breaks");
			breakBox.setForeground(Color.BLACK);
			breakBox.setFont(new Font("Cambria Math", Font.PLAIN, 12));
			breakBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					breakBoxActionPerformed(e);
				}
			});

			lblTimeBetweenBreaks.setText("Time Between Breaks:");
			lblTimeBetweenBreaks.setForeground(Color.BLACK);
			lblTimeBetweenBreaks.setFont(new Font("Cambria Math", Font.PLAIN, 15));

			lblBreakLengths.setText("Break Lengths:");
			lblBreakLengths.setForeground(Color.BLACK);
			lblBreakLengths.setFont(new Font("Cambria Math", Font.PLAIN, 15));

			randomBox.setText("Random Breaks");
			randomBox.setForeground(Color.BLACK);
			randomBox.setFont(new Font("Cambria Math", Font.PLAIN, 12));
			if (!doBreak) {
				randomBox.setEnabled(false);
			} else {
				randomBox.setEnabled(true);
			}
			randomBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					randomBoxActionPerformed(e);
				}
			});

			minTimeBeBox.setForeground(Color.BLACK);
			minTimeBeBox.setText("60");
			minTimeBeBox.setFont(new Font("Cambria Math", Font.PLAIN, 13));
			if (!doBreak || randomBreaks) {
				minTimeBeBox.setEnabled(false);
			}

			lblTo.setText("to");
			lblTo.setForeground(Color.GRAY);
			lblTo.setFont(new Font("Cambria Math", Font.PLAIN, 13));

			maxTimeBeBox.setText("90");
			maxTimeBeBox.setForeground(Color.BLACK);
			maxTimeBeBox.setFont(new Font("Cambria Math", Font.PLAIN, 13));
			if (!doBreak || randomBreaks) {
				maxTimeBeBox.setEnabled(false);
			}

			lblMins.setText("mins");
			lblMins.setForeground(Color.GRAY);
			lblMins.setFont(new Font("Cambria Math", Font.PLAIN, 13));

			label_3.setText("mins");
			label_3.setForeground(Color.GRAY);
			label_3.setFont(new Font("Cambria Math", Font.PLAIN, 13));

			minBreakBox.setText("15");
			minBreakBox.setForeground(Color.BLACK);
			minBreakBox.setFont(new Font("Cambria Math", Font.PLAIN, 13));
			if (!doBreak || randomBreaks) {
				minBreakBox.setEnabled(false);
			}

			label_4.setText("mins");
			label_4.setForeground(Color.GRAY);
			label_4.setFont(new Font("Cambria Math", Font.PLAIN, 13));

			label_5.setText("to");
			label_5.setForeground(Color.GRAY);
			label_5.setFont(new Font("Cambria Math", Font.PLAIN, 13));

			maxBreakBox.setText("90");
			maxBreakBox.setForeground(Color.BLACK);
			maxBreakBox.setFont(new Font("Cambria Math", Font.PLAIN, 13));
			if (!doBreak || randomBreaks) {
				maxBreakBox.setEnabled(false);
			}

			label_6.setText("mins");
			label_6.setForeground(Color.GRAY);
			label_6.setFont(new Font("Cambria Math", Font.PLAIN, 13));
			final GroupLayout gl_panel_4 = new GroupLayout(panel_4);
			gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_4.createSequentialGroup().addGap(10).addComponent(breakBox).addGap(18).addComponent(randomBox, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE).addContainerGap()).addGroup(gl_panel_4.createSequentialGroup().addContainerGap().addComponent(lblTimeBetweenBreaks, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE).addGap(121)).addGroup(Alignment.LEADING, gl_panel_4.createSequentialGroup().addContainerGap().addComponent(lblBreakLengths, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE).addGap(167)).addGroup(Alignment.LEADING, gl_panel_4.createSequentialGroup().addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING, false).addGroup(gl_panel_4.createSequentialGroup().addGap(21).addComponent(minBreakBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(label_4, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(label_5)).addGroup(Alignment.LEADING, gl_panel_4.createSequentialGroup().addGap(20).addComponent(minTimeBeBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(lblMins, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE).addGap(27).addComponent(lblTo))).addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE).addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup().addComponent(maxTimeBeBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(label_3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)).addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup().addComponent(maxBreakBox, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(label_6, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))).addGap(47)));
			gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_4.createSequentialGroup().addGap(20).addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE).addComponent(breakBox).addComponent(randomBox)).addGap(18).addComponent(lblTimeBetweenBreaks).addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE).addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE).addComponent(lblTo).addComponent(maxTimeBeBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addComponent(label_3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)).addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE).addComponent(minTimeBeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(lblMins, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))).addGap(18).addComponent(lblBreakLengths).addGap(17).addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE).addComponent(maxBreakBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addComponent(label_6, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)).addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE).addComponent(minBreakBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addComponent(label_4, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE).addComponent(label_5, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))).addContainerGap()));
			gl_panel_4.linkSize(SwingConstants.VERTICAL, new Component[] {
					lblTo, label_5 });
			panel_4.setLayout(gl_panel_4);
			final GroupLayout gl_panel_2 = new GroupLayout(panel_2);
			gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup().addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE));
			panel_2.setLayout(gl_panel_2);

			submit.setText("Start");
			submit.setFont(new Font("Cambria Math", Font.BOLD, 12));
			submit.setBounds(98, 290, 89, 23);
			contentPane.add(submit);
			submit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					submitActionPerformed(e);
				}
			});
			// LOAD SAVED SELECTION INFO
			try {
				final String filename = getCacheDirectory()
						+ "\\AaimistersEMinerSettings.txt";
				final Scanner in = new Scanner(new BufferedReader(new FileReader(filename)));
				String line;
				String[] opts = {};
				while (in.hasNext()) {
					line = in.next();
					if (line.contains(":")) {
						opts = line.split(":");
					}
				}
				in.close();
				if (opts.length > 1) {
					if (opts[5].equals("true")) {
						randomBox.setEnabled(true);
						if (opts[6].equals("false")) {
							maxTimeBeBox.setText(opts[7]);
							minTimeBeBox.setText(opts[8]);
							maxBreakBox.setText(opts[9]);
							minBreakBox.setText(opts[10]);
							maxTimeBeBox.setEnabled(true);
							minTimeBeBox.setEnabled(true);
							maxBreakBox.setEnabled(true);
							minBreakBox.setEnabled(true);
						}
					}
					if (opts[1].equals("true")) {
						restBox.setSelected(true);
					} else {
						restBox.setSelected(false);
					}
					colorBox.setSelectedIndex(Integer.parseInt(opts[2]));
					locationBox.setSelectedIndex(Integer.parseInt(opts[0]));
					if (opts[3].equals("true")) {
						antibanBox.setSelected(true);
					} else {
						antibanBox.setSelected(false);
					}
					if (opts[4].equals("true")) {
						paintBox.setSelected(true);
					} else {
						paintBox.setSelected(false);
					}
					if (opts[5].equals("true")) {
						breakBox.setSelected(true);
					} else {
						breakBox.setSelected(false);
						randomBox.setEnabled(false);
					}
					if (opts[6].equals("true")) {
						randomBox.setSelected(true);
						randomBox.setEnabled(true);
					} else {
						randomBox.setSelected(false);
					}
				}
			} catch (final Exception e2) {
				// e2.printStackTrace();
				log.warning("Error loading settings.");
			}
			// END LOAD SAVED SELECTION INFO
		}

		private void randomBoxActionPerformed(final ActionEvent e) {
			doBreak = breakBox.isSelected();
			randomBreaks = randomBox.isSelected();
			if (randomBreaks == true) {
				maxTimeBeBox.setEnabled(false);
				minTimeBeBox.setEnabled(false);
				maxBreakBox.setEnabled(false);
				minBreakBox.setEnabled(false);
			} else {
				if (doBreak) {
					maxTimeBeBox.setEnabled(true);
					minTimeBeBox.setEnabled(true);
					maxBreakBox.setEnabled(true);
					minBreakBox.setEnabled(true);
				}
			}
		}

		public void submitActionPerformed(final ActionEvent e) {
			final String color = (String) colorBox.getSelectedItem();
			if (color.contains("Blue")) {
				MainColor = new Color(0, 0, 100);
				ThinColor = new Color(0, 0, 100, 70);
				LineColor = new Color(255, 255, 255);
				BoxColor = MainColor;
			} else if (color.contains("Black")) {
				MainColor = new Color(0, 0, 0);
				ThinColor = new Color(0, 0, 0, 70);
				LineColor = new Color(255, 255, 255);
				BoxColor = MainColor;
			} else if (color.contains("Brown")) {
				MainColor = new Color(92, 51, 23);
				ThinColor = new Color(92, 51, 23, 70);
				BoxColor = MainColor;
			} else if (color.contains("Cyan")) {
				MainColor = new Color(0, 255, 255);
				ThinColor = new Color(0, 255, 255, 70);
				BoxColor = MainColor;
				LineColor = new Color(0, 0, 0);
			} else if (color.contains("Green")) {
				MainColor = new Color(0, 100, 0);
				ThinColor = new Color(0, 100, 0, 70);
				BoxColor = MainColor;
			} else if (color.contains("Lime")) {
				MainColor = new Color(0, 220, 0);
				ThinColor = new Color(0, 220, 0, 70);
				BoxColor = MainColor;
				LineColor = new Color(0, 0, 0);
			} else if (color.contains("Orange")) {
				MainColor = new Color(255, 127, 0);
				ThinColor = new Color(255, 127, 0, 70);
				BoxColor = MainColor;
				LineColor = new Color(0, 0, 0);
			} else if (color.contains("Pink")) {
				MainColor = new Color(238, 18, 137);
				ThinColor = new Color(238, 18, 137, 70);
				BoxColor = MainColor;
				LineColor = new Color(0, 0, 0);
			} else if (color.contains("Purple")) {
				MainColor = new Color(104, 34, 139);
				ThinColor = new Color(104, 34, 139, 70);
				BoxColor = MainColor;
			} else if (color.contains("Red")) {
				MainColor = new Color(100, 0, 0);
				ThinColor = new Color(100, 0, 0, 70);
				ClickC = Black;
				BoxColor = MainColor;
			} else if (color.contains("White")) {
				MainColor = new Color(255, 255, 255);
				ThinColor = new Color(255, 255, 255, 70);
				LineColor = new Color(0, 0, 0);
				BoxColor = new Color(140, 140, 140);
				LineColor = new Color(0, 0, 0);
			} else if (color.contains("Yellow")) {
				MainColor = new Color(238, 201, 0);
				ThinColor = new Color(238, 201, 0, 70);
				BoxColor = MainColor;
				LineColor = new Color(0, 0, 0);
			}
			final String loc = (String) locationBox.getSelectedItem();
			if (loc.contains("Varrock")) {
				varrock = true;
				bankID = 782;
				door = 24381;
				Bank = AM.BankV;
				AtPerson = AM.AtAubury;
				toMine = AM.toMineV;
				// toBank = AM.toBankV;
				PersonT = AM.AuburyT;
				BankT = AM.BankTV;
				Gate = AM.GateV;
				CityArea = AM.VCityArea;
			} else if (loc.contains("Yanille")) {
				varrock = false;
				bankID = 2213;
				door = 1600;
				Bank = AM.BankY;
				AtPerson = AM.AtWiz;
				toMine = AM.toMineY;
				// toBank = AM.toBankY;
				PersonT = AM.WizT;
				BankT = AM.BankTY;
				Gate = AM.GateY;
				CityArea = AM.YCityArea;
			}
			if (restBox.isSelected()) {
				rest = true;
			}
			if (paintBox.isSelected()) {
				painting = true;
			}
			if (antibanBox.isSelected()) {
				antiBanOn = true;
			}
			if (breakBox.isSelected()) {
				doBreak = true;
				if (randomBox.isSelected()) {
					randomBreaks = true;
				}
				maxBetween = Integer.parseInt(maxTimeBeBox.getText());
				minBetween = Integer.parseInt(minTimeBeBox.getText());
				maxLength = Integer.parseInt(maxBreakBox.getText());
				minLength = Integer.parseInt(minBreakBox.getText());
				if (minBetween < 1) {
					minBetween = 1;
				}
				if (minLength < 1) {
					minLength = 1;
				}
				if (maxBetween > 5000) {
					maxBetween = 5000;
				} else if (maxBetween < 6) {
					maxBetween = 6;
				}
				if (maxLength > 5000) {
					maxLength = 5000;
				} else if (maxLength < 5) {
					maxLength = 5;
				}
			}

			// Write settings
			try {
				final BufferedWriter out = new BufferedWriter(new FileWriter(settingsFile));
				out.write(locationBox.getSelectedIndex() + ":" // 0
						+ (restBox.isSelected() ? true : false) + ":" // 1
						+ colorBox.getSelectedIndex() + ":" // 2
						+ (antibanBox.isSelected() ? true : false) + ":" // 3
						+ (paintBox.isSelected() ? true : false) + ":" // 4
						+ (breakBox.isSelected() ? true : false) + ":" // 5
						+ (randomBox.isSelected() ? true : false) + ":" // 6
						+ maxTimeBeBox.getText() + ":" // 7
						+ minTimeBeBox.getText() + ":" // 8
						+ maxBreakBox.getText() + ":" // 9
						+ minBreakBox.getText());// 10
				out.close();
			} catch (final Exception e1) {
				log.warning("Error saving setting.");
			}
			// End write settings

			AaimistersGUI.dispose();
		}
	}

	private static interface AM {

		// Varrock
		// final RSTile toBankV[] = { new RSTile(3258, 3405), new RSTile(3260,
		// 3413), new RSTile(3253, 3420) };
		final RSTile toMineV[] = { new RSTile(3261, 3421),
				new RSTile(3260, 3412), new RSTile(3258, 3402),
				new RSTile(3253, 3401) };
		final RSArea VCityArea = new RSArea(new RSTile(3234, 3387), new RSTile(3268, 3440));
		final RSArea AtAubury = new RSArea(new RSTile(3250, 3399), new RSTile(3255, 3404));
		final RSArea BankV = new RSArea(new RSTile(3250, 3419), new RSTile(3257, 3423));
		final RSArea GateV = new RSArea(new RSTile(3252, 3398), new RSTile(3254, 3400));
		final RSTile AuburyT = new RSTile(3253, 3401);
		final RSTile BankTV = new RSTile(3253, 3420);

		// Yanille
		// final RSTile toBankY[] = { new RSTile(3602, 3093), new RSTile(2611,
		// 3092) };
		final RSTile toMineY[] = { new RSTile(2603, 3088),
				new RSTile(2595, 3088) };
		final RSArea YCityArea = new RSArea(new RSTile(2583, 3076), new RSTile(2620, 3106));
		final RSArea AtWiz = new RSArea(new RSTile(2585, 3081), new RSTile(2596, 3094));
		final RSArea BankY = new RSArea(new RSTile(2609, 3088), new RSTile(2616, 3097));
		final RSArea GateY = new RSArea(new RSTile(2595, 3086), new RSTile(2598, 3089));
		final RSTile WizT = new RSTile(2595, 3087);
		final RSTile BankTY = new RSTile(2611, 3093);
	}

	private enum State {
		TOBANK, MINE, TOMINE, PORTAL, TELE, BANK, ERROR
	}

	private RSArea Bank;
	private RSArea Gate;
	private RSArea AtPerson;
	private RSArea CityArea;
	private RSTile toMine[];

	// private RSTile toBank[];
	private RSTile PersonT;
	private RSTile BankT;

	private final String[] colorstring = { "Black", "Blue", "Brown", "Cyan",
			"Green", "Lime", "Orange", "Pink", "Purple", "Red", "White",
			"Yellow" };
	private final String[] locationstring = { "Varrock", "Yanille" };
	private long nextBreak = System.currentTimeMillis();
	private long nextLength = 60000;
	private long totalBreakTime;
	private long lastBreakTime;
	private long nextBreakT;
	private long startTime;

	private long runTime;
	private long now;

	AaimistersGUI g = new AaimistersGUI();

	public final File settingsFile = new File(getCacheDirectory(), "AaimistersEMinerSettings.txt");
	NumberFormat formatter = new DecimalFormat("#,###,###");

	Font Cam10 = new Font("Cambria Math", Font.BOLD, 10);
	Font Cam = new Font("Cambria Math", Font.BOLD, 12);
	Color PercentGreen = new Color(0, 163, 4, 150);
	Color PercentRed = new Color(163, 4, 0, 150);
	Color White150 = new Color(255, 255, 255, 150);
	Color White90 = new Color(255, 255, 255, 90);
	Color White = new Color(255, 255, 255);
	Color Background = new Color(219, 200, 167);
	Color UpGreen = new Color(0, 169, 0);
	Color LineColor = new Color(0, 0, 0);
	Color ClickC = new Color(187, 0, 0);
	Color UpRed = new Color(169, 0, 0);
	Color Black = new Color(0, 0, 0);
	Color MainColor;

	Color ThinColor;

	Color BoxColor;
	final NumberFormat nf = NumberFormat.getInstance();

	private String currentOre = "";
	private String status = "";
	public boolean currentlyBreaking = false;
	public boolean clickedPortal;
	public boolean randomBreaks;
	public boolean clickedPer;
	public boolean bankedOpen;
	public boolean antiBanOn;
	public boolean notChosen;
	public boolean showPaint = true;
	public boolean painting;
	public boolean resting;
	public boolean checked;
	public boolean doBreak;
	public boolean opened;
	public boolean mining;
	public boolean check = true;
	public boolean rest;
	public boolean logTime;
	public boolean noClick;
	// Paint Buttons
	public boolean xButton = false;

	public boolean Stat = false;
	public boolean Main = true;

	private boolean varrock;
	private boolean closed;
	int pickaxes[] = { 1265, 1267, 1269, 1273, 1271, 1275, 15259 };
	int markerPlant = 9157;
	int teleport[] = { 13630, 13629, 13628 };
	int essence = 2491;
	int Aubury = 5913;
	int Wizard = 462;
	int priceEssence;
	int aubCount = 0;
	int rail = 493;
	int rock = 512;
	int errorCount;
	int xpEss = 5;
	int currentXP;
	int gainedLvl;
	int timeToLvl;
	int idle = 0;
	int startEXP;
	int essToLvl;
	int xpGained;
	int totalEss;
	int xpToLvl;
	int essHour;
	int GPtotal;
	int bankID;
	int GPHour;
	int banker;
	int random;
	int xpHour;
	int door;
	int iness;
	int maxBetween;
	int minBetween;

	int maxLength;;

	int minLength;

	private final Image logo = getImage("http://i88.photobucket.com/albums/k170/aaimister/AaimistersEssenceMiner.gif");

	private final Image atom = getImage("http://i88.photobucket.com/albums/k170/aaimister/Atomm.png");

	private boolean breakingCheck() {
		if (nextBreak <= System.currentTimeMillis()) {
			return true;
		}
		return false;
	}

	private void breakingNew() {
		if (randomBreaks) {
			final long varTime = random(3660000, 10800000);
			nextBreak = System.currentTimeMillis() + varTime;
			nextBreakT = varTime;
			final long varLength = random(900000, 3600000);
			nextLength = varLength;
		} else {
			final int diff = random(0, 5) * 1000 * 60;
			final long varTime = random(minBetween * 1000 * 60 + diff, maxBetween
					* 1000 * 60 - diff);
			nextBreak = System.currentTimeMillis() + varTime;
			nextBreakT = varTime;
			final int diff2 = random(0, 5) * 1000 * 60;
			final long varLength = random(minLength * 1000 * 60 + diff2, maxLength
					* 1000 * 60 - diff2);
			nextLength = varLength;
		}
		logTime = true;
	}

	public boolean checkDoor() {
		try {
			final RSObject closed = objects.getNearest(door);
			if (Gate.contains(closed.getLocation())) {
				return true;
			} else {
				return false;
			}
		} catch (final Exception e) {
			return false;
		}
	}

	public void checkPlayer() {
		if (!check) {
			return;
		}
		final RSPlayer near = playerNear();
		if (near != null) {
			if (!getMyPlayer().isMoving()) {
				if (near.getScreenLocation() != null) {
					if (mouse.getLocation() != near.getScreenLocation()) {
						mouse.move(near.getScreenLocation());
						sleep(300, 550);
					}
					mouse.click(false);
					sleep(300, 500);
					if (menu.isOpen() && menu.contains("Follow")) {
						final Point menuu = menu.getLocation();
						final int Mx = menuu.y;
						final int My = menuu.y;
						final int x = Mx + random(3, 120);
						final int y = My + random(3, 98);
						mouse.move(x, y);
						sleep(2320, 3520);
						mouse.moveRandomly(100, 900);
						if (menu.isOpen()) {
							mouse.moveRandomly(100, 900);
							sleep(50);
						}
						if (menu.isOpen()) {
							mouse.moveRandomly(100, 900);
							sleep(50);
						}
					} else {
						mouse.moveRandomly(100, 900);
					}
				}
			} else {
				return;
			}
		}
	}

	public void checkXP() {
		if (!check) {
			return;
		}
		if (game.getCurrentTab() != 2) {
			game.openTab(2);
			sleep(500, 900);
		}
		mouse.move(random(678, 729), random(214, 233));
		sleep(2800, 5500);
		game.openTab(4);
		sleep(50, 100);
		mouse.moveRandomly(50, 900);
	}

	private void close() {
		final RSComponent close = interfaces.getComponent(620, 18);
		close.doAction("Close");
		sleep(100, 300);
	}

	private void createAndWaitforGUI() {
		if (SwingUtilities.isEventDispatchThread()) {
			g.AaimistersGUI.setVisible(true);
		} else {
			try {
				SwingUtilities.invokeAndWait(new Runnable() {

					public void run() {
						g.AaimistersGUI.setVisible(true);
					}
				});
			} catch (final InvocationTargetException ite) {
			} catch (final InterruptedException ie) {
			}
		}
		sleep(100);
		while (g.AaimistersGUI.isVisible()) {
			sleep(100);
		}
	}

	public void doAntiBan() {

		if (!antiBanOn) {
			return;
		}

		final int action = random(0, 6);

		switch (action) {
		case 0:
			random = random(1, 3);
			if (random == random(1, 3)) {
				rotateCamera();
				sleep(200, 400);
			}
			break;
		case 1:
			mouse.moveRandomly(100, 900);
			sleep(200, 400);
			break;
		case 2:
			mouse.moveRandomly(100, 900);
			sleep(200, 400);
			break;
		case 3:
			random = random(1, 6);
			if (random == random(0, 7)) {
				checkXP();
				sleep(200, 400);
			}
			break;
		case 4:
			mouse.moveRandomly(100, 900);
			sleep(200, 400);
			break;
		case 5:
			random = random(1, 3);
			if (random == random(0, 4)) {
				checkPlayer();
				sleep(200, 400);
			}
			break;
		}
	}

	private void doRest() {
		if (walking.getEnergy() < random(10, 30)
				&& calc.distanceTo(PersonT) >= 7 && ra() == null) {
			if (!resting && !mining) {
				status = "Resting...";
				interfaces.getComponent(750, 6).doAction("Rest");
				mouse.moveSlightly();
				resting = true;
				sleep(1500, 2000);
				return;
			}
		}
		if (resting) {
			if (getMyPlayer().getAnimation() == -1) {
				resting = false;
			}
			if (walking.getEnergy() > random(93, 100)) {
				resting = false;
			}
			if (random(1, 7) == 2) {
				check = false;
				doAntiBan();
			}
		}
	}

	public void drawMouse(final Graphics g) {
		final Point loc = mouse.getLocation();
		final long mpt = System.currentTimeMillis() - mouse.getPressTime();
		if (mouse.getPressTime() == -1 || mpt >= 1000) {
			g.setColor(ThinColor);
			g.drawLine(0, loc.y, 766, loc.y);
			g.drawLine(loc.x, 0, loc.x, 505);
			g.setColor(MainColor);
			g.drawLine(0, loc.y + 1, 766, loc.y + 1);
			g.drawLine(0, loc.y - 1, 766, loc.y - 1);
			g.drawLine(loc.x + 1, 0, loc.x + 1, 505);
			g.drawLine(loc.x - 1, 0, loc.x - 1, 505);
		}
		if (mpt < 1000) {
			g.setColor(ClickC);
			g.drawLine(0, loc.y, 766, loc.y);
			g.drawLine(loc.x, 0, loc.x, 505);
			g.setColor(MainColor);
			g.drawLine(0, loc.y + 1, 766, loc.y + 1);
			g.drawLine(0, loc.y - 1, 766, loc.y - 1);
			g.drawLine(loc.x + 1, 0, loc.x + 1, 505);
			g.drawLine(loc.x - 1, 0, loc.x - 1, 505);
		}
	}

	public void drawObjects(final Graphics g) {
		// Person
		if (perNPC() != null) {
			final RSNPC per = perNPC();
			final RSTile t = per.getLocation();
			final RSTile tx = new RSTile(t.getX() + 1, t.getY());
			final RSTile ty = new RSTile(t.getX(), t.getY() + 1);
			final RSTile txy = new RSTile(t.getX() + 1, t.getY() + 1);
			calc.tileToScreen(t);
			calc.tileToScreen(tx);
			calc.tileToScreen(ty);
			calc.tileToScreen(txy);
			final Point pn = calc.tileToScreen(t, 0, 0, 0);
			final Point px = calc.tileToScreen(tx, 0, 0, 0);
			final Point py = calc.tileToScreen(ty, 0, 0, 0);
			final Point pxy = calc.tileToScreen(txy, 0, 0, 0);
			if (calc.pointOnScreen(pn) && calc.pointOnScreen(px)
					&& calc.pointOnScreen(py) && calc.pointOnScreen(pxy)) {
				g.setColor(Black);
				g.drawPolygon(new int[] { py.x, pxy.x, px.x, pn.x }, new int[] {
						py.y, pxy.y, px.y, pn.y }, 4);
				g.setColor(ThinColor);
				g.fillPolygon(new int[] { py.x, pxy.x, px.x, pn.x }, new int[] {
						py.y, pxy.y, px.y, pn.y }, 4);
			}
		}

		// Portal
		if (ra() != null) {
			final RSTile t = portal().getLocation();
			final RSTile tx = new RSTile(t.getX() + 1, t.getY());
			final RSTile ty = new RSTile(t.getX(), t.getY() + 1);
			final RSTile txy = new RSTile(t.getX() + 1, t.getY() + 1);
			calc.tileToScreen(t);
			calc.tileToScreen(tx);
			calc.tileToScreen(ty);
			calc.tileToScreen(txy);
			final Point pn = calc.tileToScreen(t, 0, 0, 0);
			final Point px = calc.tileToScreen(tx, 0, 0, 0);
			final Point py = calc.tileToScreen(ty, 0, 0, 0);
			final Point pxy = calc.tileToScreen(txy, 0, 0, 0);
			if (calc.pointOnScreen(pn) && calc.pointOnScreen(px)
					&& calc.pointOnScreen(py) && calc.pointOnScreen(pxy)) {
				g.setColor(Black);
				g.drawPolygon(new int[] { py.x, pxy.x, px.x, pn.x }, new int[] {
						py.y, pxy.y, px.y, pn.y }, 4);
				g.setColor(ThinColor);
				g.fillPolygon(new int[] { py.x, pxy.x, px.x, pn.x }, new int[] {
						py.y, pxy.y, px.y, pn.y }, 4);
			}
		}
	}

	String formatTime(final int milliseconds) {
		final long t_seconds = milliseconds / 1000;
		final long t_minutes = t_seconds / 60;
		final long t_hours = t_minutes / 60;
		final int seconds = (int) (t_seconds % 60);
		final int minutes = (int) (t_minutes % 60);
		final int hours = (int) (t_hours % 60);
		return nf.format(hours) + ":" + nf.format(minutes) + ":"
				+ nf.format(seconds);
	}

	// Credits Aion
	private int getGuidePrice(final int itemID) {
		try {
			final URL url = new URL("http://services.runescape.com/m=itemdb_rs/viewitem.ws?obj="
					+ itemID);
			final BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = null;

			while ((line = br.readLine()) != null) {
				if (line.contains("<b>Current guide price:</b>")) {
					line = line.replace("<b>Current guide price:</b>", "");
					return (int) parse(line);
				}
			}
		} catch (final IOException e) {
		}
		return -1;
	}

	private Image getImage(final String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (final IOException e) {
			log("FUCK");
			return null;
		}
	}

	private RSTile getLocation() {
		return getMyPlayer().getLocation();
	}

	private State getState() {
		if (inventory.isFull()) {
			mining = false;
			if (Bank.contains(getLocation())) {
				return State.BANK;
			} else if (ra() != null) {
				return State.PORTAL;
			} else if (CityArea.contains(getLocation())
					&& !Bank.contains(getLocation())) {
				return State.TOBANK;
			} else {
				return State.ERROR;
			}
		} else if (!inventory.isFull()) {
			if (AtPerson.contains(getLocation())) {
				return State.TELE;
			} else if (ra() != null) {
				try {
					final RSObject ess = objects.getNearest(2491);
					final RSTile loc = ess.getArea().getNearestTile(getLocation());
					if (!calc.canReach(loc, true)
							&& calc.tileOnMap(ess.getLocation())) {
						return State.PORTAL;
					} else {
						return State.MINE;
					}
				} catch (final Exception e) {
					final RSObject ess = objects.getNearest(2491);
					walking.walkTileMM(walking.getClosestTileOnMap(ess.getArea().getNearestTile(getLocation())));
				}
			} else if (CityArea.contains(getLocation())
					&& !AtPerson.contains(getLocation())) {
				return State.TOMINE;
			} else {
				return State.ERROR;
			}
		}
		return State.ERROR;
	}

	public double getVersion() {
		return 1.31;
	}

	private RSNPC interactingNPC() {
		return npcs.getNearest(new Filter<RSNPC>() {
			public boolean accept(final RSNPC n) {
				return n.getInteracting() != null
						&& n.getInteracting().equals(getMyPlayer())
						&& Bank.contains(n.getLocation());
			}
		});
	}

	public boolean isMember() {
		return AccountManager.isMember(account.getName());
	}

	private String Location() {
		if (AtPerson.contains(getLocation())) {
			if (varrock) {
				return "Aubury's";
			} else {
				return "Wizard's";
			}
		} else if (Bank.contains(getLocation())) {
			return "Bank";
		} else if (ra() != null) {
			return "Mine";
		} else if (calc.distanceTo(BankT) > 100 && ra() == null) {
			if (!game.isLoggedIn()) {
				return "Login Screen";
			} else {
				return "Unknown";
			}
		} else {
			if (varrock) {
				return "Varrock";
			} else {
				return "Yanille";
			}
		}
	}

	@Override
	public int loop() {
		if (breakingCheck() && doBreak) {
			status = "Breaking...";
			final long endTime = System.currentTimeMillis() + nextLength;
			totalBreakTime += nextLength + 5000;
			lastBreakTime = totalBreakTime - (nextLength + 5000);
			currentlyBreaking = true;
			while (game.isLoggedIn()) {
				game.logout(false);
				sleep(50);
			}
			log("Taking a break for " + formatTime((int) nextLength));
			while (System.currentTimeMillis() < endTime
					&& currentlyBreaking == true) {
				sleep(1000);
			}
			currentlyBreaking = false;
			while (!game.isLoggedIn()) {
				try {
					breakingNew();
					game.login();
				} catch (final Exception e) {
					return 10;
				}
				sleep(50);
			}
			return 10;
		}

		if (!game.isLoggedIn()) {
			status = "Breaking...";
			return 3000;
		}

		if (startTime == 0 && skills.getCurrentLevel(14) != 0) {
			startTime = System.currentTimeMillis();
			startEXP = skills.getCurrentExp(14);
			currentXP = skills.getExpToNextLevel(14);
		}

		currentlyBreaking = false;

		if (logTime) {
			log("Next Break In: " + formatTime((int) nextBreakT) + " For: "
					+ formatTime((int) nextLength) + ".");
			logTime = false;
		}

		mouse.setSpeed(random(4, 9));
		setCamera();
		setRun();

		if (resting) {
			status = "Resting...";
			random = random(0, 7);
			if (random == random(0, 7)) {
				check = false;
				doAntiBan();
			}
			if (getMyPlayer().getAnimation() == -1 && !mining) {
				doRest();
				sleep(200, 800);
			}
			return random(250, 450);
		}

		switch (getState()) {
		case TOBANK:
			clickedPortal = false;
			status = "Walking to bank...";
			try {
				if (!Bank.contains(getLocation())) {
					if (AtPerson.contains(getLocation())) {
						if (checkDoor()) {
							openDoor();
							return random(300, 500);
						} else {
							walkTo(BankT.randomize(2, 2));
							// walking.newTilePath(toBank).randomize(2,
							// 2).traverse();
							return random(300, 600);
						}
					} else {
						walkTo(BankT.randomize(2, 2));
						// walking.newTilePath(toBank).randomize(2,
						// 2).traverse();
						return random(300, 600);
					}
				}
			} catch (final Exception e) {
				idle++;
			}

			break;
		case MINE:
			clickedPer = false;
			if (idle > 7) {
				mining = false;
				idle = 0;
			}
			try {
				if (getMyPlayer().getAnimation() != -1
						|| getMyPlayer().getAnimation() == 6752) {
					if (random(0, 10) == random(0, 10)) {
						doAntiBan();
					}
					idle = 0;
				}
				if (ra() != null) {
					final RSObject ess = objects.getNearest(essence);
					if (ess != null) {
						final RSTile loc = ess.getArea().getNearestTile(getLocation());
						if (!ess.isOnScreen()) {
							idle++;
							if (calc.distanceTo(loc) > 3 && !mining) {
								status = "Walking to essence...";
								if (!getMyPlayer().isMoving()
										|| calc.distanceTo(walking.getDestination()) < 4) {
									walking.walkTileMM(walking.getClosestTileOnMap(loc.randomize(2, 2)));
									return random(150, 300);
								}
							} else if (!mining) {
								camera.turnTo(ess);
								return random(300, 500);
							}
						} else {
							idle++;
							if (calc.distanceTo(loc) > 3) {
								if (!getMyPlayer().isMoving()
										|| calc.distanceTo(walking.getDestination()) < 4) {
									walking.walkTileMM(walking.getClosestTileOnMap(loc.randomize(2, 2)));
									return random(150, 300);
								}
							} else if (!mining) {
								status = "Mining...";
								ess.doAction("Mine");
								mining = true;
								idle = 0;
								return random(2000, 2700);
							}
						}
					}
				}
			} catch (final Exception e) {
				idle++;
			}

			break;
		case TOMINE:
			bankedOpen = false;
			mining = false;
			notChosen = true;
			opened = false;
			if (varrock) {
				status = "Walking to Aubury...";
			} else {
				status = "Walking to Wizard...";
			}
			try {
				if (!AtPerson.contains(getLocation())) {
					if (calc.distanceTo(PersonT) <= 6) {
						if (checkDoor()) {
							final RSObject closed = objects.getNearest(door);
							if (calc.distanceTo(closed.getLocation()) > 3) {
								walking.walkTileMM(closed.getLocation().randomize(1, 1));
								return random(1500, 1800);
							} else {
								openDoor();
							}
							return random(300, 500);
						} else {
							// walkTo(PersonT.randomize(2, 2));
							// walking.newTilePath(toMine).randomize(2,
							// 2).traverse();
							walkPath(toMine);
							return random(300, 600);
						}
					} else {
						// walkTo(PersonT.randomize(2, 2));
						// walking.newTilePath(toMine).randomize(2,
						// 2).traverse();
						walkPath(toMine);
						return random(300, 600);
					}
				}
			} catch (final Exception e) {
				idle++;
			}

			break;
		case TELE:
			try {
				if (idle > 15) {
					if (ra() == null) {
						final RSNPC per = perNPC();
						if (interfaces.getComponent(620, 18).isValid()) {
							close();
						}
						if (per != null) {
							per.doAction("Teleport");
							sleep(2000, 3000);
						} else {
							final RSNPC plant = plantNPC();
							plant.doAction("Teleport");
							sleep(2000, 3000);
						}
					} else {
						return 10;
					}
					idle = 0;
				}
				if (interfaces.getComponent(620, 18).isValid()) {
					close();
					sleep(300);
					clickedPer = false;
					return 350;
				}
				if (!clickedPer) {
					final RSNPC per = perNPC();
					if (per != null) {
						idle++;
						if (!clickedPer) {
							per.doAction("Teleport");
							clickedPer = true;
							waitToGO(true);
						} else {
							return random(200, 800);
						}
					} else {
						final RSNPC plant = plantNPC();
						idle++;
						if (!clickedPer) {
							plant.doAction("Teleport");
							clickedPer = true;
							waitToGO(true);
						} else {
							return random(200, 800);
						}
					}
				} else {
					idle++;
					return random(300, 500);
				}
			} catch (final Exception e) {
				idle++;
			}

			break;
		case PORTAL:
			status = "Walking to bank...";
			if (idle > 7) {
				clickedPortal = false;
				idle = 0;
			}
			try {
				if (!clickedPortal) {
					if (portal() != null) {
						final RSObject p = objects.getNearest(39831);
						final RSTile loc = p.getArea().getNearestTile(getLocation());
						if (!portal().isOnScreen()) {
							if (calc.distanceTo(loc) > 3) {
								walking.walkTileMM(walking.getClosestTileOnMap(loc.randomize(2, 2)));
								return random(1200, 1500);
							} else {
								idle++;
								if (calc.distanceTo(loc) > 3) {
									if (!getMyPlayer().isMoving()
											|| calc.distanceTo(walking.getDestination()) < 4) {
										walking.walkTileMM(walking.getClosestTileOnMap(loc.randomize(2, 2)));
										return random(150, 300);
									}
								} else if (!clickedPortal) {
									portal().doAction("Enter");
									clickedPortal = true;
									idle = 0;
									waitToGO(false);
									return random(200, 500);
								}
							}
						} else {
							idle++;
							if (calc.distanceTo(loc) > 2) {
								if (!getMyPlayer().isMoving()
										|| calc.distanceTo(walking.getDestination()) < 4) {
									walking.walkTileMM(walking.getClosestTileOnMap(loc.randomize(2, 2)));
									return random(150, 300);
								}
							}
							if (!clickedPortal) {
								portal().doAction("Enter");
								clickedPortal = true;
								idle = 0;
								waitToGO(true);
								return random(200, 500);
							}
						}
					}
				} else {
					idle++;
					return random(500, 800);
				}
			} catch (final Exception e) {
				idle++;
			}

			break;
		case BANK:
			status = "Banking...";
			if (idle > 7) {
				opened = false;
				notChosen = true;
				bankedOpen = false;
				idle = 0;
			}
			if (notChosen) {
				if (random(0, 5) == 0 || random(0, 5) == 2) {
					// bankID = boo;
				} else {
					// bankID = banker;
				}
				notChosen = false;
			}
			try {
				if (!bank.isOpen()) {
					if (bankID != 0) {
						final RSObject ban = objects.getNearest(bankID);
						if (!ban.isOnScreen()) {
							camera.turnTo(ban);
							return random(300, 600);
						} else {
							idle++;
							if (!opened) {
								ban.doAction("Use-quic");
								opened = true;
								idle = 0;
								return random(500, 1000);
							}
						}
					}
				} else {
					opened = false;
					if (inventory.containsOneOf(pickaxes)) {
						final RSItem rune = inventory.getItem(iness);
						idle++;
						if (!bankedOpen) {
							rune.doAction("Deposit-All");
							bankedOpen = true;
							return random(100, 150);
						}
					} else {
						idle++;
						if (!bankedOpen) {
							bank.depositAll();
							bankedOpen = true;
							return random(100, 150);
						}
					}
				}
			} catch (final Exception e) {
				idle++;
			}

			break;
		case ERROR:

			break;
		}
		return random(300, 600);
	}

	public void messageReceived(final MessageEvent e) {
		if (e.getMessage().contains("You've just advanced a Min")) {
			gainedLvl++;
		}
		if (e.getMessage().contains("You swing your")) {
			mining = true;
		}
	}

	public void mouseClicked(final MouseEvent e) {
	}

	public void mouseEntered(final MouseEvent e) {
	}

	public void mouseExited(final MouseEvent e) {
	}

	public void mousePressed(final MouseEvent e) {
		// X Button
		if (e.getX() >= 497 && e.getX() < 497 + 16 && e.getY() >= 344
				&& e.getY() < 344 + 16) {
			if (!xButton) {
				xButton = true;
			} else {
				xButton = false;
			}
		}
		// Next Button
		if (e.getX() >= 478 && e.getX() < 478 + 16 && e.getY() >= 413
				&& e.getY() < 413 + 14) {
			if (Main) {
				Main = false;
				Stat = true;
			} else if (!Main) {
				Stat = false;
				Main = true;
			}
		}
		// Prev Button
		if (e.getX() >= 25 && e.getX() < 25 + 16 && e.getY() >= 413
				&& e.getY() < 413 + 14) {
			if (Main) {
				Main = false;
				Stat = true;
			} else if (!Main) {
				Stat = false;
				Main = true;
			}
		}
	}

	public void mouseReleased(final MouseEvent e) {
	}

	private RSPlayer myPlayer() {
		final String myName = players.getMyPlayer().getName();
		return players.getNearest(new Filter<RSPlayer>() {
			public boolean accept(final RSPlayer p) {
				return p.getName() == myName;
			}
		});
	}

	public void onFinish() {
		runTime = System.currentTimeMillis() - startTime - totalBreakTime;
		final long totalTime = System.currentTimeMillis() - startTime;
		final String formattedTime = formatTime((int) totalTime);
		log("Thanks for using Aaimister's Essence Miner!");
		log("In " + formattedTime + " You mined " + formatter.format(totalEss)
				+ " Ore(s) and Gained $" + formatter.format(GPtotal) + "!");
		log("You Gained: " + formatter.format(gainedLvl)
				+ " level(s) in Mining!");
	}

	public void onRepaint(final Graphics g) {
		if (showPaint) {
			final long totalTime = System.currentTimeMillis() - startTime;
			final String formattedTime = formatTime((int) totalTime);

			if (!currentlyBreaking) {
				runTime = System.currentTimeMillis() - startTime
						- totalBreakTime;
				now = totalTime;
				checked = false;
			} else {
				if (!game.isLoggedIn()) {
					if (!checked) {
						runTime = now - lastBreakTime;
						checked = true;
					}
				}
			}

			if (startTime != 0) {
				currentXP = skills.getExpToNextLevel(14);
				xpToLvl = skills.getExpToNextLevel(14);
				xpGained = skills.getCurrentExp(14) - startEXP;
				xpHour = (int) (3600000.0 / (double) runTime * xpGained);
				if (xpHour != 0) {
					timeToLvl = (int) ((double) xpToLvl / (double) xpHour * 3600000.0);
				}
				totalEss = (int) (xpGained / xpEss);
				GPtotal = (int) (totalEss * priceEssence);
				essHour = (int) (3600000.0 / (double) runTime * totalEss);
				GPHour = (int) (3600000.0 / (double) runTime * GPtotal);
				essToLvl = (int) (currentXP / xpEss);
			}

			if (painting) {
				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			}

			// Person
			// drawObjects(g);

			if (!xButton) {
				// Background
				g.setColor(MainColor);
				g.fillRect(6, 344, 507, 129);
				g.setColor(LineColor);
				g.drawRect(6, 344, 507, 129);
				// Logo
				g.drawImage(logo, 6, 348, null);
				g.drawImage(atom, 40, 358, null);
				g.setColor(LineColor);
				g.setFont(Cam10);
				g.drawString("By Aaimister © " + getVersion(), 379, 369);
				// Next Button
				g.setColor(BoxColor);
				g.fillRect(478, 413, 16, 14);
				g.setColor(LineColor);
				g.setFont(Cam);
				g.drawString(">", 481, 424);
				g.drawRect(478, 413, 16, 14);
				// Shadow
				g.setColor(White90);
				g.fillRect(478, 413, 16, 7);
				// Prev Button
				g.setColor(BoxColor);
				g.fillRect(25, 413, 16, 14);
				g.setColor(LineColor);
				g.setFont(Cam);
				g.drawString("<", 28, 424);
				g.drawRect(25, 413, 16, 14);
				// Shadow
				g.setColor(White90);
				g.fillRect(25, 413, 16, 7);
				// Main Box
				g.setColor(BoxColor);
				g.fillRect(59, 374, 401, 95);
				g.setColor(White90);
				g.fillRect(59, 374, 401, 46);
				// Text
				if (Main) {
					// Column 1
					g.setColor(LineColor);
					g.drawString("Time running: " + formattedTime, 63, 390);
					g.drawString("Location: " + Location(), 63, 404);
					g.drawString("Status: " + status, 63, 418);
					g.drawString("Current Ore: " + currentOre, 63, 433);
					g.drawString("Total XP: "
							+ formatter.format((long) xpGained), 63, 447);
					g.drawString("Total XP/h: "
							+ formatter.format((long) xpHour), 63, 463);
					// Column 2
					g.drawString("Price of Essence: $"
							+ formatter.format((long) priceEssence), 264, 390);
					g.drawString("Total Money: $"
							+ formatter.format((long) GPtotal), 264, 404);
					g.drawString("Money / Hour: $"
							+ formatter.format((long) GPHour), 264, 418);
					g.drawString("Total Essence: "
							+ formatter.format((long) totalEss), 264, 433);
					g.drawString("Essence / Hour: "
							+ formatter.format((long) essHour), 264, 447);
					g.drawString("Essence to Lvl: "
							+ formatter.format((long) essToLvl), 264, 463);
				}
				if (Stat) {
					// Column 1
					g.setColor(LineColor);
					g.drawString("Time running: " + formattedTime, 63, 390);
					g.drawString("Location: " + Location(), 63, 404);
					g.drawString("Status: " + status, 63, 418);
					g.drawString("Current Ore: " + currentOre, 63, 433);
					g.drawString("Total XP: "
							+ formatter.format((long) xpGained), 63, 447);
					g.drawString("Total XP/h: "
							+ formatter.format((long) xpHour), 63, 463);
					// Column 2
					g.drawString("Total Mining XP: "
							+ formatter.format((long) xpGained), 264, 390);
					g.drawString("Mining XP/h: "
							+ formatter.format((long) xpHour), 264, 404);
					g.drawString("Level In: " + formatTime(timeToLvl), 264, 418);
					g.drawString("Mining XP to Lvl: "
							+ formatter.format((long) xpToLvl), 264, 433);
					g.drawString("Current Lvl: " + skills.getCurrentLevel(14), 264, 447);
					g.drawString("Gained Lvl(s): "
							+ formatter.format((long) gainedLvl), 264, 463);
				}
				// % Bar
				g.setColor(MainColor);
				g.fillRect(4, 318, 512, 20);
				g.setColor(Black);
				g.fillRect(6, 320, 508, 16);
				g.setColor(PercentRed);
				g.fillRect(6, 320, 508, 16);
				g.setColor(PercentGreen);
				g.fillRect(6, 320, skills.getPercentToNextLevel(14)
						* (508 / 100), 16);
				g.setColor(White);
				g.setFont(Cam);
				g.drawString("" + skills.getPercentToNextLevel(14)
						+ "% to lvl " + (skills.getCurrentLevel(14) + 1)
						+ " Mining", 194, 332);
				// Shadow
				g.setColor(White90);
				g.fillRect(4, 318, 512, 10);
				// X
				g.setColor(LineColor);
				g.setFont(Cam);
				g.drawString("X", 501, 357);
				// Main Box Shadow
				g.setColor(LineColor);
				g.drawRect(59, 374, 401, 95);
				g.drawLine(260, 380, 260, 465);
			} else {
				// X Button
				g.setColor(MainColor);
				g.fillRect(497, 344, 16, 16);
				g.setColor(LineColor);
				g.drawRect(497, 344, 16, 16);
				// X
				g.setColor(LineColor);
				g.setFont(Cam);
				g.drawString("O", 501, 357);
				// Shadow
				g.setColor(White90);
				g.fillRect(497, 344, 17, 8);
			}

			// Mouse
			drawMouse(g);
		}
	}

	public boolean onStart() {
		status = "Starting up...";

		URLConnection url = null;
		BufferedReader in = null;
		BufferedWriter out = null;

		// Check right away...
		try {
			// Open the version text file
			url = new URL("http://aaimister.webs.com/scripts/AaimistersEssMinerVersion.txt").openConnection();
			// Create an input stream for it
			in = new BufferedReader(new InputStreamReader(url.getInputStream()));
			// Check if the current version is outdated
			if (Double.parseDouble(in.readLine()) > getVersion()) {
				if (JOptionPane.showConfirmDialog(null, "Update found. Do you want to update?") == 0) {
					// If so, allow the user to choose the file to be updated.
					JOptionPane.showMessageDialog(null, "Please choose 'AaimistersEssenceMiner.java' in your scripts folder and hit 'Open'");
					final JFileChooser fc = new JFileChooser();
					// Make sure "Open" was clicked.
					if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						// If so, set up the URL for the .java file and set up
						// the IO.
						url = new URL("http://aaimister.webs.com/scripts/AaimistersEssenceMiner.java").openConnection();
						in = new BufferedReader(new InputStreamReader(url.getInputStream()));
						out = new BufferedWriter(new FileWriter(fc.getSelectedFile().getPath()));
						String inp;
						/*
						 * Until we reach the end of the file, write the next
						 * line in the file and add a new line. Then flush the
						 * buffer to ensure we lose no data in the process.
						 */
						while ((inp = in.readLine()) != null) {
							out.write(inp);
							out.newLine();
							out.flush();
						}
						// Notify the user that the script has been updated, and
						// a recompile and reload is needed.
						log("Script successfully downloaded. Please recompile and reload your scripts!");
						return false;
					} else {
						log("Update canceled");
					}
				} else {
					log("Update canceled");
				}
			} else {
				JOptionPane.showMessageDialog(null, "You have the latest version.");// User
																					// has
																					// the
																					// latest
																					// version.
																					// Tell
																					// them!
				if (in != null) {
					in.close();
				}
			}
		} catch (final IOException e) {
			log("Problem getting version. Please visit the forums.");
			return false; // Return false if there was a problem
		}

		try {
			settingsFile.createNewFile();
		} catch (final IOException ignored) {

		}

		createAndWaitforGUI();
		if (closed) {
			log.severe("The GUI window was closed!");
			return false;
		}

		startEXP = skills.getCurrentExp(14);
		currentXP = skills.getExpToNextLevel(14);
		if (doBreak) {
			breakingNew();
		}

		if (!isMember()) {
			currentOre = "Essence";
			iness = 1436;
			priceEssence = getGuidePrice(iness);
			log("Price of Essence: " + priceEssence);
		} else if (isMember()) {
			currentOre = "Pure Essence";
			iness = 7936;
			priceEssence = getGuidePrice(iness);
			log("Price of Pure Essence: " + priceEssence);
		}

		return true;
	}

	public void openDoor() {
		final RSObject closed = objects.getNearest(door);
		final RSTile doorT = closed.getLocation();
		if (Gate.contains(doorT)) {
			if (closed != null) {
				if (!closed.isOnScreen()) {
					walking.walkTileMM(doorT);
					sleep(1200, 1500);
				} else {
					closed.doAction("Open");
					sleep(1000, 1200);
				}
			}
		}
	}

	// Credits Aion
	private double parse(String str) {
		if (str != null && !str.isEmpty()) {
			str = stripFormatting(str);
			str = str.substring(str.indexOf(58) + 2, str.length());
			str = str.replace(",", "");
			if (!str.endsWith("%")) {
				if (!str.endsWith("k") && !str.endsWith("m")) {
					return Double.parseDouble(str);
				}
				return Double.parseDouble(str.substring(0, str.length() - 1))
						* (str.endsWith("m") ? 1000000 : 1000);
			}
			final int k = str.startsWith("+") ? 1 : -1;
			str = str.substring(1);
			return Double.parseDouble(str.substring(0, str.length() - 1)) * k;
		}
		return -1D;
	}

	private RSNPC perNPC() {
		final RSNPC interacting = interactingNPC();
		return interacting != null ? interacting
				: npcs.getNearest(new Filter<RSNPC>() {
					public boolean accept(final RSNPC npc) {
						if (varrock) {
							return npc.getName().equals("Aubury")
									&& AtPerson.contains(npc.getLocation());
						} else {
							return npc.getName().equals("Wizard Distentor")
									&& AtPerson.contains(npc.getLocation());
						}
					}
				});
	}

	private RSNPC plantNPC() {
		final RSNPC interacting = interactingNPC();
		return interacting != null ? interacting
				: npcs.getNearest(new Filter<RSNPC>() {
					public boolean accept(final RSNPC npc) {
						return npc.getID() == markerPlant
								&& AtPerson.contains(npc.getLocation());
					}
				});
	}

	private RSPlayer playerNear() {
		final RSPlayer me = myPlayer();
		return me != null ? me : players.getNearest(new Filter<RSPlayer>() {
			public boolean accept(final RSPlayer p) {
				return !p.isMoving() && p.isOnScreen();
			}
		});
	}

	private RSNPC portal() {
		return npcs.getNearest(new Filter<RSNPC>() {
			public boolean accept(final RSNPC npc) {
				return npc.getID() == 13628 || npc.getID() == 13629
						|| npc.getID() == 13630;
			}
		});
	}

	private RSObject ra() {
		return objects.getNearest(new Filter<RSObject>() {
			public boolean accept(final RSObject ra) {
				return ra.getID() == rail || ra.getID() == rock;
			}
		});
	}

	public void rotateCamera() {
		if (!antiBanOn) {
			return;
		}
		final char[] LR = new char[] { KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT };
		final char[] UD = new char[] { KeyEvent.VK_DOWN, KeyEvent.VK_UP };
		final char[] LRUD = new char[] { KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
				KeyEvent.VK_UP, KeyEvent.VK_UP };
		final int randomLR = random(0, 2);
		final int randomUD = random(0, 2);
		final int randomAll = random(0, 4);
		if (random(0, 3) == 0) {
			keyboard.pressKey(LR[randomLR]);
			sleepCR(random(2, 9));
			keyboard.pressKey(UD[randomUD]);
			sleepCR(random(6, 10));
			keyboard.releaseKey(UD[randomUD]);
			sleepCR(random(2, 7));
			keyboard.releaseKey(LR[randomLR]);
		} else {
			keyboard.pressKey(LRUD[randomAll]);
			if (randomAll > 1) {
				sleepCR(random(6, 11));
			} else {
				sleepCR(random(9, 12));
			}
			keyboard.releaseKey(LRUD[randomAll]);
		}
	}

	private void setCamera() {
		if (camera.getPitch() < 10) {
			camera.setPitch(true);
			sleep(1000, 1600);
		}
	}

	private void setRun() {
		if (!walking.isRunEnabled()) {
			if (walking.getEnergy() >= random(45, 100)) {
				walking.setRun(true);
				sleep(1000, 1600);
			}
		} else {
			if (rest) {
				if (calc.distanceTo(PersonT) >= 7 || ra() == null) {
					doRest();
				}
			}
		}
	}

	private boolean sleepCR(final int amtOfHalfSecs) {
		for (int x = 0; x < amtOfHalfSecs + 1; x++) {
			sleep(random(48, 53));
		}
		return true;
	}

	// Credits Aion
	private String stripFormatting(final String str) {
		if (str != null && !str.isEmpty()) {
			return str.replaceAll("(^[^<]+>|<[^>]+>|<[^>]+$)", "");
		}
		return "";
	}

	private void waitToGO(final boolean yes) {
		if (yes) {
			while (!AtPerson.contains(getLocation())) {
				if (idle > 45) {
					break;
				}
				sleep(200);
				idle++;
			}
		} else {
			while (AtPerson.contains(getLocation())) {
				if (idle > 45) {
					break;
				}
				sleep(200);
				idle++;
			}
		}
	}

	private boolean walkPath(final RSTile[] tiles) {
		try {
			if (!getMyPlayer().isMoving()
					|| calc.distanceTo(walking.getDestination()) < 4) {
				return walking.newTilePath(tiles).randomize(2, 2).traverse();
			}
		} catch (final Exception e) {

		}
		return false;
	}

	private boolean walkTo(final RSTile tile) {
		final RSWeb walkWeb = web.getWeb(getMyPlayer().getLocation(), tile);
		try {
			if (walkWeb != null) {
				if (!getMyPlayer().isMoving()
						|| calc.distanceTo(walking.getDestination()) < 4) {
					return walkWeb.step();
				}
			}
		} catch (final Exception e) {

		}
		return false;
	}
}
