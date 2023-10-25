package World;

import Enums.Direction;
import Enums.Terrain;
import Objects.Interactable;
import World.Creatures.*;

import java.util.ArrayList;

public class Room {
    private String _name;
    private Terrain _terrain;
    private ArrayList<Unit> _unitsInRoom;
    private ArrayList<Interactable> _objectsInRoom;
    private byte connections;
    //the 8 bits in connections store information about adjacent rooms:
    //the rightmost 4 (----XXXX) store if a room is accessible in that cardinal direction (by order of NESW)
    //the leftmost 4 (XXXX----) store if a room in that direction is locked (by order of NESW)
    //if a room in a direction is locked, it needs to be unlocked before the player can access it
    //if a room in a direction is unlocked, the player can freely travel in that direction
    //if a direction is locked, but without a room, something went wrong lmao
    private Room _northRoom;
    private Room _eastRoom;
    private Room _southRoom;
    private Room _westRoom;
    private boolean _visible;
    private boolean _discovered;

    public Room(){
        this("DEFAULT_ROOM");
    }
    public Room(String s){
        _name = s;
        _terrain = Terrain.DUNGEON;
        _unitsInRoom = new ArrayList<Unit>();
        _objectsInRoom = new ArrayList<Interactable>();
        connections = 0;
        _visible = false;
    }

    public boolean moveUnit(Unit unit, Direction direction){
        if(!_unitsInRoom.contains(unit)){
            return false;
        }
        try {
            if((connections & direction.directionByte) != 0) {      //if movement is allowed
                this.getRoomInDirection(direction).addUnit(unit);
                this.removeUnit(unit);
                System.out.println("UNIT:" + unit + " MOVED " + direction + " TO " + this.getRoomInDirection(direction));
                return true;
            }else{
                System.out.println("UNIT:" + unit + " FAILED MOVE " + direction + " TO " + this.getRoomInDirection(direction));
                return false;
            }
        }catch(NullPointerException e){
            //invalid room, cannot move
            System.out.println("UNIT:" + unit + " FAILED MOVE " + direction + " TO NULL ROOM");
            return false;
        }
    }
    public void addUnit(Unit unit) {
        _unitsInRoom.add(unit);
    }
    private void removeUnit(Unit unit) {
        _unitsInRoom.remove(unit);
    }

    public void connectToRoom(Room newRoom, Direction d){
        switch(d){
            case NORTH:
                setNorthRoom(newRoom, true);
                break;
            case EAST:
                setEastRoom(newRoom, true);
                break;
            case SOUTH:
                setSouthRoom(newRoom, true);
                break;
            case WEST:
                setWestRoom(newRoom, true);
                break;
        }
    }
    public boolean hasPlayer() {
        for(Unit unit : _unitsInRoom){
            if(unit.isPlayer()){
                return true;
            }
        }
        return false;
    }

    //these should never get passed a null room !!!
    //TODO: ^^fix that lmao

    public void setNorthRoom(Room northRoom, boolean connectBack) {
        _northRoom = northRoom;
        connections |= Direction.NORTH.directionByte;
        if(connectBack){
            _northRoom.setSouthRoom(this, false);
        }
    }
    public void setEastRoom(Room eastRoom, boolean connectBack) {
        _eastRoom = eastRoom;
        connections |= Direction.EAST.directionByte;
        if(connectBack){
            _eastRoom.setWestRoom(this, false);
        }
    }
    public void setSouthRoom(Room southRoom, boolean connectBack) {
        _southRoom = southRoom;
        connections |= Direction.SOUTH.directionByte;
        if(connectBack){
            _southRoom.setNorthRoom(this, false);
        }
    }
    public void setWestRoom(Room westRoom, boolean connectBack) {
        _westRoom = westRoom;
        connections |= Direction.WEST.directionByte;
        if(connectBack){
            _westRoom.setEastRoom(this, false);
        }
    }
    public Room getNorthRoom() {
        return _northRoom;
    }
    public Room getEastRoom() {
        return _eastRoom;
    }
    public Room getSouthRoom() {
        return _southRoom;
    }
    public Room getWestRoom() {
        return _westRoom;
    }
    public Room getRoomInDirection(Direction d){
        switch(d){
            case NORTH:
                return getNorthRoom();
            case EAST:
                return getEastRoom();
            case SOUTH:
                return getSouthRoom();
            case WEST:
                return getWestRoom();
            default:
                return null;
        }
    }

    public String getName() {
        return _name;
    }
    public Terrain getTerrain() {
        return _terrain;
    }
    public ArrayList<Unit> getUnitsInRoom() {
        return _unitsInRoom;
    }
    public ArrayList<Interactable> getObjectsInRoom(){
        return _objectsInRoom;
    }
    public byte getConnections() {
        return connections;
    }

    public void setVisibility(boolean visible) {
        _visible = visible;
        if(_visible && !_discovered){
            _discovered = true;
        }
    }
    public boolean isDiscovered() {
        return _discovered;
    }
    public boolean isVisible() {
        return _visible;
    }

    public String toString(){
        return getName();
    }
}