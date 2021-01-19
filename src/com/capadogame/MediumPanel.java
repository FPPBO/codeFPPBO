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

public class MediumPanel extends JPanel {
	private int areaWidth, areaHeight;
	private Trophy trophy;
	private Heart heart;
	private JLabel jlSelectLevel;
	private int[] targetMedium;
	private Boolean[] clearMedium;
	private ImageIcon iiSetting, iiBack, iiStart, iiPprofil, iiShop;
	private List<ImageIcon> iiMedium;
	private BufferedImage biTrophy, biHeart;
	private JButton jbSetting, jbBack, jbStart, jbPprofil, jbShop;
	private List<JButton> jbMedium;
	private JPanel topBar, centerBox, titleBox, selectBox, bottomBar;
	private BoxLayout boxLayoutTB, boxLayoutBB;
	private FlowLayout flowLayoutSB;
	private BorderLayout borderLayoutP, borderLayoutCB, borderLayoutTLB;
	private String dataPath;
	private String fileNameClearMedium = "dataClearMedium";
	
	public MediumPanel (CardLayoutWindow parent, int areaWidth, int areaHeight,
			Trophy trophy, Heart heart) {
		this.areaWidth = areaWidth;
		this.areaHeight = areaHeight;
		this.trophy = trophy;
		this.heart = heart;
		try {
			dataPath = MediumPanel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
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
		
		targetMedium = new int[3];
		targetMedium[0] = 5;
		targetMedium[1] = 10;
		targetMedium[2] = 15;
		clearMedium = new Boolean[3];
		loadClearMedium();
		jbMedium = new ArrayList<JButton>();
		iiMedium = new ArrayList<ImageIcon>();
		for (int i=0; i<3; i++) {
			jbMedium.add(new JButton());
			jbMedium.get(i).setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createRaisedSoftBevelBorder(), 
					BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Level " + (i+1)), 
						BorderFactory.createLoweredBevelBorder())));
			jbMedium.get(i).setFocusPainted(false);
			jbMedium.get(i).setBackground(new Color(250, 220, 90));
			jbMedium.get(i).setActionCommand("Medium" + (i+1));
			
			if (clearMedium[i] == true) {
				setClearButton(i);
			} else {
				iiMedium.add(new ImageIcon(new ImageIcon("res/fmedium-" + (i+1) + ".png").getImage().
						getScaledInstance(areaHeight/8, areaHeight/8, Image.SCALE_SMOOTH)));
				jbMedium.get(i).setIcon(iiMedium.get(i));
			}
		}
				
		for (JButton jbMedium: jbMedium) {
			selectBox.add(jbMedium);
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
		
		jbPprofil = new JButton();		
		jbPprofil.setBorder(null);
		jbPprofil.setFocusPainted(false);
		jbPprofil.setActionCommand("Pprofil");
		iiPprofil = new ImageIcon(new ImageIcon("res/non-profil.png").getImage().
				getScaledInstance(areaHeight/9, areaHeight/9, Image.SCALE_SMOOTH));
		jbPprofil.setIcon(iiPprofil);
		
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
		bottomBar.add(jbPprofil);
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
		jbBack.addActionListener(parent);
		for (JButton jbMedium: jbMedium) {
			jbMedium.addActionListener(parent);
		}
		jbStart.addActionListener(parent);
		jbPprofil.addActionListener(parent);
		jbShop.addActionListener(parent);
	}
	
	public int getTargetMedium(int index) {
		return targetMedium[index];
	}
	
	public Boolean getClearMedium(int index) {
		return clearMedium[index];
	}
	
	public void setClearMedium(int index, Boolean clear) {
		clearMedium[index] = clear;
	}
	
	public void setClearButton(int index) {
		iiMedium.add(index, new ImageIcon(new ImageIcon("res/cmedium-" + (index+1) + ".png").getImage().
				getScaledInstance(areaHeight/8, areaHeight/8, Image.SCALE_SMOOTH)));
		jbMedium.get(index).setIcon(iiMedium.get(index));
	}
	
	public void loadClearMedium() {
		try {
    		File f = new File(dataPath, fileNameClearMedium);
    		if(!f.isFile()) {
    			createClearMedium();
    		}
    		BufferedReader reader = new BufferedReader(new InputStreamReader (new FileInputStream(f)));
    		for (int i=0; i<clearMedium.length; i++) {
    			clearMedium[i] = Boolean.parseBoolean(reader.readLine());
    		}
    		reader.close();
    	}
    	catch(Exception e) { }
	}
	
	public void createClearMedium() {
		try {
    		File file = new File(dataPath, fileNameClearMedium);
    		
    		FileWriter output = new FileWriter(file);
    		BufferedWriter writer = new BufferedWriter(output);
    		for (int i=0; i<clearMedium.length; i++) {
    			writer.write(String.format("false\n"));
    		}
    		
    		writer.close();
    	}
    	catch(Exception e) { }
	}
	
	public void updateClearMedium() {
		FileWriter output = null;
    	try {
    		File f = new File(dataPath, fileNameClearMedium);
    		output = new FileWriter(f);
    		BufferedWriter writer = new BufferedWriter(output); 
    		
    		for (int i=0; i<clearMedium.length; i++) {
    			writer.write(String.format("%s\n", clearMedium[i]));
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
