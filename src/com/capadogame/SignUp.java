package com.capadogame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class SignUp extends JPanel {
	
	private JTextField fieldUsername;
	private JPasswordField fieldRePass;
	private JPasswordField fieldPass;
	private JLabel wrongUsername;
	private JLabel wrongPassword;
	private JLabel noMatch;
	public static String nameRegis;
	public static String passRegis;

	public SignUp(Form parent) {
		
		setBackground(new Color(100, 149, 237));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel title = new JLabel("CREATE    ACCOUNT");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(new Color(128, 128, 128));
		title.setFont(new Font("STXinwei", Font.BOLD | Font.ITALIC, 25));
		title.setBounds(116, 34, 367, 37);
		add(title);
		
		JLabel userName = new JLabel("Username");
		userName.setFont(new Font("STXinwei", Font.BOLD, 18));
		userName.setBounds(69, 100, 163, 22);
		add(userName);
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("STXinwei", Font.BOLD, 18));
		password.setBounds(69, 147, 163, 22);
		add(password);
		
		JLabel rePassword = new JLabel("Re-typed Password");
		rePassword.setFont(new Font("STXinwei", Font.BOLD, 18));
		rePassword.setBounds(69, 199, 163, 22);
		add(rePassword);
		
		fieldUsername = new JTextField();
		fieldUsername.setFont(new Font("STXinwei", Font.PLAIN, 12));
		fieldUsername.setForeground(new Color(248, 248, 255));
		fieldUsername.setBackground(new Color(0, 0, 128));
		fieldUsername.setBounds(282, 99, 278, 27);
		fieldUsername.setColumns(10);
		add(fieldUsername);
		
		fieldRePass = new JPasswordField();
		fieldRePass.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fieldRePass.setBackground(new Color(0, 0, 128));
		fieldRePass.setForeground(new Color(240, 255, 255));
		fieldRePass.setBounds(282, 198, 278, 27);
		add(fieldRePass);
		
		fieldPass = new JPasswordField();
		fieldPass.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fieldPass.setForeground(new Color(248, 248, 255));
		fieldPass.setBackground(new Color(0, 0, 128));
		fieldPass.setBounds(282, 146, 278, 27);
		add(fieldPass);
		
		wrongUsername = new JLabel();
		wrongUsername.setHorizontalAlignment(SwingConstants.CENTER);
		wrongUsername.setForeground(Color.RED);
		wrongUsername.setFont(new Font("Imprint MT Shadow", Font.BOLD, 15));
		wrongUsername.setBounds(269, 123, 303, 22);
		add(wrongUsername);
		
		wrongPassword = new JLabel();
		wrongPassword.setHorizontalAlignment(SwingConstants.CENTER);
		wrongPassword.setForeground(Color.RED);
		wrongPassword.setFont(new Font("Imprint MT Shadow", Font.BOLD, 15));
		wrongPassword.setBounds(269, 173, 303, 22);
		add(wrongPassword);
		
		noMatch = new JLabel();
		noMatch.setFont(new Font("Imprint MT Shadow", Font.BOLD, 15));
		noMatch.setForeground(Color.RED);
		noMatch.setHorizontalAlignment(SwingConstants.CENTER);
		noMatch.setBounds(269, 223, 303, 22);
		add(noMatch);
		
		JButton create = new JButton("CREATE");
		create.setFont(new Font("Imprint MT Shadow", Font.BOLD, 17));
		create.setForeground(new Color(240, 255, 255));
		create.setBackground(new Color(0, 0, 128));
		create.setBounds(225, 304, 163, 37);
		create.setActionCommand("Create");
		create.addActionListener(parent);
		add(create);
	}
	
	public String getFieldUsename() {
		return fieldUsername.getText();
	}
	
	public String getFieldPass() {
		return fieldPass.getText();
	}
	
	public String getFieldRePass() {
		return fieldRePass.getText();
	}
	
	public void setWrongUsernameText(String message) {
		wrongUsername.setText(message);
	}
	
	public void setWrongPasswordText(String message) {
		wrongPassword.setText(message);
	}
	
	public void setNoMatchText(String message) {
		noMatch.setText(message);
	}
	
	public void clearField() {
		fieldUsername.setText("");;
		fieldPass.setText("");
		fieldRePass.setText("");
	}
	
	public void clearMessage() {
		wrongUsername.setText("");
		wrongPassword.setText("");
		noMatch.setText("");
	}
}
