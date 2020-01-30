package rpg.characters.generator;

import java.util.Arrays;

public class Die implements Comparable<Die> {
    /*
        Container type that intended to store physical characteristics of the die
        as well as its value once it will be rolled.
     */

    public final static int D4 = 4;
    public final static int D6 = 6;
    public final static int D8 = 8;
    public final static int D10 = 10;
    public final static int D12 = 12;
    public final static int D20 = 20;
    public final static int[] VALID_DICE_TYPES = {D4, D6, D8, D10, D12, D20};

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

    public Die(int faces, int value) {
        this(faces);

        if (value < 1 && value > faces) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public int getFaces() {
        return this.faces;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public int compareTo(Die die) {
        return (this.value != die.value) ? Integer.compare(this.value, die.value) : 0;
    }
}