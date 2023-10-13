package World;

public class Dungeon {
    private Room[][] _rooms;
    private int _spawnX;
    private int _spawnY;

    public Dungeon(int size){
        _rooms = new Room[size][size];
        generateDungeon(size);

    }

    private void generateDungeon(int size){
        for(int i = 0; i < size - 1; i++){
            for(int j = 0; j < size - 1; j++){
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
