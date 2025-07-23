package org.example;
import java.util.Random;

public class Warrior extends Character implements Attacker {
    private int stamina;
    private int strength;
    private static final Random random = new Random();

    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, hp);
        this.stamina = stamina;
        this.strength = strength;
    }


    public int getStamina() { return stamina; }
    public int getStrength() { return strength; }
    public void setStamina(int stamina) { this.stamina = stamina; }
    public void setStrength(int strength) { this.strength = strength; }

    @Override
    public void attack(Character target) {
        int attackType = random.nextInt(2);
        int damage = 0;
        String attackName = "";

        if (attackType == 0) { // Heavy Attack
            if (stamina >= 5) {
                damage = strength;
                stamina -= 5;
                attackName = "Heavy Attack";
            } else {
                damage = strength / 2;
                stamina += 1;
                attackName = "Weak Attack (fallback)";
            }
        } else { // Weak Attack
            damage = strength / 2;
            stamina += 1;
            attackName = "Weak Attack";
        }

        if (stamina < 1) {
            damage = 0;
            stamina += 2;
            attackName = "No Attack (recovering stamina)";
        }

        System.out.printf("%s (%s) uses %s! Damage: %d | Stamina left: %d%n",
                getName(), "Warrior", attackName, damage, stamina);

        if (damage > 0) {
            target.setHp(target.getHp() - damage);
            System.out.printf("%s takes %d damage! HP left: %d%n",
                    target.getName(), damage, target.getHp());
        }
    }
}