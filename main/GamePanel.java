package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int ORIGINAL_TILE_SIZE = 16; //16x16 tile
    final int SCALE = 3;
    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; //48x48 tile
    //Achieves a 4 to 3 ratio
    final int MAX_SCREEN_COL = 16;
    final int MAX_SCREEN_ROW = 12;
    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 pixels
    final int FPS = 60;

    
    //You need Thread for a game clock so that the program does not stop and wait for inputs but constantly runs
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    //set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        //Set the size of this class (JPanel)
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        //Buffering function, improves rendering. All drawing from this will be done offscreen
        this.setDoubleBuffered(true);
        //gamepanel can now get the key inputs
        this.addKeyListener(keyH);
        //gamepanel is focused to recieve key inputs
        this.setFocusable(true);
    }

    //Starting the Thread and passing gamePanel into Thread(this) by using this.
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Override run method is needed when Thread is started. Thread automatically calls upon a run();
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; //0.016666... seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
            //Will do 2 things in this loop. Nr 1 is UPDATE information such as character positions
            update();
            //Nr 2 is DRAW the screen with the UPDATED information
            repaint();
            delta--;
            drawCount++;
            }

            /* Counts FPS
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            } */
        }
    }
    public void update() {
        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
        } else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        } else if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }

    }
    public void paintComponent(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        super.paintComponent(g);
        //set graphics to be graphics 2d
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        //draw character for now
        g2.fillRect(playerX, playerY, TILE_SIZE, TILE_SIZE);
        //dispose it and release any system resources that its using
        g2.dispose();
    }
}
