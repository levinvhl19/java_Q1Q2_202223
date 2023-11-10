package _test;

import java.sql.*;
import javax.swing.*;
import com.mysql.jdbc.Driver;

public class SQLExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/flugreservierung"; // replace mydatabase with your database name
        String user = "root"; // replace root with your database username
        String password = ""; // replace empty string with your database password, if any

        // register the JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error registering JDBC driver: " + e.getMessage());
            System.exit(1);
        }

        // prompt the user for a SQL statement
        String statement = JOptionPane.showInputDialog(null, "Enter a SQL statement:");

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to SQL database!");

            // execute the SQL statement
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(statement);

            // print the results
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

            rs.close();
            stmt.close();
            conn.close();
            System.out.println("Disconnected from SQL database.");
        } catch (SQLException e) {
            System.err.println("Error executing SQL statement: " + e.getMessage());
        }
    }
}
