import java.sql.*;

public class DBconnect {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public DBconnect() {
    }
    public Connection connect() {

        try {
            String url = "jdbc:mysql://localhost:3306/admin";
            con = DriverManager.getConnection(url, "root", "3377");
            System.out.println();
            return con;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

