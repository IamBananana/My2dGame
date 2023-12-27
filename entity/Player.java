package entity;

import main.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    File f1 = new File("src/res/player/boy_up_1.png");
    File f2 = new File("src/res/player/boy_up_2.png");
    File f3 = new File("src/res/player/boy_down_1.png");
    File f4 = new File("src/res/player/boy_down_2.png");
    File f5 = new File("src/res/player/boy_left_1.png");
    File f6 = new File("src/res/player/boy_left_2.png");
    File f7 = new File("src/res/player/boy_right_1.png");
    File f8 = new File("src/res/player/boy_right_2.png");

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(f1);
            up2 = ImageIO.read(f2);
            down1 = ImageIO.read(f3);
            down2 = ImageIO.read(f4);
            left1 = ImageIO.read(f5);
            left2 = ImageIO.read(f6);
            right1 = ImageIO.read(f7);
            right2 = ImageIO.read(f8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        //this ensures that the sprite is not using its walking animation while standing still
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed == true) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed == true) {
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


    }
    public void draw(Graphics2D g2) {
        //g2.setColor(Color.white);
        //draw character for now
        //g2.fillRect(x, y, gp.TILE_SIZE, gp.TILE_SIZE);

        BufferedImage image = null;
        //find which direction facing and swap image to that direction
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;                                
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;                                
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;                                
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;                                
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            break;
        }
        //draw image at x y with same width and height, image observer is null
        g2.drawImage(image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
    }
}
