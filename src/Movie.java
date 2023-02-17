import java.sql.*;

public class Movie implements Insert{
    private String name;
    private double price;
    static Connection connection = MyConnection.connection();
    static PreparedStatement ps = null;
    static ResultSet rs = null;
    public Movie(String name, double price) {
        setName(name);
        setPrice(price);
    }
    public Movie() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public void insert() throws SQLException {
        ps = connection.prepareStatement("INSERT INTO movies (name, price) VALUES (?, ?)");
        ps.setString(1, getName());
        ps.setDouble(2, getPrice());
        ps.execute();
    }
    public static void searchProcedure(String name, double price) throws SQLException {
        String res = null;
        Statement statement = connection.createStatement();
        rs = statement.executeQuery("SELECT * FROM movies");
        while (rs.next()){
            if(name.equals(rs.getString("name")) && price==rs.getDouble("price")){
                res = "Id: " + rs.getInt("id") + ", name: " + rs.getString("name") + ", price: " +rs.getDouble("price") + "tenge";
            }
        }
        if(res == null) {
            System.out.println("There is no such moive!");
        }
        System.out.println(res);
        Main.forTheAdministrator();
    }

    public static void infoAllProcedure() throws SQLException {
        Statement st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM movies");
        while (rs.next()){
            System.out.println("ID: " + rs.getInt("id") + ", name: "
                    + rs.getString("name") + ", price: " + rs.getDouble("price") + " tenge.");
        }
        BackToTheMenu.back();
    }
}
