package com.capadogame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp extends JPanel {

	private JPanel contentPane;
	private JTextField fieldUsername;
	private JPasswordField fieldRePass;
	private JPasswordField fieldPass;
	public static String nameRegis;
	public static String passRegis;

	public SignUp() {
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("CREATE    ACCOUNT");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(new Color(128, 128, 128));
		title.setFont(new Font("STXinwei", Font.BOLD | Font.ITALIC, 25));
		title.setBounds(116, 34, 367, 37);
		contentPane.add(title);
		
		JLabel userName = new JLabel("Username");
		userName.setFont(new Font("STXinwei", Font.BOLD, 18));
		userName.setBounds(69, 100, 163, 22);
		contentPane.add(userName);
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("STXinwei", Font.BOLD, 18));
		password.setBounds(69, 147, 163, 22);
		contentPane.add(password);
		
		JLabel rePassword = new JLabel("Re-typed Password");
		rePassword.setFont(new Font("STXinwei", Font.BOLD, 18));
		rePassword.setBounds(69, 199, 163, 22);
		contentPane.add(rePassword);
		
		fieldUsername = new JTextField();
		fieldUsername.setFont(new Font("STXinwei", Font.PLAIN, 12));
		fieldUsername.setForeground(new Color(248, 248, 255));
		fieldUsername.setBackground(new Color(0, 0, 128));
		fieldUsername.setBounds(282, 99, 278, 27);
		contentPane.add(fieldUsername);
		fieldUsername.setColumns(10);
		
		fieldRePass = new JPasswordField();
		fieldRePass.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fieldRePass.setBackground(new Color(0, 0, 128));
		fieldRePass.setForeground(new Color(240, 255, 255));
		fieldRePass.setBounds(282, 198, 278, 27);
		contentPane.add(fieldRePass);
		
		fieldPass = new JPasswordField();
		fieldPass.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fieldPass.setForeground(new Color(248, 248, 255));
		fieldPass.setBackground(new Color(0, 0, 128));
		fieldPass.setBounds(282, 146, 278, 27);
		contentPane.add(fieldPass);
		
		JLabel wrongPassword = new JLabel();
		wrongPassword.setHorizontalAlignment(SwingConstants.CENTER);
		wrongPassword.setForeground(Color.RED);
		wrongPassword.setFont(new Font("Imprint MT Shadow", Font.BOLD, 15));
		wrongPassword.setBounds(269, 172, 303, 22);
		contentPane.add(wrongPassword);
		
		JLabel noMatch = new JLabel();
		noMatch.setFont(new Font("Imprint MT Shadow", Font.BOLD, 15));
		noMatch.setForeground(Color.RED);
		noMatch.setHorizontalAlignment(SwingConstants.CENTER);
		noMatch.setBounds(269, 223, 303, 22);
		contentPane.add(noMatch);
		
		JButton create = new JButton("CREATE");
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(fieldPass.getText().equals(fieldRePass.getText())) {
					
					nameRegis = fieldUsername.getText();
					passRegis = fieldPass.getText();
					
					//go to Login
					SignIn login = new SignIn();
					login.setVisible(false);
					login.setSize(800, 500);
					login.setLocationRelativeTo(null);
				}
				else if (fieldPass.getText().length() < 8) {
					wrongPassword.setText("Password must be at least 8 characters");
				}
				else {
					noMatch.setText("The password doesn't match");	
				}
				
			}
		});
		create.setFont(new Font("Imprint MT Shadow", Font.BOLD, 17));
		create.setForeground(new Color(240, 255, 255));
		create.setBackground(new Color(0, 0, 128));
		create.setBounds(225, 304, 163, 37);
		contentPane.add(create);
		
	}
}
