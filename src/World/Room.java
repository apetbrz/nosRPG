package World;

import Enums.Terrain;
import Lang.Toolbox;
import Objects.Interactable;
import World.Creatures.*;

import java.util.ArrayList;

public class Room {
    String _name;
    Terrain _terrain;
    ArrayList<Unit> _unitsInRoom;
    ArrayList<Interactable> _objectsInRoom;

    public Room(){
        _name = Toolbox.DEFAULT_TEXT+this.getClass().getName();
        _terrain = Terrain.DUNGEON;
        _unitsInRoom = new ArrayList<Unit>();
        _objectsInRoom = new ArrayList<Interactable>();
    }
    public Room(String s){
        _name = s;
        _terrain = Terrain.DUNGEON;
        _unitsInRoom = new ArrayList<Unit>();
        _objectsInRoom = new ArrayList<Interactable>();
    }

    public String getName() {
        return _name;
    }
    public String toString(){
        return getName();
    }

    public ArrayList<Unit> getUnitsInRoom() {
        return _unitsInRoom;
    }

    public void addUnit(Unit unit) {
        _unitsInRoom.add(unit);
    }
}