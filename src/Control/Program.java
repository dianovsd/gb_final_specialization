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
                            String[] commands = GetString().split(",");
                            tmp.addCommands(commands);

                            if (tmp.getName().isEmpty()) {
                                throw new RuntimeException("Не указано имя");
                            }

                            if (tmp.getCommands().isEmpty()) {
                                throw new RuntimeException("Не указаны команды");
                            }

                            count.add();
                            lstAnimals.add(tmp);
                        }
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка. Повторите попытку");
                        System.out.println();
                    }
                }
                case 2 -> {
                    if (lstAnimals.isEmpty()) {
                        System.out.println("Животных нет\n");
                        break;
                    }

                    System.out.println("Выберите животное");
                    System.out.println("Список животных:\n");
                    ShowListAnimals(lstAnimals);

                    int numAnimal = ChoiceInMenu();

                    if (numAnimal < 0 || numAnimal >= lstAnimals.size()) {
                        System.out.println("Животного с данным номером нет");
                        break;
                    }

                    System.out.println("Укажите список команд, которые умеет делать животное");
                    String commands = GetString();
                    String[] commandList = commands.split(",\\s*"); // Разделение команд с учетом пробелов
                    lstAnimals.get(numAnimal).addCommands(commandList);
                }
                case 3 -> {
                    if (lstAnimals.size() != 0) {
                        System.out.println("Список животных:\n");
                        ShowListAnimals(lstAnimals);
                    } else System.out.println("Животных нет\n");
                }
                case 4 -> {
                    if (lstAnimals.isEmpty()) {
                        System.out.println("Животных нет\n");
                        break;
                    }

                    System.out.println("Напишите номер животного из списка ниже");
                    System.out.println("Список животных:\n");
                    ShowListAnimals(lstAnimals);

                    int numAnimal = ChoiceInMenu();

                    if (numAnimal < 0 || numAnimal >= lstAnimals.size()) {
                        System.out.println("Животного с данным номером нет");
                        break;
                    }

                    System.out.println(lstAnimals.get(numAnimal));
                    ShowListMakeCommands(lstAnimals.get(numAnimal));
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