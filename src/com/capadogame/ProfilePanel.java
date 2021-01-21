package com.capadogame;

import java.awt.*;
import javax.swing.*;

public class ProfilePanel extends JPanel {
	private int areaWidth, areaHeight;
	private TopBar topBar;
	private JPanel centerBox;
	private BottomBar bottomBar;
	private BoxLayout boxLayoutCB;
	private BorderLayout borderLayoutP;
	
	public ProfilePanel (CardLayoutWindow parent, int areaWidth, int areaHeight, 
			Trophy trophy, Heart heart) {
		this.areaWidth = areaWidth;
		this.areaHeight = areaHeight;
		
		/* Top Bar */
		topBar = new TopBar(parent, areaWidth, areaHeight, trophy, heart);
		
		 /* Center Box */
		centerBox = new JPanel();
		boxLayoutCB = new BoxLayout(centerBox, BoxLayout.Y_AXIS);
		centerBox.setLayout(boxLayoutCB);
		
		/* Bottom Bar */
		bottomBar = new BottomBar(parent, areaWidth, areaHeight, 1);
		
		/* Panel */		
		borderLayoutP = new BorderLayout();
		setLayout(borderLayoutP);
		add(topBar, BorderLayout.NORTH);
		add(centerBox, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		topBar.draw(g);
	}
	
}
