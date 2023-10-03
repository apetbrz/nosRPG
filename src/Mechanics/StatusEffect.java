package Mechanics;

public class StatusEffect {
    String name;
    StatusType type;
    int strength;
    int duration;
    Modifier[] modifiers;

    public StatusEffect(){
        name = "GENERIC_STATUS";
        type = StatusType.GENERIC;
        strength = 1;
        duration = -1;
        modifiers = new Modifier[]{};
    }
    public StatusEffect(Modifier[] m){
        this();
        modifiers = m;
    }
    public Modifier[] getModifiers(){
        return modifiers;
    }
    public StatusEffect(String n, StatusType t, int s, int d){
        name = n;
        type = t;
        strength = s;
        duration = d;
        modifiers = new Modifier[]{};
    }
    public StatusEffect(String n, StatusType t, int s, int d, Modifier[] m){
        name = n;
        type = t;
        strength = s;
        duration = d;
        modifiers = m;
    }
    private void apply(){

    }
    public void tick(){
        duration--;
    }

    public int getDuration() {
        return duration;
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