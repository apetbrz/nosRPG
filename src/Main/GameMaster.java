package Main;

import Enums.Command;
import Enums.Direction;
import Objects.Equipment.Weapons.MeleeWeapon;
import World.Creatures.*;
import World.Prefabs.PrefabDungeons;
import World.Prefabs.PrefabWeapons;
import World.Room;

import java.util.ArrayList;

public class GameMaster {
    GameModel _game;
    GameRenderer _renderer;
    Player _player;

    public GameMaster(){
        _game = new GameModel(PrefabDungeons.tutorialDungeon());
        _player = _game.getPlayer();
        _renderer = new GameRenderer(this);

        MeleeWeapon testSword = (MeleeWeapon)PrefabWeapons.generate("training sword");
        _game.getPlayer().equip(testSword);
    }
    public void play(){
        iterate();
    }
    public void iterate(){
        //THIS SHOULD HAPPEN ANY TIME ANYTHING HAPPENS

        _game.iterate();
        _renderer.update();
    }
    public void input(Command command, String data){
        System.out.println("INPUT READ: " + command + " " + data);
        switch(command){
            case MOVE:
                _game.movePlayer(parseDirection(data));
                break;
            case ATTACK:
                if(_game.isCombatActive()){
                    _renderer.promptUnitSelection(getCombatTargetCount());
                }
                break;
            case SELECTION:
                if(_game.isCombatActive()) _game.playerAttack(data);
        }
        iterate();
    }
    private Direction parseDirection(String data){
        switch(data.toLowerCase()){
            case "north":
                return Direction.NORTH;
            case "east":
                return Direction.EAST;
            case "south":
                return Direction.SOUTH;
            case "west":
                return Direction.WEST;
            default:
                return null;
        }
    }
    public Room[][] getMap(){
        return _game.getDungeon().getRawMap();
    }
    public Player getPlayer() {
        return _player;
    }
    public ArrayList<Unit> getVisibleUnits() {
        return _game.getUnitsInRoom();
    }
    public ArrayList<Unit> getCombatUnits(){
        return _game.getCombatUnits();
    }
    public int getCombatTargetCount(){
        ArrayList<Unit> unitsInCombat = getCombatUnits();
        if(unitsInCombat == null) return 0;
        else return unitsInCombat.size();
    }
    public String describeVisibleUnits() {
        StringBuilder output = new StringBuilder();
        for(Unit u : getVisibleUnits()){
            output.append(u.toStringDetailed());
            output.append("\n\n");
        }
        return output.toString();
    }
    public String describeCombatUnits(){
        StringBuilder output = new StringBuilder();
        int unitCount = 1;
        for(Unit u : getCombatUnits()){
            output.append(unitCount++);
            output.append(") ");
            output.append(u.toStringDetailed());
            output.append("\n\n");
        }
        return output.toString();
    }

    public String renderCombatInfo() {
        if(_game.isCombatActive()){
            return describeCombatUnits();
        }
        else{
            return describeVisibleUnits();
        }
    }
}