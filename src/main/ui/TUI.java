package ui;

import java.util.Scanner;

public class TUI implements UI {
    private Scanner input;

    public TUI() {
        this.input = new Scanner(System.in);
    }

    private void out(String msg) {
        System.out.println(msg + CC.RESET);
    }
    private void out(String msg, String color) {
        this.out(color + msg);
    }
    private void line(String inp) {
        this.out(inp + "-".repeat(60));
    }

    public static void main(String[] args) {
        TUI tui = new TUI();

        tui.start();

    }

    @Override
    public void start() {
        this.out("Velkommen", CC.GREEN);
        boolean running = true;
        while (running) {
            this.line(CC.CYAN_BRIGHT);
            this.out("Vælg fra menuen:", CC.CYAN_BRIGHT);
            this.out("  1. Opret bruger");
            this.out("  2. List brugere");
            this.out("  3. Ret bruger");
            this.out("  4. Slet bruger");
            this.out("  5. Afslut");
            this.out("Dit valg:", CC.CYAN_BRIGHT);
            int choice = this.input.nextInt();
            switch (choice) {
                case 1:
                    this.createUser();
                    break;
                case 2:
                    this.listUsers();
                    break;
                case 3:
                    this.updateUser();
                    break;
                case 4:
                    this.deleteUser();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    this.out("Kunne ikke genkende handling", CC.RED_BOLD);
            }
        }
        this.stop();
    }

    @Override
    public void createUser() {

    }

    @Override
    public void updateUser() {

    }

    @Override
    public void listUsers() {

    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void stop() {
        this.input.close();
        this.line(CC.RED);
        this.out("Shutting down", CC.RED);
        System.exit(0);
    }
}
