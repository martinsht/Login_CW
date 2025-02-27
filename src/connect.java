import org.mindrot.jbcrypt.BCrypt;

import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
public class connect {
    private static final String URL = "jdbc:mysql://localhost:3306/company";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void addActor(String firstName, String lastName, InputStream img, String hashPassword) {
        // SQL query for inserting actor data into the database
        String query = "INSERT INTO company.actor (first_name, last_name, img, password) VALUES (?, ?, ?, ?)";

// Declare variables for database connection and prepared statement
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Disable auto-commit to manually handle transactions
            connection.setAutoCommit(false);

            // Prepare the SQL query
            pstmt = connection.prepareStatement(query);

            // Set the values for the query placeholders using the input data
            pstmt.setString(1, firstName);  // Set first name
            pstmt.setString(2, lastName);   // Set last name
            pstmt.setBinaryStream(3, img);  // Set the image as a binary stream
            pstmt.setString(4, hashPassword);  // Set the hashed password

            // Execute the query and get the number of rows affected
            int rowsAffected = pstmt.executeUpdate();

            // If the insertion is successful, commit the transaction
            if (rowsAffected > 0) {
                connection.commit();
                System.out.println("Insertion committed successfully for actor: " + firstName);
            } else {
                // If insertion fails, roll back the transaction
                connection.rollback();
                System.out.println("Insertion failed. Transaction rolled back.");
            }
        } catch (SQLException ex) {
            // Handle SQL exceptions
            try {
                if (connection != null) {
                    // If an error occurs, roll back the transaction
                    connection.rollback();
                }
                System.out.println("SQL Error: " + ex.getMessage());
            } catch (SQLException rollbackEx) {
                // Print stack trace if there is an error during rollback
                rollbackEx.printStackTrace();
            }
        }
    }
    public static User login(String id, String password) {
        User user = null;
        String query = "SELECT id, first_name, img, password FROM company.actor WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(query);
        ) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        // Get the image as a binary stream and store it
                        InputStream imgStream = rs.getBinaryStream("img");
                        user = new User(rs.getString("id"), rs.getString("first_name"), imgStream);
                    }
                }
            }
            return user;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return user;
    }
}