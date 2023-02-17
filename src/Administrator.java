import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Administrator extends User{
    static Scanner in = new Scanner(System.in);
    public Administrator(String username, String password, String role) {
        super(username, password, role);
    }

    public Administrator() {
    }
    @Override
    public String toString() {
        return "Name: " + getUsername() + ", role: " + getRole();
    }
    public static void addMuvie() throws SQLException {
        System.out.println("Write the name of the movie:");
        String name = in.next();
        System.out.println("Write the price of the movie:");
        double price = in.nextDouble();
        Movie movie = new Movie(name, price);
        movie.insert();
        Movie.searchProcedure(name, price);
        Main.forTheAdministrator();
    }
    public static void deleteMovie() throws SQLException {
        System.out.println("Write the ID of the movie you want to delete");
        int id = in.nextInt();
        dropMovie(id);
        System.out.println("You have successfully deleted the movie!");
        Main.forTheAdministrator();
    }
    private static void dropMovie(int id) throws SQLException {
        Movie.ps = connection.prepareStatement("delete from movies where id = ?");
        Movie.ps.setInt(1, id);
        Movie.ps.execute();
    }
}
