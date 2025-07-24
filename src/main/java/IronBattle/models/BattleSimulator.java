package IronBattle.models;

import java.util.Scanner;

public class BattleSimulator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void fight(Character char1, Character char2) {
        int round = 1;

        while (char1.isAlive() && char2.isAlive()) {
            System.out.printf("\n=== ROUND %d ===%n", round++);


            System.out.println("\n" + char1.getName() + "'s turn:");
            ((Attacker) char1).attack(char2);



            if (!char2.isAlive()) {
                break;
            }


            System.out.println("\n" + char2.getName() + "'s turn:");
            ((Attacker) char2).attack(char1);


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
