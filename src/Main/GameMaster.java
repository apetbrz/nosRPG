package Main;

import Enums.Command;
import Enums.Direction;
import Lang.Toolbox;
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

    ArrayList<String> _uiOptions;
    int _selectionIndex = 0;
    private static final String SELECTION_ICON = "> ";

    public GameMaster(){
        _game = new GameModel(PrefabDungeons.tutorialDungeon());
        _player = _game.getPlayer();
        _renderer = new GameRenderer(this);
        _uiOptions = new ArrayList<String>();

        MeleeWeapon testSword = (MeleeWeapon)PrefabWeapons.generate("training sword");
        _game.getPlayer().equip(testSword);
    }
    public void play(){
        iterate();
    }
    public void iterate(){
        //THIS SHOULD HAPPEN ANY TIME ANYTHING HAPPENS

        _game.iterate();
        this.updateUIText();
        _renderer.update();
    }

    public void input(Command command, String data){
        System.out.println("INPUT READ: " + command + " " + data);
        if(command != Command.MOVE_CURSOR) _selectionIndex = 0;
        switch(command){
            case MOVE:
                _game.movePlayer(parseDirection(data));
                break;
            case ATTACK:
                if(_game.isCombatActive()) _game.playerAttack(""+_selectionIndex);
                else{
                    _game.forceCombat(_selectionIndex);
                }
                break;
            case SELECTION:
                break;
            case MOVE_CURSOR:
                _selectionIndex += Integer.parseInt(data);
                _selectionIndex %= _uiOptions.size();
                Toolbox.print("SELECTION INDEX MOVED, NOW: " + _selectionIndex);
                break;
        }
        iterate();
    }
    private void updateUIText(){
        if(_game.isCombatActive()){
            describeCombatUnits();
        }
        else{
            viewUnits();
        }
    }

    public void viewUnits(){
        _uiOptions.clear();
        for(Unit u : _game.getUnitsInRoom()){
            _uiOptions.add(u.toString());
        }
    }
    public void describeVisibleUnits() {
        _uiOptions.clear();
        for(Unit u : _game.getUnitsInRoom()){
            _uiOptions.add(u.toStringDetailed());
        }
    }
    public void describeCombatUnits(){
        _uiOptions.clear();
        for(Unit u : _game.getCombatUnits()){
            _uiOptions.add(u.toStringDetailed());
        }
    }
    public String getCombatPanelText() {
        if(_uiOptions == null) return "ERROR: NULL TEXT";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < _uiOptions.size(); i++){
            if(i == _selectionIndex){
                sb.append(SELECTION_ICON);
            }
            sb.append(_uiOptions.get(i));
            sb.append("\n");
        }

        return sb.toString();
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
}