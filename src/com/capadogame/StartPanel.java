package com.capadogame;

import java.awt.*;
import javax.swing.*;

public class StartPanel extends JPanel {
	private JLabel jlStart;
	private ImageIcon iiPlay;
	private JButton jbPlay;
	private TopBar topBar;
	private JPanel centerBox;
	private BottomBar bottomBar;
	private BoxLayout boxLayoutCB;
	private BorderLayout borderLayoutP;
	
	public StartPanel (CardLayoutWindow parent, int areaWidth, int areaHeight, 
			Trophy trophy, Heart heart) {		
		/* Top Bar */
		topBar = new TopBar(parent, areaWidth, areaHeight, trophy, heart);
		
		/* Center Box */
		centerBox = new JPanel();
		boxLayoutCB = new BoxLayout(centerBox, BoxLayout.Y_AXIS);
		centerBox.setLayout(boxLayoutCB);
		
		jlStart = new JLabel("Start");
		jlStart.setFont(new Font("Monospaced", Font.BOLD, 24));
		jlStart.setForeground(new Color(167, 171, 166));
		jlStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		jbPlay = new JButton();
		jbPlay.setBorder(null);
		jbPlay.setFocusPainted(false);
		jbPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbPlay.setActionCommand("Play");
		iiPlay = new ImageIcon(new ImageIcon("res/play.png").getImage().
				getScaledInstance(areaHeight/6, areaHeight/6, Image.SCALE_SMOOTH));
		jbPlay.setIcon(iiPlay);
		
		centerBox.add(Box.createRigidArea(new Dimension(0, 
				(areaHeight-areaHeight/12-areaHeight/8-areaHeight/9-100)/2)));
		centerBox.add(jlStart);
		centerBox.add(jbPlay);
		centerBox.setBackground(new Color(185, 226, 245));
		
		/* Bottom Bar */
		bottomBar = new BottomBar(parent, areaWidth, areaHeight, 0);
		
		/* Panel */		
		borderLayoutP = new BorderLayout();
		setLayout(borderLayoutP);
		add(topBar, BorderLayout.NORTH);
		add(centerBox, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);
		
		jbPlay.addActionListener(parent);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		topBar.draw(g);
	}
	
}
