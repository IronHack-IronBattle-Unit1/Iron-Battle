package IronBattle.models;

import java.util.Scanner;

public class BattleSimulator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void fight(Character player1, Character player2) {
        System.out.println("\nFIGHT BETWEEN:");
        System.out.println(player1.getStats() + " [ID: " + player1.getId() + "]");
        System.out.println("VS");
        System.out.println(player2.getStats() + " [ID: " + player2.getId() + "]");


        int round = 1;
        while (player1.isAlive() && player2.isAlive()) {
            System.out.println("\n--- Round " + round + " ---");
            ((Attacker) player1).attack(player2);
            ((Attacker) player2).attack(player1);
            System.out.println(player1.getName() + " HP: " + player1.getHp());
            System.out.println(player2.getName() + " HP: " + player2.getHp());

            System.out.println("\nPress SPACE then ENTER to continue to next round...");
            String input = scanner.nextLine();
            round++;
        }

        System.out.println("\n===== Battle Over =====");
        if (!player1.isAlive() && !player2.isAlive()) {
            System.out.println("It's a tie! Restarting battle...");
            fight(player1, player2);
        } else if (player1.isAlive()) {
            System.out.println("Winner: " + player1.getName() + " the " + player1.getType());
        } else {
            System.out.println("Winner: " + player2.getName() + " the " + player2.getType());
        }
    }
}
