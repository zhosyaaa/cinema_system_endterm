import java.sql.*;

public class User implements Insert{
    private String username;
    private String password;
    private String role;
    private double balance;
    public User(String username, String password, String role, double balance) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = balance;
    }
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public User() {}
    static Connection connection = MyConnection.connection();
    PreparedStatement ps =null;
    static ResultSet rs = null;
    public String getRole() {
        return role;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public void insert() throws SQLException {
        ps = connection.prepareStatement("INSERT INTO users (username, password, role) values (?, ? ,?)");
        ps.setString(1, getUsername());
        ps.setString(2, getPassword());
        ps.setString(3, getRole());
        ps.execute();
    }
}
