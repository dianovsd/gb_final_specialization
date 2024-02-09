package View;

import Animals.Animal;

import java.util.LinkedList;

public class Menu {
    public static void MainMenu(int values) {
        System.out.println("Выберите команду:");
        System.out.println("1. Поселить животное");
        System.out.println("2. Добавить навык");
        System.out.println("3. Показать весь список животных");
        System.out.println("4. Показать список команд конкретного животного");
        System.out.println("0. Выход\n");
    }

    public static void TypeAnimalMenu() {
        System.out.println("Выберите тип животного:");
        System.out.println("1. Домашними животными");
        System.out.println("2. Вьючными животными");
        System.out.println("0. Назад\n");
    }

    public static void ShowPets() {
        System.out.println("Выберите животное:");
        System.out.println("1. Собака");
        System.out.println("2. Кошка");
        System.out.println("3. Хомяк");
        System.out.println("0. Назад\n");
    }

    public static void ShowPackAnimals() {
        System.out.println("Выберите животное:");
        System.out.println("1. Лошадь");
        System.out.println("2. Осел");
        System.out.println("3. Верблюд");
        System.out.println("0. Назад\n");
    }

    public static void ShowListAnimals(LinkedList<Animal> lstAnimals) {
        String divider = "-".repeat(80);
        System.out.println(divider);
        System.out.printf("| %7s | %20s | %20s | %20s |\n", "номер", "имя", "тип животного", "вид животного");
        System.out.println(divider);
        for (int i = 0; i < lstAnimals.size(); i++) {
            System.out.printf("| %7s | %20s | %20s | %20s |\n", i, lstAnimals.get(i).getName(), lstAnimals.get(i).getTypeAnimal(), lstAnimals.get(i).getKindAnimal());
            System.out.println(divider);
        }
    }

    public static void ShowListMakeCommands(Animal animal) {
        System.out.println(animal.getName() + " может выполнять следующие команды:");
        for (int i = 0; i < animal.getCommands().size(); i++) {
            System.out.printf("\t%d. %s%n", i, animal.getCommands().get(i));
        }
        System.out.println();
    }
}