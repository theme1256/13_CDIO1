package ui;

import dal.IUserDAO;
import dto.UserDTO;
import funk.IUserFunk;
import funk.UserFunk;

import java.util.Scanner;

public class TUI implements UI {
    private Scanner input;
    private IUserFunk funk;

    public TUI() {
        this.input = new Scanner(System.in);
        this.funk = new UserFunk();
    }

    private void out(String msg) {
        System.out.println(msg + CC.RESET);
    }
    private void out(String msg, String color) {
        this.out(color + msg);
    }
    private void line(String inp) {
        this.out("-".repeat(60), inp);
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
        this.line(CC.GREEN);
        this.out("Opret en ny bruger", CC.GREEN);
        this.out("Hvad er brugerens ID? (11-99)");
        int id = this.input.nextInt();
        this.input.next();
        this.out("Hvad er brugerens username? (2-20 tegn)");
        String username = this.input.next();
        this.out("Hvad er brugerens initialer? (2-4 tegn)");
        String ini = this.input.next();
        this.out("Hvad er brugerens CPR-nummer?");
        String cpr = this.input.next();
        try {
            UserDTO user = this.funk.createUser(id, username, ini, cpr);

            this.out("Tildeling af roller", CC.GREEN);
            for (String test : new String[]{"Admin", "Pharmacist", "Foreman", "Operator"}) {
                this.out("Skal brugeren være " + test.toLowerCase() + "? [y/N]");
                if (this.input.next().toLowerCase().contains("y")) {
                    this.funk.addRole(user, test);
                }
            }

            this.funk.storeUser(user);
        } catch (UserDTO.DTOException | IUserDAO.DALException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listUsers() {
        this.funk.listUsers().forEach((user) -> {
            this.out(user.toString());
        });
    }

    @Override
    public void updateUser() {

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
