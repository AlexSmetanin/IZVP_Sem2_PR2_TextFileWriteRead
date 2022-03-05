import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class FileWriteRead {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("users.txt");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/itproger",
                    "root", "!QAZxsw2");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            int i=0;
            while (rs.next()) {
                String s;
                s = (rs.getString("idusers") + " " +
                        rs.getString("firstname") + " " + rs.getString("lastname")+ " " +
                        rs.getString("username") + " " + rs.getString("password") + " " +
                        rs.getString("location") + " " + rs.getString("gender") + "\n");
                fileWriter.write(s);
            }
            stmt.close();
            fileWriter.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
