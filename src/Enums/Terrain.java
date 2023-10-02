package Enums;

public enum Terrain {
    FIELD,
    FOREST,
    SWAMP,
    DUNGEON,
    DUNGEON_SEWER;
    public boolean difficult = false;
    public LightLevel lightLevel = LightLevel.LIT;
}
enum LightLevel {
    DAYLIGHT,
    LIT,
    DARK,
    PITCH_BLACK;
}