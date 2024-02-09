package Animals;

public class Dog extends Donkey.Pet {
    public Dog(String name, String commands) {
        super(name, commands);
    }

    @Override
    public void displayCommands() {
        System.out.println("Собака " + getName() + "умеет: " + getCommands());
    }


    @Override
    public void teachNewCommands(String command) {
        String updatedCommands = getCommands() + "," + command;
        setCommands(updatedCommands);
        System.out.println("Собака " + getName() + " научилась: " + command);
    }
}
