package dao;

import db.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Check if a user already exists by email
    public static boolean isExists(String email) throws SQLException {
        Connection connection = MyConnection.getConnection();
        String sql = "SELECT email FROM users WHERE email = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        boolean exists = rs.next();

        rs.close();
        ps.close();
        return exists;
    }

    // Save a new user (only username and email)
    public static int saveUser(User user) throws SQLException {
        Connection connection = MyConnection.getConnection();
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());

        int result = ps.executeUpdate();
        ps.close();
        return result;
    }
}
