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
        if(entity.direction[0]){ // up
            entityTopRow  = (int)((entityTopWorldY - entity.speedVert)/gp.tileSize);
//            System.out.println("leftCol - rightCol: " + entityLeftCol + " " + entityRightCol);
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
//            System.out.println("topRow - BottomRow: " + entityTopRow + " " + entityBottomRow);
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
//            System.out.println("topRow - BottomRow: " + entityTopRow + " " + entityBottomRow);
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
//            System.out.println("leftCol - rightCol: " + entityLeftCol + " " + entityRightCol);
            for(int i = entityLeftCol; i <= entityRightCol; i++){
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][i];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.downCollisionOn = true;
                    break;
                }
            }
        }
        entityBottomRow = (entityBottomWorldY-1)/gp.tileSize;
    }
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        // BUG: somehow clipped into wall traveling diagonal at corner
        for(int i = 0; i < gp.obj.length; i++){ // using for loop is technically inefficient - maybe do search pruning (only nearby objects) if performance is impacted
            if(gp.obj[i] != null){
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                if(entity.direction[0]){ // up
                    entity.solidArea.y -= entity.speedVert;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision) {

                            entity.upCollisionOn = true;
                        }
                        if(player){
                            index = i;
                        }
                    }
                    entity.solidArea.y = entity.worldY + entity.solidArea.y; // reverting changes in order for multi-directional movement.
                    // Could also be done by doing: entity.solidArea.y += entity.speedVert; IF SPEED DOES NOT CHANGE
                }
//                entity.solidArea.y += entity.speedVert;
                if(entity.direction[1]){ // right
                    entity.solidArea.x += entity.speedHor;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision) {
                            entity.rightCollisionOn = true;
                        }
                        if(player){
                            index = i;
                        }
//                        System.out.println("right collision");
                    }
                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                }
//                entity.solidArea.x = entity.solidAreaDefaultX;
                if(entity.direction[2]){ // down
                    entity.solidArea.y += entity.speedVert;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision) {
                            entity.downCollisionOn = true;
                        }
                        if(player){
                            index = i;
                        }
//                        System.out.println("down collision");
                    }
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;
                }
//                entity.solidArea.y = entity.solidAreaDefaultY;
                if(entity.direction[3]){ // left
                    entity.solidArea.x -= entity.speedHor;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision) {
                            entity.leftCollisionOn = true;
                        }
                        if(player){
                            index = i;
                        }
//                        System.out.println("left collision");
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
//                System.out.println(gp.obj[i].solidArea.x + " " + gp.obj[i].solidArea.y);
//                System.out.println(entity.solidArea.x + " " + entity.solidArea.y);
            }
        }

        return index;
    }
}
