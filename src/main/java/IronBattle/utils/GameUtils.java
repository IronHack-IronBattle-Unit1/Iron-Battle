package IronBattle.utils;

import IronBattle.models.*;

import java.util.Random;
import java.util.Scanner;

public class GameUtils {
    private static final Random random = new Random();
    private static final Scanner sc = new Scanner(System.in);

    public static void printMenu() {
        System.out.println("\n=== RPG Battle Simulator ===");
        System.out.println("Choose an option:");
        System.out.println("1. Manual character creation");
        System.out.println("2. Import characters from CSV");
        System.out.println("3. Auto-generate and simulate battle");
    }

    public static int getValidatedIntInput(String message, int min, int max) {
        int input = -1;
        while (input < min || input > max) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("⚠️ Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
        return input;
    }

    public static String prompt(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    public static int getRandom(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static Character createCharacterFromInput(String name) {
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

    public static Character createRandomCharacter(String name) {
        return random.nextBoolean()
                ? new Warrior(name, getRandom(100, 200), getRandom(10, 50), getRandom(1, 10))
                : new Wizard(name, getRandom(50, 100), getRandom(10, 50), getRandom(1, 50));
    }

    public static void showInitialStats(Character c1, Character c2) {
        System.out.println("\n--- Initial Stats ---");
        System.out.println("Character 1: " + c1.getStats());
        System.out.println("Character 2: " + c2.getStats());
        System.out.println("\nStarting battle...");
    }

    public static boolean askToRepeat() {
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
