package com.capadogame;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

public class LevelPanel extends JPanel {
	private int areaWidth, areaHeight;
	private JLabel jlSelectLevel;
	private int type, maxLevel;
	private String levelName;
	private int[] targetLevel;
	private Boolean[] clearLevel;
	private ImageIcon iiBack;
	private List<ImageIcon> iiLevel;
	private JButton jbBack;
	private List<JButton> jbLevel;
	private TopBar topBar;
	private JPanel centerBox, titleBox, selectBox;
	private BottomBar bottomBar;
	private FlowLayout flowLayoutSB;
	private BorderLayout borderLayoutP, borderLayoutCB, borderLayoutTLB;
	private String dataPath;
	private String fileNameClearLevel;
	
	public LevelPanel (CardLayoutWindow parent, int areaWidth, int areaHeight,
			Trophy trophy, Heart heart, int type, int maxLevel) {
		this.areaWidth = areaWidth;
		this.areaHeight = areaHeight;
		this.type = type;
		this.maxLevel = maxLevel;
		if (type == 0) {
			levelName = "Easy";
		} else if (type == 1) {
			levelName = "Medium";
		} else if (type == 2) {
			levelName = "Hard";
		}
		try {
			dataPath = LevelPanel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {}
		
		/* Top Bar */
		topBar = new TopBar(parent, areaWidth, areaHeight, trophy, heart);
		
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
		
		setTargetLevel();
		clearLevel = new Boolean[maxLevel];
		for (int i=0; i<clearLevel.length; i++) {
			clearLevel[i] = false;
		}
		
		jbLevel = new ArrayList<JButton>();
		iiLevel = new ArrayList<ImageIcon>();
		for (int i=0; i<maxLevel; i++) {
			jbLevel.add(new JButton());
			jbLevel.get(i).setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createRaisedSoftBevelBorder(), 
					BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Level " + (i+1)), 
						BorderFactory.createLoweredBevelBorder())));
			jbLevel.get(i).setFocusPainted(false);
			jbLevel.get(i).setBackground(new Color(250, 220, 90));
			jbLevel.get(i).setActionCommand(levelName + (i+1));
			
			iiLevel.add(new ImageIcon(new ImageIcon("res/f" + levelName.toLowerCase() + "-" + (i+1) + ".png").
					getImage().getScaledInstance(areaHeight/8, areaHeight/8, Image.SCALE_SMOOTH)));
			jbLevel.get(i).setIcon(iiLevel.get(i));
		}
				
		for (JButton jbLevel: jbLevel) {
			selectBox.add(jbLevel);
		}
		selectBox.setBackground(new Color(185, 226, 245));
		
		centerBox.add(titleBox, BorderLayout.PAGE_START);
		centerBox.add(Box.createRigidArea(new Dimension (areaHeight/13, 0)), BorderLayout.LINE_START);
		centerBox.add(selectBox, BorderLayout.CENTER);
		centerBox.add(Box.createRigidArea(new Dimension (areaHeight/13, 0)), BorderLayout.LINE_END);
		centerBox.setBackground(new Color(185, 226, 245));
		
		/* Bottom Bar */
		bottomBar = new BottomBar(parent, areaWidth, areaHeight, 0);
		
		/* Panel */		
		borderLayoutP = new BorderLayout();
		setLayout(borderLayoutP);
		add(topBar, BorderLayout.NORTH);
		add(centerBox, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);
		
		jbBack.addActionListener(parent);
		for (JButton jbLevel: jbLevel) {
			jbLevel.addActionListener(parent);
		}
	}
	
	public void setTargetLevel() {
		if (type == 0) {
			targetLevel = new int[maxLevel];
			targetLevel[0] = 3;
			targetLevel[1] = 5;
			targetLevel[2] = 7;
		} else if (type == 1) {
			targetLevel = new int[maxLevel];
			targetLevel[0] = 5;
			targetLevel[1] = 10;
			targetLevel[2] = 15;
		} else if (type == 2) {
			targetLevel = new int[maxLevel];
			targetLevel[0] = 12;
			targetLevel[1] = 16;
			targetLevel[2] = 22;
		}
	}
	
	public int getMaxLevel() {
		return maxLevel;
	}
	
	public int getTargetLevel(int index) {
		return targetLevel[index];
	}
	
	public Boolean getClearLevel(int index) {
		return clearLevel[index];
	}
	
	public void setClearLevel(int index) {
		clearLevel[index] = true;
		setClearButton(index);
	}
	
	public void setClearButton(int index) {
		iiLevel.add(index, new ImageIcon(new ImageIcon("res/c" + levelName.toLowerCase() + "-" + (index+1) + ".png").
				getImage().getScaledInstance(areaHeight/8, areaHeight/8, Image.SCALE_SMOOTH)));
		jbLevel.get(index).setIcon(iiLevel.get(index));
	}
	
	public void setFileNameClearLevel(String username) {
		fileNameClearLevel = "dataClear" + levelName + username;
		loadClearLevel();
	}
	
	public void loadClearLevel() {
		try {
			File f = new File(dataPath, fileNameClearLevel);
			if(!f.isFile()) {
				createClearLevel();
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader (new FileInputStream(f)));
			for (int i=0; i<clearLevel.length; i++) {
				if (Boolean.parseBoolean(reader.readLine()))
					setClearLevel(i);
			}
			reader.close();
		}
		catch(Exception e) { }
	}
	
	public void createClearLevel() {
		try {
			File file = new File(dataPath, fileNameClearLevel);

			FileWriter output = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(output);
			for (int i=0; i<clearLevel.length; i++) {
				writer.write(String.format("false\n"));
			}

			writer.close();
		}
		catch(Exception e) { }
	}
	
	public void updateClearLevel() {
		FileWriter output = null;
		try {
			File f = new File(dataPath, fileNameClearLevel);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output); 

			for (int i=0; i<clearLevel.length; i++) {
				writer.write(String.format("%s\n", clearLevel[i]));
			}

			writer.close();
		}
		catch(Exception e) { }
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		topBar.draw(g);
	}
	
}
