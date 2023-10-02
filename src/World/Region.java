package World;

public class Region {
    private String _name;
    private Room[][] rooms;
    public Region(){
        rooms = new Room[5][5];
    }
    public Region(int s){
        rooms = new Room[s][s];
    }
    public void setLayout(Room[][] r){
        rooms = r;
    }
    public void setRooms(int x, int y, Room r){
        rooms[x][y] = r;
    }
    public Room[][] getRooms(){
        return rooms;
    }

    public String getName() {
        return _name;
    }
    public String toString(){
        return getName();
    }

}