package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
    int level;
    int maxLife;
    int life;
    int maxMana;
    int strength;
    int dexterity;
    int exp;
    int nextLevelExp;
    int coin;

    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();

    public DataStorage(){

    }
}
