//Character.java

import java.util.List;

public class Character {

    private String name;
    private Integer health;
    private Integer memory;
    private List<String> inventory;

    public Character() {
    }

    public Character(String name, Integer health, Integer memory, List<String> inventory) {
        this.name = name;
        this.health = health;
        this.memory = memory;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

}
