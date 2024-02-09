package Control;

import Animals.Animal;
import Animals.*;

import java.util.LinkedList;

import static View.DataInput.*;
import static View.Menu.*;

public class Program {
    static String notPoint = "Такого пункта в меню нет, попробуйте ещё раз";

    public static void Run() {
        LinkedList<Animal> lstAnimals = new LinkedList<>();
        while (true) {
            MainMenu(Counter.getCounter());
            int choice = ChoiceInMenu();
            switch (choice) {
                case 1 -> {
                    try (Counter count = new Counter()) {
                        System.out.println("Начинаем процедуру добавления животного");
                        Animal tmp = AddAnimal();
                        if (tmp != null) {
                            System.out.println("Укажите список команд, которые умеет делать животное");
                            tmp.addCommands(GetString().split(","));
                            if (tmp.getName().length() == 0) throw new RuntimeException("Не указано имя");
                            if (tmp.getCommands().size() == 0) throw new RuntimeException("Не указаны команды");
                            count.add();
                            lstAnimals.add(tmp);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Повторите попытку");
                        System.out.println();
                    }
                }
                case 2 -> {
                    if (lstAnimals.size() != 0) {
                        System.out.println("Выберите животное");
                        System.out.println("Список животных:\n");
                        ShowListAnimals(lstAnimals);
                        int numAnimal = ChoiceInMenu();
                        if (numAnimal >= 0 && numAnimal < lstAnimals.size()) {
                            System.out.println("Укажите список команд, которые умеет делать животное");
                            String commands = GetString();
                            lstAnimals.get(numAnimal).addCommands(commands.split(","));
                        } else {
                            System.out.println("Животного с данным номером нет");
                        }
                    } else System.out.println("Животных нет\n");
                }
                case 3 -> {
                    if (lstAnimals.size() != 0) {
                        System.out.println("Список животных:\n");
                        ShowListAnimals(lstAnimals);
                    } else System.out.println("Животных нет\n");
                }
                case 4 -> {
                    if (lstAnimals.size() != 0) {
                        System.out.println("Выберите животное");
                        System.out.println("Список животных:\n");
                        ShowListAnimals(lstAnimals);
                        int numAnimal = ChoiceInMenu();
                        if (numAnimal >= 0 && numAnimal < lstAnimals.size()) {
                            System.out.println(lstAnimals.get(numAnimal));
                            ShowListMakeCommands(lstAnimals.get(numAnimal));
                        } else {
                            System.out.println("Животного с данным номером нет");
                        }
                    } else System.out.println("Животных нет\n");
                }
                case 0 -> {
                    System.out.println("Досвидания");
                    return;
                }
                default -> System.out.println(notPoint);
            }
        }
    }

    public static Animal AddAnimal() {
        System.out.println("Введите имя животного:");
        String name = GetString();
        while (true) {
            TypeAnimalMenu();
            int choiceType = ChoiceInMenu();
            switch (choiceType) {
                case 1:
                    //noinspection TextLabelInSwitchStatement
                    loopPets:
                    while (true) {
                        ShowPets();
                        int choiceKind = ChoiceInMenu();
                        switch (choiceKind) {
                            case 1:
                                return new Dog(name);
                            case 2:
                                return new Cat(name);
                            case 3:
                                return new Hamster(name);
                            case 0:
                                break loopPets;
                            default:
                                System.out.println(notPoint);
                                break;
                        }
                    }
                    break;
                case 2:
                    //noinspection TextLabelInSwitchStatement
                    loopPackAnimal:
                    while (true) {
                        ShowPackAnimals();
                        int choiceKind = ChoiceInMenu();
                        switch (choiceKind) {
                            case 1:
                                return new Horse(name);
                            case 2:
                                return new Donkey(name);
                            case 3:
                                return new Camel(name);
                            case 0:
                                break loopPackAnimal;
                            default:
                                System.out.println(notPoint);
                                break;
                        }
                    }
                    break;
                case 0:
                    return null;
                default:
                    System.out.println(notPoint);
            }
        }
    }
}