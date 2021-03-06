package com.capadogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBirdMedium implements ActionListener, MouseListener, KeyListener{

    public static FlappyBirdMedium flappybird;
    public final int WIDTH = 640, HEIGHT = 450;
    public RendererMedium renderer;
    public Rectangle bird;
    public ArrayList<Rectangle> columns;
    public int ticks, yMotion, score;
    public boolean gameOver, started ;
    public Random rand;

    public FlappyBirdMedium() {
        JFrame jframe = new JFrame();
        Timer timer = new Timer(20, this);

        renderer = new RendererMedium();
        rand = new Random();

        jframe.add(renderer);
        jframe.setTitle("Flappy Bird");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH,HEIGHT);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setVisible(true);

        bird = new Rectangle(WIDTH/2 - 10, HEIGHT/2 - 10, 20, 20);
        columns = new ArrayList<Rectangle>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();
    }

    //membuat balok rintangan
    public void addColumn(boolean start){
        int space = 250;
        int width = 100;
        Random rand = new Random();
        int height = 35 + rand.nextInt(100);

        if(start){
            columns.add(new Rectangle(WIDTH + width + columns.size() * 250, HEIGHT - height - 100, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 250, 0, width, HEIGHT - height - space));
        }
        else{
            columns.add(new Rectangle(columns.get(columns.size()-1).x + 500, HEIGHT - height - 100, width, height));
            columns.add(new Rectangle(columns.get(columns.size()-1).x, 0, width, HEIGHT-height-space));
        }
    }

    //membuat balok rintangan
    public void paintColumn(Graphics g, Rectangle column){
        g.setColor(Color.GREEN.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void jump(){
        if(gameOver){
            bird = new Rectangle(WIDTH/2-10, HEIGHT/2-10, 20, 20);
            columns.clear();
            yMotion = 0;
            score = 0;

            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);

            gameOver = false;
        }
        if(!started){
            started = true;
        }
        else if(!gameOver){
            if(yMotion>0){
                yMotion = 0;
            }
            yMotion -= 10;
        }
    }

    //jalannya game
    @Override
    public void actionPerformed(ActionEvent e) {
        int speed = 5;
        ticks++;
        if (started) {

            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);
                column.x -= speed;
            }

            if (ticks % 2 == 0 && yMotion < 15) {
                yMotion += 2;
            }

            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);

                if (column.x + column.width < 0) {
                    columns.remove(column);

                    if (column.y == 0) {
                        addColumn(false);
                    }
                }
            }
            bird.y += yMotion;

            for (Rectangle column : columns) {

                if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 2  && bird.x + bird.width / 2 < column.x + column.width / 2 + 2) {
                    score++;
                }

                if (column.intersects(bird)) {
                    gameOver = true;
                    if(bird.x <= column.x){
                        bird.x = column.x - bird.width;
                    }
                    else{
                        if(column.y != 0){
                            bird.y = column.y - bird.height;
                        }
                        else if( bird.y< column.height){
                            bird.y = column.height;
                        }
                    }
                }
            }

            if (bird.y > HEIGHT - 120 || bird.y < 0) {
                gameOver = true;
            }

            if(bird.y + yMotion >= HEIGHT - 120){
                bird.y = HEIGHT - 120 - bird.height;
//                gameOver = true;
            }
        }
        renderer.repaint();
    }

    public void repaint(Graphics g){
        g.setColor(Color.CYAN);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g.setColor(Color.ORANGE);
        g.fillRect(0,HEIGHT-150, WIDTH, 150);

        g.setColor(Color.GREEN);
        g.fillRect(0,HEIGHT-150, WIDTH, 20);

        g.setColor(Color.RED);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for(Rectangle column : columns){
            paintColumn(g, column);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("ARIAL", 1, 75));

        if(!started && score == 0){
            g.drawString("Click to start!!", 40, HEIGHT/2 - 50);
        }
        if(gameOver){
            g.drawString("Game Over!", 75, HEIGHT/2 - 50);
        }
        if(!gameOver && started){
            g.drawString(String.valueOf(score), WIDTH/2-25, 100);
        }
        if(started && score > 17){
            started = false;
        }
        if(!started && score > 17){
            g.drawString("YOU WIN!!!", 75, HEIGHT/2);
        }
    }

    public static void main(String[] args) {
        flappybird = new FlappyBirdMedium();
    }

    @Override
    public void mouseClicked(MouseEvent e){
        jump();
    }
    public void mousePressed(MouseEvent e){
    }
    public void mouseReleased(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            jump();
        }
    }
}

