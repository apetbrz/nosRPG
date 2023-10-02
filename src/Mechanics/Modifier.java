package Mechanics;

import Creatures.Unit;
import Enums.Stat;

public class Modifier {
    Stat stat;
    Unit host;

    public Modifier(Stat s, int v){
        stat = s;
        stat.bonus = v;
    }
}
