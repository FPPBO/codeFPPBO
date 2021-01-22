package com.capadogame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ShopPanel extends JPanel implements ActionListener {
	private int BIRDSHOPLIMIT = 5;
	private Bird bird;
	private Boolean[] birdPurchase;
	private JLabel jlBird;
	private TopBar topBar;
	private JPanel centerBox, birdTitle, birdArea;
	private List<JPanel> birdShop;
	private BottomBar bottomBar;
	private BoxLayout boxLayoutCB;
	private FlowLayout flowLayoutBT;
	private BorderLayout borderLayoutP;
	private CardLayout cardManagerBird;
	private String dataPath;
	private String fileNameBirdPurchase;
	
	public ShopPanel (CardLayoutWindow parent, int areaWidth, int areaHeight, 
			Trophy trophy, Heart heart, Bird bird) {
		this.bird = bird;
		try {
			dataPath = ShopPanel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {}
		
		/* Top Bar */
		topBar = new TopBar(parent, areaWidth, areaHeight, trophy, heart);
		
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
		bottomBar = new BottomBar(parent, areaWidth, areaHeight, 2);
		
		/* Panel */		
		borderLayoutP = new BorderLayout();
		setLayout(borderLayoutP);
		add(topBar, BorderLayout.NORTH);
		add(Box.createRigidArea(new Dimension (15, 0)), BorderLayout.LINE_START);
		add(centerBox, BorderLayout.CENTER);
		add(Box.createRigidArea(new Dimension (15, 0)), BorderLayout.LINE_END);
		add(bottomBar, BorderLayout.SOUTH);
		setBackground(new Color(185, 226, 245));
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
		boolean move = false;
		for (int i=0; !move && i<birdShop.size(); i++) {
			if (i!=0 && command.equalsIgnoreCase("BirdPrev" + i)) { // dari i ke i-1
				((BirdShop) ((BirdShop) birdShop.get(i)).getPrevBird()).setButton(bird, birdPurchase);
				cardManagerBird.show(birdArea, "birdShop" + (i-1));
				move = true;
			} else if (i!=birdShop.size()-1 && command.equalsIgnoreCase("BirdNext" + i)) { // dari i ke i+1
				((BirdShop) ((BirdShop) birdShop.get(i)).getNextBird()).setButton(bird, birdPurchase);
				cardManagerBird.show(birdArea, "birdShop" + (i+1));
				move = true;
			} 
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		topBar.draw(g);
	}
	
}
