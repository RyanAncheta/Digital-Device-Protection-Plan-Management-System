package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.DeviceDAO;
import model.Device;
import utils.DatabaseConnection;

public class DeviceDAOImpl implements DeviceDAO {
    private static final String SELECT_DEVICES_BY_USER_ID = 
        "SELECT rp.reg_id, p.product_name, rp.serial_no, rp.purchase_date, p.description " +
        "FROM registered_product rp " +
        "JOIN product p ON rp.product_id = p.product_id " +
        "WHERE rp.user_id = ?";

    private static final String INSERT_DEVICE = 
        "INSERT INTO registered_product (user_id, product_id, serial_no, purchase_date) VALUES (?, ?, ?, ?)";

    private static final String SEARCH_DEVICES = 
        "SELECT rp.reg_id, p.product_name, rp.serial_no, rp.purchase_date, rp.user_id, p.description " +
        "FROM registered_product rp " +
        "JOIN product p ON rp.product_id = p.product_id " +
        "WHERE p.product_name LIKE ?";

    private static final String SELECT_ALL_DEVICES = 
        "SELECT rp.reg_id, p.product_name, rp.serial_no, rp.purchase_date, rp.user_id, p.description " +
        "FROM registered_product rp " +
        "JOIN product p ON rp.product_id = p.product_id";

    @Override
    public void registerDevice(Device device) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEVICE)) {
            preparedStatement.setInt(1, device.getUserId());
            preparedStatement.setInt(2, getProductIdByName(device.getProductName().strip())); 
            preparedStatement.setString(3, device.getSerialNo());
            preparedStatement.setDate(4, device.getPurchaseDate());
            preparedStatement.executeUpdate();
            System.out.println("Device registered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Device> getDevicesByUserId(int userId) {
        List<Device> devices = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVICES_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Device device = new Device();
                device.setRegId(rs.getInt("reg_id"));
                device.setProductName(rs.getString("product_name"));
                device.setSerialNo(rs.getString("serial_no"));
                device.setPurchaseDate(rs.getDate("purchase_date"));
                device.setDescription(rs.getString("description"));
                devices.add(device);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }

    @Override
    public List<Device> searchDevices(String query) {
        List<Device> devices = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_DEVICES)) {
            preparedStatement.setString(1, "%" + query + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Device device = new Device();
                device.setRegId(rs.getInt("reg_id"));
                device.setProductName(rs.getString("product_name"));
                device.setSerialNo(rs.getString("serial_no"));
                device.setPurchaseDate(rs.getDate("purchase_date"));
                device.setUserId(rs.getInt("user_id"));
                device.setDescription(rs.getString("description"));
                devices.add(device);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }

    @Override
    public List<Device> getAllDevices() {
        List<Device> devices = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEVICES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Device device = new Device();
                device.setRegId(rs.getInt("reg_id"));
                device.setProductName(rs.getString("product_name"));
                device.setSerialNo(rs.getString("serial_no"));
                device.setPurchaseDate(rs.getDate("purchase_date"));
                device.setUserId(rs.getInt("user_id"));
                device.setDescription(rs.getString("description"));
                devices.add(device);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }

    private int getProductIdByName(String productName) throws SQLException {
        String query = "SELECT product_id FROM product WHERE product_name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, productName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("product_id");
            } else {
                throw new SQLException("Product not found: " + productName);
            }
        }
    }
    
    @Override
    public void deleteRegisteredProduct(int regId) {
        String query = "DELETE FROM registered_product WHERE reg_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        	PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, regId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
