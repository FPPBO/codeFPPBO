package com.capadogame;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBirdMedium extends JPanel implements ActionListener, MouseListener, KeyListener{
	private int areaWidth, areaHeight;
	private int target;
	private Bird birdType;
	private Rectangle bird;
	private ArrayList<Rectangle> columns;
	private int ticks, yMotion, score;
	private boolean gameOver, started;
	private JButton jbReturn;
	private Timer timer;

    public FlappyBirdMedium(CardLayoutWindow parent, int areaWidth, int areaHeight, Bird birdType, int target) {
    	this.areaWidth = areaWidth;
    	this.areaHeight = areaHeight;
    	this.birdType = birdType;
    	this.target = target;
    	gameOver = false;
    	started = false;
    	yMotion = 0;
    	score = 0;
        timer = new Timer(20, this);
        
        addMouseListener(this);
        addKeyListener(this);
        addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentShown(ComponentEvent e) {
        		FlappyBirdMedium.this.requestFocusInWindow(); // set fokus
        	}
        });
        
        bird = new Rectangle(areaWidth/2 - 10, areaHeight/2 - 10, 20, 20);
        columns = new ArrayList<Rectangle>();
        
        this.setLayout(new BorderLayout());
        
        jbReturn = new JButton("Click Here To Return");
        jbReturn.setFont(new Font("Arial", Font.PLAIN, 20));
        jbReturn.setBackground(Color.ORANGE);
        jbReturn.setForeground(Color.WHITE);
        jbReturn.setFocusPainted(false);
        jbReturn.setBorderPainted(false);
        jbReturn.setActionCommand("ReturnMedium");
        jbReturn.addActionListener(parent);
        add(jbReturn, BorderLayout.SOUTH);
        
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
            columns.add(new Rectangle(areaWidth + width + columns.size() * 250, areaHeight - height - 100, width, height));
            columns.add(new Rectangle(areaWidth + width + (columns.size() - 1) * 250, 0, width, areaHeight - height - space));
        }
        else{
            columns.add(new Rectangle(columns.get(columns.size()-1).x + 500, areaHeight - height - 100, width, height));
            columns.add(new Rectangle(columns.get(columns.size()-1).x, 0, width, areaHeight - height - space));
        }
    }

    //membuat balok rintangan
    public void paintColumn(Graphics g, Rectangle column){
        g.setColor(Color.GREEN.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void jump(){
        if(!started){ // kalau belum mulai
            started = true;
        }
        else if(!gameOver){ // kalau game masih jalan
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
        if (started && score < target && !gameOver) {

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
                        else if(bird.y < column.height){
                            bird.y = column.height;
                        }
                    }
                }
            }

            if (bird.y > areaHeight - 120 || bird.y < 0) {
                gameOver = true;
            }

            if(bird.y + yMotion >= areaHeight - 120){
                bird.y = areaHeight - 120 - bird.height;
            }
            
        } else if (score == target || gameOver){
        	timer.stop();        	
        }
        
        repaint();
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        paint(g);
    }
    
    public void paint(Graphics g){
        g.setColor(Color.CYAN);
        g.fillRect(0,0,areaWidth,areaHeight);

        g.setColor(Color.ORANGE);
        g.fillRect(0,areaHeight-150, areaWidth, 150);

        g.setColor(Color.GREEN);
        g.fillRect(0,areaHeight-150, areaWidth, 20);

        g.setColor(birdType.getColor()); // warna bird sesuai tipe
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for(Rectangle column : columns){
            paintColumn(g, column);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("ARIAL", 1, 75));

        if(!started && score == 0){ // kalau belum mulai
        	g.drawString("Click to start!!", 40, areaHeight/2 - 50);
        }
        if(gameOver && score < target){ // kalau sudah gameover
            g.drawString("Game Over!", 75, areaHeight/2 - 50);
        }
        if(!gameOver && started && score < target){ // kalau game masih jalan
            g.drawString(String.valueOf(score), areaWidth/2-25, 100);
        }
        if(started && score == target){ // kalau sudah menang
            g.drawString("YOU WIN!!!", 75, areaHeight/2);
        }
    }
    
    public boolean getGameOverStatus() {
    	return gameOver;
    }
    
    public int getScore() {
    	return score;
    }
    
    public int getTarget() {
    	return target;
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
