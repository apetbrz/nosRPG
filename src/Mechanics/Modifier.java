package Mechanics;

import World.Creatures.Unit;
import Enums.StatType;

public class Modifier {
    StatType _statToAffect;
    int _value;
    Unit host;
    StatusEffect _hostStatus;

    public Modifier(StatType statType, int amount, Unit affectedUnit, StatusEffect source){
        _statToAffect = statType;
        _value = amount;
        host = affectedUnit;
        _hostStatus = source;
    }

    public StatType getStatToAffect() {
        return _statToAffect;
    }

    public int getValue() {
        return _value;
    }

    public StatusEffect getHostStatus() {
        return _hostStatus;
    }
}
