package com.capadogame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import javax.swing.Timer;

public class Heart {
	private final int TIMELIMIT = 900;
	private final int HEARTLIMIT = 5;
	private int heart;
	private Timer heartTimer;
	private int time, minute, second;
	private String displayTimer, displayMin, displaySec;
	private String dataPath;
	private String fileNameHeart;
	private String fileNameTime;
	
	public Heart() {
		try {
			dataPath = Heart.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e1) { }
		
		heartTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (time > 0){
					time--;
					minute = time/60;
					second = time%60;
					setDisplayTimer(minute, second);
				} else {
					time = TIMELIMIT;
					heartTimer.stop();
					setHeart(getHeart()+1);
				}
			}
		});
		
		setHeart(HEARTLIMIT);
		time = TIMELIMIT;
	}
	
	public int getHeart() {
		return heart;
	}
	
	public void setHeart(int heart) {
		this.heart = heart;
		if (this.heart == HEARTLIMIT) {
			setDisplayTimer();
		} else if (this.heart < HEARTLIMIT && !heartTimer.isRunning()) {
			heartTimer.start();
		} 
	}
	
	public String getDisplayTimer() {
		return displayTimer;
	}
	
	public void setDisplayTimer() {
		displayTimer = "full";
	}
	
	public void setDisplayTimer(int minute, int second) {
		if (minute < 10) {
			displayMin = "0" + minute;
		} else {
			displayMin = String.valueOf(minute);
		}
		
		if (second < 10) {
			displaySec = "0" + second;
		} else {
			displaySec = String.valueOf(second);
		}
		
		displayTimer = displayMin + ":" + displaySec;
	}
	
	public void stopHeartTimer() {
		heartTimer.stop();
	}
	
	public void setFileNameHeartTime(String username) {
		fileNameHeart = "dataHeart" + username;
		fileNameTime = "dataTime" + username;
		loadHeart();
		loadTime();
	}
	
	public void loadHeart() {
		try {
			File f = new File(dataPath, fileNameHeart);
			if(!f.isFile()) {
				createHeart();
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader (new FileInputStream(f)));
			setHeart(Integer.parseInt(reader.readLine()));
			reader.close();
		}
		catch(Exception e) { }
	}
	
	public void loadTime() {
		try {
			File f = new File(dataPath, fileNameTime);
			if(!f.isFile()) {
				createTime();
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader (new FileInputStream(f)));
			time = Integer.parseInt(reader.readLine());
			reader.close();
		}
		catch(Exception e) { }
	}
	
	public void createHeart() {
		try {
			File file = new File(dataPath, fileNameHeart);

			FileWriter output = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(output);
			writer.write(String.valueOf(HEARTLIMIT));

			writer.close();
		}
		catch(Exception e) { }
	}
	
	public void createTime() {
		try {
			File file = new File(dataPath, fileNameTime);

			FileWriter output = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(output);
			writer.write(String.valueOf(TIMELIMIT));

			writer.close();
		}
		catch(Exception e) { }
	}
	
	public void updateHeart(int Heart) {
		FileWriter output = null;
		try {
			File f = new File(dataPath, fileNameHeart);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output); 

			writer.write(String.valueOf(heart));

			writer.close();
		}
		catch(Exception e) { }
	}
	
	public void updateTime() {
		FileWriter output = null;
		try {
			File f = new File(dataPath, fileNameTime);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output); 

			writer.write(String.valueOf(this.time));

			writer.close();
		}
		catch(Exception e) { }
	}
	
}
