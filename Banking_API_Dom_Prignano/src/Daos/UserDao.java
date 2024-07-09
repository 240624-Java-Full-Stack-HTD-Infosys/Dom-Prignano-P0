package Daos;

import Models.User;
import Utils.ConnectionUtil;

import java.io.IOException;
import java.sql.*;

public class UserDao {

    // Create bank account
    public User userRegistration(String firstName, String lastName, String username, String password) throws SQLException, IOException, ClassNotFoundException {
        User user = new User();
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO users (firstName, lastName, username, password) VALUES (?, ?, ?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, username);
            pstmt.setString(4, password);
            pstmt.executeUpdate();
            ResultSet rS = pstmt.getGeneratedKeys();
            if (rS.next()) {
                return new User(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword());
            }
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage() + '\n');
        }
        return null;
    }

    // Log into user account
    public User userLogin(String username, String password) throws SQLException, IOException, ClassNotFoundException {
        User user = new User();
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            ResultSet rS = pstmt.getGeneratedKeys();
            if (rS.next()) {
                return new User(user.getUsername(), user.getPassword());
            }
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage() + '\n');
        }
        return null;
    }

    // View user-specific bank accounts
    public void viewMyBankAccounts(int userId) {

    }

    // View a transaction history for each of my bank accounts
    public void viewMyAccountTransactionHistory() {

    }

    // Change my user information (name, phone, email, etc.)
    public void changeMyUserInformation() {

    }
}