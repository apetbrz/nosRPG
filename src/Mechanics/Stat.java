package Mechanics;

import Enums.StatType;

public class Stat{
    StatType name;
    int _base;
    int _bonus;
    int _modifier;
    public Stat(StatType statType){
        name = statType;
        _base = 0;
        _bonus = 0;
        _modifier = 0;
    }
    public Stat(StatType statType, int startingValue){
        name = statType;
        _base = startingValue;
        _bonus = 0;
        updateModifier();
    }
    private void updateModifier(){
        _modifier = (int)Math.floor(((_base + _bonus) - 10) / 2);
    }

    public int getValue(){
        return _base + _bonus;
    }
    public int getBase() {
        return _base;
    }
    public int getBonus() {
        return _bonus;
    }
    public int getModifier() {
        return _modifier;
    }

    public void add(int i) {
        _base += i;
    }
    public void set(int i){
        _base = i;
    }

    public void modify(int i){
        _bonus += i;
    }
}
