package Animals;

import java.util.LinkedList;

abstract public class Animal {
    private String name;
    private String typeAnimal;
    private String kindAnimal;
    private LinkedList<String> commands;

    public Animal(String name) {
        setName(name);
        createEmptyListCommands();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTypeAnimal(String typeAnimal) {
        this.typeAnimal = typeAnimal;
    }

    public void setKindAnimal(String kindAnimal) {
        this.kindAnimal = kindAnimal;
    }

    public String getTypeAnimal() {
        return typeAnimal;
    }

    public String getKindAnimal() {
        return kindAnimal;
    }

    public LinkedList<String> getCommands() {
        return commands;
    }

    public void createEmptyListCommands() {
        this.commands = new LinkedList<>();
    }

    public boolean addCommand(String command) {
        for (String com : this.commands) {
            if (com.equals(command.strip().toLowerCase())) {
                return false;
            }
        }
        this.commands.add(command.strip().toLowerCase());
        return true;
    }

    public void addCommands(String[] commands) {
        for (String command : commands) {
            if (!this.addCommand(command)) System.out.println("Питомец уже знает команду " + command);
        }
    }

    @Override
    public String toString() {
        return "Животное:" +
                "зовут " + getName() + ", " +
                "тип животного " + getTypeAnimal() + ", " +
                ", разновидность " + getKindAnimal();
    }
}