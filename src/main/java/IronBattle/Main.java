package IronBattle;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Random random = new Random();

    public static int getRandom(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static Character createRandomCharacter(String name) {
        if (random.nextBoolean()) {
            int hp = getRandom(100, 200);
            int stamina = getRandom(10, 50);
            int strength = getRandom(1, 10);
            return new Warrior(name, hp, stamina, strength);
        } else {
            int hp = getRandom(50, 100);
            int mana = getRandom(10, 50);
            int intelligence = getRandom(1, 50);
            return new Wizard(name, hp, mana, intelligence);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuePlaying = true;

        while (continuePlaying) {
            System.out.println("\n=== RPG Battle Simulator ===");
            System.out.println("Choose an option:");
            System.out.println("1. Manual character creation");
            System.out.println("2. Import characters from CSV");
            System.out.println("3. Auto-generate and simulate battle");

            int option = 0;
            while (option < 1 || option > 3) {
                System.out.print("Enter choice (1-3): ");
                if (sc.hasNextInt()) {
                    option = sc.nextInt();
                    sc.nextLine();
                } else {
                    System.out.println("Please enter a number (1, 2, or 3).");
                    sc.nextLine();
                }
            }

            Character c1 = null;
            Character c2 = null;

            switch (option) {
                case 1: // Manual creation
                    System.out.print("Enter name for Character 1: ");
                    String name1 = sc.nextLine();
                    c1 = createCharacterFromInput(name1, sc);

                    System.out.print("Enter name for Character 2: ");
                    String name2 = sc.nextLine();
                    c2 = createCharacterFromInput(name2, sc);
                    break;

                case 2: // CSV Import
                    List<Character> imported = CharacterImporter.importFromCSV();
                    if (imported.size() < 2) {
                        System.out.println("❌ Not enough characters in CSV to start a battle.");
                        continue;
                    }
                    c1 = imported.get(0);
                    c2 = imported.get(1);
                    break;

                case 3: // Auto-generated
                    c1 = createRandomCharacter("RandomHero1");
                    c2 = createRandomCharacter("RandomHero2");
                    break;
            }

            System.out.println("\n--- Initial Stats ---");
            System.out.println("Character 1: " + c1.getStats());
            System.out.println("Character 2: " + c2.getStats());

            System.out.println("\nStarting battle...");
            BattleSimulator.fight(c1, c2);

            // ✅ Preguntar si quiere continuar y validar que solo sea 'y' o 'n'
            String input;
            do {
                System.out.print("\nDo you want to return to the main menu? (y/n): ");
                input = sc.nextLine().trim().toLowerCase();

                if (!input.equals("y") && !input.equals("n")) {
                    System.out.println("⚠️ Invalid input. Please enter 'y' or 'n'.");
                }
            } while (!input.equals("y") && !input.equals("n"));

            if (input.equals("n")) {
                continuePlaying = false;
                System.out.println("👋 Exiting RPG Battle Simulator. Goodbye!");
            }
        }
    }

    public static Character createCharacterFromInput(String name, Scanner sc) {
        int choice = 0;
        while (choice != 1 && choice != 2) {
            System.out.print("Select class for " + name + ": (1) Warrior, (2) Wizard: ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice, please enter 1 or 2.");
                }
            } else {
                System.out.println("Please enter a number (1 or 2).");
                sc.nextLine();
            }
        }

        if (choice == 1) {
            int hp = getRandom(100, 200);
            int stamina = getRandom(10, 50);
            int strength = getRandom(1, 10);
            return new Warrior(name, hp, stamina, strength);
        } else {
            int hp = getRandom(50, 100);
            int mana = getRandom(10, 50);
            int intelligence = getRandom(1, 50);
            return new Wizard(name, hp, mana, intelligence);
        }
    }
}