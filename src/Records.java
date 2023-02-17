import java.sql.*;
public class Records extends Movie implements Insert {
    private String date;
    private String username;
    private String time;
    static Connection connection = MyConnection.connection();
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    public Records(String username, String name, double price, String date, String time) {
        super(name, price);
        this.username = username;
        this.date = date;
        this.time = time;
    }
    public Records() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @Override
    public void insert() throws SQLException {
        ps = connection.prepareStatement("INSERT INTO records (username, nameprocedure, price, date, time) VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, Login.getCurrentUser().getUsername());
        ps.setString(2, getName());
        ps.setDouble(3, getPrice());
        ps.setString(4, getDate());
        ps.setString(5, getTime());
        ps.execute();
    }
    public static void infoRecord(Records r) throws SQLException {
        String res = null;
        Statement st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM records");
        while(rs.next()){
            if(Login.getCurrentUser().getUsername().equals(rs.getString("username"))
                    && r.getName().equals(rs.getString("nameprocedure"))
                    && r.getPrice()==rs.getDouble("price")
                    && r.getDate().equals(rs.getString("date"))
                    && r.getTime().equals(rs.getString("time"))){
                res ="ID: " + rs.getInt("id") + ", username: " + rs.getString("username")
                        + ", proccedure name: " + rs.getString("nameprocedure")
                        +", price: " + rs.getDouble("price") + " tenge, date: "
                        +  rs.getString("date") + ", time: " + rs.getString("time");
            }
        }
        System.out.println(res);
    }
    public static void drop(int id) throws SQLException {
        ps = connection.prepareStatement("delete from records where id = ?");
        ps.setInt(1, id);
        ps.execute();
    }
    public static Records getRecord(int id) throws SQLException {
        Records r = null;
        Statement st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM records");
        while (rs.next()){
            if(id==rs.getInt("id")){
                r = new Records(rs.getString("username"), rs.getString("nameprocedure"), rs.getDouble("price"), rs.getString("date"), rs.getString("time"));
            }
        }
        return r;
    }
}
