package World.Creatures;

import Enums.Alliance;
import Enums.StatType;
import Mechanics.*;
import Objects.*;

import java.util.ArrayList;

public class Unit {
    String _name;
    String _race;
    String _description;
    Alliance _team;
    int _health;
    public StatSheet _stats;
    ArrayList<StatusEffect> _statuses;
    ArrayList<Modifier> _modifiers;
    Inventory _inv;
    Item _mainHand;
    Item _offHand;
    d d20 = new d(20);

    public Unit(){
        _name = "DEFAULT_UNIT";
        _race = "DEFAULT_RACE";
        _description = "DEFAULT_UNIT";
        _team = Alliance.NEUTRAL;
        _health = 10;
        _stats = new StatSheet();
        _statuses = new ArrayList<StatusEffect>();
        _modifiers = new ArrayList<Modifier>();
        _inv = new Inventory();
        _mainHand = null;
        _offHand = null;
    }
    public Unit(String name, String race, String desc, Alliance team, int health){
        _name = name;
        _race = race;
        _description = desc;
        _team = team;
        _health = health;
        _stats = new StatSheet();
        _inv = new Inventory();
        _mainHand = null;
        _offHand = null;
    }
    public Unit(String n){
        this();
        _name = n;
    }

    public void initializeStats(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, int armorClass){
        _stats.STR.set(strength);
        _stats.DEX.set(dexterity);
        _stats.CON.set(constitution);
        _stats.INT.set(intelligence);
        _stats.WIS.set(wisdom);
        _stats.CHA.set(charisma);
        _stats.AC.set(armorClass);
    }

    public void attackTarget(Unit target){
        if(_mainHand == null){
            target.damage(1 + _stats.STR.getModifier());
        }
        else{
            target.damage(_mainHand.damage());
            this.applyEffect(_mainHand.attackEffectSelf());
            target.applyEffect(_mainHand.attackEffectTarget());
        }
    }

    public void applyEffect(StatusEffect[] effectList) {
        for(StatusEffect effect : effectList){
            _statuses.add(effect);
            for(Modifier mod : effect.getModifiers()){
                _modifiers.add(mod);
            }
        }
        updateStats();
    }

    public void tickStatuses(){
        for(StatusEffect status : _statuses){
            status.tick();
            if(status.getDuration() < 1){
                _statuses.remove(status);
                for(Modifier mod : status.getModifiers()){
                    _modifiers.remove(mod);
                }
                updateStats();
            }
        }
    }
    public void updateStats(){
        for(Modifier mod : _modifiers){
            _stats.getStat(mod.getStatToAffect()).modify(mod.getValue());
        }
    }

    public void damage(int value){
        _health -= value;
    }

    public Stat getStat(StatType statType){
        return _stats.getStat(statType);
    }

    public int getModifier(StatType statType){
        return _stats.getStatModifier(statType);
    }

    public String toString(){
        return _name;
    }
}
