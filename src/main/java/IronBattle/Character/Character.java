package IronBattle.Character;
import java.util.Random;

public abstract class Character {
    private final String id;  // ID como "Char-00001"
    private String name;
    private int hp;
    private boolean isAlive;

    private static final int MIN_ID = 1;
    private static final int MAX_ID = 10000;
    private static final Random rand = new Random();

    // Constructor
    public Character(String name, int hp) {
        this.id = "Char-" + String.format("%04d", rand.nextInt(MIN_ID, MAX_ID)); // Java 17+
        this.name = name;
        this.hp = hp;
        this.isAlive = true;
    }

    // Getters
    public String getId() {
        return id;  // Ejemplo: "Char-00427"
    }
    public String getName() { return name; }
    public int getHp() { return hp; }
    // Subclasses can check if alive
    public boolean isAlive() { return isAlive; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setHp(int hp) {
        if (hp <= 0) {
            this.hp = 0;
            setAlive(false);
        } else
            this.hp = hp;
    }
    public void setAlive(boolean isAlive) { this.isAlive = isAlive; }

    public abstract String getStats();
    public abstract String getType();
}