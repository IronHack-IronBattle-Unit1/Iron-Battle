package IronBattle.Character;

import java.util.Random;

public class Warrior extends Character implements Attacker {
    private int stamina;
    private int strength;
    private static final Random random = new Random();

    // Constructor with random characteristics
    public Warrior(String name) {
        super(name, random.nextInt(101) + 100); // hp: 100–200
        this.stamina = random.nextInt(41) + 10; // stamina: 10–50
        this.strength = random.nextInt(10) + 1; // strength: 1–10
    }

    // Constructor with manually set characteristics
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
        if (!this.isAlive()) {
            System.out.println(getName() + " is dead and can't attack.");
            return;
        }
        if (!target.isAlive()) {
            System.out.println(target.getName() + " is already dead!");
            return;
        }

        // Randomly choose type of attack
        boolean heavyAttack = random.nextBoolean();

        if (heavyAttack) {
            // try to heavy attack
            if (stamina >= 5) {
                int damage = strength;
                stamina -= 5;
                target.setHp(target.getHp() - damage);
                System.out.println(getName() + " performs a HEAVY attack on " + target.getName() +
                        " for " + damage + " damage! (stamina now " + stamina + ")");
                if (target.getHp() <= 0) {
                    target.setAlive(false);
                    target.setHp(0);
                    System.out.println(target.getName() + " has died!");
                }
            } else {
                // if not enough stamina try to weak attack
                doWeakAttack(target);
            }
        } else {
            doWeakAttack(target);
        }
    }

    private void doWeakAttack(Character target) {
        if (stamina >= 1) {
            int damage = strength / 2;
            stamina += 1; // Weak attack recovers 1 stamina
            target.setHp(target.getHp() - damage);
            System.out.println(getName() + " performs a WEAK attack on " + target.getName() +
                    " for " + damage + " damage! (stamina now " + stamina + ")");
            if (target.getHp() <= 0) {
                target.setAlive(false);
                target.setHp(0);
                System.out.println(target.getName() + " has died!");
            }
        } else {
            // Not enough stamina for weak attack
            stamina += 2;
            System.out.println(getName() + " is exhausted and cannot attack. Recovers 2 stamina. (stamina now " + stamina + ")");
        }
    }

    @Override
    public String getStats() {
        return super.getName() + " the Warrior [HP: " + super.getHp() + ", Stamina: " + stamina + ", Strength: " + strength + "]";
    }

    @Override
    public String getType() {
        return "Warrior";
    }
}
