package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleSimulator {
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== BATTLE SIMULATOR ===");
        System.out.println("1. Create new characters");
        System.out.println("2. Import characters from CSV");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Character player1, player2;

        if (choice == 1) {
            System.out.println("\nCreating first character:");
            player1 = createCharacter();
            System.out.println("\nCreating second character:");
            player2 = createCharacter();
        } else {
            try {
                System.out.print("\nEnter CSV file path (or press enter for default 'characters.csv'): ");
                String filePath = scanner.nextLine();

                if (filePath.isEmpty()) {
                    filePath = "characters.csv";
                }

                List<Character> characters = CharacterImporter.importFromCSV(filePath);

                if (characters.size() < 2) {
                    System.out.println("Error: CSV file must contain at least 2 characters");
                    return;
                }

                // Show available characters
                System.out.println("\nAvailable characters:");
                for (int i = 0; i < characters.size(); i++) {
                    Character c = characters.get(i);
                    System.out.printf("%d. %s (%s) - HP: %d%n",
                            i + 1,
                            c.getName(),
                            c instanceof Warrior ? "Warrior" : "Wizard",
                            c.getHp());
                }

                // Select characters
                System.out.print("\nSelect first character (number): ");
                int char1Index = scanner.nextInt() - 1;
                scanner.nextLine();

                System.out.print("Select second character (number): ");
                int char2Index = scanner.nextInt() - 1;
                scanner.nextLine();

                player1 = characters.get(char1Index);
                player2 = characters.get(char2Index);

                System.out.println("\nSelected characters:");
                System.out.println("1. " + player1.getName() + " (" +
                        (player1 instanceof Warrior ? "Warrior" : "Wizard") + ")");
                System.out.println("2. " + player2.getName() + " (" +
                        (player2 instanceof Warrior ? "Warrior" : "Wizard") + ")");

            } catch (IOException e) {
                System.out.println("Error reading CSV file: " + e.getMessage());
                return;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid character selection");
                return;
            }
        }

        System.out.println("\n=== BATTLE BEGIN ===");
        System.out.printf("%s vs %s%n", player1.getName(), player2.getName());

        simulateBattle((Attacker)player1, (Attacker)player2, player1, player2);
    }

    private static Character createCharacter() {
        System.out.println("\nCreate a new character:");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Choose class:");
        System.out.println("1. Warrior");
        System.out.println("2. Wizard");
        System.out.print("Choice: ");
        int classChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Generate random stats? (Y/N)");
        String randomChoice = scanner.nextLine().toUpperCase();

        if (classChoice == 1) {
            if (randomChoice.equals("Y")) {
                int hp = 100 + random.nextInt(101); // 100-200
                int stamina = 10 + random.nextInt(41); // 10-50
                int strength = 1 + random.nextInt(10); // 1-10
                return new Warrior(name, hp, stamina, strength);
            } else {
                System.out.print("Enter HP (100-200): ");
                int hp = scanner.nextInt();
                System.out.print("Enter Stamina (10-50): ");
                int stamina = scanner.nextInt();
                System.out.print("Enter Strength (1-10): ");
                int strength = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return new Warrior(name, hp, stamina, strength);
            }
        } else {
            if (randomChoice.equals("Y")) {
                int hp = 50 + random.nextInt(51); // 50-100
                int mana = 10 + random.nextInt(41); // 10-50
                int intelligence = 1 + random.nextInt(50); // 1-50
                return new Wizard(name, hp, mana, intelligence);
            } else {
                System.out.print("Enter HP (50-100): ");
                int hp = scanner.nextInt();
                System.out.print("Enter Mana (10-50): ");
                int mana = scanner.nextInt();
                System.out.print("Enter Intelligence (1-50): ");
                int intelligence = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return new Wizard(name, hp, mana, intelligence);
            }
        }
    }

    private static void simulateBattle(Attacker attacker1, Attacker attacker2,
                                       Character char1, Character char2) {
        int round = 1;

        while (char1.isAlive() && char2.isAlive()) {
            System.out.printf("\n=== ROUND %d ===%n", round++);


            System.out.println("\n" + char1.getName() + "'s turn:");
            attacker1.attack(char2);


            if (!char2.isAlive()) {
                break;
            }


            System.out.println("\n" + char2.getName() + "'s turn:");
            attacker2.attack(char1);


            System.out.printf("\nAfter round %d:%n", round-1);
            System.out.printf("%s HP: %d | %s HP: %d%n",
                    char1.getName(), char1.getHp(),
                    char2.getName(), char2.getHp());
        }

        // Determine winner
        System.out.println("\n=== BATTLE END ===");
        if (!char1.isAlive() && !char2.isAlive()) {
            System.out.println("It's a tie! Both warriors have fallen.");
        } else if (!char1.isAlive()) {
            System.out.printf("%s (%s) wins!%n", char2.getName(),
                    char2 instanceof Warrior ? "Warrior" : "Wizard");
        } else {
            System.out.printf("%s (%s) wins!%n", char1.getName(),
                    char1 instanceof Warrior ? "Warrior" : "Wizard");
        }
    }
}