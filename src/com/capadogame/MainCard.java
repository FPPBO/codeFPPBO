package com.capadogame;

import javax.swing.SwingUtilities;

public class MainCard {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CardLayoutWindow frame = new CardLayoutWindow("CapadoGame");
				frame.setVisible(true);
			}
		});
	}

}
