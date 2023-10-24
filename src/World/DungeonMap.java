package World;

import Enums.Direction;
import Lang.Toolbox;

/*
Rooms are stored in the array as such:

Room[x][y]:
     x
     0  1  2  3  4  5
y 0 [ ][ ][ ][ ][ ][ ]
  1 [ ][ ][ ][ ][ ][ ]
  2 [ ][ ][ ][ ][ ][ ]
  3 [ ][ ][ ][ ][ ][ ]
  4 [ ][ ][ ][ ][ ][ ]
  5 [ ][ ][ ][ ][ ][ ]
 */


public class DungeonMap {
    private static final Direction[] CONNECT_ALL = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
    private Room[][] _rooms;
    private int _rows;
    private int _columns;
    private Room _spawn;

    public DungeonMap(int rows, int columns){
        _rooms = new Room[rows][columns];
        _rows = rows;
        _columns = columns;
    }
    public DungeonMap(int size){
        this(size,size);
    }
    public DungeonMap(int rows, int columns, Room spawnRoom){
        this(rows, columns, spawnRoom, columns/2, rows/2);
    }
    public DungeonMap(int rows, int columns, Room spawnRoom, int spawnX, int spawnY){
        this(rows, columns);
        addRoom(spawnRoom, spawnX, spawnY);
        _spawn = spawnRoom;
    }
    public void addRoom(Room newRoom, int x, int y){
        this.addRoom(newRoom, x, y, CONNECT_ALL);
    }
    public void addRoom(Room newRoom, int x, int y, Direction[] connections){
        boolean isFirstRoom = this.isEmpty();
        _rooms[y][x] = newRoom;
        if(connections != null) {
            connectRoom(x, y, connections);
        }
        if(isFirstRoom){
            _spawn = _rooms[y][x];
        }
    }

    //connectRoom serves to link a room to its neighbors,
    //linkRoom is the direct 1:1 link that connectRoom uses
    public void connectRoom(int x, int y, Direction[] connections){
        for(Direction d : connections){
            linkRoom(x, y, d);
        }
    }
    public void connectRoom(int x, int y){
        this.connectRoom(x,y,CONNECT_ALL);
    }
    public void connectAll(){
        for(int r = 0; r < _rows; r++){
            for(int c = 0; c < _columns; c++){
                connectRoom(r, c);
            }
        }
    }
    public void linkRoom(int x, int y, Direction direction){
        try{
            int newX = x + direction.x;
            int newY = y + direction.y;
            _rooms[y][x].connectToRoom(_rooms[newY][newX], direction);
            System.out.println("" + _rooms[y][x] + " connected " + direction + " to " + _rooms[newY][newX]);
        }catch(NullPointerException e){
            //System.out.println("NULL ROOM " + direction + " AT " + x+direction._x + " " + y+direction._y + " FROM " + _rooms[y][x]);
        }catch(ArrayIndexOutOfBoundsException e){
            //attempted to access room that cannot exist
            //System.out.println("OOB ROOM " + direction + " AT " + x+direction._x + " " + y+direction._y + " FROM " + _rooms[y][x]);
            //TODO: replace 2D room array with some dynamic storage system???????? low priority
        }
    }

    public void fillRooms(Room[][] newRooms, int x1, int y1, int x2, int y2, boolean replace){
        boolean roomIsEmpty;
        for(int y = y1; y <= y2; y++){
            for(int x = x1; x <= x2; x++){
                try{
                    roomIsEmpty = _rooms[y][x] == null;
                }catch(ArrayIndexOutOfBoundsException e) {
                    //invalid room
                    continue;
                }
                try {
                    if(roomIsEmpty || replace) {
                        this.addRoom(newRooms[y-y1][x-x1],x,y,null);
                        Toolbox.print("adding room " + (newRooms[y-y1][x-x1])
                                + " (" + (x-x1) + "," + (y-y1) + ") to (" + x + "," + y + ")");
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    //newRooms too small
                }catch(NullPointerException e){
                    //empty newRoom
                }
            }
        }
        connectAll();
    }
    public boolean isEmpty(){
        for(Room[] row : _rooms){
            for(Room room : row){
                if(room != null){
                    return false;
                }
            }
        }
        return true;
    }
    public Room getSpawn() {
        return _spawn;
    }
    public void setSpawn(int x, int y){
        _spawn = _rooms[y][x];
    }
    public Room[][] getRooms() {
        return _rooms;
    }
}
