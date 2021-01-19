package com.capadogame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class Trophy {
	private int trophy;
	private String dataPath;
	private String fileNameTrophy = "dataTrophy";
	
	public Trophy() {
		try {
			dataPath = Trophy.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {}
		loadTrophy();
	}

	public int getTrophy() {
		return trophy;
	}

	public void setTrophy(int trophy) {
		this.trophy = trophy;
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
