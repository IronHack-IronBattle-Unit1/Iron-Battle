# ⚔️ RPG Battle Simulator

A basic text-based RPG battle simulator built in Java. Two characters — either Warriors or Wizards — face off in a duel to the death! Characters can be created manually, imported from a CSV file, or randomly generated. Each character class has its own attributes and unique battle logic.

---

## 📜 Features

- Create characters manually or randomly
- Import characters from a CSV file
- Simulate turn-based 1v1 battles
- Warriors and Wizards have unique combat behavior
- Automatically restarts tied battles until one winner remains
- Detailed round-by-round battle log printed to the console
- Text-based menu navigation

---

## 🧠 Classes & Structure

### ✅ Required Classes & Interface

#### `Attacker` (Interface)

- `void attack(Character target)`
- Implemented by both `Warrior` and `Wizard`

---

#### `Character` (Abstract Class)

> Common parent for `Warrior` and `Wizard`

- `id`: auto-generated (private)
- `name`: String (private)
- `hp`: int (private)
- `isAlive`: boolean (private, defaults to `true`)
- Constructor: `(String name, int hp)`
- Public getters & setters

---

#### `Warrior` (Extends `Character` and Implements `Attacker`)

- `stamina`: int [10–50] (private)
- `strength`: int [1–10] (private)
- Constructor: `(String name, int hp, int stamina, int strength)`
- Overrides `attack()` with:
  - **Heavy Attack**: deals full strength, costs 5 stamina
  - **Weak Attack**: deals half strength, gains 1 stamina
  - **No stamina**: no damage, gains 2 stamina

---

#### `Wizard` (Extends `Character` and Implements `Attacker`)

- `mana`: int [10–50] (private)
- `intelligence`: int [1–50] (private)
- Constructor: `(String name, int hp, int mana, int intelligence)`
- Overrides `attack()` with:
  - **Fireball**: deals full intelligence, costs 5 mana
  - **Staff Hit**: deals 2 damage, gains 1 mana
  - **No mana**: no damage, gains 2 mana

---

## 🎯 Bonus Features

- 📥 **Import characters from CSV**  
  Easily load character data from a properly formatted CSV file.

- 🎲 **Random character creation and auto-battle simulation**  
  Generate random characters with randomized stats and instantly simulate a full battle.

- 🔄 **Loop back to the main menu for multiple matches**  
  After each battle, users can return to the main menu to run another simulation or create more characters.

---

## 🛠️ Technologies Used

- **Java 17+**
- **Standard Input/Output** for interactive console-based user experience
- **Object-Oriented Design principles** for clean, modular code structure
- **Random number generation** to ensure dynamic character creation and battle flow

---

## ⚔️ How the Battle Works

- Two characters attack simultaneously each round
- Damage is applied even if one dies during the same round
- Tie battles are restarted with new characters
- Winner is announced at the end of the battle

---

## 🧪 Sample Menu

=== RPG Battle Simulator ===

Choose an option:

  1.Manual character creation

  2.Import characters from CSV

  3.Auto-generate and simulate battle

## 📦 CSV Import Format

Thor,Warrior,150,40,8

Gandalf,Wizard,90,45,42
