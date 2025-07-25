package IronBattle.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CharacterImporter {
    private static final String FILE_PATH = "src/main/java/IronBattle/characters.csv";

    public static List<Character> importFromCSV() {
        List<Character> characters = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length < 5) continue;

                String name = parts[0];
                String type = parts[1];
                int hp = Integer.parseInt(parts[2]);

                if (type.equalsIgnoreCase("Warrior")) {
                    int stamina = Integer.parseInt(parts[3]);
                    int strength = Integer.parseInt(parts[4]);
                    characters.add(new Warrior(name, hp, stamina, strength));
                } else if (type.equalsIgnoreCase("Wizard")) {
                    int mana = Integer.parseInt(parts[3]);
                    int intelligence = Integer.parseInt(parts[4]);
                    characters.add(new Wizard(name, hp, mana, intelligence));
                }
            }

        } catch (Exception e) {
            System.err.println("❌ Failed to load characters from CSV: " + e.getMessage());
        }

        return characters;
    }
}
