package Main;

import World.Creatures.*;
import Enums.PrefabRegion;

public class Gamemaster {
    GameModel game;
    Player player;
    public Gamemaster(){
        game = new GameModel();
        game.addRegion(PrefabRegion.TEST_AREA.reg);
        player = new Player("player");

    }
    public void play(){
        Unit dummy1 = new Unit();
        Unit dummy2 = new Unit();
        Unit dummy3 = new Unit();
        Combat testFight = new Combat(new Unit[]{player, dummy1, dummy2, dummy3});
        System.out.println(testFight);
        System.out.println(player._stats.DEX.getBase());
        System.out.println(dummy1._stats.DEX.getBase());
        player._stats.DEX.add(1);
        System.out.println(player._stats.DEX.getBase());
        System.out.println(dummy1._stats.DEX.getBase());

    }

}