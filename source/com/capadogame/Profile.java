package com.capadogame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Profile extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Profile() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 450);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(0, 0, 401, 413);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton back = new JButton("");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Form form = new Form();
				form.setVisible(true);
				form.setSize(800, 500);
				form.setLocationRelativeTo(null);
				form.setDefaultCloseOperation(Profile.EXIT_ON_CLOSE);
			}
		});
		back.setForeground(new Color(135, 206, 250));
		back.setIcon(new ImageIcon(Profile.class.getResource("/image/back.png")));
		back.setFont(new Font("Tahoma", Font.PLAIN, 14));
		back.setBackground(new Color(135, 206, 250));
		back.setBounds(0, 0, 64, 40);
		panel.add(back);
		
		JTextPane title3 = new JTextPane();
		title3.setForeground(new Color(255, 255, 255));
		title3.setFont(new Font("STXinwei", Font.BOLD, 25));
		title3.setText("PROFILE");
		title3.setBackground(new Color(135, 206, 250));
		title3.setBounds(83, 22, 121, 32);
		panel.add(title3);
		
		JLabel name = new JLabel();
		name.setFont(new Font("STXinwei", Font.BOLD | Font.ITALIC, 25));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setText(Form.nama);
		name.setBackground(new Color(70, 130, 180));
		name.setBounds(60, 340, 261, 51);
		panel.add(name);
		
		JTextPane title3_1 = new JTextPane();
		title3_1.setText("INFO");
		title3_1.setForeground(Color.WHITE);
		title3_1.setFont(new Font("STXinwei", Font.BOLD, 25));
		title3_1.setBackground(new Color(135, 206, 250));
		title3_1.setBounds(202, 22, 99, 40);
		panel.add(title3_1);
		
		JLabel photo = new JLabel("");
		photo.setBounds(60, 65, 268, 256);
		panel.add(photo);
		photo.setIcon(new ImageIcon(Profile.class.getResource("/image/photo.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(70, 130, 180));
		panel_1.setBounds(400, 0, 226, 413);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel scorep = new JLabel("");
		scorep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scorep.setForeground(new Color(255, 255, 255));
		scorep.setIcon(new ImageIcon(Profile.class.getResource("/image/score.png")));
		scorep.setBounds(39, 110, 49, 35);
		panel_1.add(scorep);
		
		JLabel heartp = new JLabel("");
		heartp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		heartp.setForeground(new Color(255, 255, 255));
		heartp.setIcon(new ImageIcon(Profile.class.getResource("/image/life.png")));
		heartp.setBounds(34, 194, 49, 35);
		panel_1.add(heartp);
		
		JLabel levelUpp = new JLabel("");
		levelUpp.setForeground(new Color(255, 255, 255));
		levelUpp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		levelUpp.setIcon(new ImageIcon(Profile.class.getResource("/image/level.png")));
		levelUpp.setBounds(39, 282, 49, 48);
		panel_1.add(levelUpp);
		
		JLabel score = new JLabel("score");
		score.setForeground(new Color(255, 255, 255));
		score.setFont(new Font("Tahoma", Font.PLAIN, 14));
		score.setBounds(39, 88, 49, 23);
		panel_1.add(score);
		
		JLabel lblNewLabel_6 = new JLabel("heart");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(39, 172, 49, 23);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("level up");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setBounds(39, 256, 61, 23);
		panel_1.add(lblNewLabel_7);
		
		JLabel score_1 = new JLabel("3000");
		score_1.setForeground(new Color(255, 255, 255));
		score_1.setFont(new Font("STXinwei", Font.BOLD, 30));
		score_1.setBounds(104, 110, 90, 35);
		panel_1.add(score_1);
		
		JLabel score_1_1 = new JLabel("3000");
		score_1_1.setForeground(Color.WHITE);
		score_1_1.setFont(new Font("STXinwei", Font.BOLD, 30));
		score_1_1.setBounds(104, 194, 90, 35);
		panel_1.add(score_1_1);
		
		JLabel score_1_2 = new JLabel("EASY 3");
		score_1_2.setForeground(Color.WHITE);
		score_1_2.setFont(new Font("STXinwei", Font.BOLD, 30));
		score_1_2.setBounds(98, 282, 96, 35);
		panel_1.add(score_1_2);
	}
}
