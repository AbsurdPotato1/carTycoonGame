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
        int entityLeftCol = (entityLeftWorldX)/gp.tileSize;
        int entityRightCol = (entityRightWorldX-1)/gp.tileSize;
        int entityTopRow = (entityTopWorldY)/gp.tileSize;
        int entityBottomRow = (entityBottomWorldY-1)/gp.tileSize;
        int tileNum1;
        // TODO: PROBLEM DISCOVERED HERE: leftcol / rightcol and toprow / bottomrow correspond incorrectly - movement incorrect on walls during multi-direction
        // DISCOVERY: GLOBAL VARIABLERS entityLeftCol, entityRightCol, entityTopRow, entityBottomRow are being reassigned during collision, thus issues occur.
        if(entity.direction[0]){ // up
            entityTopRow  = (int)((entityTopWorldY - entity.speedVert)/gp.tileSize);
            System.out.println("leftCol - rightCol: " + entityLeftCol + " " + entityRightCol);
            for(int i = entityLeftCol; i <= entityRightCol; i++){
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][i];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.upCollisionOn = true;
//                    entity.worldY = entity.worldY / gp.tileSize * gp.tileSize;
                    break;
                }
            }
        }
        entityTopRow = (entityTopWorldY)/gp.tileSize;
        if(entity.direction[1]){ // right
            entityRightCol  = (int)((entityRightWorldX + entity.speedHor)/gp.tileSize);
            System.out.println("topRow - BottomRow: " + entityTopRow + " " + entityBottomRow);
            for(int i = entityTopRow; i <= entityBottomRow; i++) {
                tileNum1 = gp.tileM.mapTileNum[i][entityRightCol];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.rightCollisionOn = true;
                    break;
                }
            }
        }
        entityRightCol = (entityRightWorldX-1)/gp.tileSize;
        if(entity.direction[3]){ // left
            entityLeftCol  = (int)((entityLeftWorldX - entity.speedHor)/gp.tileSize);
            System.out.println("topRow - BottomRow: " + entityTopRow + " " + entityBottomRow);
            for(int i = entityTopRow; i <= entityBottomRow; i++) {

                tileNum1 = gp.tileM.mapTileNum[i][entityLeftCol];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.leftCollisionOn = true;
                    break;
                }
            }
        }
        entityLeftCol = (entityLeftWorldX)/gp.tileSize;
        if(entity.direction[2]){ // bottom
            entityBottomRow  = (int)((entityBottomWorldY + entity.speedVert)/gp.tileSize); // - entity.curUpSpeed is because it will be negative - not positive like other variables.
            System.out.println("leftCol - rightCol: " + entityLeftCol + " " + entityRightCol);
            for(int i = entityLeftCol; i <= entityRightCol; i++){
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][i];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.downCollisionOn = true;
                    break;
                }
            }
        }
        entityBottomRow = (entityBottomWorldY-1)/gp.tileSize;

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
