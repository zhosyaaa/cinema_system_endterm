import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Client extends User{
    static Scanner in = new Scanner(System.in);
    public Client(String username, String password, String role, Double balance) {
        super(username, password, role, balance);
    }
    public Client() {}
    @Override
    public String toString() {
        return "Name: " + getUsername() + ", balance: " + getBalance() + ", role: " + getRole();
    }
    public static void signUp() throws SQLException {
        System.out.println("Write the movie ID");
        int id = in.nextInt();
        System.out.println("Write the date");
        String date = in.next();
        String dateM = checkDateF(date);
        System.out.println("Write the time");
        String time = in.next();
        String  timeM = checkTimeF(time);
        Movie movie = searchMovie(id);
        Records r = new Records(Login.getCurrentUser().getUsername(), movie.getName(), movie.getPrice(),dateM, timeM );
        changeBalance(movie, -1);
        r.insert();
        Records.infoRecord(r);
        System.out.println("You have successfully signed up!");
        Main.forTheСlient();
    }
    public static void cancelRecording() throws SQLException {
        System.out.println("Write the record id");
        int id = in.nextInt();
        Records r = Records.getRecord(id);
        if(r==null){
            System.out.println("invorrect values");
            Main.forTheСlient();
        }
        changeBalance(r, 1);
        Records.drop(id);
        System.out.println("You have successfully deleted the record");
        Main.forTheСlient();
    }
    private static Movie searchMovie(int id) throws SQLException {
        Movie movie = null;
        Statement st = connection.createStatement();
        rs = st.executeQuery("SELECT * FROM movies");
        while (rs.next()){
            if(id==rs.getInt("id")){
                movie = new Movie(rs.getString("name"),rs.getDouble("price"));
            }
        }
        if(movie==null){
            System.out.println("There is no such procedure!");
            Movie.infoAllProcedure();
            Main.forTheСlient();
        }
        return movie;
    }

    private static void changeBalance(Movie movie, int reduceOrReturn) throws SQLException {
        double res = Login.getCurrentUser().getBalance()+(reduceOrReturn * (movie.getPrice()));
        if(res<0) {
            System.out.println("Insufficient funds");
            Main.forTheСlient();
        }
        Login.getCurrentUser().setBalance(res);
    }
    public static boolean checkDate(String str){
        boolean res=false;
        char[] arr = str.toCharArray();
        if(arr.length!=10) return false;
        //12/02/2023
        int d = Integer.parseInt(str.substring(0, 2));
        int m = Integer.parseInt(str.substring(3, 5));
        if(arr[2]=='/' && arr[5]=='/'){
            if (d > 0 && d < 32 && m > 0 && m < 13) res=true;
        }
        return res;
    }
    public static String checkDateF(String str){
        boolean res = checkDate(str);
        if(!res){
            System.out.println("Wrong date, write again!");
            String date = in.next();
            checkDateF(date);
        }
        return str;
    }
    public static String checkTimeF(String str){
        boolean res = chechTime(str);
        if(!res){
            System.out.println("Wrong time, write again!");
            String time = in.next();
            checkTimeF(time);
        }
        return str;
    }
    public static boolean chechTime(String str){
        boolean res=false;
        char[] arr = str.toCharArray();
        if(str.length()!=5) return false;
        int s = Integer.parseInt(str.substring(0, 2));
        int m= Integer.parseInt(str.substring(3, 5));
        if(arr[2]==':'){
            if(s < 24 && m < 60){
                res=true;
            }
        }
        return res;
    }
}
