package dao.impl;

import dao.UserDAO;
import model.RegisteredProduct;
import model.User;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Connection connection = DatabaseConnection.getConnection();

    @Override
    public User validateUser(String username, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("email"), rs.getString("cellphone"), rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void registerUser(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user (username, password, first_name, last_name, email, cellphone, address) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getCellphone());
            ps.setString(7, user.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUsernameTaken(String username) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM user WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isEmailTaken(String email) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM user WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<RegisteredProduct> getUserDevices(int userId) {
        List<RegisteredProduct> devices = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT rp.reg_id, rp.serial_no, rp.purchase_date, p.product_name, p.model, p.description " +
                    "FROM registered_product rp " +
                    "JOIN product p ON rp.product_id = p.product_id " +
                    "WHERE rp.user_id = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RegisteredProduct device = new RegisteredProduct(rs.getInt("reg_id"), rs.getString("serial_no"), rs.getDate("purchase_date"),
                        rs.getString("product_name"), rs.getString("model"), rs.getString("description"));
                devices.add(device);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }

    @Override
    public List<User> searchUsers(String query) {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE username LIKE ? OR email LIKE ?");
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("email"), rs.getString("cellphone"), rs.getString("address")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("email"), rs.getString("cellphone"), rs.getString("address")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";

    @Override
    public User getUserById(int userId) {
        User user = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
