package Enums;

public enum Stat {
    STRENGTH,
    DEXTERITY,
    CONSTITUTION,
    INTELLIGENCE,
    WISDOM,
    CHARISMA,
    ARMOR_CLASS,
    ATTACK,
    SPELL_ATTACK;

    public int base;
    public int bonus;
    public int modifier(){
        return (int)Math.floor(((base + bonus) - 10) / 2);
    }
}
