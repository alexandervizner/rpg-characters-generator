package rpg.characters.generator;

import java.util.Arrays;

public class Die implements Comparable<Die> {
    /*
        Container type that intended to store physical characteristics of the die
        as well as its value once it will be rolled.
     */

    private final static int D4 = 4;
    private final static int D6 = 6;
    private final static int D8 = 8;
    private final static int D10 = 10;
    private final static int D12 = 12;
    private final static int D20 = 20;
    private final static int[] VALID_DICE_TYPES = {D4, D6, D8, D10, D12, D20};

    private int faces;
    private int value;

    public Die() {
        this(D6);
    }

    public Die(int faces) {
        if (Arrays.stream(VALID_DICE_TYPES).noneMatch(i -> i == faces)) {
            throw new IllegalArgumentException();
        }

        this.faces = faces;
    }

    public int getFaces() {
        return this.faces;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Die die) {
        return (this.value != die.value) ? Integer.compare(this.value, die.value) : 0;
    }
}