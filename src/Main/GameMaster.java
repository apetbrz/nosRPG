package Main;

import Enums.Direction;
import Mechanics.WeaponAction;
import Mechanics.d;
import Objects.Equipment.Weapons.MeleeWeapon;
import World.Creatures.*;
import World.Prefabs.PrefabDungeons;
import World.Prefabs.PrefabWeapons;
import World.Room;

public class GameMaster {
    GameModel _game;
    GameRenderer _renderer;
    Player _player;

    public GameMaster(){
        _game = new GameModel(PrefabDungeons.tutorialDungeon());
        _renderer = new GameRenderer(this);
        _player = _game.getPlayer();

        MeleeWeapon testSword = (MeleeWeapon)PrefabWeapons.generate("training sword");
        _game.getPlayer().equip(testSword);
        _game.getCurrentRoom().addUnit(new Unit("Dummy"));
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
}