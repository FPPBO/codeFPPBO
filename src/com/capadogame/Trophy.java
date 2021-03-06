package com.capadogame;

import java.io.*;
import java.net.*;

public class Trophy {
	private int trophy;
	private String dataPath;
	private String fileNameTrophy;
	
	public Trophy() {
		try {
			dataPath = Trophy.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {}
		
		this.trophy = 0;
	}

	public int getTrophy() {
		return trophy;
	}

	public void setTrophy(int trophy) {
		this.trophy = trophy;
	}
	
	public void setFileNameTrophy(String username) {
		fileNameTrophy = "dataTrophy" + username;
		loadTrophy();
	}
	
	public void loadTrophy() {
		try {
			File f = new File(dataPath, fileNameTrophy);
			if(!f.isFile()) {
				createTrophy();
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader (new FileInputStream(f)));
			setTrophy(Integer.parseInt(reader.readLine()));
			reader.close();
		}
		catch(Exception e) { }
	}
	
	public void createTrophy() {
		try {
			File file = new File(dataPath, fileNameTrophy);

			FileWriter output = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(output);
			writer.write("0");

			writer.close();
		}
		catch(Exception e) { }
	}
	
	public void updateTrophy(int trophy) {
		FileWriter output = null;
		try {
			File f = new File(dataPath, fileNameTrophy);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output); 

			writer.write(String.valueOf(trophy));

			writer.close();
		}
		catch(Exception e) { }
	}
	
}
