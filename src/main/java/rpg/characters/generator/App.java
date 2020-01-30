package rpg.characters.generator;

import java.util.List;

public class App {
    public static void main(String[] args) {
        DiceRoller roller = new DiceRoller();
        List<Integer> attrs = roller.generate();
        Character elf = new Character("Elf", attrs);

        System.out.println(elf);
    }
}
