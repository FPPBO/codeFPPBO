package com.capadogame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.net.*;

public class SignIn extends JPanel {
	
	public JTextField user;
	private JPasswordField pass;
	private JLabel wrongUser;
	private JLabel wrongPass;
	private String dataPath;
	private String fileNamePassword;
	
	public SignIn(Form parent, Profile profile) {
		
		setBackground(new Color(100, 149, 237));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel password = new JLabel("");
		password.setFont(new Font("Tahoma", Font.PLAIN, 11));
		password.setBounds(138, 173, 39, 38);
		password.setIcon(new ImageIcon(SignIn.class.getResource("/image/pass.png")));
		password.setHorizontalAlignment(SwingConstants.CENTER);
		add(password);
		
		JLabel title = new JLabel("CAPADO");
		title.setBackground(new Color(0, 0, 0));
		title.setBounds(95, 22, 243, 38);
		title.setFont(new Font("Imprint MT Shadow", Font.BOLD | Font.ITALIC, 40));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title);
		
		JLabel title2 = new JLabel("GAME");
		title2.setBounds(339, 11, 176, 61);
		title2.setForeground(new Color(240, 248, 255));
		title2.setFont(new Font("Imprint MT Shadow", Font.BOLD | Font.ITALIC, 40));
		add(title2);
		
		JLabel username = new JLabel("");
		username.setBounds(138, 112, 39, 38);
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setIcon(new ImageIcon(SignIn.class.getResource("/image/user.png")));
		add(username);
		
		JLabel instruction = new JLabel("please insert your username and password");
		instruction.setHorizontalAlignment(SwingConstants.CENTER);
		instruction.setForeground(Color.GRAY);
		instruction.setBounds(170, 87, 305, 14);
		add(instruction);
		
		user = new JTextField();
		user.setFont(new Font("STXinwei", Font.PLAIN, 12));
		user.setForeground(new Color(255, 255, 255));
		user.setBounds(212, 118, 292, 32);
		user.setBackground(new Color(0, 0, 128));
		user.setColumns(10);
		add(user);
		
		wrongUser = new JLabel("");
		wrongUser.setFont(new Font("Imprint MT Shadow", Font.BOLD, 12));
		wrongUser.setForeground(Color.RED);
		wrongUser.setHorizontalAlignment(SwingConstants.CENTER);
		wrongUser.setBounds(185, 151, 255, 22);
		add(wrongUser);
		
		pass = new JPasswordField();
		pass.setForeground(new Color(255, 255, 255));
		pass.setBackground(new Color(0, 0, 139));
		pass.setBounds(212, 179, 293, 32);
		add(pass);
		
		wrongPass = new JLabel("");
		wrongPass.setFont(new Font("Imprint MT Shadow", Font.BOLD, 12));
		wrongPass.setForeground(Color.RED);
		wrongPass.setHorizontalAlignment(SwingConstants.CENTER);
		wrongPass.setBounds(185, 211, 255, 22);
		add(wrongPass);
		
		JButton signUp = new JButton("SIGN UP");
		signUp.setForeground(new Color(240, 255, 255));
		signUp.setFont(new Font("Imprint MT Shadow", Font.BOLD, 15));
		signUp.setBounds(247, 244, 105, 32);
		signUp.setBackground(new Color(0, 0, 139));
		signUp.setActionCommand("SignUp");
		signUp.addActionListener(parent);
		add(signUp);
		
		try {
			dataPath = SignIn.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {}
		
		JButton signIn = new JButton("SIGN IN");
		signIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearMessage();
				fileNamePassword = "dataPassword" + user.getText();
				
				File f = new File(dataPath, fileNamePassword);
				if(!f.isFile()) {
					wrongUser.setText("User not found");
				} else {
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader (new FileInputStream(f)));
						if (!reader.readLine().equalsIgnoreCase(pass.getText())) {
							wrongPass.setText("You entered the wrong password");
						} else {
							profile.setUsername(user.getText());
							profile.setPassword(password.getText());
							parent.setVisible(false);
						}
						reader.close();
					} catch (IOException e1) { }
				}
			}
		});
		signIn.setForeground(new Color(240, 255, 255));
		signIn.setBackground(new Color(0, 0, 139));
		signIn.setFont(new Font("Imprint MT Shadow", Font.BOLD, 15));
		signIn.setBounds(233, 344, 135, 27);
		add(signIn);
	}
	
	public void clearField() {
		user.setText("");
		pass.setText("");
	}
	
	public void clearMessage() {
		wrongUser.setText("");
		wrongPass.setText("");
	}
	
}
