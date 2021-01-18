package com.capadogame;

import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CardLayoutWindow extends Frame implements WindowListener, ActionListener{
	CardLayout cardManager;
	SettingDialog settingDialog;
	StartPanel startPanel;
	ProfilPanel profilPanel;
	ShopPanel shopPanel;
	LevelPanel levelPanel;
	EasyPanel easyPanel;
	MediumPanel mediumPanel;
	HardPanel hardPanel;
	BGM bgm;
	Bird bird;
	Trophy trophy;
	Heart heart;
	int areaWidth, areaHeight;
	
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
		
		settingDialog = new SettingDialog(this, bgm);
		startPanel = new StartPanel(this, areaWidth, areaHeight, trophy, heart);
		profilPanel = new ProfilPanel (this, areaWidth, areaHeight, trophy, heart);
		shopPanel  = new ShopPanel (this, areaWidth, areaHeight, trophy, heart, bird);
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
		bgm.close();
	}
	public void windowClosing(WindowEvent e) {
		bgm.stop();
		this.dispose();
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
	}
	
//	klo menang
//	trophy.setTrophy(trophy.getTrophy()+1);
// 	ganti gambar level => repaint tambah tulisan clear aja ya :((
	
//	klo kalah
//	heart kurang
	
	public void startThread() {
		Thread windowThread = new Thread() {
			public void run() {
				while (true) {
					startPanel.repaint();
					profilPanel.repaint();
					shopPanel.repaint();
					levelPanel.repaint();
					easyPanel.repaint();
					mediumPanel.repaint();
					hardPanel.repaint();
					try {
						Thread.sleep(1000/15);
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
        } else if (command.equalsIgnoreCase("Easy1") && heart.getHeart() != 0) {
        	
        } else if (command.equalsIgnoreCase("Easy2") && heart.getHeart() != 0) {
        	
        } else if (command.equalsIgnoreCase("Easy3") && heart.getHeart() != 0) {
        	
        } else if (command.equalsIgnoreCase("Medium")) {
      	 	cardManager.show(this, "mediumPanel");
        } else if (command.equalsIgnoreCase("Medium1") && heart.getHeart() != 0) {
        	
        } else if (command.equalsIgnoreCase("Medium2") && heart.getHeart() != 0) {
        	
        } else if (command.equalsIgnoreCase("Medium3") && heart.getHeart() != 0) {
        	
        } else if (command.equalsIgnoreCase("Hard")) {
      	 	cardManager.show(this, "hardPanel");
        } else if (command.equalsIgnoreCase("Hard1") && heart.getHeart() != 0) {
        	
        } else if (command.equalsIgnoreCase("Hard2") && heart.getHeart() != 0) {
        	
        } else if (command.equalsIgnoreCase("Hard3") && heart.getHeart() != 0) {
        	
        } 
	}
	
}
