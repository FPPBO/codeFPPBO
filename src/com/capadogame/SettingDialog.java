package com.capadogame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SettingDialog extends JDialog {
	private JPanel jpSetting;
	private JLabel jlMusic;
	private JButton jbMusic;
	private BGM bgm;
	private FlowLayout flowLayoutS;
	private final Color red = new Color(255, 87, 87);
	private final Color green = new Color(126, 217, 87);
	
	public SettingDialog(CardLayoutWindow parent, BGM bgm) {
		super(parent, "Setting", true);
		setSize(300, 150);
		setLocationRelativeTo(parent);

		this.bgm = bgm;
		
		jpSetting = new JPanel();
		flowLayoutS = new FlowLayout(FlowLayout.CENTER, 50, 38);
		jpSetting.setLayout(flowLayoutS);
		
		jlMusic = new JLabel("Music");
		jlMusic.setFont(new Font("Arial", Font.BOLD, 20));
		
		jbMusic = new JButton("ON");
		jbMusic.setFont(new Font("Arial", Font.BOLD, 16));
		jbMusic.setBackground(green);
		jbMusic.setFocusPainted(false);
		jbMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButton();
			}
		});
		jbMusic.setMinimumSize(new Dimension(66, 29));
		jbMusic.setPreferredSize(new Dimension(66, 29));
		
		jpSetting.add(jlMusic);
		jpSetting.add(jbMusic);
		jpSetting.setBackground(new Color(245, 222, 89));
		
		add(jpSetting);
	}
	
	public void setButton() {
		if (jbMusic.getText() == "ON") {
			jbMusic.setText("OFF");
			jbMusic.setBackground(red);
			this.bgm.stop();
		} else {
			jbMusic.setText("ON");
			jbMusic.setBackground(green);
			this.bgm.play();
		}
	}
	
}
