package ui;

import dal.IUserDAO;
import dto.UserDTO;
import funk.IUserFunk;
import funk.PasswordController;
import funk.UserFunk;

import java.sql.SQLException;
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
        this.input.nextLine();
        this.out("Hvad er brugerens username? (2-20 tegn)");
        String username = this.input.nextLine();
        this.out("Hvad er brugerens initialer? (2-4 tegn)");
        String ini = this.input.nextLine();
        this.out("Hvad er brugerens CPR-nummer?");
        String cpr = this.input.nextLine();
        try {
            UserDTO user = this.funk.createUser(id, username, ini, cpr);

            this.out("Tildeling af roller", CC.GREEN);
            for (String test : new String[]{"Admin", "Pharmacist", "Foreman", "Operator"}) {
                this.out("Skal brugeren være " + test.toLowerCase() + "? [y/N]");
                if (this.input.nextLine().toLowerCase().contains("y")) {
                    this.funk.addRole(user, test);
                }
            }
            this.funk.storeUser(user);
            System.out.print("Password tildelt til " + user.getUserName() + " |ID: " + user.getUserId() + "| er: ");
            this.out(user.getPassword(), CC.PURPLE_BOLD_BRIGHT);

        } catch (ClassNotFoundException | SQLException |UserDTO.DTOException | IUserDAO.DALException e) {
            System.out.println("Der findes allerede en bruger med dette ID");
        }
    }

    @Override
    public void listUsers() {
        this.funk.getUsers().forEach((user) -> {
            this.out(user.toString());
        });
    }

    @Override
    public void updateUser() {
        this.line(CC.GREEN);
        this.out("Opdater brugeren", CC.GREEN);
        this.out("Hvad er brugerens ID?");
        int oldID = this.input.nextInt();
        this.input.nextLine();
        this.out("Opdater brugerens ID (11-99)");
        int userID = this.input.nextInt();
        this.input.nextLine();
        this.out("Opdater brugerens username (2-20 tegn)");
        String username = this.input.nextLine();
        this.out("Opdater brugerens initialer");
        String ini = this.input.nextLine();
        this.out("Opdater brugerens CPR-nummer");
        String cpr = this.input.nextLine();
        try {
            UserDTO user = this.funk.createUser(userID, username, ini, cpr);

            this.out("Tildeling af roller", CC.GREEN);
            for (String test : new String[]{"Admin", "Pharmacist", "Foreman", "Operator"}) {
                this.out("Skal brugeren være" + test.toLowerCase() + "? [y/N]");
                if (this.input.nextLine().toLowerCase().contains("y")) {
                    this.funk.addRole(user, test);
                }
            }

            this.funk.updateUser(oldID, user);
        } catch (ClassNotFoundException | SQLException | UserDTO.DTOException | IUserDAO.DALException e){
            e.printStackTrace();
      }
    }

    @Override
    public void deleteUser() {
        this.out("---FJERN BRUGER---", CC.CYAN_BRIGHT);
        this.out("Indstast Bruger ID");

        int id = this.input.nextInt();
        this.input.nextLine();

        try {
            UserDTO user = funk.getUser(id);
            System.out.println(user.toString());

            this.out("Er du sikker på du vil fjerne " + user.getUserName() + "ID: " + user.getUserId() + " fra databasen? + [y/N]", CC.RED);

            if (this.input.nextLine().toLowerCase().contains("y")) {
                this.out("Bruger: " + user.getUserName() + " ID: " + user.getUserId() + " er succesfuldt blevet fjernet fra databasen");
                this.funk.deleteUser(id);
            }
        } catch (SQLException | IUserDAO.DALException | NullPointerException e){
            this.out("Denne bruger eksisterer ikke");
        }
    }

    @Override
    public void stop() {
        this.input.close();
        this.line(CC.RED);
        this.out("Shutting down", CC.RED);
        System.exit(0);
    }
}
