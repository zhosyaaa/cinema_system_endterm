import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner in= new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        mainMenu();
    }
    public static void mainMenu() throws SQLException {
        System.out.println("""
                    1) Log in
                    2) Registration
                    3) Exit
                    """);
        int menu = in.nextInt();
        switch (menu){
            case 1 -> Login.login();
            case 2 -> Registration.reg();
            case 3 -> Login.exit();
        }
    }
    public static void forTheAdministrator() throws SQLException {
        System.out.println("""
                    1) Add a movie
                    2) Delete a movie
                    3) List of films
                    4) Log out of your account
                    5) My account
                    """);
        int menu = in.nextInt();
        switch (menu){
            case 1 -> Administrator.addMuvie();
            case 2 -> Administrator.deleteMovie();
            case 3 -> Movie.infoAllProcedure();
            case 4 -> Login.logOut();
            case 5 -> Login.myAccount();
        }
    }
    public static void forTheСlient() throws SQLException {
        System.out.println("""
                 1) Buy a ticket
                 2) Return ticket
                 3) List of films
                 4) Log out of your account
                 5) My account
                 """);
        int menu = in.nextInt();
        switch (menu){
            case 1 -> Client.signUp();
            case 2 -> Client.cancelRecording();
            case 3 -> Movie.infoAllProcedure();
            case 4 -> Login.logOut();
            case 5 -> Login.myAccount();
        }
    }
}