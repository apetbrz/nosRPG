package Enums;

public enum Direction {
    NORTH(0, -1, (byte)0b00001000),
    EAST(1, 0, (byte)0b00000100),
    SOUTH(0, 1, (byte)0b00000010),
    WEST(-1, 0, (byte)0b00000001);
    public int x;
    public int y;
    public byte directionByte;

    private Direction(int _x, int _y, byte b) {
        this.x = _x;
        this.y = _y;
        directionByte = b;
    }
}
