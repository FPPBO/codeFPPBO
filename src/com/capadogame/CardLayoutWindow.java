package com.capadogame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardLayoutWindow extends Frame implements WindowListener, ActionListener{
	private CardLayout cardManager;
	private SettingDialog settingDialog;
	private StartPanel startPanel;
	private ProfilePanel profilePanel;
	private ShopPanel shopPanel;
	private PackPanel packPanel;
	private LevelPanel easyPanel;
	private FlappyBirdEasy flappyBirdEasy;
	private LevelPanel mediumPanel;
	private FlappyBirdMedium flappyBirdMedium;
	private LevelPanel hardPanel;
	private FlappyBirdHard flappyBirdHard;
	private BGM bgm;
	private Bird bird;
	private Trophy trophy;
	private Heart heart;
	private Profile profile;
	private Form loginForm;
	private boolean changeProfile;
	private int areaWidth, areaHeight;
			
	
	public CardLayoutWindow (String frameTitle) {
		super(frameTitle);
		
		areaWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		areaHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		setSize(areaWidth, areaHeight);
		setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-areaWidth)/2, 
				(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-areaHeight)/2);
		setResizable(false);
		
		addWindowListener(this);
		
		bgm = new BGM();
		bird = new Bird();
		trophy = new Trophy();
		heart = new Heart();
		profile = new Profile();
		loginForm = new Form(profile);
		changeProfile = false;
		
		settingDialog = new SettingDialog(this, bgm);
		startPanel = new StartPanel(this, areaWidth, areaHeight, trophy, heart);
		profilePanel = new ProfilePanel (this, areaWidth, areaHeight, trophy, heart, profile);
		shopPanel = new ShopPanel (this, areaWidth, areaHeight, trophy, heart, bird);
		packPanel = new PackPanel (this, areaWidth, areaHeight, trophy, heart);
		easyPanel = new LevelPanel (this, areaWidth, areaHeight, trophy, heart, 0, 3);
		mediumPanel = new LevelPanel (this, areaWidth, areaHeight, trophy, heart, 1, 3);
		hardPanel = new LevelPanel (this, areaWidth, areaHeight, trophy, heart, 2, 3);
		
		cardManager = new CardLayout();
		setLayout(cardManager);
		add(startPanel, "startPanel");
		add(profilePanel, "profilePanel");
		add(shopPanel, "shopPanel");
		add(packPanel, "packPanel");
		add(easyPanel, "easyPanel");
		add(mediumPanel, "mediumPanel");
		add(hardPanel, "hardPanel");
		
		startThread();
	}
		
	public void windowActivated(WindowEvent e) {
		// do nothing
	}
	public void windowClosed(WindowEvent e) {
		heart.stopHeartTimer();
		heart.updateTime();
		heart.updateHeart(heart.getHeart());
		trophy.updateTrophy(trophy.getTrophy());
		bird.updateBird(bird.getType());
		shopPanel.updateBirdPurchase();
		easyPanel.updateClearLevel();;
		mediumPanel.updateClearLevel();
		hardPanel.updateClearLevel();
		bgm.close();
	}
	public void windowClosing(WindowEvent e) {
		bgm.stop();
		this.dispose();
		loginForm.dispose();
	}
	public void windowDeactivated(WindowEvent e) {
		// do nothing
	}
	public void windowDeiconified(WindowEvent e) {
		// do nothing
	}
	public void windowIconified(WindowEvent e) {
		// do nothing
	}
	public void windowOpened(WindowEvent e) {
		cardManager.show(this, "startPanel");
		bgm.play();
		loginForm.setVisible(true);
	}
	
	public void startThread() {
		Thread windowThread = new Thread() {
			public void run() {
				while (true) {
					if (!changeProfile && profile.getUsername() != "") {
						heart.setFileNameHeartTime(profile.getUsername());
						trophy.setFileNameTrophy(profile.getUsername());
						bird.setFileNameBird(profile.getUsername());
						easyPanel.setFileNameClearLevel(profile.getUsername());
						mediumPanel.setFileNameClearLevel(profile.getUsername());
						hardPanel.setFileNameClearLevel(profile.getUsername());
						shopPanel.setFileNameBirdPurchase(profile.getUsername());
						profilePanel.setFilenameProfile(profile.getUsername());
						changeProfile = true;
					}
					startPanel.repaint();
					profilePanel.repaint();
					shopPanel.repaint();
					packPanel.repaint();
					easyPanel.repaint();
					mediumPanel.repaint();
					hardPanel.repaint();
					try {
						Thread.sleep(1000/5);
					} catch (InterruptedException ex) {}
				}
			}
		};
		windowThread.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equalsIgnoreCase("Setting")) {
			settingDialog.setVisible(true);
		} else if (command.equalsIgnoreCase("Start")) {
			cardManager.show(this, "startPanel");
		} else if (command.equalsIgnoreCase("Profile")) {
			cardManager.show(this, "profilePanel");
		} else if (command.equalsIgnoreCase("Shop")) {
			cardManager.show(this, "shopPanel");
		} else if (command.equalsIgnoreCase("Play") || command.equalsIgnoreCase("BackLevel")) {
			cardManager.show(this, "packPanel");
		} else if (command.equalsIgnoreCase("Easy")) {
			cardManager.show(this, "easyPanel");
		} else if (command.equalsIgnoreCase("ReturnEasy")) {
			if (flappyBirdEasy.getTarget() == flappyBirdEasy.getScore()) {
				for (int i=0; i<easyPanel.getMaxLevel(); i++) {
					if (flappyBirdEasy.getTarget() == easyPanel.getTargetLevel(i) && !easyPanel.getClearLevel(i)) {
						easyPanel.setClearLevel(i);
						trophy.setTrophy(trophy.getTrophy()+1);
						break;
					}
				}
			} else if (flappyBirdEasy.getGameOverStatus() == true){
				heart.setHeart(heart.getHeart()-1);
			}
			cardManager.show(this, "easyPanel");
		} else if (command.equalsIgnoreCase("Medium")) {
			cardManager.show(this, "mediumPanel");
		} else if (command.equalsIgnoreCase("ReturnMedium")) {
			if (flappyBirdMedium.getTarget() == flappyBirdMedium.getScore()) {
				for (int i=0; i<mediumPanel.getMaxLevel(); i++) {
					if (flappyBirdMedium.getTarget() == mediumPanel.getTargetLevel(i) && !mediumPanel.getClearLevel(i)) {
						mediumPanel.setClearLevel(i);
						mediumPanel.setClearButton(i);
						trophy.setTrophy(trophy.getTrophy()+1);
						break;
					}
				}
			} else if (flappyBirdMedium.getGameOverStatus() == true){
				heart.setHeart(heart.getHeart()-1);
			}
			cardManager.show(this, "mediumPanel");
		} else if (command.equalsIgnoreCase("Hard")) {
			cardManager.show(this, "hardPanel");
		} else if (command.equalsIgnoreCase("ReturnHard")) {
			if (flappyBirdHard.getTarget() == flappyBirdHard.getScore()) {
				for (int i=0; i<hardPanel.getMaxLevel(); i++) {
					if (flappyBirdHard.getTarget() == hardPanel.getTargetLevel(i) && !hardPanel.getClearLevel(i)) {
						hardPanel.setClearLevel(i);
						hardPanel.setClearButton(i);
						trophy.setTrophy(trophy.getTrophy()+1);
						break;
					}
				}
			} else if (flappyBirdHard.getGameOverStatus() == true){
				heart.setHeart(heart.getHeart()-1);
			}
			cardManager.show(this, "hardPanel");
		} else {
			if (heart.getHeart() == 0) {
				JOptionPane.showMessageDialog(this, String.format("%s%n%s", "You can't start the game because your heart has run out.",
						"Just wait a moment."), "Message", JOptionPane.INFORMATION_MESSAGE);
			} else {
				boolean found = false;
				for (int i=0; !found && i<easyPanel.getMaxLevel(); i++) {
					if (command.equalsIgnoreCase("Easy" + (i+1))) {
						flappyBirdEasy = new FlappyBirdEasy(this, areaWidth, areaHeight, bird, easyPanel.getTargetLevel(i));
						add(flappyBirdEasy, "flappyBirdEasy");
						cardManager.show(this, "flappyBirdEasy");
						found = true;
					}
				}
				for (int i=0; !found && i<mediumPanel.getMaxLevel(); i++) {
					if (command.equalsIgnoreCase("Medium" + (i+1))) {
						flappyBirdMedium = new FlappyBirdMedium(this, areaWidth, areaHeight, bird, mediumPanel.getTargetLevel(i));
						add(flappyBirdMedium, "flappyBirdMedium");
						cardManager.show(this, "flappyBirdMedium");
						found = true;
					}
				}
				for (int i=0; !found && i<easyPanel.getMaxLevel(); i++) {
					if (command.equalsIgnoreCase("Hard" + (i+1))) {
						flappyBirdHard = new FlappyBirdHard(this, areaWidth, areaHeight, bird, hardPanel.getTargetLevel(i));
						add(flappyBirdHard, "flappyBirdHard");
						cardManager.show(this, "flappyBirdHard");
						found = true;
					}
				}
			}
		}
	}
	
}