package com.capadogame;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class TopBar extends JPanel {
	private int areaWidth, areaHeight;
	private Trophy trophy;
	private Heart heart;
	private ImageIcon iiSetting;
	private BufferedImage biTrophy, biHeart;
	private JButton jbSetting;
	private BoxLayout boxLayoutTB;
	
	public TopBar(CardLayoutWindow parent, int areaWidth, int areaHeight, 
			Trophy trophy, Heart heart) {
		this.areaWidth = areaWidth;
		this.areaHeight = areaHeight;
		this.trophy = trophy;
		this.heart = heart;
		
		boxLayoutTB = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(boxLayoutTB);
		
		/* Setting */
		jbSetting = new JButton();
		jbSetting.setBorder(null);
		jbSetting.setFocusPainted(false);
		jbSetting.setActionCommand("Setting");
		iiSetting = new ImageIcon(new ImageIcon("res/setting.png").getImage().
				getScaledInstance(areaHeight/12, areaHeight/12, Image.SCALE_SMOOTH));
		jbSetting.setIcon(iiSetting);
		
		try {
			/* Trophy */
			biTrophy = ImageIO.read(new File(System.getProperty("user.dir") + "/res/trophy.png"));
			/* Heart */
			biHeart = ImageIO.read(new File(System.getProperty("user.dir") + "/res/heart.png"));
		} catch (IOException e) {}
		
		add(Box.createRigidArea(new Dimension(((areaWidth-3*(areaHeight/9))/4 - areaHeight/12)/2, 0)));
		add(jbSetting);
		setOpaque(false);
		
		jbSetting.addActionListener(parent);
	}
	
	public void draw(Graphics g){
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
