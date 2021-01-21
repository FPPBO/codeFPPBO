package com.capadogame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

public class PackPanel extends JPanel {
	private List<JButton> jbLevel;
	private TopBar topBar;
	private JPanel centerBox;
	private BottomBar bottomBar;
	private BoxLayout boxLayoutCB;
	private BorderLayout borderLayoutP;
	
	public PackPanel (CardLayoutWindow parent, int areaWidth, int areaHeight,
			Trophy trophy, Heart heart) {		
		/* Top Bar */
		topBar = new TopBar(parent, areaWidth, areaHeight, trophy, heart);
		
		 /* Center Box */
		centerBox = new JPanel();
		boxLayoutCB = new BoxLayout(centerBox, BoxLayout.Y_AXIS);
		centerBox.setLayout(boxLayoutCB);
		
		jbLevel = new ArrayList<JButton>();
		jbLevel.add(new JButton("Easy"));
		jbLevel.add(new JButton("Medium"));
		jbLevel.add(new JButton("Hard"));
		for (int i=0; i<jbLevel.size(); i++) {
			int lr = 0;
			if (i == 0)	lr = 70;
			else if(i == 1)	lr = 56;
			else if (i == 2)lr = 71;
			jbLevel.get(i).setFont(new Font("Arial", Font.BOLD, 20));
			jbLevel.get(i).setBackground(new Color(190, 230, 250));
			jbLevel.get(i).setForeground(Color.GRAY);
			jbLevel.get(i).setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createCompoundBorder(
							BorderFactory.createRaisedBevelBorder(), 
							BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)), 
					BorderFactory.createEmptyBorder(7, lr, 7, lr)));
			jbLevel.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
			jbLevel.get(i).setFocusPainted(false);
			jbLevel.get(i).setActionCommand(jbLevel.get(i).getText());
		}
		
		centerBox.add(Box.createRigidArea(new Dimension(0, areaHeight/4)));
		centerBox.add(jbLevel.get(0));
		centerBox.add(Box.createRigidArea(new Dimension(0, 10)));
		centerBox.add(jbLevel.get(1));
		centerBox.add(Box.createRigidArea(new Dimension(0, 10)));
		centerBox.add(jbLevel.get(2));
		centerBox.setBackground(new Color(245, 222, 89));
		
		/* Bottom Bar */
		bottomBar = new BottomBar(parent, areaWidth, areaHeight, 0);
		
		/* Panel */		
		borderLayoutP = new BorderLayout();
		setLayout(borderLayoutP);
		add(topBar, BorderLayout.NORTH);
		add(centerBox, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);
		
		for (JButton jbLevel: jbLevel) {
			jbLevel.addActionListener(parent);
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		topBar.draw(g);
	}
	
}
