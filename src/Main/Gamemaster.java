package Main;

import Enums.Terrain;
import Mechanics.Combat;
import Mechanics.WeaponAction;
import Mechanics.d;
import Objects.Equipment.Weapons.MeleeWeapon;
import World.Creatures.*;

public class Gamemaster {
    GameModel _game;
    GameRenderer _renderer;
    Player _player;

    public Gamemaster(){
        _game = new GameModel();
        _renderer = new GameRenderer();
        _renderer.initialize(_game);
        _player = new Player("player");
        _game.addPlayer(_player);
        MeleeWeapon testSword = new MeleeWeapon("Training Sword",
                "A basic wooden sword, for training.",
                1,
                new WeaponAction[]{
                        new WeaponAction("Slash", new d[]{new d(6)}, 1),
                        new WeaponAction("Stab", new d[]{new d(8)}),
                        new WeaponAction("Hilt", new d[]{new d(4)}, 2)
                        });
        _game.getPlayer().equip(testSword);
        _game.getCurrentRoom().addUnit(new Unit("Dummy"));

    }
    public void play(){
        iterate();

    }
    public void iterate(){
        //TODO: MAP MOVEMENT HERE

        _game.iterate();
    }
}