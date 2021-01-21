package com.capadogame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

public class CardLayoutWindow extends Frame implements WindowListener, ActionListener{
	private CardLayout cardManager;
	private SettingDialog settingDialog;
	private StartPanel startPanel;
	private ProfilPanel profilPanel;
	private ShopPanel shopPanel;
	private LevelPanel levelPanel;
	private EasyPanel easyPanel;
	private FlappyBirdEasy flappyBirdEasy;
	private MediumPanel mediumPanel;
	private FlappyBirdMedium flappyBirdMedium;
	private HardPanel hardPanel;
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
		profilPanel = new ProfilPanel (this, areaWidth, areaHeight, trophy, heart);
		shopPanel = new ShopPanel (this, areaWidth, areaHeight, trophy, heart, bird);
		levelPanel = new LevelPanel (this, areaWidth, areaHeight, trophy, heart);
		easyPanel = new EasyPanel (this, areaWidth, areaHeight, trophy, heart);
		mediumPanel = new MediumPanel (this, areaWidth, areaHeight, trophy, heart);
		hardPanel = new HardPanel (this, areaWidth, areaHeight, trophy, heart);
		
		cardManager = new CardLayout();
		setLayout(cardManager);
		add(startPanel, "startPanel");
		add(profilPanel, "profilPanel");
		add(shopPanel, "shopPanel");
		add(levelPanel, "levelPanel");
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
		easyPanel.updateClearEasy();
		mediumPanel.updateClearMedium();
		hardPanel.updateClearHard();
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
						easyPanel.setFileNameClearEasy(profile.getUsername());
						mediumPanel.setfileNameClearMedium(profile.getUsername());
						hardPanel.setFileNameClearHard(profile.getUsername());
						shopPanel.setFileNameBirdPurchase(profile.getUsername());
						changeProfile = true;
					}
					startPanel.repaint();
					profilPanel.repaint();
					shopPanel.repaint();
					levelPanel.repaint();
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
		} else if (command.equalsIgnoreCase("Profil")) {
			cardManager.show(this, "profilPanel");
		} else if (command.equalsIgnoreCase("Shop")) {
			cardManager.show(this, "shopPanel");
		} else if (command.equalsIgnoreCase("Play") || command.equalsIgnoreCase("BackLevel")) {
			cardManager.show(this, "levelPanel");
		} else if (command.equalsIgnoreCase("Easy")) {
			cardManager.show(this, "easyPanel");
		} else if (command.equalsIgnoreCase("ReturnEasy")) {
			if (flappyBirdEasy.getTarget() == flappyBirdEasy.getScore()) {
				for (int i=0; i<3; i++) {
					if (flappyBirdEasy.getTarget() == easyPanel.getTargetEasy(i) && !easyPanel.getClearEasy(i)) {
						easyPanel.setClearEasy(i);
						trophy.setTrophy(trophy.getTrophy()+1);
						break;
					}
				}
			} else if (flappyBirdEasy.getGameOverStatus() == true){
				heart.setHeart(heart.getHeart()-1);
			}
			cardManager.show(this, "easyPanel");
		} else if (command.equalsIgnoreCase("Easy1") && heart.getHeart() != 0) {
			flappyBirdEasy = new FlappyBirdEasy(this, areaWidth, areaHeight, bird, easyPanel.getTargetEasy(0));
			add(flappyBirdEasy, "flappyBirdEasy");
			cardManager.show(this, "flappyBirdEasy");
		} else if (command.equalsIgnoreCase("Easy2") && heart.getHeart() != 0) {
			flappyBirdEasy = new FlappyBirdEasy(this, areaWidth, areaHeight, bird, easyPanel.getTargetEasy(1));
			add(flappyBirdEasy, "flappyBirdEasy");
			cardManager.show(this, "flappyBirdEasy");
		} else if (command.equalsIgnoreCase("Easy3") && heart.getHeart() != 0) {
			flappyBirdEasy = new FlappyBirdEasy(this, areaWidth, areaHeight, bird, easyPanel.getTargetEasy(2));
			add(flappyBirdEasy, "flappyBirdEasy");
			cardManager.show(this, "flappyBirdEasy");
		} else if (command.equalsIgnoreCase("Medium")) {
			cardManager.show(this, "mediumPanel");
		} else if (command.equalsIgnoreCase("ReturnMedium")) {
			if (flappyBirdMedium.getTarget() == flappyBirdMedium.getScore()) {
				for (int i=0; i<3; i++) {
					if (flappyBirdMedium.getTarget() == mediumPanel.getTargetMedium(i) && !mediumPanel.getClearMedium(i)) {
						mediumPanel.setClearMedium(i);
						mediumPanel.setClearButton(i);
						trophy.setTrophy(trophy.getTrophy()+1);
						break;
					}
				}
			} else if (flappyBirdMedium.getGameOverStatus() == true){
				heart.setHeart(heart.getHeart()-1);
			}
			cardManager.show(this, "mediumPanel");
		} else if (command.equalsIgnoreCase("Medium1") && heart.getHeart() != 0) {
			flappyBirdMedium = new FlappyBirdMedium(this, areaWidth, areaHeight, bird, mediumPanel.getTargetMedium(0));
			add(flappyBirdMedium, "flappyBirdMedium");
			cardManager.show(this, "flappyBirdMedium");
		} else if (command.equalsIgnoreCase("Medium2") && heart.getHeart() != 0) {
			flappyBirdMedium = new FlappyBirdMedium(this, areaWidth, areaHeight, bird, mediumPanel.getTargetMedium(1));
			add(flappyBirdMedium, "flappyBirdMedium");
			cardManager.show(this, "flappyBirdMedium");
		} else if (command.equalsIgnoreCase("Medium3") && heart.getHeart() != 0) {
			flappyBirdMedium = new FlappyBirdMedium(this, areaWidth, areaHeight, bird, mediumPanel.getTargetMedium(2));
			add(flappyBirdMedium, "flappyBirdMedium");
			cardManager.show(this, "flappyBirdMedium");
		} else if (command.equalsIgnoreCase("Hard")) {
			cardManager.show(this, "hardPanel");
		} else if (command.equalsIgnoreCase("ReturnHard")) {
			if (flappyBirdHard.getTarget() == flappyBirdHard.getScore()) {
				for (int i=0; i<3; i++) {
					if (flappyBirdHard.getTarget() == hardPanel.getTargetHard(i) && !hardPanel.getClearHard(i)) {
						hardPanel.setClearHard(i);
						hardPanel.setClearButton(i);
						trophy.setTrophy(trophy.getTrophy()+1);
						break;
					}
				}
			} else if (flappyBirdHard.getGameOverStatus() == true){
				heart.setHeart(heart.getHeart()-1);
			}
			cardManager.show(this, "hardPanel");
		} else if (command.equalsIgnoreCase("Hard1") && heart.getHeart() != 0) {
			flappyBirdHard = new FlappyBirdHard(this, areaWidth, areaHeight, bird, hardPanel.getTargetHard(0));
			add(flappyBirdHard, "flappyBirdHard");
			cardManager.show(this, "flappyBirdHard");
		} else if (command.equalsIgnoreCase("Hard2") && heart.getHeart() != 0) {
			flappyBirdHard = new FlappyBirdHard(this, areaWidth, areaHeight, bird, hardPanel.getTargetHard(0));
			add(flappyBirdHard, "flappyBirdHard");
			cardManager.show(this, "flappyBirdHard");
		} else if (command.equalsIgnoreCase("Hard3") && heart.getHeart() != 0) {
			flappyBirdHard = new FlappyBirdHard(this, areaWidth, areaHeight, bird, hardPanel.getTargetHard(0));
			add(flappyBirdHard, "flappyBirdHard");
			cardManager.show(this, "flappyBirdHard");
		} else {
			JOptionPane.showMessageDialog(this, String.format("%s%n%s", "You can't start the game because your heart has run out.",
					"Just wait a moment."), "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
}
