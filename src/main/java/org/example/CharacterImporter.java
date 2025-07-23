package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharacterImporter {
    public static List<Character> importFromCSV(String filePath) throws IOException {
        List<Character> characters = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Skip header
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length < 7) {
                    System.err.println("Invalid line: " + line);
                    continue;
                }

                String type = values[0].trim();
                String name = values[1].trim();
                int hp = Integer.parseInt(values[2].trim());

                if (type.equalsIgnoreCase("Warrior")) {
                    int stamina = Integer.parseInt(values[3].trim());
                    int strength = Integer.parseInt(values[4].trim());
                    characters.add(new Warrior(name, hp, stamina, strength));
                }
                else if (type.equalsIgnoreCase("Wizard")) {
                    int mana = Integer.parseInt(values[5].trim());
                    int intelligence = Integer.parseInt(values[6].trim());
                    characters.add(new Wizard(name, hp, mana, intelligence));
                }
            }
        }

        return characters;
    }
}