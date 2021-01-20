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
import javax.swing.DropMode;

public class SignIn extends JFrame {

	public JPanel panel;
	public JTextField user;
	public static String userName;
	public static String sandi;
	private JPasswordField pass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignIn() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 640, 450);
		
		panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel password = new JLabel("");
		password.setFont(new Font("Tahoma", Font.PLAIN, 11));
		password.setBounds(138, 173, 39, 38);
		password.setIcon(new ImageIcon(SignIn.class.getResource("/image/pass.png")));
		password.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(password);
		
		JLabel title = new JLabel("CAPADO");
		title.setBackground(new Color(0, 0, 0));
		title.setBounds(95, 22, 243, 38);
		title.setFont(new Font("Imprint MT Shadow", Font.BOLD | Font.ITALIC, 40));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(title);
		
		JLabel title2 = new JLabel("GAME");
		title2.setBounds(339, 11, 176, 61);
		title2.setForeground(new Color(240, 248, 255));
		title2.setFont(new Font("Imprint MT Shadow", Font.BOLD | Font.ITALIC, 40));
		panel.add(title2);
		
		JLabel username = new JLabel("");
		username.setBounds(138, 112, 39, 38);
		username.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(username);
		username.setIcon(new ImageIcon(SignIn.class.getResource("/image/user.png")));
		
		user = new JTextField();
		user.setFont(new Font("STXinwei", Font.PLAIN, 12));
		user.setForeground(new Color(255, 255, 255));
		user.setBounds(212, 118, 292, 32);
		user.setBackground(new Color(0, 0, 128));
		panel.add(user);
		user.setColumns(10);
		
		JLabel instruction = new JLabel("please insert your username and password");
		instruction.setHorizontalAlignment(SwingConstants.CENTER);
		instruction.setForeground(Color.GRAY);
		instruction.setBounds(170, 87, 305, 14);
		panel.add(instruction);
		
		JLabel wrongPass = new JLabel("");
		wrongPass.setFont(new Font("Imprint MT Shadow", Font.BOLD, 12));
		wrongPass.setForeground(Color.RED);
		wrongPass.setHorizontalAlignment(SwingConstants.CENTER);
		wrongPass.setBounds(185, 311, 255, 22);
		panel.add(wrongPass);
		
		JButton signUp = new JButton("SIGN UP");
		signUp.setForeground(new Color(240, 255, 255));
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// go to Create Account
				SignUp regis = new SignUp();
				regis.setVisible(true);
				regis.setSize(800, 500);
				regis.setLocationRelativeTo(null);
				regis.setDefaultCloseOperation(SignIn.EXIT_ON_CLOSE);
				
			}
		});
		signUp.setFont(new Font("Imprint MT Shadow", Font.BOLD, 15));
		signUp.setBounds(247, 244, 105, 32);
		signUp.setBackground(new Color(0, 0, 139));
		panel.add(signUp);
		
		JButton signIn = new JButton("SIGN IN");
		signIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(password.getText().length() < 8) {
					wrongPass.setText("Password must be at least 8 characters");
				}
				
				userName = user.getText();
				sandi = pass.getText();
				
				//membandingkan dengan data regis/signup yang sudah ada atau belum jika belum harus mengisi regis/sign up 
				// go to Home
				
			}
		});
		signIn.setForeground(new Color(240, 255, 255));
		signIn.setBackground(new Color(0, 0, 139));
		signIn.setFont(new Font("Imprint MT Shadow", Font.BOLD, 15));
		signIn.setBounds(233, 344, 135, 27);
		panel.add(signIn);
		
		pass = new JPasswordField();
		pass.setForeground(new Color(255, 255, 255));
		pass.setBackground(new Color(0, 0, 139));
		pass.setBounds(212, 179, 293, 32);
		panel.add(pass);
		
	
	}
}
