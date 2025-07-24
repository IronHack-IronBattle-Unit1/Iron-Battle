package IronBattle;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

    private static void printMenu() {
        System.out.println("\n=== RPG Battle Simulator ===");
        System.out.println("Choose an option:");
        System.out.println("1. Manual character creation");
        System.out.println("2. Import characters from CSV");
        System.out.println("3. Auto-generate and simulate battle");
    }

    private static String prompt(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    private static int getValidatedIntInput(String message, int min, int max) {
        int input = -1;
        while (input < min || input > max) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                sc.nextLine(); // clear buffer
            } else {
                System.out.println("⚠️ Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
        return input;
    }

    private static Character createCharacterFromInput(String name) {
        int choice = getValidatedIntInput("Select class for " + name + ": (1) Warrior, (2) Wizard: ", 1, 2);

        if (choice == 1) {
            return new Warrior(
                    name,
                    getRandom(100, 200),
                    getRandom(10, 50),
                    getRandom(1, 10)
            );
        } else {
            return new Wizard(
                    name,
                    getRandom(50, 100),
                    getRandom(10, 50),
                    getRandom(1, 50)
            );
        }
    }

    private static Character createRandomCharacter(String name) {
        return random.nextBoolean()
                ? new Warrior(name, getRandom(100, 200), getRandom(10, 50), getRandom(1, 10))
                : new Wizard(name, getRandom(50, 100), getRandom(10, 50), getRandom(1, 50));
    }

    private static int getRandom(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private static void showInitialStats(Character c1, Character c2) {
        System.out.println("\n--- Initial Stats ---");
        System.out.println("Character 1: " + c1.getStats());
        System.out.println("Character 2: " + c2.getStats());
        System.out.println("\nStarting battle...");
    }

    private static boolean askToRepeat() {
        String input;
        do {
            input = prompt("\nDo you want to return to the main menu? (y/n): ").trim().toLowerCase();
            if (!input.equals("y") && !input.equals("n")) {
                System.out.println("⚠️ Invalid input. Please enter 'y' or 'n'.");
            }
        } while (!input.equals("y") && !input.equals("n"));
        return input.equals("y");
    }
}
