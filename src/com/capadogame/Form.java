package com.capadogame;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import javax.swing.JFrame;

public class Form extends JFrame implements ActionListener{
	
	SignIn signIn;
	SignUp signUp;
	String dataPath;
	String fileNameUsername = "dataUsername";
	String fileNamePassword;
	
	public Form (Profile profile) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(640, 450);
		setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-getSize().width)/2, 
				(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-getSize().height)/2);
		setResizable(false);
		
		try {
			dataPath = Form.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {}
		
		signIn = new SignIn(this, profile);
		setContentPane(signIn);
		
		signUp = new SignUp(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equalsIgnoreCase("Create")) {
			signUp.clearMessage();
			if (signUp.getFieldUsename().length() < 1) {
				signUp.setWrongUsernameText("Usename must be at least 1 characters");
			} else if (findUsername(signUp.getFieldUsename())) {
				signUp.setWrongUsernameText("Username already exists");
			} else if (signUp.getFieldPass().length() < 8) {
				signUp.setWrongPasswordText("Password must be at least 8 characters");
			} else if (!signUp.getFieldPass().equals(signUp.getFieldRePass())) {
				signUp.setNoMatchText("The password doesn't match");
			} else {
				updateUsername(signUp.getFieldUsename());
				fileNamePassword = "dataPassword" + signUp.getFieldUsename();
				createPassword();
				
				setContentPane(signIn);
				signIn.clearField();
				signIn.clearMessage();
				setVisible(true);
			}
		} else if (command.equalsIgnoreCase("SignUp")) {
			setContentPane(signUp);
			signUp.clearField();
			signUp.clearMessage();
			setVisible(true);
		}
	}
	
	public boolean findUsername(String username) {
		try {
			File f = new File(dataPath, fileNameUsername);
			
			String thisLine;
			BufferedReader reader = new BufferedReader(new InputStreamReader (new FileInputStream(f)));
			while ((thisLine = reader.readLine()) != null) {
				if (thisLine.equalsIgnoreCase(username)) {
					return true;
				}
			}
			reader.close();
		}
		catch(Exception e) { }
		
		return false;
	}
	
	public void updateUsername(String username) {
		FileWriter output = null;
		try {
			File f = new File(dataPath, fileNameUsername);
			output = new FileWriter(f, true);
			
			BufferedWriter writer = new BufferedWriter(output); 
			PrintWriter printer = new PrintWriter(writer);
			printer.println(username);
			
			printer.close();
			writer.close();
		}
		catch(Exception e) { }
	}
	
	public void createPassword() {
		try {
			File file = new File(dataPath, fileNamePassword);

			FileWriter output = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(output);
			writer.write(String.format("%s\n", signUp.getFieldPass()));

			writer.close();
		}
		catch(Exception e) { }
	}
	
}
