package IronBattle;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== RPG Battle Simulator ===");

        System.out.print("Enter name for Character 1: ");
        String name1 = sc.nextLine();

        int choice1 = 0;
        while (choice1 != 1 && choice1 != 2) {
            System.out.print("Select class for " + name1 + ": (1) Warrior, (2) Wizard: ");
            if (sc.hasNextInt()) {
                choice1 = sc.nextInt();
                sc.nextLine();
                if (choice1 != 1 && choice1 != 2) {
                    System.out.println("Invalid choice, please enter 1 or 2.");
                }
            } else {
                System.out.println("Please enter a number (1 or 2).");
                sc.nextLine();
            }
        }
        Character c1 = (choice1 == 2) ? new Wizard(name1) : new Warrior(name1);

        System.out.print("Enter name for Character 2: ");
        String name2 = sc.nextLine();

        int choice2 = 0;
        while (choice2 != 1 && choice2 != 2) {
            System.out.print("Select class for " + name2 + ": (1) Warrior, (2) Wizard: ");
            if (sc.hasNextInt()) {
                choice2 = sc.nextInt();
                sc.nextLine();
                if (choice2 != 1 && choice2 != 2) {
                    System.out.println("Invalid choice, please enter 1 or 2.");
                }
            } else {
                System.out.println("Please enter a number (1 or 2).");
                sc.nextLine();
            }
        }
        Character c2 = (choice2 == 2) ? new Wizard(name2) : new Warrior(name2);

        System.out.println("\n--- Initial Stats ---");
        System.out.println("Character 1: " + c1.getStats());
        System.out.println("Character 2: " + c2.getStats());

        System.out.println("\nStarting battle...");
        BattleSimulator.fight(c1, c2);
    }
}
