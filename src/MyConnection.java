import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection connection(){
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cinemaSystem", "postgres", "1079");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
