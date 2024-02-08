public class Cat extends Pet {
    public Cat(String name, String commands){
        super(name, commands);
    }

    @Override
    public void displayCommands() {
        System.out.println("Кошка " + getName() + "умеет: " + getCommands());
    }


    @Override
    public void teachNewCommands(String command) {
        String updatedCommands = getCommands() + "," + command;
        setCommands(updatedCommands);
        System.out.println("Кошка " + getName() + " научилась: " + command);
    }
}
