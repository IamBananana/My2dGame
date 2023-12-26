package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        //make window exit on close
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //cant resize
        window.setResizable(false);
        //set title
        window.setTitle("2D Game!");

        //gamepanel is a subclass of JPanel and we only want the gamepanel stuff
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        //causes this window to be sized to fit the preferred size and layouts of its subcomponents in GamePanel
        window.pack();

        //set location to be center
        window.setLocationRelativeTo(null);
        //set window to be visible
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
