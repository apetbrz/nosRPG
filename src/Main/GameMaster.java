package Main;

import Enums.Direction;
import Mechanics.WeaponAction;
import Mechanics.d;
import Objects.Equipment.Weapons.MeleeWeapon;
import World.Creatures.*;
import World.Dungeon;
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
        //TODO: THIS SHOULD HAPPEN ANY TIME ANYTHING HAPPENS

        _game.iterate();
        _renderer.update(this);
    }
    public void input(String command, Direction singleDirection){
        System.out.println("INPUT READ: " + command + " " + singleDirection);
        switch(command){
            case "move":
                _game.movePlayer(singleDirection);
        }
        iterate();
    }
    public void input(String command, byte[] vars){
        System.out.println("INPUT READ: " + command);
        iterate();
    }

    public Room[][] getMap(){
        return _game.getDungeon().getRawMap();
    }
    public Player getPlayer() {
        return _player;
    }
    public ArrayList<Unit> getVisibleUnits() {
        return _game.getCurrentRoom().getUnitsInRoom();
    }

    public String describeVisibleUnits() {
        StringBuilder output = new StringBuilder();
        for(Unit u : _game.getCurrentRoom().getUnitsInRoom()){
            output.append(u.toStringDetailed());
            output.append("\n\n");
        }
        return output.toString();
    }
}