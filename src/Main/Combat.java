package Main;

import Creatures.Unit;
import Mechanics.d;

import java.util.ArrayList;

public class Combat {
    ArrayList<Unit> units;
    d initiative = new d(20);
    public Combat(Unit[] unitList){
        int[] rolls = new int[unitList.length];
        for(int i = 0; i < unitList.length; i++){
            rolls[i] = initiative.roll();
        }
    }
}
