public abstract class Animal {
    private String name;
    private String commands;

    public Animal(String name, String commands) {
        this.name = name;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public String getCommands() {
        return commands;
    }

    public void setSkills(String newCommands) {
        this.commands = newCommands;
    }

    public abstract void displayCommands();

    public abstract void teachNewCommands(String command);
}

