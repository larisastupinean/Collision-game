package game;

public class Main {

    public static void main(String[] args) {
        Window win = new Window();
        new Supervisor(win).start();
    }
}
