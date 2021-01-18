package com.capadogame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShopPanel extends JPanel implements ActionListener {
	int areaWidth, areaHeight;
	Trophy trophy;
	Heart heart;
	Bird bird;
	Boolean[] birdPurchase;
	JLabel jlBird;
	ImageIcon iiSetting, iiStart, iiProfil, iiShop;
	BufferedImage biTrophy, biHeart;
	JButton jbSetting, jbStart, jbProfil,jbShop;
	JPanel topBar, centerBox, bottomBar;
	BoxLayout boxLayoutTB, boxLayoutCB, boxLayoutBB;
	BorderLayout borderLayoutP;
	CardLayout cardManagerBird;
	JPanel birdTitle;
	FlowLayout flowLayoutBT;
	JPanel birdShop, birdShop0, birdShop1, birdShop2, birdShop3, birdShop4;
	String dataPath;
	String fileNameBirdPurchase = "dataBirdPurchase";
	
	public ShopPanel (CardLayoutWindow parent, int areaWidth, int areaHeight, 
			Trophy trophy, Heart heart, Bird bird) {
		this.areaWidth = areaWidth;
		this.areaHeight = areaHeight;
		this.trophy = trophy;
		this.heart = heart;
		this.bird = bird;
		try {
			dataPath = ShopPanel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
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
		boxLayoutCB = new BoxLayout(centerBox, BoxLayout.Y_AXIS);
		centerBox.setLayout(boxLayoutCB);
		
		birdTitle = new JPanel();
		flowLayoutBT = new FlowLayout(FlowLayout.LEFT, areaHeight/10, 0);
		birdTitle.setLayout(flowLayoutBT);
		
		jlBird = new JLabel("Bird");
		jlBird.setFont(new Font("Arial", Font.BOLD, 20));
		
		birdTitle.add(jlBird);
		birdTitle.setBackground(new Color(185, 226, 245));
		
		birdShop = new JPanel();
		cardManagerBird = new CardLayout();
		birdShop.setLayout(cardManagerBird);
		
		birdPurchase = new Boolean[5];
		loadBirdPurchase();
		birdShop0 = new BirdShop(this, areaWidth, areaHeight, bird, birdPurchase, 0);
		birdShop1 = new BirdShop(this, areaWidth, areaHeight, bird, birdPurchase, 1);
		birdShop2 = new BirdShop(this, areaWidth, areaHeight, bird, birdPurchase, 2);
		birdShop3 = new BirdShop(this, areaWidth, areaHeight, bird, birdPurchase, 3);
		birdShop4 = new BirdShop(this, areaWidth, areaHeight, bird, birdPurchase, 4);
		((BirdShop) birdShop0).setNextBird(birdShop1);
		((BirdShop) birdShop1).setNextBird(birdShop2);
		((BirdShop) birdShop2).setNextBird(birdShop3);
		((BirdShop) birdShop3).setNextBird(birdShop4);
		((BirdShop) birdShop1).setPrevBird(birdShop0);
		((BirdShop) birdShop2).setPrevBird(birdShop1);
		((BirdShop) birdShop3).setPrevBird(birdShop2);
		((BirdShop) birdShop4).setPrevBird(birdShop3);
		
		birdShop.add(birdShop0, "birdShop0");
		birdShop.add(birdShop1, "birdShop1");
		birdShop.add(birdShop2, "birdShop2");
		birdShop.add(birdShop3, "birdShop3");
		birdShop.add(birdShop4, "birdShop4");
		cardManagerBird.show(birdShop, "birdShop0");
		
		centerBox.add(Box.createRigidArea(new Dimension(0, 15)));
		centerBox.add(birdTitle);
		centerBox.add(Box.createRigidArea(new Dimension(0, 5)));
		centerBox.add(birdShop);
		centerBox.add(Box.createRigidArea(new Dimension(0, 300)));
		centerBox.setBackground(new Color(185, 226, 245));
		
		/* Bottom Bar */
		bottomBar = new JPanel();
		boxLayoutBB = new BoxLayout(bottomBar, BoxLayout.X_AXIS);
		bottomBar.setLayout(boxLayoutBB);
		
		jbStart = new JButton();
		jbStart.setBorder(null);
		jbStart.setFocusPainted(false);
		jbStart.setActionCommand("Start");
		iiStart = new ImageIcon(new ImageIcon("res/non-start.png").getImage().
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
		iiShop = new ImageIcon(new ImageIcon("res/shop.png").getImage().
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
		add(Box.createRigidArea(new Dimension (15, 0)), BorderLayout.LINE_START);
		add(centerBox, BorderLayout.CENTER);
		add(Box.createRigidArea(new Dimension (15, 0)), BorderLayout.LINE_END);
		add(bottomBar, BorderLayout.SOUTH);
		setBackground(new Color(185, 226, 245));
		
		jbSetting.addActionListener(parent);
		jbStart.addActionListener(parent);
		jbProfil.addActionListener(parent);
		jbShop.addActionListener(parent);
	}
	
	public void loadBirdPurchase() {
		try {
    		File f = new File(dataPath, fileNameBirdPurchase);
    		if(!f.isFile()) {
    			createBirdPurchase();
    		}
    		BufferedReader reader = new BufferedReader(new InputStreamReader (new FileInputStream(f)));
    		for (int i=0; i<birdPurchase.length; i++) {
    			birdPurchase[i] = Boolean.parseBoolean(reader.readLine());
    		}
    		reader.close();
    	}
    	catch(Exception e) { }
	}
	
	public void createBirdPurchase() {
		try {
    		File file = new File(dataPath, fileNameBirdPurchase);
    		
    		FileWriter output = new FileWriter(file);
    		BufferedWriter writer = new BufferedWriter(output);
    		writer.write(String.format("true\n"));
    		for (int i=1; i<birdPurchase.length; i++) {
    			writer.write(String.format("false\n"));
    		}
    		
    		writer.close();
    	}
    	catch(Exception e) { }
	}
	
	public void updateBirdPurchase() {
		FileWriter output = null;
    	try {
    		File f = new File(dataPath, fileNameBirdPurchase);
    		output = new FileWriter(f);
    		BufferedWriter writer = new BufferedWriter(output); 
    		
    		for (int i=0; i<birdPurchase.length; i++) {
    			writer.write(String.format("%s\n", birdPurchase[i]));
    		}
    		
    		writer.close();
    	}
    	catch(Exception e) { }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equalsIgnoreCase("BirdPrev1")) { // ke 0 dari 1
			((BirdShop) ((BirdShop) birdShop1).getPrevBird()).setButton(bird, birdPurchase);
			cardManagerBird.show(birdShop, "birdShop0");
        } else if (command.equalsIgnoreCase("BirdNext0")) { // ke 1 dari 0
        	((BirdShop) ((BirdShop) birdShop0).getNextBird()).setButton(bird, birdPurchase);
        	cardManagerBird.show(birdShop, "birdShop1");
        } else if (command.equalsIgnoreCase("BirdPrev2")) { // ke 1 dari 2
        	((BirdShop) ((BirdShop) birdShop2).getPrevBird()).setButton(bird, birdPurchase);
        	cardManagerBird.show(birdShop, "birdShop1");
        } else if (command.equalsIgnoreCase("BirdNext1")) { // ke 2 dari 1
            ((BirdShop) ((BirdShop) birdShop1).getNextBird()).setButton(bird, birdPurchase);
            cardManagerBird.show(birdShop, "birdShop2");
        } else if (command.equalsIgnoreCase("BirdPrev3")) { // ke 2 dari 3
            ((BirdShop) ((BirdShop) birdShop3).getPrevBird()).setButton(bird, birdPurchase);
            cardManagerBird.show(birdShop, "birdShop2");
        } else if (command.equalsIgnoreCase("BirdNext2")) { // ke 3 dari 2
            ((BirdShop) ((BirdShop) birdShop2).getNextBird()).setButton(bird, birdPurchase);
            cardManagerBird.show(birdShop, "birdShop3");
        } else if (command.equalsIgnoreCase("BirdPrev4")) { // ke 3 dari 4
            ((BirdShop) ((BirdShop) birdShop4).getPrevBird()).setButton(bird, birdPurchase);
            cardManagerBird.show(birdShop, "birdShop3");
        } else if (command.equalsIgnoreCase("BirdNext3")) { // ke 4 dari 3
            ((BirdShop) ((BirdShop) birdShop3).getNextBird()).setButton(bird, birdPurchase);
            cardManagerBird.show(birdShop, "birdShop4");
        } 
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
