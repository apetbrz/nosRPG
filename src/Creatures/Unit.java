package Creatures;

import Enums.*;
import Mechanics.*;
import Objects.*;

import java.util.ArrayList;

public class Unit {
    String name;
    String race;
    String description;
    Alliance team;
    int health;
    StatSheet stats;
    ArrayList<StatusEffect> statuses;
    Inventory inv;
    Item mainHand;
    Item offHand;

    public Unit(){
        name = "DEFAULT_UNIT";
        race = "DEFAULT_RACE";
        description = "DEFAULT_UNIT";
        team = Alliance.NEUTRAL;
        health = 10;
        stats = new StatSheet();
        inv = new Inventory();
        mainHand = null;
        offHand = null;
    }
    public Unit(String n, String r, String d, Alliance t, int h){
        name = n;
        race = r;
        description = d;
        team = t;
        health = h;
        stats = new StatSheet();
        inv = new Inventory();
        mainHand = null;
        offHand = null;
    }
    public Unit(String template){
        this();
    }

    public void attack(Unit target){
        if(mainHand == null){
            target.damage(1 + stats.STR.modifier());
        }
        else{
            target.damage(mainHand.damage());
            this.applyEffect(mainHand.attackEffectSelf());
            target.applyEffect(mainHand.attackEffectTarget());
        }
    }

    private void applyEffect(StatusEffect[] effectList) {
        for(StatusEffect effect : effectList){
            statuses.add(effect);
        }
    }

    public void damage(int value){
        health -= value;
    }
}
