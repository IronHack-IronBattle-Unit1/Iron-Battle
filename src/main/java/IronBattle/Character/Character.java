package IronBattle.Character;

import java.util.Random;

public abstract class Character {
    private final String idChar;  // ID como "Char-00001"
    private String nameChar;
    private int health;
    private boolean alive;

    private static final int MIN_ID = 1;
    private static final int MAX_ID = 10000;
    private static final Random rand = new Random();

    // Constructor
    public Character(String nameChar, int health, boolean alive) {
        this.idChar = "Char-" + String.format("%04d", rand.nextInt(MIN_ID, MAX_ID)); // Java 17+
        this.nameChar = nameChar;
        this.health = health;
        this.alive = alive;
    }

    // Getters
    public String getIdChar() {
        return idChar;  // Ejemplo: "Char-00427"
    }

    public String getNameChar() { return nameChar; }
    public int getHealth() { return health; }
    public boolean isAlive() { return alive; }

    // Setters
    public void setNameChar(String nameChar) { this.nameChar = nameChar; }

    public void setHealth(int health) {
        if (health <= 0) {
            this.health = 0;
            setAlive(false);
        } else
            this.health = health;
    }

    public void setAlive(boolean alive) { this.alive = alive; }
}