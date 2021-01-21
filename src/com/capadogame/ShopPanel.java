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
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShopPanel extends JPanel implements ActionListener {
	private int BIRDSHOPLIMIT = 5;
	private int areaWidth, areaHeight;
	private Trophy trophy;
	private Heart heart;
	private Bird bird;
	private Boolean[] birdPurchase;
	private JLabel jlBird;
	private ImageIcon iiSetting, iiStart, iiProfil, iiShop;
	private BufferedImage biTrophy, biHeart;
	private JButton jbSetting, jbStart, jbProfil,jbShop;
	private JPanel topBar, centerBox, bottomBar;
	private BoxLayout boxLayoutTB, boxLayoutCB, boxLayoutBB;
	private BorderLayout borderLayoutP;
	private CardLayout cardManagerBird;
	private JPanel birdTitle;
	private FlowLayout flowLayoutBT;
	private JPanel birdArea;
	private List<JPanel> birdShop;
	private String dataPath;
	private String fileNameBirdPurchase;
	
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
		
		birdArea = new JPanel();
		cardManagerBird = new CardLayout();
		birdArea.setLayout(cardManagerBird);
		
		birdPurchase = new Boolean[BIRDSHOPLIMIT];
		birdPurchase[0] = true;
		for (int i=1; i<birdPurchase.length; i++) {
			birdPurchase[i] = false;
		}
		birdShop = new ArrayList<JPanel>();
		for (int i=0; i<BIRDSHOPLIMIT; i++) {
			birdShop.add(new BirdShop(this, areaWidth, areaHeight, bird, birdPurchase, i));
		}
		for (int i=0; i<birdShop.size(); i++) {
			if (i != 0)
				((BirdShop) birdShop.get(i)).setPrevBird(birdShop.get(i-1));
			if (i != birdShop.size()-1)
				((BirdShop) birdShop.get(i)).setNextBird(birdShop.get(i+1));
		}
		
		for (int i=0; i<birdShop.size(); i++) {
			birdArea.add(birdShop.get(i), "birdShop" + i);
		}
		cardManagerBird.show(birdArea, "birdShop0");
		
		centerBox.add(Box.createRigidArea(new Dimension(0, 15)));
		centerBox.add(birdTitle);
		centerBox.add(Box.createRigidArea(new Dimension(0, 5)));
		centerBox.add(birdArea);
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
	
	public void setFileNameBirdPurchase(String username) {
		fileNameBirdPurchase = "dataBirdPurchase" + username;
		loadBirdPurchase();
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
			((BirdShop) birdShop.get(0)).setButton(bird, birdPurchase);
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
		for (int i=0; i<birdShop.size(); i++) {
			if (i!=0 && command.equalsIgnoreCase("BirdPrev" + i)) { // dari i ke i-1
				((BirdShop) ((BirdShop) birdShop.get(i)).getPrevBird()).setButton(bird, birdPurchase);
				cardManagerBird.show(birdArea, "birdShop" + (i-1));
			} else if (i!=birdShop.size()-1 && command.equalsIgnoreCase("BirdNext" + i)) { // dari i ke i+1
				((BirdShop) ((BirdShop) birdShop.get(i)).getNextBird()).setButton(bird, birdPurchase);
				cardManagerBird.show(birdArea, "birdShop" + (i+1));
			} 
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
