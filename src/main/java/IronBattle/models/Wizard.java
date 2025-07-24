package IronBattle.models;

import java.util.Random;

public class Wizard extends Character implements Attacker {
    private int mana;
    private int intelligence;
    private static final Random random = new Random();

    // Constructor with random characteristics
    public Wizard(String name) {
        super(name, random.nextInt(51) + 50); // hp: 50–100
        this.mana = random.nextInt(41) + 10; // mana: 10–50
        this.intelligence = random.nextInt(50) + 1; // intelligence: 1–50
    }

    // Constructor with manually set characteristics
    public Wizard(String name, int hp, int mana, int intelligence) {
        super(name, hp);
        this.mana = mana;
        this.intelligence = intelligence;
    }

    public int getMana() { return mana; }
    public int getIntelligence() { return intelligence; }

    public void setMana(int mana) { this.mana = mana; }
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }

    @Override
    public void attack(Character target) {
        if (!this.isAlive()) {
            System.out.println(getName() + " is dead and can't cast spells.");
            return;
        }
        if (!target.isAlive()) {
            System.out.println(target.getName() + " is already dead!");
            return;
        }

        // Randomly choose type of attack
        boolean castFireball = random.nextBoolean();

        if (castFireball) {
            if (mana >= 5) {
                // Fireball: intelligence damage, -5 mana
                int damage = intelligence;
                mana -= 5;
                target.setHp(target.getHp() - damage);
                System.out.println(getName() + " casts FIREBALL on " + target.getName()
                        + " for " + damage + " damage! (mana now " + mana + ")");
                if (target.getHp() <= 0) {
                    target.setAlive(false);
                    target.setHp(0);
                    System.out.println(target.getName() + " has died!");
                }
            } else {
                // No mana for Fireball — trying Staff Hit
                doStaffHit(target);
            }
        } else {
            doStaffHit(target);
        }
    }

    private void doStaffHit(Character target) {
        if (mana >= 1) {
            // Staff hit: 2 damage, +1 mana
            int damage = 2;
            mana += 1;
            target.setHp(target.getHp() - damage);
            System.out.println(getName() + " hits with STAFF " + target.getName()
                    + " for " + damage + " damage! (mana now " + mana + ")");
            if (target.getHp() <= 0) {
                target.setAlive(false);
                target.setHp(0);
                System.out.println(target.getName() + " has died!");
            }
        } else {
            // No mana for staff hit
            mana += 2;
            System.out.println(getName() + " is out of mana and can't attack. Recovers 2 mana. (mana now " + mana + ")");
        }
    }

    @Override
    public String getStats() {
        return super.getName() + " the Wizard [HP: " + getHp() + ", Mana: " + mana + ", Intelligence: " + intelligence + "]";
    }

    @Override
    public String getType() {
        return "Wizard";
    }
}


