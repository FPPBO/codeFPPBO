package com.capadogame;

import javax.swing.*;
import java.awt.*;

public class RendererMedium extends JPanel {

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        FlappyBirdMedium.flappybird.repaint(g);
    }
}

