package com.capadogame;

import java.awt.*;
import java.io.*;
import java.net.*;

public class Bird {
	private int type;
	private Color color;
	private String dataPath;
	private String fileNameBird;
	
	public Bird() {
		try {
			dataPath = Bird.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {}
		
		setType(0);
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		setColor();
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor() {
		if (this.type == 0) {
			this.color = Color.RED;
		} else if (this.type == 1) {
			this.color = Color.MAGENTA;
		} else if (this.type == 2) {
			this.color = Color.YELLOW;
		} else if (this.type == 3) {
			this.color = Color.PINK;
		} else if (this.type == 4) {
			this.color = Color.BLUE;
		}
	}
	
	public void setFileNameBird(String username) {
		fileNameBird = "dataBird" + username;
		loadBird();
	}
	
	public void loadBird() {
		try {
			File f = new File(dataPath, fileNameBird);
			if(!f.isFile()) {
				createBird();
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader (new FileInputStream(f)));
			setType(Integer.parseInt(reader.readLine()));
			reader.close();
		}
		catch(Exception e) { }
	}
	
	public void createBird() {
		try {
			File file = new File(dataPath, fileNameBird);

			FileWriter output = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(output);
			writer.write("0");

			writer.close();
		}
		catch(Exception e) { }
	}
	
	public void updateBird(int type) {
		FileWriter output = null;
		try {
			File f = new File(dataPath, fileNameBird);
			output = new FileWriter(f);
			BufferedWriter writer = new BufferedWriter(output); 

			writer.write(String.valueOf(type));

			writer.close();
		}
		catch(Exception e) { }
	}
	
}
