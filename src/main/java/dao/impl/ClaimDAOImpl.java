package dao.impl;

import dao.ClaimDAO;
import model.Claim;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClaimDAOImpl implements ClaimDAO {
    private static final String INSERT_CLAIM = "INSERT INTO claim (reg_id, date_of_claim, description, status) VALUES (?, ?, ?, ?)";
    private static final String SELECT_CLAIMS_BY_REG_ID = "SELECT * FROM claim WHERE reg_id = ?";
    private static final String SELECT_RECENT_CLAIMS = "SELECT * FROM claim ORDER BY date_of_claim DESC LIMIT 10";
    private static final String UPDATE_CLAIM_STATUS = "UPDATE claim SET status = ? WHERE claim_id = ?";
    private static final String SELECT_CLAIMS_BY_USER_ID = "SELECT c.claim_id, c.reg_id, c.date_of_claim, c.description, c.status\r\n"
    		+ "FROM claim c\r\n"
    		+ "JOIN registered_product rp ON c.reg_id = rp.reg_id\r\n"
    		+ "WHERE rp.user_id = ?;";
    private static final String SELECT_PENDING_CLAIMS = "SELECT * FROM claim WHERE status = 'Pending'";

    @Override
    public void submitClaim(Claim claim) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLAIM)) {
            preparedStatement.setInt(1, claim.getRegId());
            preparedStatement.setDate(2, new java.sql.Date(claim.getDateOfClaim().getTime()));
            preparedStatement.setString(3, claim.getDescription());
            preparedStatement.setString(4, claim.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Claim> getClaimsByRegId(int regId) {
        List<Claim> claims = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLAIMS_BY_REG_ID)) {
            preparedStatement.setInt(1, regId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                claims.add(new Claim(
                        rs.getInt("claim_id"),
                        rs.getInt("reg_id"),
                        rs.getDate("date_of_claim"),
                        rs.getString("description"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return claims;
    }
    @Override
    public List<Claim> getRecentClaims() {
        List<Claim> claims = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECENT_CLAIMS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setClaimId(rs.getInt("claim_id"));
                claim.setRegId(rs.getInt("reg_id"));
                claim.setDateOfClaim(rs.getDate("date_of_claim"));
                claim.setDescription(rs.getString("description"));
                claim.setStatus(rs.getString("status"));
                claims.add(claim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return claims;
    }

    @Override
    public void updateClaimStatus(int claimId, String status) {
        String sql = "UPDATE claim SET status = ? WHERE claim_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, claimId);
            preparedStatement.executeUpdate();

            int regId = getRegIdByClaimId(claimId);
            int userId = getUserIdByRegId(regId);

            createNotification(userId, claimId, "Your claim (ID: " + claimId + ") status has been updated to: " + status);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getRegIdByClaimId(int claimId) throws SQLException {
        String sql = "SELECT reg_id FROM claim WHERE claim_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, claimId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("reg_id");
            }
            throw new SQLException("Claim not found for claimId: " + claimId);
        }
    }

    private int getUserIdByRegId(int regId) throws SQLException {
        String sql = "SELECT user_id FROM registered_product WHERE reg_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, regId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
            throw new SQLException("Registered product not found for regId: " + regId);
        }
    }

    private void createNotification(int userId, int claimId, String message) {
        String sql = "INSERT INTO notifications (user_id, claim_id, message, is_read) VALUES (?, ?, ?, FALSE)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, claimId);
            preparedStatement.setString(3, message);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Claim> getClaimsByUserId(int userId) {
        List<Claim> claims = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLAIMS_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setClaimId(rs.getInt("claim_id"));
                claim.setRegId(rs.getInt("reg_id"));
                claim.setDateOfClaim(rs.getDate("date_of_claim"));
                claim.setDescription(rs.getString("description"));
                claim.setStatus(rs.getString("status"));
                claims.add(claim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return claims;
    }
    
    @Override
    public int getClaimCountByRegId(int regId) {
        String query = "SELECT COUNT(*) FROM claim WHERE reg_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, regId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    @Override
    public List<Claim> getPendingClaims() {
        List<Claim> claims = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PENDING_CLAIMS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Claim claim = new Claim();
                claim.setClaimId(rs.getInt("claim_id"));
                claim.setRegId(rs.getInt("reg_id"));
                claim.setDateOfClaim(rs.getDate("date_of_claim"));
                claim.setDescription(rs.getString("description"));
                claim.setStatus(rs.getString("status"));
                claims.add(claim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return claims;
    }
}
