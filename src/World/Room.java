package World;

import Enums.Terrain;
import World.Creatures.*;

import java.util.ArrayList;

public class Room {
    String _name;
    Terrain terrain;
    ArrayList<Unit> unitsInRoom;

    public Room(String s){
        _name = s;
        terrain = Terrain.DUNGEON;
    }

    public String getName() {
        return _name;
    }
    public String toString(){
        return getName();
    }
}