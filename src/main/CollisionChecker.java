package main;


import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
//        System.out.println(entityTopWorldY);
        int entityLeftCol = (entityLeftWorldX)/gp.tileSize;
        int entityRightCol = (entityRightWorldX-1)/gp.tileSize;
        int entityTopRow = (entityTopWorldY)/gp.tileSize;
        int entityBottomRow = (entityBottomWorldY-1)/gp.tileSize;
//        System.out.println(entityTopRow);
        int tileNum1;
        if(entity.direction[0]){ // up
            entityTopRow  = (int)((entityTopWorldY - entity.speedVert)/gp.tileSize);
            for(int i = entityLeftCol; i <= entityRightCol; i++){
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][i];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.upCollisionOn = true;
                    entity.worldY = entity.worldY / gp.tileSize * gp.tileSize;
                    break;
                }
            }
        }
        if(entity.direction[1]){ // right
            entityRightCol  = (int)((entityRightWorldX + entity.speedHor)/gp.tileSize);
            for(int i = entityTopRow; i <= entityBottomRow; i++) {
                tileNum1 = gp.tileM.mapTileNum[i][entityRightCol];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.rightCollisionOn = true;
//                    entity.worldX = ((int)(entity.worldX + 898 / 4) / gp.tileSize +1) * gp.tileSize - 898 / 4 - 3;
                    break;
                }
            }
        }
        if(entity.direction[3]){ // left
            entityLeftCol  = (int)((entityLeftWorldX - entity.speedHor)/gp.tileSize);
            for(int i = entityTopRow; i <= entityBottomRow; i++) {
                tileNum1 = gp.tileM.mapTileNum[i][entityLeftCol];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.leftCollisionOn = true;
                    break;
                }
            }
        }
        if(entity.direction[2]){ // bottom
            entityBottomRow  = (int)((entityBottomWorldY + entity.speedVert)/gp.tileSize); // - entity.curUpSpeed is because it will be negative - not positive like other variables.
            for(int i = entityLeftCol; i <= entityRightCol; i++){
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][i];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.downCollisionOn = true;
//                    entity.worldY = ((int)(entity.worldY) / gp.tileSize + 1) * gp.tileSize ;
//                    entity.curUpSpeed = 0;
//                    entity.jumping = false;
                    break;
                }
            }
        }

//        switch(entity.direction){
//            case "up":
//                entityTopRow  = (entityTopWorldY - entity.speedVert)/gp.tileSize;
//                for(int i = entityLeftCol; i <= entityRightCol; i++){
//                    tileNum1 = gp.tileM.mapTileNum[entityTopRow][i];
//                    if(gp.tileM.tile[tileNum1].collision) {
//                        entity.collisionOn = true;
//                        break;
//                    }
//                }
////                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
////                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
////                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
////                    entity.collisionOn = true;
//                break;
//            case "down":
//                entityBottomRow  = (entityBottomWorldY + entity.speedVert)/gp.tileSize;
//                for(int i = entityLeftCol; i <= entityRightCol; i++){
//                    tileNum1 = gp.tileM.mapTileNum[entityBottomRow][i];
//                    if(gp.tileM.tile[tileNum1].collision) {
//                        entity.collisionOn = true;
//                        break;
//                    }
//                }
////                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
////                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
////                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
////                    entity.collisionOn = true;
//                break;
//            case "left":
//                entityLeftCol  = (entityLeftWorldX - entity.speedHor)/gp.tileSize;
//                for(int i = entityTopRow; i <= entityBottomRow; i++) {
//                    tileNum1 = gp.tileM.mapTileNum[i][entityLeftCol];
//                    if(gp.tileM.tile[tileNum1].collision) {
//                        entity.collisionOn = true;
//                        break;
//                    }
//                }
////                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
////                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
////                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
////                    entity.collisionOn = true;
//                break;
//            case "right":
//                entityRightCol  = (entityRightWorldX + entity.speedHor)/gp.tileSize;
//                for(int i = entityTopRow; i <= entityBottomRow; i++) {
//                    tileNum1 = gp.tileM.mapTileNum[i][entityRightCol];
//                    if(gp.tileM.tile[tileNum1].collision) {
//                        entity.collisionOn = true;
//                        break;
//                    }
//                }
////                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
////                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
////                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
////                    entity.collisionOn = true;
//                break;
//        }
    }
}
