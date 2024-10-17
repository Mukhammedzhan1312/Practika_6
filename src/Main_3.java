enum WeaponType {
    SWORD, BOW, STAFF
}

enum ArmorType {
    LIGHT_ARMOR, HEAVY_ARMOR, ROBE
}

enum SkillType {
    MAGIC, PHYSICAL
}

//// Класс оружия
class Weapon implements Cloneable {
    private String name;
    private int damage;
    private WeaponType weaponType;

    public Weapon(String name, int damage, WeaponType weaponType) {
        this.name = name;
        this.damage = damage;
        this.weaponType = weaponType;
    }

    @Override
    public Weapon clone() {
        try {
            return (Weapon) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Weapon{name='" + name + "', damage=" + damage + ", type=" + weaponType + '}';
    }
}

//// Класс брони
class Armor implements Cloneable {
    private String name;
    private int defense;
    private ArmorType armorType;

    public Armor(String name, int defense, ArmorType armorType) {
        this.name = name;
        this.defense = defense;
        this.armorType = armorType;
    }

    @Override
    public Armor clone() {
        try {
            return (Armor) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Armor{name='" + name + "', defense=" + defense + ", type=" + armorType + '}';
    }
}

//// Класс умений
class Skill implements Cloneable {
    private String name;
    private int power;
    private SkillType skillType;

    public Skill(String name, int power, SkillType skillType) {
        this.name = name;
        this.power = power;
        this.skillType = skillType;
    }

    @Override
    public Skill clone() {
        try {
            return (Skill) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Skill{name='" + name + "', power=" + power + ", type=" + skillType + '}';
    }
}

//// Класс персонажа
class Character implements Cloneable {
    private String name;
    private int health;
    private int strength;
    private int agility;
    private int intelligence;
    private Weapon weapon;
    private Armor armor;
    private Skill skill;

    public Character(String name, int health, int strength, int agility, int intelligence, Weapon weapon, Armor armor, Skill skill) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.weapon = weapon;
        this.armor = armor;
        this.skill = skill;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public Character clone() {
        try {
            Character clone = (Character) super.clone();
            clone.weapon = this.weapon.clone();
            clone.armor = this.armor.clone();
            clone.skill = this.skill.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Character{name='" + name + "', health=" + health + ", strength=" + strength +
                ", agility=" + agility + ", intelligence=" + intelligence +
                ", weapon=" + weapon + ", armor=" + armor + ", skill=" + skill + '}';
    }
}

public class Main_3 {
    public static void main(String[] args) {
        Weapon sword = new Weapon("Excalibur", 50, WeaponType.SWORD);
        Armor heavyArmor = new Armor("Dragon Armor", 40, ArmorType.HEAVY_ARMOR);
        Skill fireball = new Skill("Fireball", 100, SkillType.MAGIC);
        Character original = new Character("Knight", 100, 20, 15, 10, sword, heavyArmor, fireball);
        System.out.println("Оригинальный персонаж:");
        System.out.println(original);
        Character clone = original.clone();
        System.out.println("\nКлонированный персонаж:");
        System.out.println(clone);
        System.out.println("\nПроверка глубокого клонирования:");
        System.out.println("Оригинальный и клонированный персонаж имеют разные объекты оружия: " +
                (original.getWeapon() != clone.getWeapon()));
    }
}
