package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];

        getTileImage();
        loadMap("maps/map02.txt");
    }

    public void getTileImage(){

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/ground.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/earth.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String FilePath){
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(FilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                String numbers[] = line.split(" ");
                while(col < gp.maxWorldCol){
//                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[row][col] = num;
                    col++;

                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
//        int x = 0;
//        int y = 0;
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldRow][worldCol];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - (int)gp.player.worldX + gp.player.screenX;
            int screenY = worldY - (int)gp.player.worldY + gp.player.screenY;

            if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
               worldX < gp.player.worldX + gp.screenWidth - gp.player.screenX &&
               worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
               worldY < gp.player.worldY + gp.screenHeight - gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }

}

//package tile;
//
//import main.GamePanel;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//public class TileManager {
//    GamePanel gp;
//    Tile[] tile;
//    int mapTileNum[][];
//
//    public TileManager(GamePanel gp){
//
//        this.gp = gp;
//
//        tile = new Tile[10];
//        mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];
//
//        getTileImage();
//        loadMap();
//    }
//
//    public void getTileImage(){
//
//        try {
//
//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/grass.png"));
//
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/wall.png"));
//
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/water.png"));
//
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    public void loadMap(){
//        try {
//            InputStream is = getClass().getClassLoader().getResourceAsStream("/maps/map01.txt");
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            int col = 0;
//            int row = 0;
//            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
//                String line = br.readLine();
//                while(col < gp.maxScreenCol){
//                    String numbers[] = line.split(" ");
//
//                    int num = Integer.parseInt(numbers[col]);
//
//                    mapTileNum[row][col] = num;
//                    col++;
//
//                }
//                if(col == gp.maxScreenCol){
//                    col = 0;
//                    row++;
//                }
//            }
//            br.close();
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void draw(Graphics2D g2){
//        int col = 0;
//        int row = 0;
//        int x = 0;
//        int y = 0;
//        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
//
//            int tileNum = mapTileNum[row][col];
//            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
//            col++;
//            x += gp.tileSize;
//
//            if(col == gp.maxScreenCol){
//                col = 0;
//                x = 0;
//                row++;
//                y += gp.tileSize;
//            }
//
//        }
//    }
//
//}
