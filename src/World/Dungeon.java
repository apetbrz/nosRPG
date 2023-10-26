package World;


import Enums.Direction;
import World.Creatures.Player;
import World.Creatures.Unit;
import World.Prefabs.RoomGenerator;

public class Dungeon {
    private String _name;
    private DungeonMap _dungeonMap;
    private RoomGenerator _generator;

    public Dungeon(int rows, int columns){
        this(rows,columns,true);
    }
    public Dungeon(int rows, int columns, boolean generate){
        _dungeonMap = new DungeonMap(rows, columns);
        _generator = new RoomGenerator(1);
        if(generate){
            Room[][] generatedDungeon = _generator.generate(rows,columns);
            fillRooms(generatedDungeon,0,0,columns,rows,false);
        }
    }

    public void addRoom(Room newRoom){
        this.addRoom(newRoom, 0, 0);
    }
    public void addRoom(Room newRoom, int x, int y){
        _dungeonMap.addRoom(newRoom, x, y);
    }
    public void addRoom(Room newRoom, int x, int y, Direction[] directions){
        _dungeonMap.addRoom(newRoom, x, y, directions);
    }
    public void fillRooms(Room[][] newRooms, int x1, int y1, int x2, int y2, boolean replace){
        _dungeonMap.fillRooms(newRooms,x1,y1,x2,y2,replace);
    }
    public void fillRooms(Room[][] newRooms, int x1, int y1, int x2, int y2){
        _dungeonMap.fillRooms(newRooms,x1,y1,x2,y2,false);
    }
    public void connectRooms(int x, int y, Direction[] directions){
        _dungeonMap.connectRoom(x,y,directions);
    }

    public void addUnit(Unit unit, int x, int y){
        _dungeonMap.addUnit(unit, x, y);
    }
    public Room[][] getRawMap() {
        return _dungeonMap.getRooms();
    }
    public void setSpawn(int x, int y) {
        _dungeonMap.setSpawn(x,y);
    }
    public Room getSpawn(){
        return _dungeonMap.getSpawn();
    }
    public void setName(String name) {
        _name = name;
    }
    public String getName() {
        return _name;
    }
}