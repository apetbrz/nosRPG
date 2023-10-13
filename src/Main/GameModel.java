package Main;

import Mechanics.*;
import World.*;
import World.Creatures.Player;
import World.Creatures.Unit;

public class GameModel {
    private Dungeon _dungeon;
    private static final int DEFAULT_DUNGEON_SIZE = 5;
    private Room _currentRoom;
    private Player _player;
    private int _playerX;
    private int _playerY;
    private Combat _activeCombat;

    public GameModel(){
        initializeMap(DEFAULT_DUNGEON_SIZE);
    }
    public GameModel(Dungeon newMap){
        _dungeon = newMap;
    }

    public void addPlayer(Player newPlayer){
        _player = newPlayer;
        _currentRoom.addUnit(_player);
    }
    public Player getPlayer(){
        return _player;
    }
    private void initializeMap(int size){
        _dungeon = new Dungeon(size);
        int[] spawn = _dungeon.getSpawn();
        _playerX = spawn[1];
        _playerY = spawn[0];
        _currentRoom = _dungeon.getRooms()[_playerY][_playerX];
    }

    public Dungeon getDungeon() {
        return _dungeon;
    }

    public Room getCurrentRoom() {
        return _currentRoom;
    }

    public Combat getActiveCombat() {
        return _activeCombat;
    }

    public boolean checkForFight(){     //returns true if combat found, false if nothing
        for(Unit testUnit : _currentRoom.getUnitsInRoom()){
            if(!testUnit.isPlayer()){
                _activeCombat = new Combat(_currentRoom.getUnitsInRoom().toArray(new Unit[0]));
                return true;
            }
        }
        _activeCombat = null;
        return false;
    }

    public void iterate(){
        //TODO: MAP MOVEMENT HERE
        if(checkForFight()){
            _activeCombat.fight();
        }
    }
}
