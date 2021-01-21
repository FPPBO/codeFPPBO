package com.capadogame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EasyPanel extends JPanel {
	private final int LEVEL = 3;
	private int areaWidth, areaHeight;
	private Trophy trophy;
	private Heart heart;
	private JLabel jlSelectLevel;
	private int[] targetEasy;
	private Boolean[] clearEasy;
	private ImageIcon iiSetting, iiBack, iiStart, iiProfil, iiShop;
	private List<ImageIcon> iiEasy;
	private BufferedImage biTrophy, biHeart;
	private JButton jbSetting, jbBack, jbStart, jbProfil, jbShop;
	private List<JButton> jbEasy;
	private JPanel topBar, centerBox, titleBox, selectBox, bottomBar;
	private BoxLayout boxLayoutTB, boxLayoutBB;
	private FlowLayout flowLayoutSB;
	private BorderLayout borderLayoutP, borderLayoutCB, borderLayoutTLB;
	private String dataPath;
	private String fileNameClearEasy;
	
	public EasyPanel (CardLayoutWindow parent, int areaWidth, int areaHeight,
			Trophy trophy, Heart heart) {
		this.areaWidth = areaWidth;
		this.areaHeight = areaHeight;
		this.trophy = trophy;
		this.heart = heart;
		try {
			dataPath = EasyPanel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {}
		
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
		borderLayoutCB = new BorderLayout();
		centerBox.setLayout(borderLayoutCB);
		
		titleBox = new JPanel();
		borderLayoutTLB = new BorderLayout();
		titleBox.setLayout(borderLayoutTLB);
		
		jbBack = new JButton();
		iiBack = new ImageIcon(new ImageIcon("res/back.png").getImage().
				getScaledInstance(areaHeight/13, areaHeight/13, Image.SCALE_SMOOTH));
		jbBack.setIcon(iiBack);
		jbBack.setBorder(null);
		jbBack.setFocusPainted(false);
		jbBack.setActionCommand("BackLevel");
		
		jlSelectLevel = new JLabel("Select Level");
		jlSelectLevel.setFont(new Font("Monospaced", Font.BOLD, 24));
		jlSelectLevel.setForeground(Color.WHITE);
		jlSelectLevel.setHorizontalAlignment(JLabel.CENTER);
		
		titleBox.add(jbBack, BorderLayout.LINE_START);
		titleBox.add(jlSelectLevel, BorderLayout.CENTER);
		titleBox.setBackground(new Color(130, 212, 250));
		
		selectBox = new JPanel();
		flowLayoutSB = new FlowLayout(FlowLayout.LEFT, 10, 10);
		selectBox.setLayout(flowLayoutSB);
		
		targetEasy = new int[LEVEL];
		targetEasy[0] = 3;
		targetEasy[1] = 5;
		targetEasy[2] = 7;
		
		clearEasy = new Boolean[LEVEL];
		for (int i=0; i<clearEasy.length; i++) {
			clearEasy[i] = false;
		}
		
		jbEasy = new ArrayList<JButton>();
		iiEasy = new ArrayList<ImageIcon>();
		for (int i=0; i<LEVEL; i++) {
			jbEasy.add(new JButton());
			jbEasy.get(i).setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createRaisedSoftBevelBorder(), 
					BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Level " + (i+1)), 
						BorderFactory.createLoweredBevelBorder())));
			jbEasy.get(i).setFocusPainted(false);
			jbEasy.get(i).setBackground(new Color(250, 220, 90));
			jbEasy.get(i).setActionCommand("Easy" + (i+1));
			
			iiEasy.add(new ImageIcon(new ImageIcon("res/feasy-" + (i+1) + ".png").getImage().
					getScaledInstance(areaHeight/8, areaHeight/8, Image.SCALE_SMOOTH)));
			jbEasy.get(i).setIcon(iiEasy.get(i));
		}
				
		for (JButton jbEasy: jbEasy) {
			selectBox.add(jbEasy);
		}
		selectBox.setBackground(new Color(185, 226, 245));
		
		centerBox.add(titleBox, BorderLayout.PAGE_START);
		centerBox.add(Box.createRigidArea(new Dimension (areaHeight/13, 0)), BorderLayout.LINE_START);
		centerBox.add(selectBox, BorderLayout.CENTER);
		centerBox.add(Box.createRigidArea(new Dimension (areaHeight/13, 0)), BorderLayout.LINE_END);
		centerBox.setBackground(new Color(185, 226, 245));
		
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
		
		jbBack.addActionListener(parent);
		for (JButton jbEasy: jbEasy) {
			jbEasy.addActionListener(parent);
		}
		jbStart.addActionListener(parent);
		jbProfil.addActionListener(parent);
		jbShop.addActionListener(parent);
	}
	
	public int getTargetEasy(int index) {
		return targetEasy[index];
	}
	
	public Boolean getClearEasy(int index) {
		return clearEasy[index];
	}
	
	public void setClearEasy(int index) {
		clearEasy[index] = true;
		setClearButton(index);
	}
	
	public void setClearButton(int index) {
		iiEasy.add(index, new ImageIcon(new ImageIcon("res/ceasy-" + (index+1) + ".png").getImage().
				getScaledInstance(areaHeight/8, areaHeight/8, Image.SCALE_SMOOTH)));
		jbEasy.get(index).setIcon(iiEasy.get(index));
	}
	
	public void setFileNameClearEasy(String username) {
		fileNameClearEasy = "dataClearEasy" + username;
		loadClearEasy();
	}
	
	public void loadClearEasy() {
		try {
			File f = new File(dataPath, fileNameClearEasy);
			if(!f.isFile()) {
				createClearEasy();
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader (new FileInputStream(f)));
			for (int i=0; i<clearEasy.length; i++) {
				if (Boolean.parseBoolean(reader.readLine()))
					setClearEasy(i);
			}
			reader.close();
		}
		catch(Exception e) { }
	}
	
	public void createClearEasy() {
		try {
			File file = new File(dataPath, fileNameClearEasy);

			FileWriter output = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(output);
			for (int i=0; i<clearEasy.length; i++) {
				writer.write(String.format("false\n"));
			}

			writer.close();
		}
		catch(Exception e) { }
	}
	
	public void updateClearEasy() {
		FileWriter output = null;
		try {
			File f = new File(dataPath, fileNameClearEasy);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output); 

			for (int i=0; i<clearEasy.length; i++) {
				writer.write(String.format("%s\n", clearEasy[i]));
			}

			writer.close();
		}
		catch(Exception e) { }
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
