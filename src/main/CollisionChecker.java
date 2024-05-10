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
    public int checkObject(Entity entity, boolean player){ // args: entity - entity to check collision with; player - true if entity is player, false if other (NPC, etc.)
        int index = 999;
        for(int i = 0; i < gp.obj.length; i++){ // using for loop is technically inefficient - maybe do search pruning (only nearby objects) if performance is impacted
            if(gp.obj[i] != null){
                entity.solidArea.x = entity.worldX + entity.solidArea.x; // move entity solidArea temporarily
                entity.solidArea.y = entity.worldY + entity.solidArea.y; // move entity solidArea temporarily

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x; // move obj solidArea temporarily
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y; // move obj solidArea temporarily

                if(entity.direction[0]) { // up
                    entity.solidArea.y -= entity.speedVert; // move solidArea to next frame position temporarily
                }
                if(entity.solidArea.intersects(gp.obj[i].solidArea)){ // if touching
                    if(gp.obj[i].collision) { // if object is collide-able
                        entity.upCollisionOn = true;
                    }
                    if(player){
                        index = i; // return index of object that is collided with
                    }
                }
                if(entity.direction[0]) {
                    entity.solidArea.y += entity.speedVert; // reverting changes -- only works if speed DOES NOT change
                }
                if(entity.direction[1]) { // right
                    entity.solidArea.x += entity.speedHor;
                }
                if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                    if(gp.obj[i].collision) {
                        entity.rightCollisionOn = true;
                    }
                    if(player){
                        index = i;
                    }
                }
                if(entity.direction[0]) {
                    entity.solidArea.x -= entity.speedHor;
                }
                if(entity.direction[2]) { // down
                    entity.solidArea.y += entity.speedVert;
                }
                if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                    if(gp.obj[i].collision) {
                        entity.downCollisionOn = true;
                    }
                    if(player){
                        index = i;
                    }
                }
                if(entity.direction[2]) {
                    entity.solidArea.y -= entity.speedVert;
                }
                if(entity.direction[3]) { // left
                    entity.solidArea.x -= entity.speedHor;
                }
                if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                    if(gp.obj[i].collision) {
                        entity.leftCollisionOn = true;
                    }
                    if(player){
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
