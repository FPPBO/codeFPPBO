package sample;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBirdEasy1implements ActionListener, MouseListener, KeyListener {

    public static FlappyBirdEasy1 flappybird;
    public final int WIDTH = 800, HEIGHT = 600;
    public Renderer renderer;
    public Rectangle bird;
    public ArrayList<Rectangle> columns;
    public int ticks, yMotion, score;
    public boolean gameOver, started ;

    public FlappyBird() {
        JFrame jframe = new JFrame();
        Timer timer = new Timer(20, this);


        renderer = new Renderer();
        jframe.add(renderer);
        jframe.setTitle("Flappy Bird");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH,HEIGHT);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setVisible(true);
        bird = new Rectangle(WIDTH/2-10, HEIGHT/2-10, 20, 20);
        columns = new ArrayList<Rectangle>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();
    }

    public void addColumn(boolean start){
        int space = 300;
        int width = 100;
        Random rand = new Random();
        int height = 50 + rand.nextInt(100);

        if(start){
            columns.add(new Rectangle(WIDTH+width+columns.size()*300, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1)*300, 0, width, HEIGHT-height-space));
        }
        else{
           columns.add(new Rectangle(columns.get(columns.size()-1).x + 600, HEIGHT - height - 120, width, height));
           columns.add(new Rectangle(WIDTH + width + (columns.size() - 1)*300, 0, width, HEIGHT-height-space));
        }
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        ticks++;
        int speed = 1;
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
                column.x -= speed;
                if (column.x + column.width < 0) {
                    columns.remove(column);

                    if (column.y == 0) {
                        addColumn(false);
                    }
                }
            }
            bird.y += yMotion;

            for (Rectangle column : columns) {

                if(column.y == 0 && bird.x + bird.width /2 > column.x + column.width/2 - 5 && bird.x + bird.width/2 < column.x + column.width/2 + 10){
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
                bird.y = HEIGHT - 120 - bird.height - 30;
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
        g.setFont(new Font("ARIAL", 1, 100));

        if(!started){
            g.drawString("Click to start!", 75, HEIGHT/2);
        }

        if(gameOver){
            g.drawString("Game Over!", 75, HEIGHT/2);
        }
        if(!gameOver && started){
            g.drawString(String.valueOf(score), WIDTH/2-25, 100);
        }
    }

    public static void main(String[] args) {
        flappyBird = new FlappyBirdEasy1();
    }

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

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            jump();
        }
    }
}

