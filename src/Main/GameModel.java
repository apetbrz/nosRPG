package Main;

import Enums.Direction;
import Lang.Toolbox;
import Mechanics.*;
import World.*;
import World.Creatures.Player;
import World.Creatures.Unit;

import java.util.ArrayList;

public class GameModel {
    //TODO: REWORK GAME MODEL TO WORK WITH WINDOW CONTROLS INSTEAD OF CONSOLE INPUT
    private static final int DEFAULT_DUNGEON_SIZE = 5;
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
        see(_currentRoom,true);
    }

    //CALLED WHENEVER INPUT IS TAKEN

    public void iterate(){
        if(!isCombatActive()){
            Toolbox.print("COMBAT CHECK: " + checkForFight());
        }else{

        }
    }
    public void movePlayer(Direction direction){
        if(isCombatActive()){
            Toolbox.print("CANNOT MOVE, COMBAT IS ACTIVE");
            return;
        }
        try {
            boolean success = _currentRoom.moveUnit(_player, direction);
            if(success) {
                see(_currentRoom, false);
                _currentRoom = _currentRoom.getRoomInDirection(direction);
                see(_currentRoom, true);
            }
        }catch(NullPointerException e){
            //null room, do nothing
        }
    }
    public void playerAttack(String data) {
        _player.attackTarget(getCombatUnits().get(Integer.parseInt(data)));
        if(_activeCombat.checkCombatEnd()){
            Toolbox.print("COMBAT IS OVER");
            _activeCombat = null;
        }
    }

    public void see(Room room, boolean visibility){
        Direction[] directions = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
        Room iterator;
        for(Direction d : directions){
            iterator = room;
            while(iterator != null){
                iterator.setVisibility(visibility);
                iterator = iterator.getRoomInDirection(d);
            }
        }
    }
    public boolean checkForFight(){     //returns true if combat found, false if nothing
        for(Unit testUnit : _currentRoom.getUnitsInRoom()){
            if(testUnit.checkAggression(_player)){
                _activeCombat = new Combat(_currentRoom.getUnitsInRoom().toArray(new Unit[0]));
                return true;
            }
        }
        return false;
    }
    public boolean isCombatActive(){
        return _activeCombat != null;
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
    public ArrayList<Unit> getUnitsInRoom(){
        return _currentRoom.getUnitsInRoom();
    }

    public ArrayList<Unit> getCombatUnits() {
        if(isCombatActive()){
            return _activeCombat.getNonPartyMembers();
        }
        return null;
    }
}
