package Mechanics;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class d {
    int num;
    public d(int n){
        num = n;
    }
    public int roll(){
        return ThreadLocalRandom.current().nextInt(1, num + 1);
    }
    public int roll(int count){
        return IntStream.range(0, count).map(i -> roll()).sum();
    }
}
