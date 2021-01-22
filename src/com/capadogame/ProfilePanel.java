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
	private JLabel name ;
	
	public ProfilePanel (CardLayoutWindow parent, int areaWidth, int areaHeight, 
			Trophy trophy, Heart heart, Profile profile) {
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
		
		JTextPane title3 = new JTextPane();
		title3.setForeground(new Color(255, 255, 255));
		title3.setFont(new Font("STXinwei", Font.BOLD, 40));
		title3.setText("PROFILE   INFO");
		title3.setBackground(new Color(70, 130, 180));
		title3.setBounds(100, 150, 200, 200);
		add(title3);
		
		name = new JLabel();
		name.setFont(new Font("STXinwei", Font.BOLD | Font.ITALIC, 25));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setText(profile.getUsername());
		name.setBackground(new Color(70, 130, 180));
		name.setBounds(400, 250, 700, 450);
		add(name);
		
		JLabel photo = new JLabel("");
		photo.setBounds(500, 150, 700, 300);
		add(photo);
		photo.setIcon(new ImageIcon(Profile.class.getResource("/image/photo.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(70, 130, 180));
		panel_1.setBounds(400, 0, 226, 413);
		add(panel_1);
		panel_1.setLayout(null);
	}
	
	public void setFileNameProfile(String username) {
		name.setText(username);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		topBar.draw(g);
	}
	
}