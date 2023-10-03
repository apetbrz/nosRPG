package Mechanics;

import Enums.StatType;

import static Enums.StatType.*;

public class StatSheet {

    public Stat STR;
    public Stat DEX;
    public Stat CON;
    public Stat INT;
    public Stat WIS;
    public Stat CHA;
    public Stat AC;

    public StatSheet(){
        STR = new Stat(STRENGTH,10);
        DEX = new Stat(DEXTERITY,10);
        CON = new Stat(CONSTITUTION,10);
        INT = new Stat(INTELLIGENCE,10);
        WIS = new Stat(WISDOM,10);
        CHA = new Stat(CHARISMA,10);
        AC = new Stat(ARMOR_CLASS,10);
    }
    public Stat getStat(StatType statType){
        switch(statType){
            case STRENGTH:
                return STR;
            case DEXTERITY:
                return DEX;
            case CONSTITUTION:
                return CON;
            case INTELLIGENCE:
                return INT;
            case WISDOM:
                return WIS;
            case CHARISMA:
                return CHA;
            default:
                return AC;
        }
    }
    public int getStatValue(StatType statType){
        return getStat(statType).getValue();
    }
    public int getStatModifier(StatType statType){
        return getStat(statType).getModifier();
    }

}
