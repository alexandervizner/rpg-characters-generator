package rpg.characters.generator;

import java.util.*;

class DiceRoller {
    /*
        Main class which does all the magic of random attributes generation.
     */

    private final static int DEFAULT_DICE_NUMBER = 4;

    private Die[] dicePool;
    private Random random;

    public DiceRoller() {
        this(DEFAULT_DICE_NUMBER);
    }

    public DiceRoller(int diceNumber) {
        this.dicePool = new Die[diceNumber];
        random = new Random();
    }

    public Die[] getDicePool() {
        return dicePool;
    }

    public void roll() {
        for (int i = 0; i < dicePool.length; i++) {
            dicePool[i] = new Die(Die.D6, random.nextInt(6) + 1);
        }
    }

    public List<Integer> getAttributes() {
        List<Integer> attributes = new ArrayList<>();

        while (attributes.size() < Character.ATTRS_NUMBER) {
            roll();
            attributes.add(getSumDiscardingLowest(dicePool));
        }

        return attributes;
    }

    public List<Integer> generate() {
        List<Integer> attributes = new ArrayList<>();

        while (!isMeetRequirements(attributes)) {
            attributes = getAttributes();
        }

        return attributes;
    }

    public int getSumDiscardingLowest(Die[] dice) {
        Arrays.sort(dice);

        return Arrays.stream(dice, 1, dice.length).mapToInt(Die::getValue).sum();
    }

    public boolean isMeetRequirements(List<Integer> attributes) {
        int fifteenCount = 0;
        int total = 0;

        for (Integer attr : attributes) {
            if (attr >= Constants.MIN_REQUIRED_VALUE) {
                fifteenCount++;
            }

            total += attr;
        }

        return fifteenCount >= Constants.MIN_REQUIRED_VALUES_COUNT
                && total >= Constants.MIN_REQUIRED_CHARACTER_ATTRS_SUM;
    }
}
