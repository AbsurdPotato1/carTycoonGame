package main;


import entity.Entity;
import object.SuperTool;
import tile_interactive.InteractiveTile;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = (int)entity.worldX + entity.solidArea.x;
        int entityRightWorldX = (int)entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = (int)entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = (int)entity.worldY + entity.solidArea.y + entity.solidArea.height;
        int entityLeftCol = (entityLeftWorldX)/GamePanel.tileSize;
        int entityRightCol = (entityRightWorldX-1)/GamePanel.tileSize;
        int entityTopRow = (entityTopWorldY)/GamePanel.tileSize;
        int entityBottomRow = (entityBottomWorldY-1)/GamePanel.tileSize;
        int tileNum1;
        if(entity.direction[0]){ // up
            entityTopRow  = (int)((entityTopWorldY - entity.speedVert)/GamePanel.tileSize);
//            System.out.println("leftCol - rightCol: " + entityLeftCol + " " + entityRightCol);
            for(int i = entityLeftCol; i <= entityRightCol; i++){
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][i];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.upCollisionOn = true;
//                    entity.worldY = entity.worldY / GamePanel.tileSize * GamePanel.tileSize;
                    break;
                }
            }
        }
        entityTopRow = (entityTopWorldY)/GamePanel.tileSize;
        if(entity.direction[1]){ // right
            entityRightCol  = (int)((entityRightWorldX + entity.speedHor)/GamePanel.tileSize);
//            System.out.println("topRow - BottomRow: " + entityTopRow + " " + entityBottomRow);
            for(int i = entityTopRow; i <= entityBottomRow; i++) {
                tileNum1 = gp.tileM.mapTileNum[i][entityRightCol];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.rightCollisionOn = true;
                    break;
                }
            }
        }
        entityRightCol = (entityRightWorldX-1)/GamePanel.tileSize;
        if(entity.direction[3]){ // left
            entityLeftCol  = (int)((entityLeftWorldX - entity.speedHor)/GamePanel.tileSize);
//            System.out.println("topRow - BottomRow: " + entityTopRow + " " + entityBottomRow);
            for(int i = entityTopRow; i <= entityBottomRow; i++) {

                tileNum1 = gp.tileM.mapTileNum[i][entityLeftCol];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.leftCollisionOn = true;
                    break;
                }
            }
        }
        entityLeftCol = (entityLeftWorldX)/GamePanel.tileSize;
        if(entity.direction[2]){ // bottom
            entityBottomRow  = (int)((entityBottomWorldY + entity.speedVert)/GamePanel.tileSize); // - entity.curUpSpeed is because it will be negative - not positive like other variables.
//            System.out.println("leftCol - rightCol: " + entityLeftCol + " " + entityRightCol);
            for(int i = entityLeftCol; i <= entityRightCol; i++){
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][i];
                if(gp.tileM.tile[tileNum1].collision) {
                    entity.downCollisionOn = true;
                    break;
                }
            }
        }
        entityBottomRow = (entityBottomWorldY-1)/GamePanel.tileSize;
    }
    public int checkObject(Entity entity, boolean player){ // args: entity - entity to check collision with; player - true if entity is player, false if other (NPC, etc.)
        int index = 99999;
        for(int i = 0; i < gp.obj.size(); i++){ // using for loop is technically inefficient - maybe do search pruning (only nearby objects) if performance is impacted
            entity.solidArea.x = (int)entity.worldX + entity.solidArea.x; // move entity solidArea temporarily
            entity.solidArea.y = (int)entity.worldY + entity.solidArea.y; // move entity solidArea temporarily

            gp.obj.get(i).solidArea.x = gp.obj.get(i).worldX + gp.obj.get(i).solidArea.x + gp.obj.get(i).collisionXOffset; // move obj solidArea temporarily
            gp.obj.get(i).solidArea.y = gp.obj.get(i).worldY + gp.obj.get(i).solidArea.y + gp.obj.get(i).collisionYOffset; // move obj solidArea temporarily

            if(entity.direction[0]) { // up
                entity.solidArea.y -= entity.speedVert; // move solidArea to next frame position temporarily
            }
            if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){ // if touching
                if(gp.obj.get(i).collision) { // if object is collide-able
                    entity.upCollisionOn = true;
                }
                if(player){
                    index = i; // return index of object that is collided with
                }
            }
            if(entity.direction[0]) {
                entity.solidArea.y += entity.speedVert; // reverting changes - works since speed DOES NOT change
            }
            if(entity.direction[1]) { // right
                entity.solidArea.x += entity.speedHor;
            }
            if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                if(gp.obj.get(i).collision) {
                    entity.rightCollisionOn = true;
                }
                if(player){
                    index = i;
                }
            }
            if(entity.direction[1]) {
                entity.solidArea.x -= entity.speedHor;
            }
            if(entity.direction[2]) { // down
                entity.solidArea.y += entity.speedVert;
            }
            if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                if(gp.obj.get(i).collision) {
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
            if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                if(gp.obj.get(i).collision) {
                    entity.leftCollisionOn = true;
                }
                if(player){
                    index = i;
                }
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.obj.get(i).solidArea.x = gp.obj.get(i).solidAreaDefaultX;
            gp.obj.get(i).solidArea.y = gp.obj.get(i).solidAreaDefaultY;
        }
        return index;
    }
    // NPC or Monsters
    public int checkEntity(Entity entity, Entity[] target){
        int index = 99999;
        for(int i = 0; i < target.length; i++){ // using for loop is technically inefficient - maybe do search pruning (only nearby objects) if performance is impacted
            if(target[i] != null){
                entity.solidArea.x = (int)entity.worldX + entity.solidArea.x; // move entity solidArea temporarily
                entity.solidArea.y = (int)entity.worldY + entity.solidArea.y; // move entity solidArea temporarily

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x; // move obj solidArea temporarily
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y; // move obj solidArea temporarily

                if(entity.direction[0]) { // up
                    entity.solidArea.y -= entity.speedVert; // move solidArea to next frame position temporarily
                }
                if(entity.solidArea.intersects(target[i].solidArea)){ // if touching
                    entity.upCollisionOn = true;
                    index = i;
                }
                if(entity.direction[0]) {
                    entity.solidArea.y += entity.speedVert; // reverting changes - works since speed DOES NOT change
                }
                if(entity.direction[1]) { // right
                    entity.solidArea.x += entity.speedHor;
                }
                if(entity.solidArea.intersects(target[i].solidArea)){
                    entity.rightCollisionOn = true;
                    index = i;
                }
                if(entity.direction[1]) {
                    entity.solidArea.x -= entity.speedHor;
                }
                if(entity.direction[2]) { // down
                    entity.solidArea.y += entity.speedVert;
                }
                if(entity.solidArea.intersects(target[i].solidArea)){
                    entity.downCollisionOn = true;
                    index = i;
                }
                if(entity.direction[2]) {
                    entity.solidArea.y -= entity.speedVert;
                }
                if(entity.direction[3]) { // left
                    entity.solidArea.x -= entity.speedHor;
                }
                if(entity.solidArea.intersects(target[i].solidArea)){
                    entity.leftCollisionOn = true;
                    index = i;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }

        return index;
    }
    public int checkTile(Entity entity, InteractiveTile[] target){
        int index = 99999;
        for(int i = 0; i < target.length; i++){ // using for loop is technically inefficient - maybe do search pruning (only nearby objects) if performance is impacted
            if(target[i] != null){
                entity.solidArea.x = (int)entity.worldX + entity.solidArea.x; // move entity solidArea temporarily
                entity.solidArea.y = (int)entity.worldY + entity.solidArea.y; // move entity solidArea temporarily

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x; // move obj solidArea temporarily
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y; // move obj solidArea temporarily

                if(entity.direction[0]) { // up
                    entity.solidArea.y -= entity.speedVert; // move solidArea to next frame position temporarily
                }
                if(entity.solidArea.intersects(target[i].solidArea)){ // if touching
                    entity.upCollisionOn = true;
                    index = i;
                }
                if(entity.direction[0]) {
                    entity.solidArea.y += entity.speedVert; // reverting changes - works since speed DOES NOT change
                }
                if(entity.direction[1]) { // right
                    entity.solidArea.x += entity.speedHor;
                }
                if(entity.solidArea.intersects(target[i].solidArea)){
                    entity.rightCollisionOn = true;
                    index = i;
                }
                if(entity.direction[1]) {
                    entity.solidArea.x -= entity.speedHor;
                }
                if(entity.direction[2]) { // down
                    entity.solidArea.y += entity.speedVert;
                }
                if(entity.solidArea.intersects(target[i].solidArea)){
                    entity.downCollisionOn = true;
                    index = i;
                }
                if(entity.direction[2]) {
                    entity.solidArea.y -= entity.speedVert;
                }
                if(entity.direction[3]) { // left
                    entity.solidArea.x -= entity.speedHor;
                }
                if(entity.solidArea.intersects(target[i].solidArea)){
                    entity.leftCollisionOn = true;
                    index = i;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }

        return index;
    }
    public int checkTool(Entity entity, boolean player){ // args: entity - entity to check collision with; player - true if entity is player, false if other (NPC, etc.)
        int index = 99999;
        for(int i = 0; i < gp.tools.size(); i++){ // using for loop is technically inefficient - maybe do search pruning (only nearby objects) if performance is impacted

            entity.solidArea.x = (int)entity.worldX + entity.solidArea.x; // move entity solidArea temporarily
            entity.solidArea.y = (int)entity.worldY + entity.solidArea.y; // move entity solidArea temporarily

            gp.tools.get(i).solidArea.x = gp.tools.get(i).worldX + gp.tools.get(i).solidArea.x + gp.tools.get(i).collisionXOffset; // move obj solidArea temporarily
            gp.tools.get(i).solidArea.y = gp.tools.get(i).worldY + gp.tools.get(i).solidArea.y + gp.tools.get(i).collisionYOffset; // move obj solidArea temporarily

            if(entity.direction[0]) { // up
                entity.solidArea.y -= entity.speedVert; // move solidArea to next frame position temporarily
            }
            if(entity.solidArea.intersects(gp.tools.get(i).solidArea)){ // if touching
                if(gp.tools.get(i).collision) { // if object is collide-able
                    entity.upCollisionOn = true;
                }
                if(player){
                    index = i; // return index of object that is collided with
                }
            }
            if(entity.direction[0]) {
                entity.solidArea.y += entity.speedVert; // reverting changes - works since speed DOES NOT change
            }
            if(entity.direction[1]) { // right
                entity.solidArea.x += entity.speedHor;
            }
            if(entity.solidArea.intersects(gp.tools.get(i).solidArea)){
                if(gp.tools.get(i).collision) {
                    entity.rightCollisionOn = true;
                }
                if(player){
                    index = i;
                }
            }
            if(entity.direction[1]) {
                entity.solidArea.x -= entity.speedHor;
            }
            if(entity.direction[2]) { // down
                entity.solidArea.y += entity.speedVert;
            }
            if(entity.solidArea.intersects(gp.tools.get(i).solidArea)){
                if(gp.tools.get(i).collision) {
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
            if(entity.solidArea.intersects(gp.tools.get(i).solidArea)){
                if(gp.tools.get(i).collision) {
                    entity.leftCollisionOn = true;
                }
                if(player){
                    index = i;
                }
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.tools.get(i).solidArea.x = gp.tools.get(i).solidAreaDefaultX;
            gp.tools.get(i).solidArea.y = gp.tools.get(i).solidAreaDefaultY;
        }

        return index;
    }
    public void checkPlayer(Entity entity){
        if(gp.player != null){
            entity.solidArea.x = (int)entity.worldX + entity.solidArea.x; // move entity solidArea temporarily
            entity.solidArea.y = (int)entity.worldY + entity.solidArea.y; // move entity solidArea temporarily

            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x; // move obj solidArea temporarily
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y; // move obj solidArea temporarily

            if(entity.direction[0]) { // up
                entity.solidArea.y -= entity.speedVert; // move solidArea to next frame position temporarily
            }
            if(entity.solidArea.intersects(gp.player.solidArea)){ // if touching
                entity.upCollisionOn = true;
            }
            if(entity.direction[0]) {
                entity.solidArea.y += entity.speedVert; // reverting changes - works since speed DOES NOT change
            }
            if(entity.direction[1]) { // right
                entity.solidArea.x += entity.speedHor;
            }
            if(entity.solidArea.intersects(gp.player.solidArea)){
                entity.rightCollisionOn = true;
            }
            if(entity.direction[1]) {
                entity.solidArea.x -= entity.speedHor;
            }
            if(entity.direction[2]) { // down
                entity.solidArea.y += entity.speedVert;
            }
            if(entity.solidArea.intersects(gp.player.solidArea)){
                entity.downCollisionOn = true;
            }
            if(entity.direction[2]) {
                entity.solidArea.y -= entity.speedVert;
            }
            if(entity.direction[3]) { // left
                entity.solidArea.x -= entity.speedHor;
            }
            if(entity.solidArea.intersects(gp.player.solidArea)){
                entity.leftCollisionOn = true;
            }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        }
    }
}
