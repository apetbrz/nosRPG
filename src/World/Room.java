package World;

import Enums.Terrain;

public class Room {
    String _name;
    Terrain terrain;
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