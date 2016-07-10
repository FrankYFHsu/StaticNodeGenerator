/* 
 * Copyright 2016 NCLab.
 * Released under GPLv3. See LICENSE.txt for details. 
 */
package tw.edu.ncu.ce.nclab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class StaticNodeGeneratorGUI extends JFrame {

	private Host mSelectedHost = null;

	private int id = 0;

	/**
	 * 
	 */
	private static final long serialVersionUID = -170149224840653514L;
	private JTextField rangeLengthField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaticNodeGeneratorGUI frame = new StaticNodeGeneratorGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StaticNodeGeneratorGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout());

		MapAreaPanel showPanel = new MapAreaPanel(this);
		showPanel.setBackground(Color.WHITE);

		showPanel.setLayout(null);

		contentPane.add(showPanel, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel();

		contentPane.add(controlPanel, BorderLayout.EAST);

		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

		JLabel rangeLabel = new JLabel("Range");

		controlPanel.add(rangeLabel);

		rangeLengthField = new JTextField();
		rangeLengthField.setText("100");
		rangeLengthField.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				rangeLengthField.getPreferredSize().height));
		rangeLengthField.setHorizontalAlignment(SwingConstants.LEFT);
		rangeLengthField.setAlignmentX(Component.LEFT_ALIGNMENT);
		rangeLengthField.setColumns(1);

		controlPanel.add(rangeLengthField);

		JButton btnADD = new JButton("Add");
		btnADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double range = Double.parseDouble(rangeLengthField.getText());
				Coord initCoord = new Coord(0, 0);
				setSeletedHost(new Host(id, initCoord, range, showPanel));
				id++;
			}
		});
		controlPanel.add(btnADD);

		JButton btnOutPut = new JButton("Output");
		btnOutPut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Component[] components = showPanel.getComponents();
				for (Component c : components) {
					// TODO to print nodes' location
					if (c instanceof Host) {
						Host h = (Host) c;
						System.out.println(h);
					}
				}

			}
		});
		controlPanel.add(btnOutPut);

		// TODO add a textField to input location and a button to perform
		// drawing host at that location

		// TODO maybe use JScrollPane?
		this.setResizable(false);
		pack();

	}

	public Host getSeletedHost() {
		return this.mSelectedHost;
	}

	public void setSeletedHost(Host newHost) {
		this.mSelectedHost = newHost;
	}

}
