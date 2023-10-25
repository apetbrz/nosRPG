package Enums;

public enum Alliance {
    PARTY,
    MONSTER,
    NEUTRAL,
    FRIENDLY;
    private Alliance[] Allies;
    private Alliance[] Enemies;

    static{
        PARTY.Allies = new Alliance[]{PARTY,FRIENDLY};
        PARTY.Enemies = new Alliance[]{MONSTER};

        MONSTER.Allies = new Alliance[]{MONSTER};
        MONSTER.Enemies = new Alliance[]{PARTY,FRIENDLY};

        NEUTRAL.Allies = new Alliance[]{NEUTRAL};
        NEUTRAL.Enemies = new Alliance[]{};

        FRIENDLY.Allies = new Alliance[]{PARTY,FRIENDLY};
        FRIENDLY.Allies = new Alliance[]{MONSTER};
    }

    public Alliance[] getAllies() {
        return Allies;
    }
    public Alliance[] getEnemies() {
        return Enemies;
    }
}
