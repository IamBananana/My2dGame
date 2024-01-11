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
        //arbitrary size, increase if add more tiles
        tile = new Tile[10];
        mapTileNum = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];
        getTileImage();
        loadMap("src/res/maps/world01.txt");
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

            file = new File("src/res/tiles/earth.png");
            fileStream = new FileInputStream(file);
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(fileStream);

            file = new File("src/res/tiles/tree.png");
            fileStream = new FileInputStream(file);
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(fileStream);

            file = new File("src/res/tiles/sand.png");
            fileStream = new FileInputStream(file);
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(fileStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            int col = 0;
            int row = 0;
            
            while (col < gp.MAX_WORLD_COL && row < gp.MAX_WORLD_ROW) {
                    int num = scanner.nextInt();
                    mapTileNum[col][row] = num;
                    col++;

                if (col == gp.MAX_WORLD_COL) {
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
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.MAX_WORLD_COL && worldRow < gp.MAX_WORLD_ROW) {
            int tileNum = mapTileNum[worldCol][worldRow];

            //First check worlds x and y by comparing it to the map and multiplying the tile size for each tile
            int worldX = worldCol * gp.TILE_SIZE;
            int worldY = worldRow * gp.TILE_SIZE;
            //Finds where on the screen we draw the tile after checking what world position its in
            int screenX = worldX - gp.player.worldX + gp.player.SCREEN_X;
            int screenY = worldY - gp.player.worldY + gp.player.SCREEN_Y;

            if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.SCREEN_X && 
                worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.SCREEN_X && 
                worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.SCREEN_Y && 
                worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.SCREEN_Y) {
                    
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);    
            }
            worldCol++;

            if (worldCol == gp.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
