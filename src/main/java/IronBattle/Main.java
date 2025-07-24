package IronBattle;

import IronBattle.models.*;
import IronBattle.utils.GameUtils;

import java.util.List;

public class Main {
    private static final Random random = new Random();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuePlaying = true;

        while (continuePlaying) {
            printMenu();
            int option = getValidatedIntInput("Enter choice (1-3): ", 1, 3);

            Character c1 = null, c2 = null;

            switch (option) {
                case 1:
                    c1 = createCharacterFromInput(prompt("Enter name for Character 1: "));
                    c2 = createCharacterFromInput(prompt("Enter name for Character 2: "));
                    break;

                case 2:
                    List<Character> imported = CharacterImporter.importFromCSV();
                    if (imported.size() < 2) {
                        System.out.println("❌ Not enough characters in CSV to start a battle.");
                        continue;
                    }
                    c1 = imported.get(0);
                    c2 = imported.get(1);
                    break;

                case 3:
                    c1 = createRandomCharacter("RandomHero1");
                    c2 = createRandomCharacter("RandomHero2");
                    break;
            }

            showInitialStats(c1, c2);
            BattleSimulator.fight(c1, c2);

            continuePlaying = askToRepeat();
        }

        System.out.println("👋 Exiting RPG Battle Simulator. Goodbye!");
    }
}
