package com.capadogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BirdShop extends JPanel {
	int type;
	String title, description;
	ImageIcon iiPrev, iiImage, iiNext;
	JButton jbPrev, jbSelect, jbPurchase, jbNext;
	JLabel jlImage, jlTitle;
	JTextArea jtaDescription;
	JPanel centerShop;
	GridBagLayout gridBagLayoutCS;
	GridBagConstraints c;
	BoxLayout boxLayoutP;
	JPanel prevBird, nextBird;
	
	public BirdShop (ShopPanel parent, int areaWidth, int areaHeight, 
			Bird bird, Boolean[] birdPurchase, int type) {
		/* set */
		this.type = type;
		setString();
		
		/* Prev */
		jbPrev = new JButton();
		if (type == 0) {
			iiPrev = new ImageIcon(new ImageIcon("res/no-prev.png").getImage().
					getScaledInstance(areaHeight/10, areaHeight/10, Image.SCALE_SMOOTH));
		} else {
			iiPrev = new ImageIcon(new ImageIcon("res/prev.png").getImage().
					getScaledInstance(areaHeight/10, areaHeight/10, Image.SCALE_SMOOTH));
		}
		jbPrev.setIcon(iiPrev);
		jbPrev.setBorder(null);
		jbPrev.setFocusPainted(false);
		jbPrev.setActionCommand("BirdPrev" + type);
		
		/* Center */
		centerShop = new JPanel();
		gridBagLayoutCS = new GridBagLayout();
		centerShop.setLayout(gridBagLayoutCS);
		c = new GridBagConstraints();
		
		iiImage = new ImageIcon(new ImageIcon("res/bird-" + type + ".png").getImage().
				getScaledInstance(areaHeight/8, areaHeight/8, Image.SCALE_SMOOTH));
		jlImage = new JLabel();
		jlImage.setIcon(iiImage);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(15, 15, 0, 0);
		c.weightx = 0;
		c.gridheight = 2;
		centerShop.add(jlImage, c);
		
		jlTitle = new JLabel(title);
		jlTitle.setFont(new Font("Arial", Font.BOLD, 14));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(15, 20, 0, 0);
		c.weightx = 0.5;
		c.gridheight = 1;
		c.gridwidth = 2;
		centerShop.add(jlTitle, c);
		
		jtaDescription = new JTextArea(description, 3, 50);
		jtaDescription.setFont(new Font("Arial", Font.PLAIN, 12));
		jtaDescription.setLineWrap(true);
		jtaDescription.setWrapStyleWord(true);
		jtaDescription.setOpaque(false);
		jtaDescription.setEditable(false);
		
        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0, 30, 0, 0);
		c.weightx = 0.5;
		c.gridwidth = 2;
		centerShop.add(jtaDescription, c);
		
		jbSelect = new JButton();
		jbSelect.setFocusPainted(false);
		jbSelect.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.WHITE, 2, true),
				BorderFactory.createEmptyBorder(3, 20, 3, 20)));
		jbSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bird.setType(type);
                jbSelect.setEnabled(false);
    			jbSelect.setText("Selected");
    			jbSelect.setBackground(new Color(217, 217, 217));
            }
        });
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;
		c.insets = new Insets(0, 50, 0, 50);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		centerShop.add(jbSelect, c);
				
		jbPurchase = new JButton();
		jbPurchase.setFocusPainted(false);
		jbPurchase.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.WHITE, 2, true),
				BorderFactory.createEmptyBorder(3, 20, 3, 20)));
		setButton(bird, birdPurchase);
		jbPurchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	birdPurchase[type] = true;
    			setButton(bird, birdPurchase);
            }
        });
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;
		c.gridx = 2;
		c.gridy = 2;
		centerShop.add(jbPurchase, c);
		centerShop.setBackground(new Color (253, 253, 151));
		centerShop.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.WHITE, 3, true), 
				BorderFactory.createRaisedSoftBevelBorder()));
		
		/* Next */
		jbNext = new JButton();
		if (type == birdPurchase.length-1) {
			iiNext = new ImageIcon(new ImageIcon("res/no-next.png").getImage().
					getScaledInstance(areaHeight/10, areaHeight/10, Image.SCALE_SMOOTH));
		} else {
			iiNext = new ImageIcon(new ImageIcon("res/next.png").getImage().
					getScaledInstance(areaHeight/10, areaHeight/10, Image.SCALE_SMOOTH));
		}
		jbNext.setIcon(iiNext);
		jbNext.setBorder(null);
		jbNext.setFocusPainted(false);
		jbNext.setActionCommand("BirdNext" + type);
		
		/* Panel */
		boxLayoutP = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(boxLayoutP);
		add(jbPrev);
		add(centerShop);
		add(jbNext);
		setBackground(new Color(185, 226, 245));
		
		jbPrev.addActionListener(parent);
		jbNext.addActionListener(parent);
	}
	
	public void setString() {
		if (type == 0) {
			title = "Dove";
			description = "blasabukdb audbila abjbda abjsa bsajb "
					+ "ayusgau saisb asguahis a gayk abga da6s agsyajb";
		} else if (type == 1) {
			title = "Fluffy";
			description = "blasabukdb audbila abjbda abjsa bsajb "
					+ "ayusgau sask saisb asguahis a gayk abga da6s agsyajb";
		} else if (type == 2) {
			title = "Lovely";
			description = "blasabukdb audbila abjbda abjsa bsajb "
					+ "ayusgau sask asguahis a gayk abga da6s agsyajb";
		} else if (type == 3) {
			title = "Seagull";
			description = "blasabukdb audbila abjbda abjsa bsajb "
					+ "ayusgau sask asguahis a gayk abga da6s agsyajb";
		} else if (type == 4) {
			title = "Crystal";
			description = "blasabukdb audbila abjbda abjsa bsajb "
					+ "ayusgau sask asguahis a gayk abga da6s agsyajb";
		}
	}
	
	public void setButton(Bird bird, Boolean[] birdPurchase) {
		if (birdPurchase[type] == true) { // sudah dibeli
			jbPurchase.setEnabled(false);
			jbPurchase.setText("Purchased");
			jbPurchase.setBackground(new Color(217, 217, 217));
			
			if (bird.getType() == type) {
				jbSelect.setEnabled(false);
				jbSelect.setText("Selected");
				jbSelect.setBackground(new Color(217, 217, 217));
			} else {
				jbSelect.setEnabled(true);
				jbSelect.setText("Select");
				jbSelect.setBackground(new Color(255, 222, 89));
			}
			
		} else { // belum dibeli
			jbPurchase.setEnabled(true);
			jbPurchase.setText("Purchase");
			jbPurchase.setBackground(new Color(255, 222, 89));
			
			jbSelect.setEnabled(false);
			jbSelect.setText("Select");
			jbSelect.setBackground(new Color(217, 217, 217));
		}
	}
	
	public void setPrevBird(JPanel prevBird) {
		this.prevBird = prevBird;
	}
	
	public JPanel getPrevBird() {
		return this.prevBird;
	}
	
	public void setNextBird(JPanel nextBird) {
		this.nextBird = nextBird;
	}
	
	public JPanel getNextBird() {
		return this.nextBird;
	}
	
}
