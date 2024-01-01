package tile;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW];
        getTileImage();
        loadMap("src/res/maps/map01.txt");
    }
    public void getTileImage() {
        try {
            File file = new File("src/res/tiles/grass.png");
            FileInputStream fileStream = new FileInputStream(file);
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(fileStream);

            file = new File("src/res/tiles/wall.png");
            fileStream = new FileInputStream(file);
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(fileStream);

            file = new File("src/res/tiles/water.png");
            fileStream = new FileInputStream(file);
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(fileStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            int col = 0;
            int row = 0;
            
            while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
                    int num = scanner.nextInt();
                    mapTileNum[col][row] = num;
                    col++;

                if (col == gp.MAX_SCREEN_COL) {
                    col = 0;
                    row++;
                }
            }
            scanner.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);    
            col++;
            x += gp.TILE_SIZE;

            if (col == gp.MAX_SCREEN_COL) {
                col = 0;
                x = 0;
                row++;
                y += gp.TILE_SIZE;
            }
        }
    }
}
