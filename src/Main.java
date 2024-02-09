import static Control.Program.Run;


public class Main {
    public static void main(String[] args) {
        try {
            Run();
        } catch (Exception e) {
            System.out.println("Ошибка, описание ниже: ");
            System.out.println(e.getMessage());
        }
    }
}