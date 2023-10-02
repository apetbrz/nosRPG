package Mechanics;

import Enums.Stat;

public class StatSheet {

    public Stat STR = Stat.STRENGTH;
    public Stat DEX = Stat.DEXTERITY;
    public Stat CON = Stat.CONSTITUTION;
    public Stat INT = Stat.INTELLIGENCE;
    public Stat WIS = Stat.WISDOM;
    public Stat CHA = Stat.CHARISMA;
    public Stat AC = Stat.ARMOR_CLASS;

    public StatSheet(){
        STR.base = 10;
        DEX.base = 10;
        CON.base = 10;
        INT.base = 10;
        WIS.base = 10;
        CHA.base = 10;
        AC.base = 10;
    }

}
