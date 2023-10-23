package World;


public class Dungeon {
    private Room[][] _rooms;
    private int _rows;
    private int _columns;
    private int _spawnX;
    private int _spawnY;

    public Dungeon(int rows, int columns){
        _rooms = new Room[rows][columns];
        _rows = rows;
        _columns = columns;

        generateDungeon(_rows,_columns);
    }
    public Dungeon(int size){
        this(size,size);
    }

    private void generateDungeon(int rows, int columns){
        for(int i = 0; i < rows - 1; i++){
            for(int j = 0; j < columns - 1; j++){
                _rooms[i][j] = new Room();
            }
        }
        _spawnX = 0;
        _spawnY = 0;
    }

    public int[] getSpawn() {
        return new int[]{_spawnY,_spawnX};
    }

    public Room[][] getRooms() {
        return _rooms;
    }
}
