package com.capadogame;

import java.awt.*;
import javax.swing.*;

public class BottomBar extends JPanel{
	private ImageIcon iiStart, iiProfile, iiShop;
	private JButton jbStart, jbProfile, jbShop;
	private BoxLayout boxLayoutBB;
	
	public BottomBar(CardLayoutWindow parent, int areaWidth, int areaHeight, int type) {
		boxLayoutBB = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(boxLayoutBB);
		
		/* Start */
		jbStart = new JButton();
		jbStart.setBorder(null);
		jbStart.setFocusPainted(false);
		jbStart.setActionCommand("Start");
		if (type == 0) // open start
			iiStart = new ImageIcon(new ImageIcon("res/start.png").getImage().
					getScaledInstance(areaHeight/9, areaHeight/9, Image.SCALE_SMOOTH));
		else
			iiStart = new ImageIcon(new ImageIcon("res/non-start.png").getImage().
					getScaledInstance(areaHeight/9, areaHeight/9, Image.SCALE_SMOOTH));
		jbStart.setIcon(iiStart);
		
		/* Profile */
		jbProfile = new JButton();		
		jbProfile.setBorder(null);
		jbProfile.setFocusPainted(false);
		jbProfile.setActionCommand("Profile");
		if (type == 1) // open profile
			iiProfile = new ImageIcon(new ImageIcon("res/profile.png").getImage().
					getScaledInstance(areaHeight/9, areaHeight/9, Image.SCALE_SMOOTH));
		else
			iiProfile = new ImageIcon(new ImageIcon("res/non-profile.png").getImage().
					getScaledInstance(areaHeight/9, areaHeight/9, Image.SCALE_SMOOTH));
		jbProfile.setIcon(iiProfile);
		
		/* Shop */
		jbShop = new JButton();
		jbShop.setBorder(null);
		jbShop.setFocusPainted(false);
		jbShop.setActionCommand("Shop");
		if (type == 2) // open shop
			iiShop = new ImageIcon(new ImageIcon("res/shop.png").getImage().
					getScaledInstance(areaHeight/9, areaHeight/9, Image.SCALE_SMOOTH));
		else
			iiShop = new ImageIcon(new ImageIcon("res/non-shop.png").getImage().
					getScaledInstance(areaHeight/9, areaHeight/9, Image.SCALE_SMOOTH));
		jbShop.setIcon(iiShop);
		
		add(Box.createRigidArea(new Dimension ((areaWidth-3*(areaHeight/9))/4, 0)));
		add(jbStart);
		add(Box.createRigidArea(new Dimension ((areaWidth-3*(areaHeight/9))/4, 0)));
		add(jbProfile);
		add(Box.createRigidArea(new Dimension ((areaWidth-3*(areaHeight/9))/4, 0)));
		add(jbShop);
		setBackground(Color.WHITE);
		
		jbStart.addActionListener(parent);
		jbProfile.addActionListener(parent);
		jbShop.addActionListener(parent);
	}
}
