package Main;

import Enums.Direction;
import Mechanics.*;
import World.*;
import World.Creatures.Player;
import World.Creatures.Unit;

public class GameModel {
    //TODO: REWORK GAME MODEL TO WORK WITH WINDOW CONTROLS INSTEAD OF CONSOLE INPUT
    private static final int DEFAULT_DUNGEON_SIZE = 5;
    private GameMaster _gameMaster;
    private Dungeon _dungeon;
    private Room _currentRoom;
    private Player _player;
    private Combat _activeCombat;

    public GameModel(Dungeon newMap){
        _dungeon = newMap;
        _currentRoom = _dungeon.getSpawn();
        initializePlayer();
    }
    public GameModel(){
        this(DEFAULT_DUNGEON_SIZE);
    }
    public GameModel(int size){
        this(size,size);
    }
    public GameModel(int rows, int columns) {
        _dungeon = new Dungeon(rows, columns);
        _currentRoom = _dungeon.getSpawn();
        initializePlayer();
    }

    private void initializePlayer(){
        _player = new Player("Player");
        _currentRoom.addUnit(_player);
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
        /*if(checkForFight()){
            _activeCombat.fight();
        }*/
    }
    public void movePlayer(Direction direction){
        try {
            boolean success = _currentRoom.moveUnit(_player, direction);
            if(success) {
                _currentRoom = _currentRoom.getRoomInDirection(direction);
            }
        }catch(NullPointerException e){
            //null room, do nothing
        }
    }

    public Dungeon getDungeon() {
        return _dungeon;
    }

    public Player getPlayer(){
        return _player;
    }

    public Room getCurrentRoom() {
        return _currentRoom;
    }
}
