import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login extends User{
    private static User currentUser = null;
    public static User getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    static Scanner in = new Scanner(System.in);
    //конструкторы
    public Login(String username, String password, String role) {
        super(username, password, role);
    }
    public Login(){}
    public static void login() throws SQLException {
        System.out.println("Write username: ");
        String username = in.next();
        System.out.println("Write password: ");
        String pass = in.next();
        searchUser(username, pass);
    }
    private static void searchUser(String name, String pass) throws SQLException {
        String role = null;
        Statement statement = connection.createStatement();
        rs = statement.executeQuery("SELECT * FROM users");
        while (rs.next()){
            if ((name.equals(rs.getString("username"))) && pass.equals(rs.getString("password"))){
                role = rs.getString("role");
            }
        }
        if(role==null){
            System.out.println("There is no such user or the wrong password!");
            Main.mainMenu();
        }
        successfulLogin(name, pass, role);
    }
    private static void successfulLogin(String name, String pass, String role) throws SQLException {
        if (role.equals("administrator")) {
            setCurrentUser(new Administrator(name, pass, role));
            System.out.println(getCurrentUser());
            Main.forTheAdministrator();
        }
        else if (role.equals("client")) {
            System.out.println("Write down your balance: ");
            double balance = in.nextDouble();
            setCurrentUser(new Client(name, pass, role, balance));
            System.out.println(getCurrentUser());
            Main.forTheСlient();
        }
    }
    public static void myAccount() throws SQLException {
        System.out.println(getCurrentUser());
        BackToTheMenu.back();
    }
    public static void logOut() throws SQLException {
        setCurrentUser(null);
        Main.mainMenu();
    }
    public static void exit(){
        System.exit(0);
    }
}
