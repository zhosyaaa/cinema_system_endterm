import java.sql.SQLException;

public interface Insert {
   default void insert() throws SQLException {}
}
