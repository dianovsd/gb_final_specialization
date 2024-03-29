package Control;

public class Counter implements AutoCloseable{
    private static int counter = 0;
    private boolean isOpen = true;

    public Counter() {
    }
    public static int getCounter() {
        return counter;
    }

    public void add() throws Exception {
        if (counter == 2) throw new RuntimeException("Достигнут максимум");
        int tmp = counter;
        counter ++;
        if (tmp == counter) throw new RuntimeException("Увеличение счётчика не произошло");
    }

    @Override
    public void close() throws Exception {
        if (isOpen) {
            isOpen = false;
        } else {
            throw new RuntimeException("Ресурс уже закрыт");
        }
    }
}
