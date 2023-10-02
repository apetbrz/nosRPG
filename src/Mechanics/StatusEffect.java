package Mechanics;

public class StatusEffect {
    String name;
    StatusType type;
    int strength;
    int duration;
    Modifier[] modifier;

    public StatusEffect(){
        name = "DEFAULT_STATUS";
        type = StatusType.GENERIC;
        strength = 1;
        duration = -1;
        modifier = new Modifier[]{};
    }
    public StatusEffect(String n, StatusType t, int s, int d){
        name = n;
        type = t;
        strength = s;
        duration = d;
        modifier = new Modifier[]{};
    }
    public StatusEffect(String n, StatusType t, int s, int d, Modifier[] m){
        name = n;
        type = t;
        strength = s;
        duration = d;
        modifier = m;
    }
    private void apply(){

    }
    public void tick(){
        duration--;
    }
}
enum StatusType{
    GENERIC,
    BLEED,
    POISON,
    CURSE,
    BLESSING,
    PERMANENT;
}