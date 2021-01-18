package com.capadogame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JDesktopPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Form extends JFrame {

	public JPanel panel;
	public JTextField user;
	public JPasswordField pass;
	public static String nama;
	public static String sandi;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form frame = new Form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Form() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 450);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 206, 209));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel password = new JLabel("");
		password.setFont(new Font("Tahoma", Font.PLAIN, 11));
		password.setBounds(108, 251, 39, 38);
		password.setIcon(new ImageIcon(Form.class.getResource("/image/pass.png")));
		password.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(password);
		
		pass = new JPasswordField();
		pass.setBackground(new Color(255, 160, 122));
		pass.setBounds(187, 253, 292, 36);
		panel.add(pass);
		
		JLabel title = new JLabel("CAPADO");
		title.setBackground(new Color(0, 0, 0));
		title.setBounds(70, 44, 243, 38);
		title.setFont(new Font("Imprint MT Shadow", Font.BOLD | Font.ITALIC, 40));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(title);
		
		JLabel title2 = new JLabel("GAME");
		title2.setBounds(304, 71, 176, 61);
		title2.setForeground(new Color(240, 248, 255));
		title2.setFont(new Font("Imprint MT Shadow", Font.BOLD | Font.ITALIC, 40));
		panel.add(title2);
		
		JLabel username = new JLabel("");
		username.setBounds(108, 186, 39, 38);
		username.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(username);
		username.setIcon(new ImageIcon(Form.class.getResource("/image/user.png")));
		
		user = new JTextField();
		user.setBounds(187, 186, 292, 32);
		user.setBackground(new Color(255, 160, 122));
		panel.add(user);
		user.setColumns(10);
		
		JLabel instruction = new JLabel("please insert your username and password");
		instruction.setForeground(Color.GRAY);
		instruction.setBounds(173, 143, 212, 14);
		panel.add(instruction);
		
		JLabel wrongPass = new JLabel("");
		wrongPass.setForeground(Color.RED);
		wrongPass.setHorizontalAlignment(SwingConstants.CENTER);
		wrongPass.setBounds(148, 313, 255, 14);
		panel.add(wrongPass);
		
		JButton login = new JButton("LOGIN");
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(pass.getText().length() >= 8) {
					nama = user.getText();
					
					Profile profil = new Profile();
					profil.setVisible(true);
					profil.setSize(800, 500);
					profil.setLocationRelativeTo(null);
					profil.setDefaultCloseOperation(Profile.EXIT_ON_CLOSE);
				}
				else {
					wrongPass.setText("Your password must be at least 8 character");
				}
			}
		});
		login.setFont(new Font("Imprint MT Shadow", Font.BOLD, 15));
		login.setBounds(218, 340, 105, 32);
		login.setBackground(new Color(0, 139, 139));
		panel.add(login);
		
	
	}
}
