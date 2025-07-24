package IronBattle;

import IronBattle.utils.GameUtils;
import IronBattle.models.Character;
//import IronBattle.models.CharacterImporter;
//import IronBattle.models.BattleSimulator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        boolean continuePlaying = true;

        while (continuePlaying) {
            GameUtils.printMenu();
            int option = GameUtils.getValidatedIntInput("Enter choice (1-3): ", 1, 3);

            Character c1 = null, c2 = null;

            switch (option) {
                case 1:
                    c1 = GameUtils.createCharacterFromInput(GameUtils.prompt("Enter name for Character 1: "));
                    c2 = GameUtils.createCharacterFromInput(GameUtils.prompt("Enter name for Character 2: "));
                    break;

//                case 2:
//                    List<Character> imported = CharacterImporter.importFromCSV();
//                    if (imported.size() < 2) {
//                        System.out.println("❌ Not enough characters in CSV to start a battle.");
//                        continue;
//                    }
//                    c1 = imported.get(0);
//                    c2 = imported.get(1);
//                    break;

                case 3:
                    c1 = GameUtils.createRandomCharacter("RandomHero1");
                    c2 = GameUtils.createRandomCharacter("RandomHero2");
                    break;
            }

            GameUtils.showInitialStats(c1, c2);
//            BattleSimulator.fight(c1, c2);

            continuePlaying = GameUtils.askToRepeat();
        }

        System.out.println("👋 Exiting RPG Battle Simulator. Goodbye!");
    }
}