import java.sql.SQLException;

public interface BackToTheMenu {
    public static void back() throws SQLException {
        if(Login.getCurrentUser().getRole().equals("administrator")){
            Main.forTheAdministrator();
        }else {
            Main.forTheСlient();
        }
    }
}
