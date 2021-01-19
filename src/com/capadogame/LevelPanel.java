package com.capadogame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class LevelPanel extends JPanel {
	private int areaWidth, areaHeight;
	private Trophy trophy;
	private Heart heart;
	private ImageIcon iiSetting, iiStart, iiProfil, iiShop;
	private BufferedImage biTrophy, biHeart;
	private JButton jbSetting, jbStart, jbProfil, jbShop;
	private List<JButton> jbLevel;
	private JPanel topBar, centerBox, bottomBar;
	private BoxLayout boxLayoutTB, boxLayoutCB, boxLayoutBB;
	private BorderLayout borderLayoutP;
	
	public LevelPanel (CardLayoutWindow parent, int areaWidth, int areaHeight,
			Trophy trophy, Heart heart) {
		this.areaWidth = areaWidth;
		this.areaHeight = areaHeight;
		this.trophy = trophy;
		this.heart = heart;
		
		/* Top Bar */
		topBar = new JPanel();
		boxLayoutTB = new BoxLayout(topBar, BoxLayout.X_AXIS);
		topBar.setLayout(boxLayoutTB);
		
		jbSetting = new JButton();
		jbSetting.setBorder(null);
		jbSetting.setFocusPainted(false);
		jbSetting.setActionCommand("Setting");
		iiSetting = new ImageIcon(new ImageIcon("res/setting.png").getImage().
				getScaledInstance(areaHeight/12, areaHeight/12, Image.SCALE_SMOOTH));
		jbSetting.setIcon(iiSetting);
		
		try {
			biTrophy = ImageIO.read(new File("C:/Users/Hp/git/CapadoGame/res/trophy.png"));
			biHeart = ImageIO.read(new File("C:/Users/Hp/git/CapadoGame/res/heart.png"));
		} catch (IOException e) {}
		
		topBar.add(Box.createRigidArea(new Dimension(((areaWidth-3*(areaHeight/9))/4 - areaHeight/12)/2, 0)));
		topBar.add(jbSetting);
		topBar.setOpaque(false);
		
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
		bottomBar = new JPanel();
		boxLayoutBB = new BoxLayout(bottomBar, BoxLayout.X_AXIS);
		bottomBar.setLayout(boxLayoutBB);
		
		jbStart = new JButton();
		jbStart.setBorder(null);
		jbStart.setFocusPainted(false);
		jbStart.setActionCommand("Start");
		iiStart = new ImageIcon(new ImageIcon("res/start.png").getImage().
				getScaledInstance(areaHeight/9, areaHeight/9, Image.SCALE_SMOOTH));
		jbStart.setIcon(iiStart);
		
		jbProfil = new JButton();		
		jbProfil.setBorder(null);
		jbProfil.setFocusPainted(false);
		jbProfil.setActionCommand("Profil");
		iiProfil = new ImageIcon(new ImageIcon("res/non-profil.png").getImage().
				getScaledInstance(areaHeight/9, areaHeight/9, Image.SCALE_SMOOTH));
		jbProfil.setIcon(iiProfil);
		
		jbShop = new JButton();
		jbShop.setBorder(null);
		jbShop.setFocusPainted(false);
		jbShop.setActionCommand("Shop");
		iiShop = new ImageIcon(new ImageIcon("res/non-shop.png").getImage().
				getScaledInstance(areaHeight/9, areaHeight/9, Image.SCALE_SMOOTH));
		jbShop.setIcon(iiShop);
		
		bottomBar.add(Box.createRigidArea(new Dimension ((areaWidth-3*(areaHeight/9))/4, 0)));
		bottomBar.add(jbStart);
		bottomBar.add(Box.createRigidArea(new Dimension ((areaWidth-3*(areaHeight/9))/4, 0)));
		bottomBar.add(jbProfil);
		bottomBar.add(Box.createRigidArea(new Dimension ((areaWidth-3*(areaHeight/9))/4, 0)));
		bottomBar.add(jbShop);
		bottomBar.setBackground(Color.WHITE);
		
		/* Panel */		
		borderLayoutP = new BorderLayout();
		setLayout(borderLayoutP);
		add(topBar, BorderLayout.NORTH);
		add(centerBox, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);
		
		jbSetting.addActionListener(parent);
		for (JButton jbLevel: jbLevel) {
			jbLevel.addActionListener(parent);
		}
		jbStart.addActionListener(parent);
		jbProfil.addActionListener(parent);
		jbShop.addActionListener(parent);
	}

	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
		g.fillRect(0, 0, areaWidth, areaHeight/12);
		
        drawTrophy(g);
        drawHeart(g);
    }
	
	public void drawTrophy(Graphics g) {
		g.setColor(new Color(245, 222, 89));
        g.setFont(new Font("Monospaced", Font.BOLD, 38));
        FontMetrics metrics = getFontMetrics(g.getFont());
        
        g.drawImage(resize(biTrophy, areaHeight/12, areaHeight/12), 
        		(areaWidth - areaHeight/12 - 10 - metrics.stringWidth(String.valueOf(trophy.getTrophy())))/2, 
        		0, this);
        
        g.drawString(String.valueOf(trophy.getTrophy()),
        		(areaWidth - areaHeight/12 - 10 - metrics.stringWidth(String.valueOf(trophy.getTrophy())))/2 + areaHeight/12 + 10, 
        		(areaHeight/12+28)/2);
	}
	
	public void drawHeart(Graphics g) {
        g.setFont(new Font("Monospaced", Font.BOLD, 38));
        FontMetrics metrics = getFontMetrics(g.getFont());
        
        g.drawImage(resize(biHeart, areaHeight/12, areaHeight/12), 
        		3*((areaWidth-3*(areaHeight/9))/4 + areaHeight/9) + 
        		((areaWidth-3*(areaHeight/9))/4 - areaHeight/12 - 10 - metrics.stringWidth(heart.getDisplayTimer()))/2, 
        		0, this);
        
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(heart.getHeart()),
        		3*((areaWidth-3*(areaHeight/9))/4 + areaHeight/9) + 
        		((areaWidth-3*(areaHeight/9))/4 - areaHeight/12 - 10 - metrics.stringWidth(heart.getDisplayTimer()))/2 + 
        		(areaHeight/12 - metrics.stringWidth(String.valueOf(heart.getHeart())))/2, 
        		(areaHeight/12+28)/2);
        
        g.setColor(new Color(255, 87, 87));
        g.drawString(heart.getDisplayTimer(),
        		3*((areaWidth-3*(areaHeight/9))/4 + areaHeight/9) + 
        		((areaWidth-3*(areaHeight/9))/4 - areaHeight/12 - 10 - metrics.stringWidth(heart.getDisplayTimer()))/2 +
        		areaHeight/12 + 10, (areaHeight/12+28)/2);
	}
	
	public BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	} 
	
}
