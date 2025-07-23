package org.example;
import java.util.Random;

public class Wizard extends Character implements Attacker {
    private int mana;
    private int intelligence;
    private static final Random random = new Random();

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
        int attackType = random.nextInt(2);
        int damage = 0;
        String attackName = "";

        if (attackType == 0) { // Fireball
            if (mana >= 5) {
                damage = intelligence;
                mana -= 5;
                attackName = "Fireball";
            } else {
                damage = 2;
                mana += 1;
                attackName = "Staff Hit (fallback)";
            }
        } else { // Staff Hit
            damage = 2;
            mana += 1;
            attackName = "Staff Hit";
        }

        if (mana < 1) {
            damage = 0;
            mana += 2;
            attackName = "No Attack (recovering mana)";
        }

        System.out.printf("%s (%s) casts %s! Damage: %d | Mana left: %d%n",
                getName(), "Wizard", attackName, damage, mana);

        if (damage > 0) {
            target.setHp(target.getHp() - damage);
            System.out.printf("%s takes %d damage! HP left: %d%n",
                    target.getName(), damage, target.getHp());
        }
    }
}